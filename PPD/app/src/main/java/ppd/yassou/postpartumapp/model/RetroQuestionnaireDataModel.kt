package ppd.yassou.postpartumapp.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class RetroQuestionnaireDataModel(
    // id
    @field:SerializedName("id")
    var id: String?,

    //type
    @field:SerializedName("type")
    var type: String?,


    //title
    @field:SerializedName("title")
    var title: String?,

    //total questions
    @field:SerializedName("totalQuestions")
    var totalQuestions: String?,

    //description
    @field:SerializedName("description")
    var description: String?,

    //disclaimer
    @field:SerializedName("disclaimer")
    var disclaimer: String?,

    //scale low
    @field:SerializedName("scaleLow")
    var scaleLow: String?,

    //scale medium
    @field:SerializedName("scaleMedium")
    var scaleMedium: String?,

    //scale high
    @field:SerializedName("scaleHigh")
    var scaleHigh: String?,

    //list of questions
    @field:SerializedName("questions")
    var questions: List<QuestionsDataModel>?) : Serializable



