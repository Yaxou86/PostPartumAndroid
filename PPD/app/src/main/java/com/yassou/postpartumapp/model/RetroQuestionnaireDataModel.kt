package com.yassou.postpartumapp.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class RetroQuestionnaireDataModel(
    @field:SerializedName("id")
    // id
    var id: String?, @field:SerializedName("type")
    //type
    var type: String?, @field:SerializedName("title")
    //title
    var title: String?, @field:SerializedName("totalQuestions")
    //total questions
    var totalQuestions: String?, @field:SerializedName("description")
    //description
    var description: String?, @field:SerializedName("disclaimer")
    //disclaimer
    var disclaimer: String?, @field:SerializedName("scaleLow")
    //scale low
    var scaleLow: String?, @field:SerializedName("scaleMedium")
    //scale medium
    var scaleMedium: String?, @field:SerializedName("scaleHigh")
    //scale high
    var scaleHigh: String?, @field:SerializedName("questions")
    //list of questions
    var questions: List<QuestionsDataModel>?
) : Serializable



