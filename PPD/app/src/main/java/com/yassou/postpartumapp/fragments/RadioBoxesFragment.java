package com.yassou.postpartumapp.fragments;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.yassou.postpartumapp.Activities.QuestionaireActivity;
import com.yassou.postpartumapp.R;
import com.yassou.postpartumapp.model.ChoicesDataModel;
import com.yassou.postpartumapp.model.QuestionsDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This fragment provide the RadioButton/Single Options.
 */
public class RadioBoxesFragment extends Fragment {
    private final ArrayList<RadioButton> radioButtonArrayList = new ArrayList<>();

    private FragmentActivity mContext;
    private Button nextOrFinishButton;
    private QuestionsDataModel radioButtonTypeQuestion;
    private TextView questionRBTypeTextView;
    private RadioGroup radioGroupForChoices;
    private boolean atLeastOneChecked = false;
    private int currentPagePosition = 0;
    private int clickedRadioButtonPosition = 0;
    int score;
    String scale;

    public RadioBoxesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        if (getArguments() != null) {
            radioButtonTypeQuestion = (QuestionsDataModel) bundle.getSerializable("question");
            currentPagePosition = getArguments().getInt("page_position") + 1;
        }

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_radio_boxes, container, false);

        nextOrFinishButton = rootView.findViewById(R.id.nextOrFinishButton);
        questionRBTypeTextView = rootView.findViewById(R.id.questionRBTypeTextView);
        questionRBTypeTextView.setText(radioButtonTypeQuestion.getQuestion());
        radioGroupForChoices = rootView.findViewById(R.id.radioGroupForChoices);


        nextOrFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPagePosition == ((QuestionaireActivity) mContext).questionaireTotalQuestions()) {
                    ((QuestionaireActivity) mContext).goToResultsActivity();
                } else {
                    ((QuestionaireActivity) mContext).nextQuestion(score) ;
                }

            }
        });


        return rootView;
    }

    private void initialQuestionSetup() {
        ((QuestionaireActivity) mContext).updateQuestionnaireProgress(0);

    }

    private void saveActionsOfRadioBox() {
        for (int i = 0; i < radioButtonArrayList.size(); i++) {
            if (i == clickedRadioButtonPosition) {
                RadioButton radioButton = radioButtonArrayList.get(i);
                if (radioButton.isChecked()) {
                    atLeastOneChecked = true;

                }
            }
        }

        if (atLeastOneChecked) {
            nextOrFinishButton.setEnabled(true);
        } else {
            nextOrFinishButton.setEnabled(false);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();


        List<ChoicesDataModel> choices = radioButtonTypeQuestion.getChoices();
        radioButtonArrayList.clear();

        for (final ChoicesDataModel choice : choices) {
            RadioButton rb = new RadioButton(mContext);
            rb.setText(choice.getTitle());
            rb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            rb.setTextColor(ContextCompat.getColor(mContext, R.color.purple));
            rb.setPadding(10, 40, 10, 40);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 25;
            rb.setLayoutParams(params);

            View view = new View(mContext);
            view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.orchid));
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));

            radioGroupForChoices.addView(rb);
            radioGroupForChoices.addView(view);
            radioButtonArrayList.add(rb);

            rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    clickedRadioButtonPosition = radioButtonArrayList.indexOf(buttonView);
                    RadioBoxesFragment.this.saveActionsOfRadioBox();
                    if (isChecked) {
                        score = choice.getValue();
                    }
                }
            });

        }

        if (atLeastOneChecked) {
            nextOrFinishButton.setEnabled(true);
        } else {
            nextOrFinishButton.setEnabled(false);
        }

        /* If the current question is last in the questionnaire then
        the "Next" button will change into "Finish" button*/
        if (currentPagePosition == ((QuestionaireActivity) mContext).questionaireTotalQuestions()) {
            nextOrFinishButton.setText(R.string.finish);
        } else {
            nextOrFinishButton.setText(R.string.next);
        }
        initialQuestionSetup();
    }
}