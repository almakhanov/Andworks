package com.qwer.firstintranet

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.list_layout.view.*

class StudentAdapter(val studentList: ArrayList<Student>) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val s : Student = studentList[position]
        holder.forName.text = s.getterName()
        holder.forAge.text = s.getterAge().toString()
        holder.forGpa.text = s.getterGpa().toString()
        holder.forSalary.text = s.getterSalary().toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val forName = itemView.forName as TextView
        val forAge = itemView.forAge as TextView
        val forSalary = itemView.forSalary as TextView
        val forGpa = itemView.forGpa as TextView
    }
}