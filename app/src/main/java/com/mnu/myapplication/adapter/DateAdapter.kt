package com.mnu.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.mnu.myapplication.R
import com.mnu.myapplication.adapter.DateAdapter.DateViewHolder
import com.mnu.myapplication.data.DateModel

class DateAdapter(private val datelist:ArrayList<DateModel>) : RecyclerView.Adapter<DateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rc_date_item, parent,false)
        return DateAdapter.DateViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.testname.text = datelist.get(position).testname
        holder.count.text = datelist.get(position).count.toString()
    }

    override fun getItemCount(): Int {
        return datelist.size
    }



    class DateViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val testname = itemView.findViewById<TextView>(R.id.tv_testname)
        val count = itemView.findViewById<TextView>(R.id.tv_count)
    }


}