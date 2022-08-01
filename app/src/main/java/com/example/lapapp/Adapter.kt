package com.example.lapapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lapapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class Adapter(private val list: List<Int>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycle, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        val dataFormat = SimpleDateFormat("mm:ss:S", Locale.getDefault())
        holder.laptime.text = dataFormat.format(currentItem).toString()

        holder.number.text = (position + 1).toString()
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var laptime: TextView = itemView.findViewById(R.id.textView2)
        var number : TextView = itemView.findViewById(R.id.numbertxt)
    }
}