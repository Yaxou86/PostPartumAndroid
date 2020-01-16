package com.example.postpartumapp.Activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.postpartumapp.Adapter.ViewPagerAdapter
import com.example.postpartumapp.R
import com.example.postpartumapp.fragments.RadioBoxesFragment
import com.example.postpartumapp.model.RetroQuestionnaireDataModel
import kotlinx.android.synthetic.main.footer.*
import java.util.*


class QuestionaireActivity : AppCompatActivity() {

    internal val fragmentArrayList = ArrayList<Fragment>()
    private var questionsViewPager: ViewPager? = null
    var myQuestionModel: RetroQuestionnaireDataModel ? = null

    var questionsRemaining: TextView? = null
    var topProgressBar: ProgressBar? = null

   var Button = nextOrFinishButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.questions_layout)

        questionsRemaining = findViewById(R.id.questions_remaining)
        topProgressBar = findViewById(R.id.determinantProgressBar)



         myQuestionModel =
            intent.getSerializableExtra("questions_set") as RetroQuestionnaireDataModel
        setDataOnView(myQuestionModel!!)

    }





    fun questionaireTotalQuestions(): Int {
        return myQuestionModel?.questions?.size!!
    }



    private fun setDataOnView(questionModel: RetroQuestionnaireDataModel) {

        val questionItems = questionModel.questions

        for (i in questionItems.indices) {
            val question = questionItems[i]

            val radioButtonBundle = Bundle()
            radioButtonBundle.putSerializable("question", question)
            radioButtonBundle.putInt("page_position", i)

            val radioBoxesFragment = RadioBoxesFragment()
            radioBoxesFragment.arguments = radioButtonBundle
            fragmentArrayList.add(radioBoxesFragment)

            questionsRemaining?.text = question.title

        }

         questionsViewPager = findViewById(R.id.pager)
        questionsViewPager?.offscreenPageLimit = 1
         val mPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragmentArrayList)
        questionsViewPager?.adapter = mPagerAdapter

    }



    fun nextQuestion() {
        var item = questionsViewPager!!.currentItem + 1
        questionsViewPager!!.currentItem = item

        val currentQuestionPosition: Int = item++
        updateQuestionnaireProgress(currentQuestionPosition)
    }

    @SuppressLint("NewApi")
    fun updateQuestionnaireProgress (progress: Int) {
        questionsRemaining?.text = myQuestionModel?.questions?.get(progress)?.title
        topProgressBar!!.setProgress((progress + 1)*10, true)
    }

    fun goToResultsActivity() {
        Toast.makeText(this, "Need to implement the INTENT HERE ", Toast.LENGTH_LONG).show()
    }


}
