package com.example.firebasedatabase.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasedatabase.Models.Contact
import com.example.firebasedatabase.R
import kotlinx.android.synthetic.main.item_view.view.*


class ContactAdapter(var activity: Activity , var data:ArrayList<Contact>)
    : RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_name = itemView.txt_name
        val tv_phone = itemView.txt_phone
        val tv_address = itemView.txt_address

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_name.text = data[position].name
        holder.tv_address.text = data[position].address
        holder.tv_phone.text = data[position].phone
    }

    override fun getItemCount(): Int {
        return data.size

    }
}