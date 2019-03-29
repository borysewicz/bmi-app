package com.example.bmi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bmi.R

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

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

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bmiText.text = "BMI: 100"
        holder.dateText.text = "02.02.2019"
        holder.heightText.text = "177cm"
        holder.massText.text = "69 kg"
        holder.stateText.text = "OK"
    }

    override fun getItemCount(): Int {
        return 10
    }



}