package com.example.homework_2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_2.api.APIClient
import com.example.homework_2.models.Party
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var parties = ArrayList<Party>()
    private var adapter = PartiesAdapter(parties)

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar_back)
        supportActionBar?.title = ""

        toolbar_back.setNavigationOnClickListener {
            Toast.makeText(this, "НАЗАД", Toast.LENGTH_LONG).show()
        }

        parties_view.adapter = adapter
        parties_view.layoutManager = LinearLayoutManager(this)
//        parties_view.adapter = PartiesAdapter(
//            listOf(
//                Party(
//                    "Вписка в честь сдачи сессии",
//                    1500,
//                    2001,
//                    3045,
//                    "https://pp.userapi.com/c850424/v850424950/28176/KnVFpduuHdc.jpg?ava=1",
//                    0,
//                    false),
//                Party("Днюха у Катюхи",
//                    1500,
//                    5077,
//                    4006,
//                    "http://goo.gl/gEgYUd",
//                    3,
//                    true),
//                Party("Моя днюха",
//                    1500,
//                    30305,
//                    10033,
//                    "https://pp.userapi.com/c849024/v849024810/1a8917/cXWWMGS9Jjw.jpg",
//                    8,
//                    true),
//                Party("23 февраля",
//                    1500,
//                    507,
//                    1009,
//                    "https://pp.userapi.com/c623131/v623131726/483be/bZ6JWMQyzOU.jpg?ava=1",
//                    33,
//                    false),
//                Party("День вступления в клан",
//                    1500,
//                    5133,
//                    4002,
//                    "https://pp.userapi.com/c840323/v840323362/6a938/Iq-wkGFEtCw.jpg?ava=1",
//                    1,
//                    false)
//            )
//        )

        APIClient.instance.apiService.getParties()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                adapter.addParties(it)
            }, {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                })
    }


    //Прорисовываем меню добавления, кнопку
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    //Нажатие на кнопку "Добавить", открытие новой активности для добавления
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.add_party) {
            Toast.makeText(this, "ДОБАВИТЬ", Toast.LENGTH_SHORT).show()
            startActivityForResult(Intent(this@MainActivity, AddPartyActivity::class.java), 1)
        }
        return super.onOptionsItemSelected(item)
    }

    //Новая активность закрылась, данные введены, теперь добавляем в адаптер
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            var newparty = data!!.getSerializableExtra("newparty") as Party
            adapter.addParty(newparty)
            //adapter=PartiesAdapter(parties)
            Toast.makeText(this, newparty.name, Toast.LENGTH_LONG).show()
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}
