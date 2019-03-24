package com.example.bmi.logic


class BmiForKgCm : Bmi {

    override fun countBmi(mass: Int, height : Int): Double {
        if (mass < 20) throw MassException("Mass must be larger than 20 kg")
        if (height < 50) throw HeightException("Height must be larger than 50 cm")

        return mass * 10000.0/ (height*height)
    }

}

