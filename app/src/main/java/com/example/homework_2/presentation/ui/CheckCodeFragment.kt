package com.example.homework_2.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.homework_2.R
import com.example.homework_2.presentation.presenter.check_code.CheckCodePresenter
import com.example.homework_2.presentation.presenter.check_code.ICheckCodeView
import com.example.homework_2.presentation.ui.party_list.PartyListActivity
import kotlinx.android.synthetic.main.fragment_check_code.*
import kotlinx.android.synthetic.main.header.*

class CheckCodeFragment : Fragment(), ICheckCodeView {

    private val presenter = CheckCodePresenter(this)

    override fun trueCode() {
        startActivity(Intent(context, PartyListActivity::class.java))
        activity?.finish()
    }

    override fun showError(message: String) {
        Toast.makeText(this.activity, message, Toast.LENGTH_LONG).show()
    }

    //По кнопке идет просто на пустую вьюху, а нам надо чтобы там что-то лежало
    //Это нужно, чтобы вернуть код вместе с телефоном
    //Эту функцию нужно вызывать от
    companion object {
        fun newInstance(phone: String): CheckCodeFragment {
            val args = Bundle()//пустой объект чтобы туда что-то класть
            args.putString("phone", phone)//кладем туда телефон

            val flag = CheckCodeFragment()//создаем Instance нового фрагмента
            flag.arguments = args //
            return flag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_check_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "Введи код, полученный в смс", Toast.LENGTH_SHORT).show()



        //При нажатии кнопки вызываем презентер, чтобы проверить код
        btn_enter_code.setOnClickListener {
            arguments?.getString("phone")?.let { it1 -> presenter.checkCode(it1, et_check_code_code.text.toString()) }
        }


        toolbar_back.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }
}