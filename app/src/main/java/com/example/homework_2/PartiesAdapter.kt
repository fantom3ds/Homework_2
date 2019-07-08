package com.example.homework_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_2.models.Party
import kotlinx.android.synthetic.main.item_parties_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class PartiesAdapter(private var parties: ArrayList<Party>) : RecyclerView.Adapter<PartiesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_parties_view, parent, false))

        //return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_view, parent,false))
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
                "${currentBalance!! / 100}.${currentBalance!! % 100}/${fullBalance!! / 100}.${fullBalance!! % 100}"

            //поле приглашения
            if (inventedMe!!)
                holder.itemView.invented_me.text = "Вас пригласили"
            else
                holder.itemView.invented_me.text = ""

            //Подгружаем фотки
            Glide.with(holder.itemView.context).load(image).into(holder.itemView.image)

            //инициализируем класс форматтера с паттерном форматирования
            var frm = SimpleDateFormat("dd.MM.yyyy")
            //засовываем в поле отформатированную дату
            holder.itemView.party_date.text = frm.format(Date(date!! * 1000))
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //Чтобы добавлять новый элемент
    fun addParty(item:Party){
        parties.add(item)
        notifyDataSetChanged()
    }
}