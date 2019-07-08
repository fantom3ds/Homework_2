package com.example.homework_2

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homework_2.models.Party
import kotlinx.android.synthetic.main.activity_add_party.*
import kotlinx.android.synthetic.main.activity_add_party.party_date
import kotlinx.android.synthetic.main.activity_add_party.party_name
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.item_parties_view.*
import kotlin.random.Random

class AddPartyActivity : AppCompatActivity() {

    var party = Party()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_party)

        setSupportActionBar(toolbar_back)
        supportActionBar?.title = "Добавить"

        toolbar_back.setNavigationOnClickListener {
            Toast.makeText(this, "НАЗАД", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }


        btn_add_party.setOnClickListener {
            party.name = party_name.text.toString()
            party.date = 16674578457

//            party.countNewEvent = Random(10).nextInt()
//            party.currentBalance = Random(10000).nextInt()
//            party.fullBalance = Random(20000).nextInt()
//            party.inventedMe = Random(100).nextBoolean()
//            party.date = Random(10000).nextLong() * 1000

            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("newparty", party)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }


}
