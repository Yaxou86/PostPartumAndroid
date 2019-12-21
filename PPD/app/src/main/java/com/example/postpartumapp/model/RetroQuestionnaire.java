package com.example.postpartumapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetroQuestionnaire {

    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("totalQuestions")
    private String totalQuestions;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("disclaimer")
    private String disclaimer;

    @SerializedName("scaleLow")
    private String scaleLow;

    @SerializedName("scaleMedium")
    private String scaleMedium;

    @SerializedName("scaleHigh")
    private String scaleHigh;

    @SerializedName("questions")
    private List<Questions> questions;


    public RetroQuestionnaire(String id, String type, String title,String totalQuestions,String description, String disclaimer, String scaleLow, String scaleMedium, String scaleHigh, List<Questions> questions ){

        this.id = id;
        this.type = type;
        this.title = title;
        this.totalQuestions = totalQuestions;
        this.description = description;
        this.disclaimer = disclaimer;
        this.scaleLow= scaleLow;
        this.scaleMedium = scaleMedium;
        this.scaleHigh= scaleHigh;
        this.questions = questions;

    }

    // id
    public String getId(){
        return id;
    }
    public void  setId(String id){
        this.id = id;
    }

    //type
    public String  getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    //title
    public String getTitle(){
            return title;
        }
    public void setTitle(String title){
            this.title = title;
    }

    //total questions
    public String getTotalQuestions(){
        return totalQuestions;
    }
    public void setTotalQuestions (String totalQuestions){
        this.totalQuestions = totalQuestions;

    }

    //description
    public String getDescription(){
        return description;
    }
    public void setDescription ( String description) {
        this.description = description;
    }

    //disclaimer
    public String getDisclaimer(){
        return disclaimer;
    }
    public void setDisclaimer (String disclaimer) {
        this.disclaimer = disclaimer;
    }

    //scale low
    public String getScaleLow(){
        return scaleLow;
    }
    public void setScaleLow (String scaleLow) {
        this.scaleLow = scaleLow;
    }

    //scale medium
    public String getScaleMedium(){
        return scaleMedium;
    }
    public void setScaleMedium (String scaleMedium) {
        this.scaleMedium = scaleMedium;
    }

    //scale high
    public String getScaleHigh(){
        return scaleHigh;
    }
    public void setScaleHigh (String scaleHigh) {
        this.scaleHigh = scaleHigh;
    }

    //list of questions
    public List<Questions> getQuestions(){
        return questions;
    }
    public void  setQuestions (List <Questions> questions){
        this.questions = questions;
    }

}



