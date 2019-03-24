package com.example.bmi

import com.example.bmi.logic.Bmi
import com.example.bmi.logic.BmiForKgCm
import org.junit.Assert
import org.junit.Test

class MyFirstTest {

    @Test
    fun for_valid_data_should_return_bmi() {
        val bmi = BmiForKgCm()
        Assert.assertEquals(22.492,bmi.countBmi(65,170),0.001)
    }

}

// dodaj logike, wprowadzanie danych