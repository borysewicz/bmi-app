package com.example.bmi.models

/**
 * Class that represents a single BMI counting
 * @param bmi BMI value
 * @param height Height value
 * @param mass Mass value
 * @param state Interpretation of BMI
 * @param date Date of counting
 * @param color Color to mark BMI and state
*/
class HistoryViewModel(val bmi : String, val height : String, val mass : String,
                       val state : String, val date : String, val color : Int)
