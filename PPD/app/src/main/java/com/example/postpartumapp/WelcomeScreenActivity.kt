package com.example.postpartumapp

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.postpartumapp.Network.RetrofitClientInstance
import com.example.postpartumapp.model.RetroQuestionnaire
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response





class WelcomeScreenActivity : AppCompatActivity() {

    lateinit var progerssProgressDialog: ProgressDialog

    val WEB_URL =
        "https://www.nimh.nih.gov/health/publications/postpartum-depression-facts/index.shtml"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_screen_layout)


        val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.ppd)


        val textView = findViewById<TextView>(R.id.what_is_PPD)
        textView.setOnClickListener()
        {

            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            openURL.data = Uri.parse(WEB_URL)
            startActivity(openURL)

        }

        val button = findViewById<Button>(R.id.take_a_test)

        button.setOnClickListener()
        {
            Toast.makeText(
                this@WelcomeScreenActivity,
                R.string.toast_message, Toast.LENGTH_LONG
            ).show()


            progerssProgressDialog = ProgressDialog(this)
            progerssProgressDialog.setTitle("Loading")
            progerssProgressDialog.setCancelable(false)
            progerssProgressDialog.show()
            getData()


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



                /*Log.e("Yasmina Tangou", Gson().toJson(response.body()?.id))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.type))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.title))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.totalQuestions))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.description))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.disclaimer))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.scaleLow))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.scaleMedium))
                Log.e("Yasmina Tangou", Gson().toJson(response.body()?.scaleHigh))*/

                for(item in response.body()?.questions!!) {
                    Log.e("Yasmina Tangou",  Gson().toJson(item.question))
                    //Log.e("Yasmina Tangou",  Gson().toJson(item.choices))
                    for(item2 in item.choices) {
                        Log.e("Yasmina Tangou",  item2.title)
                        Log.e("Yasmina Tangou",  item2.value.toString())

                    }
                }



            }

            override fun onFailure(call: Call<RetroQuestionnaire>, t: Throwable) {
                Log.e("Yasmina Error Tang", t.message)
                progerssProgressDialog.dismiss()
            }

        })


    }
}

