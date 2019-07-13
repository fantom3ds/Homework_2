package com.example.homework_2.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.homework_2.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.header.*

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context,"Введи номер телефона для получения кода",Toast.LENGTH_SHORT).show()
        btn_getCode.setOnClickListener {
            startActivity(Intent(context, PartyListActivity::class.java ))
        }


        toolbar_back.setNavigationOnClickListener{
            activity?.onBackPressed()
        }
    }
}