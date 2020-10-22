package app.yassou.postpartumapp.Activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeScreenActivity : AppCompatActivity() {


    val WEB_URL =
        "https://www.womenshealth.gov/mental-health/mental-health-conditions/postpartum-depression"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(app.yassou.postpartumapp.R.layout.welcome_screen_layout)

        val imageView = ImageView(this)
        imageView.setImageResource(app.yassou.postpartumapp.R.drawable.ppd)


        val textView = findViewById<TextView>(app.yassou.postpartumapp.R.id.what_is_PPD)
        textView.setOnClickListener()
        {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            openURL.data = Uri.parse(WEB_URL)
            startActivity(openURL)
        }

        val testStarterbutton = findViewById<Button>(app.yassou.postpartumapp.R.id.take_a_test)

        testStarterbutton.setOnClickListener()
        {
            showDisclaimer()
        }
    }

    private fun showDisclaimer() {
        val myIntent = Intent(this@WelcomeScreenActivity, DialogActivity::class.java)
        this@WelcomeScreenActivity.startActivity(myIntent)
    }
}


