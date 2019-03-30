package com.example.bmi.activities

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bmi.R
import com.example.bmi.adapter.HistoryAdapter
import com.example.bmi.models.HistoryModelList
import com.example.bmi.models.HistoryViewModel
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_history.*
import java.text.SimpleDateFormat
import java.util.*


class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(toolbar)

        val vievManager = LinearLayoutManager(this)
        val stringHistory = getSharedPreferences(getString(R.string.bmi_preferences_key),Context.MODE_PRIVATE)
                            .getString(getString(R.string.bmi_preferences_history),"")
        val history : HistoryModelList
        history = if (stringHistory != ""){
            Gson().fromJson(getSharedPreferences
                (getString(R.string.bmi_preferences_key), Context.MODE_PRIVATE)
                .getString(getString(R.string.bmi_preferences_history),"")
                ,HistoryModelList::class.java)
        }else HistoryModelList(emptyArray(),0)
        val viewAdapter = HistoryAdapter(getSortedModelList(history))

        val recyclerView = findViewById<RecyclerView>(R.id.bmi_history_recycler).apply{
            setHasFixedSize(true)
            layoutManager = vievManager
            adapter = viewAdapter
        }
        recyclerView.addItemDecoration(SpacesItemDecoration(16)) // should define a constant
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getSortedModelList(history : HistoryModelList) : List<HistoryViewModel>{
        val modelList :List<HistoryViewModel> = history.modelList.filter { s -> s != "" }.map {
            Gson().fromJson(it,HistoryViewModel::class.java)
        }.sortedByDescending {model ->
            val date = Calendar.getInstance()
            val format = SimpleDateFormat("dd-MM-yyyy hh:mm",Locale.getDefault())
            date.time = (format.parse(model.date))
            date
        }
        return modelList
    }

}

class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space
        }
    }
}
