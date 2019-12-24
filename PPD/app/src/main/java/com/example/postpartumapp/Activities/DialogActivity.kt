package com.example.postpartumapp.Activities

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.postpartumapp.Network.RetrofitClientInstance
import com.example.postpartumapp.R
import com.example.postpartumapp.model.RetroQuestionnaire
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DialogActivity : Activity() {

    lateinit var progerssProgressDialog: ProgressDialog
    var dataList: RetroQuestionnaire ?= null
    private var disclaimerText: TextView ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog)

        disclaimerText = findViewById(R.id.disclaimer_text)

        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        getData()

        val disclaimerButton = findViewById<Button>(R.id.disclaimer_button)
        disclaimerButton.setOnClickListener()
        {
            val myIntent = Intent(this@DialogActivity, QuestionaireActivity::class.java)
            this.startActivity(myIntent)
        }


    }

    private fun getData() {
        val serviceCall = RetrofitClientInstance.getRetrofitInstance()
            .create(RetrofitClientInstance.GetDataService::class.java)
        serviceCall.allQuestions.enqueue(object : Callback<RetroQuestionnaire> {

            override fun onResponse(
                call: Call<RetroQuestionnaire>,
                response: Response<RetroQuestionnaire>
            ) {
                progerssProgressDialog.dismiss()

                dataList = response.body()!!
                displayDisclaimer(dataList!!)


                //TODO: Remove this once everything works well
                /*Log.e("Yasmina Tangou", Gson().toJson(response.body()?.id))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.type))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.title))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.totalQuestions))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.description))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.disclaimer))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.scaleLow))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.scaleMedium))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.scaleHigh))*/

                /* for(item in response.body()?.questions!!) {
                     Log.e("Yasmina Tangou",  Gson().toJson(item.question))
                     //Log.e("Yasmina Tangou",  Gson().toJson(item.choices))
                     for(item2 in item.choices) {
                         Log.e("Yasmina Tangou",  item2.title)
                         Log.e("Yasmina Tangou",  item2.value.toString())

                     }
                 }
 */

            }

            override fun onFailure(call: Call<RetroQuestionnaire>, t: Throwable) {
                Log.e("Yasmina Error Tang", t.message)
                progerssProgressDialog.dismiss()
            }

        })


    }

    private fun displayDisclaimer(dataList: RetroQuestionnaire) {
        disclaimerText?.text = dataList.disclaimer

    }
}
