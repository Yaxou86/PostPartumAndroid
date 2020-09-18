package ppd.yassou.postpartumapp.Network

import ppd.yassou.postpartumapp.model.RetroQuestionnaireDataModel

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitClientInstance {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://yaxou86.github.io"


    val retrofitInstance: Retrofit?
        get() {

            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit
        }

    interface GetDataService {

        @get:GET("/PostPartumApp/ppdQuestionnaire")
        val allQuestions: Call<RetroQuestionnaireDataModel>
    }

}
