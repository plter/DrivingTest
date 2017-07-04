package top.yunp.drivingtest.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import top.yunp.drivingtest.databinding.JudgeLayoutBinding;
import top.yunp.drivingtest.databinding.MultiChoiceLayoutBinding;
import top.yunp.drivingtest.databinding.SingleChoiceLayoutBinding;
import top.yunp.drivingtest.reader.Question;
import top.yunp.drivingtest.reader.QuestionType;

/**
 * Created by plter on 6/22/17.
 */

public class AnswerFieldController {


    private Context context;
    private Question question;
    private View view;
    private SingleChoiceLayoutBinding singleChoiceLayoutBinding;
    private JudgeLayoutBinding judgeLayoutBinding;
    private MultiChoiceLayoutBinding multiChoiceLayoutBinding;

    public AnswerFieldController(Context context, Question question) {
        this.context = context;
        this.question = question;

        if (question.getType().equals(QuestionType.SINGLE_CHOICE)) {
            singleChoiceLayoutBinding = SingleChoiceLayoutBinding.inflate(LayoutInflater.from(context));
            singleChoiceLayoutBinding.rbA.setText(question.getA());
            singleChoiceLayoutBinding.rbB.setText(question.getB());
            singleChoiceLayoutBinding.rbC.setText(question.getC());
            singleChoiceLayoutBinding.rbD.setText(question.getD());
            view = singleChoiceLayoutBinding.getRoot();
        } else if (question.getType().equals(QuestionType.JUDGE)) {
            judgeLayoutBinding = JudgeLayoutBinding.inflate(LayoutInflater.from(context));
            judgeLayoutBinding.rbA.setText(question.getA());
            judgeLayoutBinding.rbB.setText(question.getB());
            view = judgeLayoutBinding.getRoot();
        } else if (question.getType().equals(QuestionType.MULTI_CHOICE)) {
            multiChoiceLayoutBinding = MultiChoiceLayoutBinding.inflate(LayoutInflater.from(context));
            multiChoiceLayoutBinding.cbA.setText(question.getA());
            multiChoiceLayoutBinding.cbB.setText(question.getB());
            multiChoiceLayoutBinding.cbC.setText(question.getC());
            multiChoiceLayoutBinding.cbD.setText(question.getD());
            view = multiChoiceLayoutBinding.getRoot();
        } else {
            Toast.makeText(context, "Unsupported question type!", Toast.LENGTH_SHORT).show();
        }
    }

    public Context getContext() {
        return context;
    }

    public View getView() {
        return view;
    }

    /**
     * 用户选择是否正确
     *
     * @return
     */
    public boolean isCorrect() {

        String answer = "";

        if (question.getType().equals(QuestionType.SINGLE_CHOICE)) {
            RadioButton rb = (RadioButton) singleChoiceLayoutBinding.radioGroup.findViewById(singleChoiceLayoutBinding.radioGroup.getCheckedRadioButtonId());
            if (rb == singleChoiceLayoutBinding.rbA) {
                answer = "a";
            } else if (rb == singleChoiceLayoutBinding.rbB) {
                answer = "b";
            } else if (rb == singleChoiceLayoutBinding.rbC) {
                answer = "c";
            } else if (rb == singleChoiceLayoutBinding.rbD) {
                answer = "d";
            }
        } else if (question.getType().equals(QuestionType.JUDGE)) {
            RadioButton rb = (RadioButton) judgeLayoutBinding.judgeLayoutRadioGroup.findViewById(judgeLayoutBinding.judgeLayoutRadioGroup.getCheckedRadioButtonId());
            if (rb == judgeLayoutBinding.rbA) {
                answer = "a";
            } else if (rb == judgeLayoutBinding.rbB) {
                answer = "b";
            }
        } else if (question.getType().equals(QuestionType.MULTI_CHOICE)) {
            if (multiChoiceLayoutBinding.cbA.isChecked()) {
                answer += "a";
            }
            if (multiChoiceLayoutBinding.cbB.isChecked()) {
                answer += "b";
            }
            if (multiChoiceLayoutBinding.cbC.isChecked()) {
                answer += "c";
            }
            if (multiChoiceLayoutBinding.cbD.isChecked()) {
                answer += "d";
            }
        }

        return answer.equals(question.getAnswer().toLowerCase());
    }
}
