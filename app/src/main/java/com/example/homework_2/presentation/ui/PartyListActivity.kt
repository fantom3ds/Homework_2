package com.example.homework_2.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_2.App
import com.example.homework_2.R
import com.example.homework_2.data.model.Party
import com.example.homework_2.presentation.presenter.party_list.IPartyListView
import com.example.homework_2.presentation.presenter.party_list.PartyListPresenter
import kotlinx.android.synthetic.main.activity_party_list.*
import kotlinx.android.synthetic.main.header.*
import java.util.ArrayList

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
        if (isLoading)
            progress_circular2.visibility = View.VISIBLE
        else
            progress_circular2.visibility = View.GONE
    }

    //Поле адаптера, для последующего добавления туда новых
    private var adapter = PartiesAdapter(ArrayList<Party>())

    //Инициализируем презентер
    var presenter = PartyListPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party_list)
        //AlertDialog.Builder(this).setMessage(App.instance.token).show()
        //Шапка
        setSupportActionBar(toolbar_back)
        supportActionBar?.title = ""
        toolbar_back.setNavigationOnClickListener {
            Toast.makeText(this, "НАЗАД", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

        parties_view.layoutManager = LinearLayoutManager(this)
        parties_view.adapter = adapter

        //1 - пинаем презентера, просим его получить вечеринки
        presenter.getParties()



        adapter.onItemClickFunction = { party ->
            Toast.makeText(this, party.name, Toast.LENGTH_LONG).show()
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
            var newparty = data.getSerializableExtra("newparty") as Party
            adapter.addParty(newparty)
            //adapter=PartiesAdapter(parties)
            Toast.makeText(this, newparty.name, Toast.LENGTH_LONG).show()
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}
