package com.example.homework_2.presentation.ui.party_list.add_party

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.homework_2.R
import com.example.homework_2.data.model.Party
import com.example.homework_2.presentation.ui.party_list.OnFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_add_party.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.header.view.*
import java.util.*
import kotlin.random.Random


class AddPartyFragment : Fragment() {

    private var party = Party()
    //2) создаем переменную интерфейса
    private var mListener: OnFragmentInteractionListener? = null
    private var datePickerDialog: DatePickerDialog? = null
    private val myCallBack = OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        party_date.setText("${dayOfMonth}.${monthOfYear}.${year}")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_party, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context as AppCompatActivity).setSupportActionBar(navigation_header_container.toolbar_back)
        (context as AppCompatActivity).supportActionBar?.title = "Добавление вечеринки"
        toolbar_back?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        party_date.setOnClickListener {
            Calendar.getInstance().apply {
                datePickerDialog = activity?.applicationContext?.let { it1 ->
                    DatePickerDialog(
                        it1,
                        myCallBack,
                        get(Calendar.YEAR),
                        get(Calendar.MONTH),
                        get(Calendar.DAY_OF_MONTH)
                    )
                }
            }
            datePickerDialog?.show()
        }

        btn_add_party.setOnClickListener {
            party.name = party_name.text.toString()
            party.countNewEvent = Random(10).nextInt()
            party.currentBalance = Random(100000).nextInt()
            party.fullBalance = Random(20000).nextInt()
            party.inventedMe = Random(100).nextBoolean()
            party.date = Random(0).nextLong(10000000, 100000000) * 1000

            activity?.onBackPressed()
            //4) Передаем данные
            mListener?.onFragmentInteraction(party)
        }
    }

    //3) При вставке фрагмента в контейнер, присваиваем переменной контекст, приводя ее тип к интерфейсу
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        //context - это контекст Activity
        mListener = context as OnFragmentInteractionListener
    }
}
