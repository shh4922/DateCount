package com.mnu.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mnu.myapplication.R
import com.mnu.myapplication.data.TextModel

class TextAdapter(private val textlist:ArrayList<TextModel>) : RecyclerView.Adapter<TextAdapter.TextViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rc_text_item, parent,false)
        return TextViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val currentItem = textlist[position]
        holder.texts.text = currentItem.text
    }

    override fun getItemCount(): Int {
        return  textlist.size
    }

    class TextViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val texts  = itemView.findViewById<TextView>(R.id.tv_text)//명언
    }

}