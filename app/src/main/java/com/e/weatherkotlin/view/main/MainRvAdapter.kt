package com.e.weatherkotlin.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.e.weatherkotlin.R
import com.e.weatherkotlin.model.WeatherModel

class MainRvAdapter(private var callback: CallbackClickHandler?) : RecyclerView.Adapter<MainRvAdapter.RecViewHolder>() {
    private var dataList: List<WeatherModel> = listOf()

    fun setWeather(data: List<WeatherModel>) {
        dataList = data
        notifyDataSetChanged()
    }

    fun removeListener() {
        callback = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.main_fragment_item, parent, false)
        return RecViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class RecViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: WeatherModel) {
            itemView.findViewById<TextView>(R.id.mainFragmentRecyclerItemTextView)
                .text =
                model.city.city
            itemView.setOnClickListener {
                callback?.handleClick(model)
            }
        }
    }
}