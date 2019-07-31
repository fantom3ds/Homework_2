package com.example.homework_2.presentation.ui.party_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.homework_2.R
import com.example.homework_2.data.model.Party
import com.example.homework_2.presentation.presenter.party_list.IPartyListView
import com.example.homework_2.presentation.presenter.party_list.PartyListPresenter
import com.example.homework_2.presentation.ui.BalanceFragment
import com.example.homework_2.presentation.ui.MusicFragment
import com.example.homework_2.presentation.ui.users_list.UserListFragment
import kotlinx.android.synthetic.main.activity_party_list.*



//Засовываем интерфейс IPartyListView, чтобы были функции отображения
class PartyListActivity : AppCompatActivity() {

    val icons = listOf(
        R.drawable.ic_person_black_24dp,
        R.drawable.ic_attach_money_black_24dp,
        R.drawable.ic_shopping_cart_black_24dp,
        R.drawable.ic_queue_music_black_24dp
    )

    //Поле адаптера, для последующего добавления туда новых
    val adapter = PartiesAdapter(
        arrayListOf(
            Party(
                image = "https://sun7-6.userapi.com/c851024/v851024946/176e8c/KoqJGQbs1LQ.jpg?ava=1",
                name = "Моя днюха"
            ),
            Party(
                image = "https://sun9-13.userapi.com/c850424/v850424950/28175/n6b2dhhw1ZQ.jpg?ava=1",
                name = "Паб \"Беспредел\""
            ),
            Party(
                image = "https://pp.userapi.com/c848736/v848736678/d24cb/_o0cuXni7To.jpg?ava=1",
                name = "Встреча соклановцев"
            ),
            Party(
                image = "https://pp.userapi.com/c604421/v604421379/2c94d/sPPjOvddmNE.jpg?ava=1",
                name = "Вписка с одногруппниками"
            ),
            Party(
                image = "https://pp.userapi.com/c623131/v623131726/483bd/xIhpnAuIjjg.jpg?ava=1",
                name = "Встреча с куратором"
            ),


            Party(
                image = "https://sun7-6.userapi.com/c851024/v851024946/176e8c/KoqJGQbs1LQ.jpg?ava=1",
                name = "Моя днюха"
            ),
            Party(
                image = "https://sun9-13.userapi.com/c850424/v850424950/28175/n6b2dhhw1ZQ.jpg?ava=1",
                name = "Паб \"Беспредел\""
            ),
            Party(
                image = "https://pp.userapi.com/c848736/v848736678/d24cb/_o0cuXni7To.jpg?ava=1",
                name = "Встреча соклановцев"
            ),
            Party(
                image = "https://pp.userapi.com/c604421/v604421379/2c94d/sPPjOvddmNE.jpg?ava=1",
                name = "Вписка с одногруппниками"
            ),
            Party(
                image = "https://pp.userapi.com/c623131/v623131726/483bd/xIhpnAuIjjg.jpg?ava=1",
                name = "Встреча с куратором"
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party_list)
        //AlertDialog.Builder(this).setMessage(App.instance.token).show()
        //Шапка
        setSupportActionBar(toolbar)


        viewpager.adapter = SampleFragmentPagerAdapter(supportFragmentManager, this@PartyListActivity)
        //связываем табы с ViewPager
        plv_tab_layout.setupWithViewPager(viewpager)
        for (i in 0 until icons.size) {
            plv_tab_layout.getTabAt(i)?.setIcon(icons[i])
        }




        toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "НАЗАД", Toast.LENGTH_SHORT).show()
            //onBackPressed()
        }







    }


    //Прорисовываем меню добавления, кнопку
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    //Нажатие на кнопку "Добавить", открытие новой активности для добавления
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_party) {
            Toast.makeText(this, "ДОБАВИТЬ", Toast.LENGTH_SHORT).show()
            startActivityForResult(Intent(this@PartyListActivity, AddPartyActivity::class.java), 1)
        }
        return super.onOptionsItemSelected(item)
    }

    //Новая активность закрылась, данные введены, теперь добавляем в адаптер
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            val newparty = data.getSerializableExtra("newparty") as Party
            adapter.addParty(newparty)
            //adapter=PartiesAdapter(parties)
            Toast.makeText(this, newparty.name, Toast.LENGTH_LONG).show()
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    inner class SampleFragmentPagerAdapter(fm: FragmentManager, private val context: Context) :
        FragmentPagerAdapter(fm) {

        private val fragments = listOf(
            PartyListFragment(),
            BalanceFragment(),
            UserListFragment(),
            MusicFragment()
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

}
