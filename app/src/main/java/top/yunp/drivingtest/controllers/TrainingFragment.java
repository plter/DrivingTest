package top.yunp.drivingtest.controllers;


import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import top.yunp.drivingtest.databinding.FragmentTrainingBinding;
import top.yunp.drivingtest.databinding.SingleChoiceLayoutBinding;
import top.yunp.drivingtest.helpers.InternalVideoTool;
import top.yunp.drivingtest.reader.Question;
import top.yunp.drivingtest.reader.QuestionsReader;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class TrainingFragment extends Fragment {


    private FragmentTrainingBinding binding;

    private static final String KEY_QUESTION_INDEX = "questionIndex";
    private static final String KEY_REMAIN_QUESTIONS = "remainQuestions";
    private SharedPreferences sharedPreferences;

    private final ObservableField<String> title = new ObservableField<>("");
    private final ObservableField<Integer> preBtnVisibility = new ObservableField<>(View.VISIBLE);
    private List<Question> questions;
    private Question currentQuestion;
    private SingleChoiceLayoutBinding currentSingleChoiceLayoutBinding;
    private AnswerFieldController currentAnswerFieldController = null;
    private TrainingType trainingType;
    private int questionIndex = 0;
    private final ObservableField<String> pageTitle = new ObservableField<>("No title");

    private List<Question> tryToReadCachedRemainQuestions(String baseDir) {
        List<Question> cachedQuestions = null;
        String questionsJsonString = getSharedPreferences().getString(KEY_REMAIN_QUESTIONS, null);
        if (questionsJsonString != null) {
            cachedQuestions = QuestionsReader.parseJsonString(getContext(), questionsJsonString, baseDir);
        }
        return cachedQuestions;
    }

    public SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = getContext().getSharedPreferences(this.getClass().getName(), Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    private void cacheRemainQuestions() {
        getSharedPreferences().edit()
                .putString(KEY_REMAIN_QUESTIONS, QuestionsReader.encodeQuestionsToJsonObject(questions))
                .apply();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTrainingBinding.inflate(inflater);

        trainingType = getTrainingType();
        preBtnVisibility.set(trainingType == TrainingType.FLOW ? View.VISIBLE : View.GONE);

        //read questions
        if (getTrainingType() == TrainingType.FLOW) {
            questions = getSourceQuestions();
            questionIndex = getCachedQuestionIndex();
        } else {
            questions = tryToReadCachedRemainQuestions(getQuestionsBaseDir());

            if (questions == null) {
                questions = QuestionsReader.cloneQuestions(getSourceQuestions());
            }

            questionIndex = randomIndex();
        }

        showQuestion();

        binding.setController(this);
        return binding.getRoot();
    }

    private int getCachedQuestionIndex() {
        return getSharedPreferences().getInt(KEY_QUESTION_INDEX, 0);
    }

    protected abstract String getQuestionsBaseDir();

    @Override
    public void onDestroyView() {
        switch (getTrainingType()) {
            case FLOW:
                cacheQuestionIndex();
                break;
            case RANDOM:
                cacheRemainQuestions();
                break;
        }

        super.onDestroyView();
    }

    private void cacheQuestionIndex() {
        getSharedPreferences().edit().putInt(KEY_QUESTION_INDEX, questionIndex).apply();
    }

    protected abstract TrainingType getTrainingType();

    public FragmentTrainingBinding getBinding() {
        return binding;
    }

    public abstract List<Question> getSourceQuestions();

    public ObservableField<String> getTitle() {
        return title;
    }

    private int randomIndex() {
        return (int) (Math.random() * questions.size());
    }

    public void btnCloseClickedHandler(View v) {
        getFragmentManager().popBackStack();
    }

    public ObservableField<Integer> getPreBtnVisibility() {
        return preBtnVisibility;
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

    public void btnNextClickedHandler(View v) {
        if (currentAnswerFieldController.isCorrect()) {
            switch (trainingType) {
                case FLOW:
                    questionIndex++;
                    break;
                case RANDOM:
                    questions.remove(questionIndex);
                    questionIndex = randomIndex();
                    break;
                default:
                    questionIndex = (int) (Math.random() * questions.size());
                    Toast.makeText(getContext(), "未知的训练模式，则程序将以随机练题模式执行", Toast.LENGTH_SHORT).show();
                    break;
            }

            if (questionIndex >= questions.size()) {
                questionIndex = 0;
                Toast.makeText(getContext(), "题目已经练完，将从头开始", Toast.LENGTH_SHORT).show();
            }

            showQuestion();
        } else {
            new AlertDialog.Builder(getContext())
                    .setTitle("提示")
                    .setMessage(Html.fromHtml("<font color='red'>正确答案是：" + currentQuestion.getAnswer().toUpperCase() + "</font>，解释如下：<br><br><font color='blue'>" + currentQuestion.getDescription() + "</font>"))
                    .setPositiveButton("记住了", null)
                    .setNegativeButton("关闭", null)
                    .show();
        }
    }


    /**
     * 根据题号呈现问题
     */
    private void showQuestion() {
        if (currentQuestion != null) {
            currentQuestion.recycleBitmap();
        }

        if (questions.size() > 0) {
            currentQuestion = questions.get(questionIndex);
            title.set("[" + (questionIndex + 1) + "/" + questions.size() + "]" + currentQuestion.getTitle());

            binding.answerContainer.removeAllViews();
            currentAnswerFieldController = new AnswerFieldController(getContext(), currentQuestion);
            binding.answerContainer.addView(currentAnswerFieldController.getView());

            checkToLoadImage(currentQuestion);
            checkToLoadVideo(currentQuestion);
        } else {
            new AlertDialog.Builder(getContext())
                    .setTitle("你好")
                    .setMessage("已经练完了所有的题目")
                    .setPositiveButton("确定", null)
                    .show();

        }
    }

    private void checkToLoadImage(Question question) {
        if (question.getImage() != null) {
            binding.imageView.setVisibility(View.VISIBLE);
            binding.imageView.setImageBitmap(question.getImageBitmap());
        } else {
            binding.imageView.setVisibility(View.GONE);
            binding.imageView.setImageBitmap(null);
        }
    }

    private void checkToLoadVideo(Question question) {
        if (question.getVideo() != null) {
            binding.videoView.setVisibility(View.VISIBLE);

            InternalVideoTool.showAssetVideoTo(getContext(), question.getBaseDir() + question.getVideo(), "video.mp4", binding.videoView);
        } else {
            binding.videoView.setVideoURI(null);
            binding.videoView.setVisibility(View.GONE);
        }
    }

    public ObservableField<String> getPageTitle() {
        return pageTitle;
    }

}
