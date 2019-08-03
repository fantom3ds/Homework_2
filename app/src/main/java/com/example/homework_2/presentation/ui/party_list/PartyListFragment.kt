package com.example.homework_2.presentation.ui.party_list

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_2.R
import com.example.homework_2.data.model.Party
import com.example.homework_2.presentation.ui.party_list.add_party.AddPartyFragment
import com.example.homework_2.presentation.ui.party_list.party_card.PartyCardFragment
import kotlinx.android.synthetic.main.fragment_party_list.*
import kotlinx.android.synthetic.main.header.*


class PartyListFragment() : Fragment() {

    //1 - пинаем презентера, просим его получить вечеринки
    //presenter.getParties()

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

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_party_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fr_rv_party_list.layoutManager = LinearLayoutManager(context)
        fr_rv_party_list.adapter = adapter

        (context as AppCompatActivity).setSupportActionBar(toolbar_back)
        (context as AppCompatActivity).supportActionBar?.title = "Вечеринки"

        //(context as AppCompatActivity).setSupportActionBar(toolbar_back)
        toolbar_back?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        //Если во фрагменте есть собственная менюшка, нужно прописать вот это
        setHasOptionsMenu(true)

        adapter.onItemClickFunction = { party, _ ->

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.aparty_list_frame_layout, PartyCardFragment.newInstance(party),"PCF")
                ?.addToBackStack(null)
                ?.commit()
        }
    }


    //Прорисовываем меню добавления, кнопку
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.add_party) {
            Toast.makeText(context, "ДОБАВИТЬ", Toast.LENGTH_SHORT).show()

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.aparty_list_frame_layout, AddPartyFragment(), "APF")
                ?.addToBackStack(null)
                ?.commit()
            //Прописать открытие фрагмента с добавлением
        }
        return super.onOptionsItemSelected(item)
    }

    fun addParty(party: Party){
        adapter.addParty(party)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mListener = context as OnFragmentInteractionListener
    }
}