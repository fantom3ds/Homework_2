package com.example.homework_2.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.homework_2.R
import kotlinx.android.synthetic.main.fragment_enter_sms.*
import kotlinx.android.synthetic.main.header.*

class EnterSmsFragment : Fragment() {

    companion object {
        fun newInstance(phone: String):CheckCode {
            val args = Bundle()//пустой объект чтобы туда что-то класть
            args.putString("phone", phone)//кладем туда

            val flag = CheckCodeFragment()
            flag.arguments = args
            return flag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_enter_sms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context,"Введи код, полученный в смс",Toast.LENGTH_SHORT).show()

        btn_enter_code.setOnClickListener {
            startActivity(Intent(context, PartyListActivity::class.java ))
        }


        toolbar_back.setNavigationOnClickListener{
            activity?.onBackPressed()
        }
    }
}