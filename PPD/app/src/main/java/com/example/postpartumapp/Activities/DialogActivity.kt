package com.example.postpartumapp.Activities

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class DialogActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.postpartumapp.R.layout.dialog)

        val button = findViewById<Button>(com.example.postpartumapp.R.id.disclaimer_button)

//        val jsonObj = JSONObject(intent.getStringExtra("OurData"))

        button.setOnClickListener()
        {
            Toast.makeText(
                this@DialogActivity,
                "Dialog success", Toast.LENGTH_LONG
            ).show()
        }


    }
}
