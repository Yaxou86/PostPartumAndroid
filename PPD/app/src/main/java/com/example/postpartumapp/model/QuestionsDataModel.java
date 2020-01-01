package com.example.postpartumapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuestionsDataModel implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("title")
    private String title;

    @SerializedName("question")
    private String question;

    @SerializedName("choices")
    private List<ChoicesDataModel> choices;


    public QuestionsDataModel(String name, String type, String title, String question) {

        this.name = name;
        this.type = type;
        this.title = title;
        this.question = question;
    }

    //name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //question
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    //list of choices
    public List<ChoicesDataModel> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoicesDataModel> choices) {
        this.choices = choices;
    }

}

