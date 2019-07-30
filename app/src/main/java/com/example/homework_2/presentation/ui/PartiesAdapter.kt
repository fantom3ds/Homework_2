package com.example.homework_2.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_2.R
import com.example.homework_2.data.model.Party
import kotlinx.android.synthetic.main.item_parties_view.view.*
import java.text.SimpleDateFormat

class PartiesAdapter(private var parties: MutableList<Party>) : RecyclerView.Adapter<PartiesAdapter.ViewHolder>() {

    var onItemClickFunction: ((party: Party, view: View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_parties_view, parent, false))
    }

    override fun getItemCount(): Int {
        return parties.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.party_name.text = parties[position].name

        parties[position].apply {
            holder.itemView.party_name.text = name
            holder.itemView.event_count.text = countNewEvent.toString()
            holder.itemView.party_balance.text =
                    //"${currentBalance?:0 / 100}.${currentBalance?:0 % 100}/${fullBalance?:0 / 100}.${fullBalance?:0 % 100}"
                "%d.%02d/%d.%02d".format(
                    (currentBalance ?: 0) / 100,
                    (currentBalance ?: 0) % 100,
                    (fullBalance ?: 0) / 100,
                    (fullBalance ?: 0) % 100
                )

            //поле приглашения
            if (inventedMe == true)
                holder.itemView.invented_me.text = "Вас пригласили"
            else
                holder.itemView.invented_me.text = ""

            //Подгружаем фотки
            Glide.with(holder.itemView.context).load(image).into(holder.itemView.image)

            //инициализируем класс форматтера с паттерном форматирования
            var frm = SimpleDateFormat("dd.MM.yyyy")
            //засовываем в поле отформатированную дату
            holder.itemView.party_date.text = frm.format((date ?: 0) * 1000)
            //если не null то взять то число, иначе то что после ?
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.rootView.setOnClickListener {
                onItemClickFunction?.invoke(parties[adapterPosition], itemView)
            }
        }
    }

    //Чтобы добавлять новый элемент
    fun addParty(item: Party) {
        parties.add(item)
        notifyItemInserted(parties.size - 1)
    }

    fun addParties(list: List<Party>) {
        parties.addAll(list)
        notifyItemInserted(parties.size - list.size)
    }

    fun setParties(list: List<Party>) {
        parties.clear()
        parties.addAll(list)
        notifyDataSetChanged()
    }
}