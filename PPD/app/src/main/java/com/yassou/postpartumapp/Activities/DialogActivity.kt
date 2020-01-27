package com.yassou.postpartumapp.Activities

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.yassou.postpartumapp.Network.RetrofitClientInstance
import com.yassou.postpartumapp.R
import com.yassou.postpartumapp.model.RetroQuestionnaireDataModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DialogActivity : Activity() {

    lateinit var progerssProgressDialog: ProgressDialog
    var dataList: RetroQuestionnaireDataModel?= null
    private var disclaimerText: TextView ?= null
    private var gson : Gson ?= null


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
            myIntent.putExtra("questions_set", dataList)
            this.startActivity(myIntent)
        }


    }

    private fun getData() {
        val serviceCall = RetrofitClientInstance.getRetrofitInstance()
            .create(RetrofitClientInstance.GetDataService::class.java)
        serviceCall.allQuestions.enqueue(object : Callback<RetroQuestionnaireDataModel> {

            override fun onResponse(
                call: Call<RetroQuestionnaireDataModel>,
                response: Response<RetroQuestionnaireDataModel>
            ) {
                progerssProgressDialog.dismiss()

                dataList = response.body()!!
                displayDisclaimer(dataList!!)

            }

            override fun onFailure(call: Call<RetroQuestionnaireDataModel>, t: Throwable) {
                Log.e("Yasmina Error Tang", t.message)
                progerssProgressDialog.dismiss()
            }

        })


    }

    private fun displayDisclaimer(dataList: RetroQuestionnaireDataModel) {
        disclaimerText?.text = dataList.disclaimer

    }
}
