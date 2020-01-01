package com.example.postpartumapp.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.postpartumapp.Adapter.ViewPagerAdapter
import com.example.postpartumapp.R
import com.example.postpartumapp.fragments.RadioBoxesFragment
import com.example.postpartumapp.model.RetroQuestionnaireDataModel
import java.util.*


class QuestionaireActivity : AppCompatActivity() {

    val totalQuestionsSize: Int = 10
    internal val fragmentArrayList = ArrayList<Fragment>()
    private var questionsViewPager: ViewPager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.postpartumapp.R.layout.questions_layout)

        val myQuestionModel =
            intent.getSerializableExtra("questions_set") as RetroQuestionnaireDataModel
        setDataOnView(myQuestionModel)


    }

    private fun setDataOnView(questionModel: RetroQuestionnaireDataModel) {

        val questionItems = questionModel.questions

        /* totalQuestions = questionsItems.size.toString()
         val questionPosition = "1/$totalQuestions"
         setTextWithSpan(questionPosition)

         preparingQuestionInsertionInDb(questionsItems)
         preparingInsertionInDb(questionsItems)*/

        for (i in questionItems.indices) {
            val question = questionItems[i]
           /* Log.e("Mehdi", question.question)
            Log.e("Mehsi", question.name)
            Log.e("Mehdi", question.title)*/
            for (j in question.choices.indices) {
                val questionChoices = question.choices[j]
               /* Log.e("Mehdi", questionChoices.title)
                Log.e("Mehdi", questionChoices.value.toString())*/
            }

            val radioButtonBundle = Bundle()
            radioButtonBundle.putSerializable("question", question)
            radioButtonBundle.putInt("page_position", i)

            val radioBoxesFragment = RadioBoxesFragment()
            radioBoxesFragment.arguments = radioButtonBundle
            fragmentArrayList.add(radioBoxesFragment)

        }

         questionsViewPager = findViewById(R.id.pager)
        questionsViewPager?.offscreenPageLimit = 1
         val mPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragmentArrayList)
        questionsViewPager?.adapter = mPagerAdapter
    }

    fun nextQuestion() {
        val item = questionsViewPager!!.currentItem + 1
        questionsViewPager!!.currentItem = item

        val currentQuestionPosition = (item + 1).toString()

        /*val questionPosition = "$currentQuestionPosition/$totalQuestions"
        setTextWithSpan(questionPosition)*/

    }

}
