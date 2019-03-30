package com.example.bmi.activities

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bmi.R
import com.example.bmi.adapter.HistoryAdapter

import kotlinx.android.synthetic.main.activity_history.*



class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(toolbar)

        val vievManager = LinearLayoutManager(this)
        val viewAdapter = HistoryAdapter()

        val recyclerView = findViewById<RecyclerView>(R.id.bmi_history_recycler).apply{
            setHasFixedSize(true)
            layoutManager = vievManager
            adapter = viewAdapter
        }
        recyclerView.addItemDecoration(SpacesItemDecoration(16)) // should define a constant
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
