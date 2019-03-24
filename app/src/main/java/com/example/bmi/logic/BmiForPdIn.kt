package com.example.bmi.logic

class BmiForPdIn : Bmi {

    override fun countBmi(mass: Int, height: Int): Double {
        if (mass < 44) throw MassException("Mass must be larger than 44 lb")
        if (height < 20) throw HeightException("Height must be larger than 20 in")
        return 703.0*mass/(height*height)
    }
}