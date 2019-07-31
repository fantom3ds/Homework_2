package com.example.homework_2.presentation.ui.party_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_2.R
import com.example.homework_2.data.model.Party
import com.example.homework_2.presentation.presenter.party_list.IPartyListView
import kotlinx.android.synthetic.main.activity_party_list.*
import kotlinx.android.synthetic.main.fragment_party_list.*
import kotlinx.android.synthetic.main.item_parties_view.view.*


class PartyListFragment() : Fragment(),IPartyListView {

    //1 - пинаем презентера, просим его получить вечеринки
    //presenter.getParties()

    private var alertDialog: AlertDialog? = null

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

        adapter.onItemClickFunction = { _, view ->
            activity?.civ_user_avatar?.setImageDrawable(view.image.drawable)
            activity?.tv_party_name?.text = view.party_name.text
        }

        fr_rv_party_list.layoutManager = LinearLayoutManager(context)
        fr_rv_party_list.adapter = adapter

    }



    //3 - вьюха отображает уже полученные вечеринки (шаг 2 в презентере)
    override fun showParties(parties: List<Party>) {
        //тут вызываем написанную функцию, полностью очистить лист и установить новый
        //adapter.setParties(parties)
        adapter.parties = parties as MutableList<Party>
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun setLoading(isLoading: Boolean) {
        //Здесь показываем вьюху загрузки (нужно создать новую)
        alertDialog?.dismiss()
        if (isLoading) {
            alertDialog = context?.let {
                AlertDialog.Builder(it)
                    .setView(R.layout.progressbar_for_alert_dialog)
                    .setCancelable(false)
                    .create()
            }
            alertDialog?.show()
        }
    }
}