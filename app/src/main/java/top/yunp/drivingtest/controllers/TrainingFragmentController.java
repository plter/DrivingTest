package top.yunp.drivingtest.controllers;

import android.databinding.ObservableField;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import top.yunp.drivingtest.databinding.FragmentTrainingBinding;
import top.yunp.drivingtest.databinding.SingleChoiceLayoutBinding;
import top.yunp.drivingtest.reader.Question;

/**
 * Created by plter on 6/22/17.
 */

public abstract class TrainingFragmentController {
    private final FragmentTrainingBinding binding;
    private final TrainingFragment fragment;
    private final ObservableField<String> title = new ObservableField<>("");
    private final ObservableField<Spanned> description = new ObservableField<>();
    private final ObservableField<Integer> preBtnVisibility = new ObservableField<>(View.VISIBLE);
    private List<Question> questions;
    private Question currentQuestion;
    private SingleChoiceLayoutBinding currentSingleChoiceLayoutBinding;
    private AnswerFieldController currentAnswerFieldController = null;
    private TrainingType trainingType;
    private int questionIndex = 0;

    public TrainingFragmentController(TrainingFragment fragment, FragmentTrainingBinding binding) {
        this.binding = binding;
        this.fragment = fragment;

        questions = getQuestions();

        trainingType = getTrainingType();
        preBtnVisibility.set(trainingType == TrainingType.FLOW ? View.VISIBLE : View.GONE);

        if (trainingType == TrainingType.FLOW) {
            questionIndex = 0;
        } else {
            questionIndex = randomIndex();
        }

        showQuestion();
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    private void showQuestion() {
        description.set(null);

        currentQuestion = questions.get(questionIndex);
        title.set("[" + (questionIndex + 1) + "/" + questions.size() + "]" + currentQuestion.getTitle());

        binding.answerContainer.removeAllViews();
        currentAnswerFieldController = new AnswerFieldController(fragment.getContext(), currentQuestion);
        binding.answerContainer.addView(currentAnswerFieldController.getView());
        if (currentQuestion.getImageBitmap() != null) {
            binding.imageView.setImageBitmap(currentQuestion.getImageBitmap());
        }
    }

    public void btnNextClickedHandler(View v) {
        if (currentAnswerFieldController.isCorrect()) {

            if (currentQuestion != null) {
                currentQuestion.recycleBitmap();
            }
            switch (trainingType) {
                case FLOW:
                    questionIndex++;
                    break;
                case RANDOM:
                    questionIndex = randomIndex();
                    break;
                default:
                    questionIndex = (int) (Math.random() * questions.size());
                    Toast.makeText(fragment.getContext(), "未知的训练模式，则程序将以随机练题模式执行", Toast.LENGTH_SHORT).show();
                    break;
            }

            if (questionIndex >= questions.size()) {
                questionIndex = 0;
                Toast.makeText(fragment.getContext(), "题目已经练完，将从头开始", Toast.LENGTH_SHORT).show();
            }

            showQuestion();
            description.set(null);

        } else {
            description.set(Html.fromHtml("<font color='red'>正确答案是：" + currentQuestion.getAnswer().toUpperCase() + "</font>，解释如下：<br><br><font color='blue'>" + currentQuestion.getDescription() + "</font>"));
        }
    }

    private int randomIndex() {
        return (int) (Math.random() * questions.size());
    }

    public void btnPreClickedHandler(View v) {
        if (trainingType == TrainingType.FLOW) {
            questionIndex--;
            if (questionIndex < 0) {
                questionIndex = 0;
            }
            showQuestion();
        }
    }

    public void btnCloseClickedHandler(View v) {
        fragment.getFragmentManager().popBackStack();
    }

    public ObservableField<Integer> getPreBtnVisibility() {
        return preBtnVisibility;
    }

    public ObservableField<Spanned> getDescription() {
        return description;
    }

    public abstract List<Question> getQuestions();

    public abstract TrainingType getTrainingType();

    /**
     * 练题模式
     */
    public enum TrainingType {
        /**
         * 随机练题
         */
        RANDOM,
        /**
         * 顺序练题
         */
        FLOW
    }
}
