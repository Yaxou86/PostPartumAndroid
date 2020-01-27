package com.yassou.postpartumapp.Activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity



class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.yassou.postpartumapp.R.layout.activity_results)

        val WEB_URL = "https://www.zocdoc.com/search?address=&insurance_carrier=&day_filter=AnyDay&filters=%7B%7D&gender=-1&language=-1&offset=0&insurance_plan=-1&reason_visit=2501&sees_children=false&after_5pm=false&before_10am=false&sort_type=Default&dr_specialty=104&ip=72.90.179.125&search_query=Postpartum%20Depression&searchType=procedure"


        val EditText = findViewById<EditText>(com.yassou.postpartumapp.R.id.editText)


        val scheduleButton = findViewById<Button>(com.yassou.postpartumapp.R.id.schedule_button)

        scheduleButton.setOnClickListener()
        {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            openURL.data = Uri.parse(WEB_URL)
            startActivity(openURL)

        }



    }
}
