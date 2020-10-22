package app.yassou.postpartumapp.Activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import app.yassou.postpartumapp.R



class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val finalScore = findViewById<TextView>(R.id.total_score)
        val ppdLevel = findViewById<TextView>(R.id.scale)


        val totalScore =  intent.getStringExtra("score")
        finalScore.text = "$totalScore"

        val scale = intent.getStringExtra("scale")
        ppdLevel.text = "$scale"

    }
}
