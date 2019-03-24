package com.example.bmi

import com.example.bmi.logic.BmiDescriptor
import com.example.bmi.logic.BmiForKgCm
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BmiDescriptorTest : StringSpec() {
    init{
        "for obese BMI should return pompeian pink (#B80000) color "{
            val bmi = BmiForKgCm()
            val obeseBmi = bmi.countBmi(mass=150,height=170)
            val descriptor = BmiDescriptor()
            descriptor.getColor(obeseBmi) shouldBe("#B80000")
        }
        "for underweight BMI should return lapis lazuli (#26619C) color"{
            BmiDescriptor().getColor(17.0) shouldBe("#26619C")
        }
        "for normal BMI should return category Normal"{
            BmiDescriptor().getCategory("23.0") shouldBe("Normal")
        }
    }
}