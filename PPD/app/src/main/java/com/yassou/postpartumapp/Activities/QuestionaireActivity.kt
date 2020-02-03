package com.yassou.postpartumapp.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.yassou.postpartumapp.Adapter.ViewPagerAdapter
import com.yassou.postpartumapp.fragments.RadioBoxesFragment
import com.yassou.postpartumapp.model.RetroQuestionnaireDataModel
import java.util.*




class QuestionaireActivity : AppCompatActivity() {

    internal val fragmentArrayList = ArrayList<Fragment>()
    private var questionsViewPager: ViewPager? = null
    var myQuestionModel: RetroQuestionnaireDataModel? = null

    var questionsRemaining: TextView? = null
    var topProgressBar: ProgressBar? = null

    var totalScore: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.yassou.postpartumapp.R.layout.questions_layout)


        questionsRemaining = findViewById(com.yassou.postpartumapp.R.id.questions_remaining)
        topProgressBar = findViewById(com.yassou.postpartumapp.R.id.determinantProgressBar)



        myQuestionModel =
            intent.getSerializableExtra("questions_set") as RetroQuestionnaireDataModel
        setDataOnView(myQuestionModel!!)

    }

    fun questionaireTotalQuestions(): Int {
        return myQuestionModel?.questions?.size!!
    }


    private fun setDataOnView(questionModel: RetroQuestionnaireDataModel) {

        val questionItems = questionModel.questions

        for (i in questionItems?.indices!!) {
            val question = questionItems[i]

            val radioButtonBundle = Bundle()
            radioButtonBundle.putSerializable("question", question)
            radioButtonBundle.putInt("page_position", i)

            val radioBoxesFragment = RadioBoxesFragment()
            radioBoxesFragment.arguments = radioButtonBundle
            fragmentArrayList.add(radioBoxesFragment)

            questionsRemaining?.text = question.title
        }


        questionsViewPager = findViewById(com.yassou.postpartumapp.R.id.pager)
        questionsViewPager?.offscreenPageLimit = 1
        val mPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragmentArrayList)
        questionsViewPager?.adapter = mPagerAdapter
    }


    fun nextQuestion(currentScore: Int) {
        tallyScore(currentScore)

        var item = questionsViewPager!!.currentItem + 1
        questionsViewPager!!.currentItem = item

        val currentQuestionPosition: Int = item++
        updateQuestionnaireProgress(currentQuestionPosition)
    }


    @SuppressLint("NewApi")
    fun updateQuestionnaireProgress(progress: Int) {
        questionsRemaining?.text = myQuestionModel?.questions?.get(progress)?.title
        topProgressBar!!.setProgress((progress + 1) * 10, true)
    }


    fun goToResultsActivity() {

       // myQuestionModel.scaleLow
       Log.e("Yasmina hmara", scale())

        val myIntent = Intent(this@QuestionaireActivity,ResultsActivity::class.java) //not application context
        myIntent.putExtra("score", totalScore.toString())
        myIntent.putExtra("scale", scale())


        startActivity(myIntent)
    }


    private fun tallyScore(myScore: Int) {
        totalScore += myScore
    }

    private fun scale(): String {


        return when (totalScore){
            in 0..9 -> myQuestionModel?.scaleLow.toString()
            in 10..12->  myQuestionModel?.scaleMedium.toString()
            else -> myQuestionModel?.scaleHigh.toString()
        }
    }
}

