package com.yassou.postpartumapp.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class QuestionsDataModel(
    @field:SerializedName("name")
    //name
    var name: String?,

    @field:SerializedName("type")
    var type: String?,

    @field:SerializedName("title")
    //title
    var title: String?,

    @field:SerializedName("question")
    //question
    var question: String?) : Serializable {

    //list of choices
    @SerializedName("choices")
    var choices: List<ChoicesDataModel>? = null

}

