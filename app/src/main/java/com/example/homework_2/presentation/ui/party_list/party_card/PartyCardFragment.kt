package com.example.homework_2.presentation.ui.party_list.party_card

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bumptech.glide.Glide
import com.example.homework_2.R
import com.example.homework_2.data.model.Party
import com.example.homework_2.expand
import com.example.homework_2.hide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_party_card.*


//Засовываем интерфейс IPartyListView, чтобы были функции отображения
class PartyCardFragment : Fragment(), View.OnClickListener {

    override fun onClick(p0: View?) {
        when (p0?.id) {
            //нажатие на кнопку "добавить чек" (разворот первого bottom sheet)
            R.id.btn_add_check -> {
                bottomSheetBehavior.expand()
            }
            R.id.btn_load_check -> {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, GALLERY_REQUEST)
            }
            //нажатие на кнопку фотографирования
            R.id.btn_open_camera -> {
                val cameraIntent = Intent()
                cameraIntent.action = MediaStore.ACTION_IMAGE_CAPTURE
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
            //нажатие на кнопку ручного ввода суммы, открываем второй bottom sheet, закрываем первый
            R.id.btn_enter_summ -> {
                bottomSheetBehavior.hide()
                bottomSheetEnderCheckBehavior.expand()
            }
            //нажатие на кнопку подтверждения ввода чека
            R.id.btn_enter_check -> {
                //сюда сунуть проверку и ввод
                bottomSheetEnderCheckBehavior.hide()
            }
        }
    }

    lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    lateinit var bottomSheetEnderCheckBehavior: BottomSheetBehavior<LinearLayout>
    private val CAMERA_REQUEST = 0
    private val GALLERY_REQUEST = 1

    private val mBottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
            when (newState) {
                BottomSheetBehavior.STATE_EXPANDED -> {
                    v_shadow.visibility = View.VISIBLE
                }
                else -> {
                    v_shadow.visibility = View.GONE
                }
            }
        }

        override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {}
    }


    companion object {
        fun newInstance(party: Party): PartyCardFragment {
            val args = Bundle()//пустой объект чтобы туда что-то класть
            args.putSerializable("party", party)//кладем туда телефон
            val flag = PartyCardFragment()//создаем Instance нового фрагмента
            flag.arguments = args //
            return flag
        }
    }

    private val icons = listOf(
        R.drawable.ic_person_black_24dp,
        R.drawable.ic_attach_money_black_24dp,
        R.drawable.ic_shopping_cart_black_24dp,
        R.drawable.ic_queue_music_black_24dp
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_party_card, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewpager.adapter = activity?.let { it2 ->
            it2.supportFragmentManager?.let {
                SampleFragmentPagerAdapter(it, activity!!)
            }
        }
        //связываем табы с ViewPager
        plv_tab_layout.setupWithViewPager(viewpager)
        for (i in 0 until icons.size) {
            plv_tab_layout.getTabAt(i)?.setIcon(icons[i])
        }

        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        bottomSheetEnderCheckBehavior = BottomSheetBehavior.from(bottom_sheet_enter_check)

        //навешиваем callback'и, чтобы затемнять экран при развороте bottom sheet
        bottomSheetBehavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
        bottomSheetEnderCheckBehavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)

        val party = arguments?.getSerializable("party") as Party?
        party?.let {
            context?.let { it1 -> Glide.with(it1).load(it.image).into(civ_user_avatar) }
            tv_party_name.text=it.name
        }
    }


    inner class SampleFragmentPagerAdapter(fm: FragmentManager, private val context: Context) :
        FragmentPagerAdapter(fm) {

        private val fragments = listOf(
            UserListFragment(),
            BalanceFragment(),
            PurchasesFragment(),
            NotificationFragment()
        )

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            // генерируем заголовок в зависимости от позиции
            //return tabTitles[position]
            return null
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null)
            when (requestCode) {
                GALLERY_REQUEST -> {
                    //выбрали рисунок из галерееи
                    val ChossefileUri = data.data
                    if (ChossefileUri != null) {
                        val fileUri = ChossefileUri
                        fileUri.let {
                            header_image.setImageURI(it)
                        }
                    }
                }
                CAMERA_REQUEST -> {
                    // Фотка сделана, извлекаем картинку
                    val thumbnailBitmap = data.extras!!.get("data") as Bitmap?
                    header_image.setImageBitmap(thumbnailBitmap)
                }
            }
    }


    fun setParty(party: Party) {
        Toast.makeText(context, party.name, Toast.LENGTH_LONG).show()
    }
}

