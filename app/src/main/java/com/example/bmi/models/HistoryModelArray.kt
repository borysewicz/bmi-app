package com.example.bmi.models

import com.google.gson.Gson


/**
 * Class that represents the list of last 10 BMI counts
 * @param  currentIndex The index where the next BMI counting will be put
 * @property modelList The list of last BMI countings
 */

class HistoryModelList(val modelList:Array<String>, var currentIndex : Int)


object HistoryModelListManger{
    /**
     * Function to update BMI history
     * @param history BMI history serialized in JSON
     * @param model New entry, BMI counting saved as HistoryViewModel
     * @return Updated history serialized in JSON
     */
    fun updateHistory(history: String, model: HistoryViewModel): String{
        val list : HistoryModelList = if (history == "")
            HistoryModelList(Array(10) {""},0)
        else Gson().fromJson(history,HistoryModelList::class.java)
        val modelJson = Gson().toJson(model)
        list.modelList[list.currentIndex] = modelJson
        list.currentIndex = (list.currentIndex +1) % 10
        return Gson().toJson(list)
    }
}