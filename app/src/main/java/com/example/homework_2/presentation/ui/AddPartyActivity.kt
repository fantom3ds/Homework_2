package com.example.homework_2.presentation.ui

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homework_2.data.model.Party
import kotlinx.android.synthetic.main.activity_add_party.*
import kotlinx.android.synthetic.main.activity_add_party.party_name
import kotlinx.android.synthetic.main.header.*
import kotlin.random.Random
import android.app.DatePickerDialog.OnDateSetListener
import com.example.homework_2.presentation.ui.party_list.PartyListActivity
import java.util.*


class AddPartyActivity : AppCompatActivity() {

    var party = Party()

    val myCallBack = OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        party_date.setText("${dayOfMonth}.${monthOfYear}.${year}")
    }

    var datePickerDialog: DatePickerDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.homework_2.R.layout.activity_add_party)

        setSupportActionBar(toolbar_back)
        supportActionBar?.title = ""



        toolbar_back.setNavigationOnClickListener {
            Toast.makeText(this, "НАЗАД", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }



        party_date.setOnClickListener {
            val calendar = Calendar.getInstance()

            calendar.apply {
                datePickerDialog = DatePickerDialog(
                    this@AddPartyActivity,
                    myCallBack,
                    get(Calendar.YEAR),
                    get(Calendar.MONTH),
                    get(Calendar.DAY_OF_MONTH)
                )
            }

            datePickerDialog?.show()
//            datePickerDialog = DatePickerDialog(this, myCallBack, 2019, 7, 20)
//            datePickerDialog?.show()
        }





        btn_add_party.setOnClickListener {
            party.name = party_name.text.toString()
            //party.date = 16674578457/1000

            party.countNewEvent = Random(10).nextInt()
            party.currentBalance = Random(100000).nextInt()
            party.fullBalance = Random(20000).nextInt()
            party.inventedMe = Random(100).nextBoolean()
            party.date = Random(0).nextLong(10000000, 100000000) * 1000

            var intent = Intent(this, PartyListActivity::class.java)
            intent.putExtra("newparty", party)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }


}
