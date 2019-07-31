package com.example.homework_2.presentation.ui.party_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_2.R
import com.example.homework_2.data.model.Party
import kotlinx.android.synthetic.main.fragment_party_list.*

class PartyListFragment : Fragment() {

    private val adapter = PartiesAdapter(
        arrayListOf(
            Party(
                image = "https://sun7-6.userapi.com/c851024/v851024946/176e8c/KoqJGQbs1LQ.jpg?ava=1",
                name = "Моя днюха"
            ),
            Party(
                image = "https://sun9-13.userapi.com/c850424/v850424950/28175/n6b2dhhw1ZQ.jpg?ava=1",
                name = "Паб \"Беспредел\""
            ),
            Party(
                image = "https://pp.userapi.com/c848736/v848736678/d24cb/_o0cuXni7To.jpg?ava=1",
                name = "Встреча соклановцев"
            ),
            Party(
                image = "https://pp.userapi.com/c604421/v604421379/2c94d/sPPjOvddmNE.jpg?ava=1",
                name = "Вписка с одногруппниками"
            ),
            Party(
                image = "https://pp.userapi.com/c623131/v623131726/483bd/xIhpnAuIjjg.jpg?ava=1",
                name = "Встреча с куратором"
            ),


            Party(
                image = "https://sun7-6.userapi.com/c851024/v851024946/176e8c/KoqJGQbs1LQ.jpg?ava=1",
                name = "Моя днюха"
            ),
            Party(
                image = "https://sun9-13.userapi.com/c850424/v850424950/28175/n6b2dhhw1ZQ.jpg?ava=1",
                name = "Паб \"Беспредел\""
            ),
            Party(
                image = "https://pp.userapi.com/c848736/v848736678/d24cb/_o0cuXni7To.jpg?ava=1",
                name = "Встреча соклановцев"
            ),
            Party(
                image = "https://pp.userapi.com/c604421/v604421379/2c94d/sPPjOvddmNE.jpg?ava=1",
                name = "Вписка с одногруппниками"
            ),
            Party(
                image = "https://pp.userapi.com/c623131/v623131726/483bd/xIhpnAuIjjg.jpg?ava=1",
                name = "Встреча с куратором"
            )
        )
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_party_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fr_rv_party_list.layoutManager = LinearLayoutManager(activity)
        fr_rv_party_list.adapter = adapter
    }



}