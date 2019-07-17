package com.example.homework_2.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.homework_2.R
import com.example.homework_2.data.repository.LoginRepository
import com.example.homework_2.presentation.presenter.login.ILoginView
import com.example.homework_2.presentation.presenter.login.LoginPresenter
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.header.*

class LoginFragment : Fragment(), ILoginView {

    val presenter = LoginPresenter(this)

    override fun showError(error: String) {
        Toast.makeText(this.activity, "", Toast.LENGTH_LONG).show()
    }

    override fun successLogin(token: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, CheckCodeFragment.newInstance(et_login_phone.text.toString()))
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "Введи номер телефона для получения кода", Toast.LENGTH_SHORT).show()

        this.btn_enter_code.setOnClickListener {
            presenter.login(et_login_phone.text.toString())
        }


        toolbar_back.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }
}