package com.example.bmi

import com.example.bmi.logic.BmiForPdIn
import com.example.bmi.logic.HeightException
import com.example.bmi.logic.MassException
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import org.junit.Assert

class BmiForPdInTest : StringSpec() {
    init{
        "for valid mass and height should return bmi"{
            val bmi = BmiForPdIn()
            bmi.countBmi(mass = 180, height = 80) shouldBeAround (19.771)
        }
        "for too low mass should throw massException"{
            val bmi = BmiForPdIn()
            shouldThrow<MassException> { bmi.countBmi(1,80) }
        }
        "for too low height should throw heightException"{
            val bmi = BmiForPdIn()
            shouldThrow<HeightException> {bmi.countBmi(60,1)  }
        }
    }

    infix fun Double.shouldBeAround( value : Double){
        Assert.assertEquals(value,this,0.001)
    }
}