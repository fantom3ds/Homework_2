package com.example.homework_2.presentation.ui.party_list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.homework_2.R
import com.example.homework_2.data.model.Party
import com.example.homework_2.presentation.presenter.party_list.IPartyListView
import com.example.homework_2.presentation.presenter.party_list.PartyListPresenter
import com.example.homework_2.presentation.ui.AddPartyActivity
import com.example.homework_2.presentation.ui.users_list.UserListFragment
import kotlinx.android.synthetic.main.activity_party_list.*
import kotlinx.android.synthetic.main.item_parties_view.view.*

//Засовываем интерфейс IPartyListView, чтобы были функции отображения
class PartyListActivity : AppCompatActivity(), IPartyListView {

    //3 - вьюха отображает уже полученные вечеринки (шаг 2 в презентере)
    override fun showParties(parties: List<Party>) {
        //тут вызываем написанную функцию, полностью очистить лист и установить новый
        adapter.setParties(parties)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun setLoading(isLoading: Boolean) {
        //Здесь показываем вьюху загрузки (нужно создать новую)
        alertDialog?.dismiss()
        if (isLoading) {
            alertDialog = AlertDialog.Builder(this)
                .setView(R.layout.progressbar_for_alert_dialog)
                .setCancelable(false)
                .create()
            alertDialog?.show()
        }
    }

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

    private lateinit var fragments: List<Fragment>
    private lateinit var titles: List<String>

    //Инициализируем презентер
    var presenter = PartyListPresenter(this)

    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party_list)
        //AlertDialog.Builder(this).setMessage(App.instance.token).show()
        //Шапка
        setSupportActionBar(toolbar)

//        supportFragmentManager
//            .beginTransaction()//начинаем транзанкцию
//            .replace(
//                R.id.container,
//                PartyListFragment(),
//                "party_list_fragment"
//            )//заменяет в контейнере по id, ставит фрагмент туда
//            .addToBackStack(null) //чтобы работало возвращение
//            .commit()//конец транзакции


        fragments = listOf(PartyListFragment(), UserListFragment())
        titles = listOf("Вечеринки", "Пользователи")

        viewpager.adapter = MyPagerAdapter(supportFragmentManager)
        //связываем табы с ViewPager
        plv_tab_layout.setupWithViewPager(viewpager)



        toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "НАЗАД", Toast.LENGTH_SHORT).show()
            //onBackPressed()
        }


//        recyclerview.layoutManager = LinearLayoutManager(this)
//        recyclerview.adapter = adapter

        //1 - пинаем презентера, просим его получить вечеринки
        //presenter.getParties()


        //При нажатии на элемент списка помещаем название вечеринки и фотку в шапку
//        adapter.onItemClickFunction = { party, view ->
//            Toast.makeText(this, party.name, Toast.LENGTH_LONG).show()
//
//            civ_user_avatar.setImageDrawable(view.image.drawable)
//            tv_party_name.text = view.party_name.text
//        }


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


    inner class MyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        //получаем текущий элемент
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }


}
