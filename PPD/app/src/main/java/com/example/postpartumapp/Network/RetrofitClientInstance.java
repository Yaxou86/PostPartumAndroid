package com.example.postpartumapp.Network;

import com.example.postpartumapp.model.RetroQuestionnaireDataModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://yaxou86.github.io";


    public static Retrofit getRetrofitInstance(){

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

    public interface GetDataService {

        @GET("/PostPartumApp/ppdQuestionnaire")
        Call<RetroQuestionnaireDataModel> getAllQuestions();
    }

}
