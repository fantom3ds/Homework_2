package com.example.homework_2.presentation.ui.party_list

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_2.R
import com.example.homework_2.data.model.Party
import com.example.homework_2.presentation.ui.party_list.add_party.AddPartyFragment

class PartiesActivity : AppCompatActivity(), OnFragmentInteractionListener {

    //5) Имплементируем интерфейс в активности, в функции ищем фрагмент по тегу и вызываем у него функцию setCity
    override fun onFragmentInteraction(party: Party) {
        val fragment = supportFragmentManager.findFragmentByTag("PLF") as PartyListFragment
        fragment.addParty(party)
    }


    private var adapter = PartiesAdapter(arrayListOf())
    private var alertDialog: AlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party_list)

        supportFragmentManager.beginTransaction()
            .replace(R.id.aparty_list_frame_layout, PartyListFragment(), "PLF")
            .addToBackStack(null)
            .commit()
    }

}
