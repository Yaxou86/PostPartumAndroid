package com.yassou.postpartumapp.Activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.yassou.postpartumapp.Adapter.ViewPagerAdapter
import com.yassou.postpartumapp.R
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
        Toast.makeText(this, "Total Score is:$totalScore", Toast.LENGTH_LONG).show()
    }

    private fun tallyScore(myScore: Int) {
        totalScore += myScore
    }


}