package com.mnu.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mnu.myapplication.adapter.DateAdapter.DateViewHolder
import com.mnu.myapplication.data.DateModel
import com.mnu.myapplication.databinding.RcDateItemBinding


//처음에 불러온 유저에관한 데이터를 리스트로 해서 저장된것을 받아옴.
class DateAdapter(private val datelist:ArrayList<DateModel>) : RecyclerView.Adapter<DateViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val binding = RcDateItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(datelist[position])
    }

    override fun getItemCount(): Int {
        return datelist.size
    }



//    class DateViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
//        val testname = itemView.findViewById<TextView>(R.id.home_rc_tv_testname)
//        val date = itemView.findViewById<TextView>(R.id.home_rc_tv_date)
//
//
//    }

    class DateViewHolder(private val binding: RcDateItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(dateModel: DateModel){
            binding.homeTestname.text=dateModel.testname
            binding.homeDate.text=dateModel.date.toString()
        }
    }


}