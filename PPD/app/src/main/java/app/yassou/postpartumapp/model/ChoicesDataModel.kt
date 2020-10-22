package app.yassou.postpartumapp.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class ChoicesDataModel(
    @field:SerializedName("title")
    //title
    var title: String?, @field:SerializedName("value")
    //value
    var value: Int?) : Serializable