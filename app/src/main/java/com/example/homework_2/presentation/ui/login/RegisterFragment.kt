package com.example.homework_2.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.homework_2.R
import com.example.homework_2.presentation.presenter.registration.IRegisterView
import com.example.homework_2.presentation.presenter.registration.RegisterPresenter
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.header.*

class RegisterFragment : Fragment(), IRegisterView {

    val presenter = RegisterPresenter(this)

    //3) Презентер вызывает этот метод при успешной регистрации
    override fun successRegister() {
        //Вызываем фрагмент проверки кода,туда передаем телефон из этого фрагмента
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container,
                CheckCodeFragment.newInstance(et_register_phone.text.toString())
            )
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun showError(message: String) {
        Toast.makeText(this.activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "Укажите свое имя и телефон", Toast.LENGTH_SHORT).show()

        //Навешиваем действия
        btn_register_getCode.setOnClickListener{
            presenter.register(et_register_user_name.text.toString(),et_register_phone.text.toString())
        }
        btn_register_been_fun.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, LoginFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        toolbar_back.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }
}