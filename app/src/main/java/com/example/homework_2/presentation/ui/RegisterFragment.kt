package com.example.homework_2.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.homework_2.R
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.header.*

class RegisterFragment : Fragment(), View.OnClickListener {

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_getCode -> {
                startActivity(Intent(this.context, PartyListActivity::class.java))
            }
            R.id.has_been_fun -> {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, LoginFragment())
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "Укажите свое имя и телефон", Toast.LENGTH_SHORT).show()

        //Навешиваем действия
        btn_getCode.setOnClickListener(this)
        has_been_fun.setOnClickListener(this)

        toolbar_back.setNavigationOnClickListener{
            activity?.onBackPressed()
        }
    }
}