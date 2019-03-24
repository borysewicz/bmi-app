package com.example.bmi

import com.example.bmi.logic.BmiForKgCm
import com.example.bmi.logic.HeightException
import com.example.bmi.logic.MassException
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import org.junit.Assert

class BmiForKgCmTest : StringSpec() {
    init{
        "for valid mass and height return bmi"{
            val bmi = BmiForKgCm()
            bmi.countBmi(65,170) shouldBeAround (22.491)
        }
        "for too low mass should throw massException"{
            val bmi = BmiForKgCm()
            shouldThrow<MassException> {
                bmi.countBmi(10,177)
            }
        }
        "for too low height should throw heightException"{
            val bmi = BmiForKgCm()
            shouldThrow<HeightException> {
                bmi.countBmi(mass=69,height=5)
            }
        }
    }

    infix fun Double.shouldBeAround( value : Double){
        Assert.assertEquals(value,this,0.001)
    }

}