package com.example.bmi

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.bmi.logic.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var bmiStrategy : Bmi = BmiForKgCm() // by default, we will count BMI in metric units

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.bmi_menu_about))
        prepareCountButton()
        prepareInfoButton()
        setTextViews()
    }

    private fun prepareCountButton() {
        val button : Button = findViewById(R.id.bmi_main_countBtn)
        button.setOnClickListener{
            val height = getValueFromEditText(findViewById(R.id.bmi_main_heightET))
            val mass = getValueFromEditText(findViewById(R.id.bmi_main_massET))
            if (height == -1 || mass == -1){
                return@setOnClickListener
            }
            val resBox : TextView = findViewById(R.id.bmi_main_resultTV)
            val interpretationBox : TextView = findViewById(R.id.bmi_main_descriptionTV)
            val infoButton : ImageButton = findViewById(R.id.bmi_main_infoBtn)
            try{
                val bmi = bmiStrategy.countBmi(mass=mass,height=height)
                interpretationBox.text = BmiDescriptor().getShortDescription(bmi)
                resBox.setColor(bmi)
                resBox.text = String.format(Locale.US,"%.2f",bmi)  // using Locale.US to show the bmi value with dot separator instead of comma, helps with parsing to double for info activity
                infoButton.visibility = View.VISIBLE
            }catch (e: MassException){
                findViewById<EditText>(R.id.bmi_main_massET).error = e.message
            }catch (e: HeightException){
                findViewById<EditText>(R.id.bmi_main_heightET).error = e.message
            }
        }
    }

    private fun prepareInfoButton(){
        val button = findViewById<ImageButton>(R.id.bmi_main_infoBtn)
        button.setOnClickListener{
            val bmi = findViewById<TextView>(R.id.bmi_main_resultTV).text
            val intent = Intent(this,InfoActivity::class.java).putExtra(getString(R.string.bmi_bmi_value),bmi)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putCharSequence(getString(R.string.label_height),findViewById<EditText>(R.id.bmi_main_heightET).text)
        outState?.putCharSequence(getString(R.string.label_mass),findViewById<EditText>(R.id.bmi_main_massET).text)
        outState?.putCharSequence(getString(R.string.label_bmi),findViewById<TextView>(R.id.bmi_main_resultTV).text)
        outState?.putCharSequence(getString(R.string.label_description),findViewById<TextView>(R.id.bmi_main_descriptionTV).text)
        outState?.putInt(getString(R.string.key_descriptionColor),findViewById<TextView>(R.id.bmi_main_resultTV).currentTextColor)
        outState?.putInt(getString(R.string.bmi_main_infoBtnVisibility),findViewById<ImageButton>(R.id.bmi_main_infoBtn).visibility)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        findViewById<EditText>(R.id.bmi_main_heightET).setText(savedInstanceState?.getCharSequence(getString(R.string.label_height)) ?: "" )
        findViewById<EditText>(R.id.bmi_main_massET).setText(savedInstanceState?.getCharSequence(getString(R.string.label_mass)) ?: "" )
        findViewById<TextView>(R.id.bmi_main_resultTV).text = (savedInstanceState?.getCharSequence(getString(R.string.label_bmi)) ?: "" )
        findViewById<TextView>(R.id.bmi_main_descriptionTV).text =(savedInstanceState?.getCharSequence(getString(R.string.label_description)) ?: "" )
        findViewById<TextView>(R.id.bmi_main_resultTV).setTextColor(savedInstanceState?.getInt(getString(R.string.key_descriptionColor)) ?: Color.BLACK)
        findViewById<ImageButton>(R.id.bmi_main_infoBtn).visibility = savedInstanceState?.getInt((getString(R.string.bmi_main_infoBtnVisibility)))  ?: View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu:Menu):Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.bmi_menu_units -> swapUnits()
            R.id.bmi_menu_about -> startAboutActivity()
        }
        return true
    }

    private fun swapUnits():Boolean{
        if (bmiStrategy is BmiForKgCm) bmiStrategy = BmiForPdIn()
        else bmiStrategy = BmiForKgCm()
        clearFields()
        setTextViews()
        return true
    }

    private fun clearFields(){
        findViewById<EditText>(R.id.bmi_main_heightET).text.clear()
        findViewById<EditText>(R.id.bmi_main_massET).text.clear()
        findViewById<TextView>(R.id.bmi_main_resultTV).text = ""
        findViewById<TextView>(R.id.bmi_main_descriptionTV).setText(R.string.label_fill)
        findViewById<ImageButton>(R.id.bmi_main_infoBtn).visibility = View.INVISIBLE
    }

    private fun setTextViews(){
        if (bmiStrategy is BmiForKgCm){
            findViewById<TextView>(R.id.bmi_main_heightTV).setText(R.string.label_bmiForKgCmHeight)
            findViewById<TextView>(R.id.bmi_main_massTV).setText(R.string.label_bmiForKgCmMass)
        } else{
            findViewById<TextView>(R.id.bmi_main_heightTV).setText(R.string.label_bmiForPdInHeight)
            findViewById<TextView>(R.id.bmi_main_massTV).setText(R.string.label_bmiForPdInMass)
        }
    }

    private fun startAboutActivity(){
        val intent = Intent(this,AboutActivity::class.java)
        startActivity(intent)
    }

    private fun getValueFromEditText(editText: EditText?): Int {
        if (editText?.text.toString() == ""){
            editText?.error = getString(R.string.error_empty)
            return -1
        }
        return editText?.text.toString().toInt()
    }

    private fun TextView.setColor(bmi:Double){
       this.setTextColor(Color.parseColor(BmiDescriptor().getColor(bmi)))
    }
}
