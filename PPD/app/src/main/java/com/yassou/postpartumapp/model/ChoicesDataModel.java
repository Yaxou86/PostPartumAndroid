package com.yassou.postpartumapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChoicesDataModel implements Serializable {

    @SerializedName("title")
    private String title;

    @SerializedName("value")
    private Integer value;


    public ChoicesDataModel(String title, Integer value){
        this.title = title;
        this.value = value;
    }


    //title
    public String getTitle() {
        return title;
    }
    public void setTitle (String title){
        this.title = title;
    }



    //value
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value){
        this.value = value;
    }
}