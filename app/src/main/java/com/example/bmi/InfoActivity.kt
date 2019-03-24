package com.example.bmi

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity;
import com.example.bmi.logic.BmiDescriptor

import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        setSupportActionBar(toolbar)
        setTextViews(intent.getStringExtra(getString(R.string.bmi_bmi_value)))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setTextViews(bmi: String?) {
        findViewById<TextView>(R.id.bmi_info_bmiTV).text = bmi
        findViewById<TextView>(R.id.bmi_info_bmiTV).setTextColor(Color.parseColor(BmiDescriptor().getColor(bmi?.toDouble() ?: 0.0)))
        findViewById<TextView>(R.id.bmi_info_bmiCategoryTV).text = BmiDescriptor().getCategory(bmi ?: "0.0")
        findViewById<TextView>(R.id.bmi_info_descriptionTV).text = BmiDescriptor().getLongDescription(bmi ?: "0.0")
    }

}
