package com.example.bmi.adapter

import android.annotation.SuppressLint
import android.provider.Settings.System.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bmi.R
import com.example.bmi.models.HistoryViewModel

class HistoryAdapter(val history: List<HistoryViewModel>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(val cardView: CardView,val bmiText: TextView, val heightText: TextView,
                            val massText: TextView,val stateText: TextView,val dateText : TextView ) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent:ViewGroup,viewType: Int) : HistoryAdapter.HistoryViewHolder{
        val cardView : CardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_row,parent,false) as CardView
        val bmiText : TextView = cardView.findViewById(R.id.bmi_history_row_bmi)
        val heightText : TextView = cardView.findViewById(R.id.bmi_history_row_height)
        val massText : TextView = cardView.findViewById(R.id.bmi_history_row_mass)
        val stateText : TextView = cardView.findViewById(R.id.bmi_history_row_state)
        val dateText : TextView = cardView.findViewById(R.id.bmi_history_row_date)

        return HistoryViewHolder(cardView,bmiText,heightText,massText,stateText,dateText)
        }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bmiText.text = "BMI: ${history[position].bmi}"
        holder.dateText.text = history[position].date
        holder.heightText.text = "Height: ${history[position].height}"
        holder.massText.text = "Mass: ${history[position].mass}"
        holder.stateText.text = "State:\n${history[position].state}"
        holder.bmiText.setTextColor(history[position].color)
        holder.stateText.setTextColor(history[position].color)
    }

    override fun getItemCount(): Int {
        return history.size
    }



}