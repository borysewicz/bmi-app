package com.example.bmi.logic

class BmiDescriptor {
    private enum class Categories(val bmi:Double){
        STARVED(16.0), UNDERWEIGHT(18.5),NORMAL(24.9),OVERWEIGHT(29.9),OBESE(100.0)
    }
     fun getShortDescription(bmi:Double): String{
         return when {
             bmi < Categories.STARVED.bmi -> "You're starved!"
             bmi  < Categories.UNDERWEIGHT.bmi -> "You're underweight!"
             bmi  < Categories.NORMAL.bmi -> "Your BMI is correct!"
             bmi < Categories.OVERWEIGHT.bmi -> "You're overweight!"
             else -> "You're obese!"
         }
     }

    fun getColor(bmi:Double):String{
        return when{
            bmi < Categories.STARVED.bmi -> "#00A693"
            bmi  < Categories.UNDERWEIGHT.bmi -> "#26619C"
            bmi  < Categories.NORMAL.bmi -> "#39FF14"
            bmi < Categories.OVERWEIGHT.bmi -> "#F48042"
            else -> "#B80000"
        }
    }

    fun getCategory(bmi:String):String{
        val bmiVal : Double = bmi.toDouble()
        return when{
            bmiVal < Categories.STARVED.bmi -> "Starved"
            bmiVal  < Categories.UNDERWEIGHT.bmi -> "Underweight"
            bmiVal  < Categories.NORMAL.bmi -> "Normal"
            bmiVal < Categories.OVERWEIGHT.bmi -> "Overweight"
            else -> "Obese"
        }
    }

    fun getLongDescription(bmi:String):String{
        val bmiVal : Double = bmi.toDouble()
        return when{
            bmiVal < Categories.STARVED.bmi -> "Starved: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            bmiVal  <Categories.UNDERWEIGHT.bmi-> "Underweight: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            bmiVal  < Categories.NORMAL.bmi -> "Normal: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            bmiVal < Categories.OVERWEIGHT.bmi -> "Overweight: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            else -> "Obese: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        }
    }
}

