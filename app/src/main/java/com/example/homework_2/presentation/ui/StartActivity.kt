package com.example.homework_2.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.homework_2.App
import com.example.homework_2.R
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.android.synthetic.main.header.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        if(App.instance.token!=null){
            startActivity(Intent(this, PartyListActivity::class.java))
            //Завершаем эту, чтобы по кнопке назад не перейти сюда
            finish()
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, StartFragment(), "start")
            .addToBackStack(null)
            .commit()

    }





}
