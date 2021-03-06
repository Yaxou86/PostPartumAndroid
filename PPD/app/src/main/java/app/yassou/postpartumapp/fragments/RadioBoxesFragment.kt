package app.yassou.postpartumapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import app.yassou.postpartumapp.Activities.QuestionaireActivity
import app.yassou.postpartumapp.model.QuestionsDataModel
import app.yassou.postpartumapp.R
import java.util.*


/**
 * This fragment provide the RadioButton/Single Options.
 */
class RadioBoxesFragment : Fragment() {
    private val radioButtonArrayList = ArrayList<RadioButton>()

    private var mContext: FragmentActivity? = null
    private var nextOrFinishButton: Button? = null
    private var radioButtonTypeQuestion: QuestionsDataModel? = null
    private var questionRBTypeTextView: TextView? = null
    private var radioGroupForChoices: RadioGroup? = null
    private var atLeastOneChecked = false
    private var currentPagePosition = 0
    private var clickedRadioButtonPosition = 0
    internal var score: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bundle = arguments

        if (arguments != null) {
            radioButtonTypeQuestion = bundle!!.getSerializable("question") as QuestionsDataModel
            currentPagePosition = arguments!!.getInt("page_position") + 1
        }

        val rootView =
            inflater.inflate(R.layout.fragment_radio_boxes, container, false) as ViewGroup

        nextOrFinishButton = rootView.findViewById(R.id.nextOrFinishButton)
        questionRBTypeTextView = rootView.findViewById(R.id.questionRBTypeTextView)
        questionRBTypeTextView!!.text = radioButtonTypeQuestion!!.question
        radioGroupForChoices = rootView.findViewById(R.id.radioGroupForChoices)


        nextOrFinishButton!!.setOnClickListener {
            if (currentPagePosition == (mContext as QuestionaireActivity).questionaireTotalQuestions()) {
                (mContext as QuestionaireActivity).goToResultsActivity()
            } else {
                (mContext as QuestionaireActivity).nextQuestion(score)
            }
        }


        return rootView
    }

    private fun initialQuestionSetup() {
        (mContext as QuestionaireActivity).updateQuestionnaireProgress(0)

    }

    private fun saveActionsOfRadioBox() {
        for (i in radioButtonArrayList.indices) {
            if (i == clickedRadioButtonPosition) {
                val radioButton = radioButtonArrayList[i]
                if (radioButton.isChecked) {
                    atLeastOneChecked = true

                }
            }
        }

        nextOrFinishButton!!.isEnabled = atLeastOneChecked
    }


    @SuppressLint("NewApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = activity


        val choices = radioButtonTypeQuestion!!.choices
        radioButtonArrayList.clear()

        for (choice in choices!!) {
            val rb = RadioButton(mContext)
            rb.text = choice.title
            rb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            rb.setTextAppearance(R.style.fontForRadioButtonChoice)
            rb.setTextColor(ContextCompat.getColor(mContext!!, R.color.black))
            rb.setPadding(10, 40, 10, 40)
            rb.setBackgroundColor(ContextCompat.getColor(mContext!!,R.color.colorBackground))

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.leftMargin = 25
            rb.layoutParams = params

            val view = View(mContext)
            view.setBackgroundColor(ContextCompat.getColor(mContext!!, R.color.colorBackground))
            view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1)

            radioGroupForChoices!!.addView(rb)
            radioGroupForChoices!!.addView(view)
            radioButtonArrayList.add(rb)

            rb.setOnCheckedChangeListener { buttonView, isChecked ->
                clickedRadioButtonPosition = radioButtonArrayList.indexOf(buttonView)
                this@RadioBoxesFragment.saveActionsOfRadioBox()
                if (isChecked) {
                    score = choice.value!!
                }
            }

        }

        nextOrFinishButton!!.isEnabled = atLeastOneChecked

        /* If the current question is last in the questionnaire then
        the "Next" button will change into "Finish" button*/
        if (currentPagePosition == (mContext as QuestionaireActivity).questionaireTotalQuestions()) {
            nextOrFinishButton!!.setText(R.string.finish)
        } else {
            nextOrFinishButton!!.setText(R.string.next)
        }
        initialQuestionSetup()
    }
}