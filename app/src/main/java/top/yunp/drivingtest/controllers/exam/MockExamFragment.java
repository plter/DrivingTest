package top.yunp.drivingtest.controllers.exam;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import top.yunp.drivingtest.databinding.FragmentMockExamBinding;
import top.yunp.drivingtest.helpers.InternalVideoTool;
import top.yunp.drivingtest.reader.Question;
import top.yunp.drivingtest.reader.QuestionType;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class MockExamFragment extends Fragment {


    private FragmentMockExamBinding binding;
    private List<Question> questions;
    private List<Question> judgeQuestions = new ArrayList<>();
    private List<Question> singleChoiceQuestions = new ArrayList<>();
    private List<Question> multiChoiceQuestions = new ArrayList<>();
    private List<Question> examQuestions = new ArrayList<>();
    private ObservableField<String> pageTitle = new ObservableField<>("No title");
    private ObservableField<String> questionTitle = new ObservableField<>("No title");
    private ObservableField<String> nextBtnLabel = new ObservableField<>("下一题");
    private ObservableField<String> humanReadableTimeRemaining = new ObservableField<>("No title");
    private int timeRemaining;
    private int questionIndex = 0;
    private Question currentQuestion;
    private AnswerFieldController currentAnswerFieldController;
    private int totalScore = 100;
    private int scoreSubbed = 0;
    private int scorePerQuestion = 1;
    private int scoreGot = 0;

    public MockExamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        questions = getSourceQuestions();

        binding = FragmentMockExamBinding.inflate(inflater);
        binding.setController(this);

        restartExam();
        return binding.getRoot();
    }

    private void categoryQuestions(List<Question> qs) {
        judgeQuestions.clear();
        singleChoiceQuestions.clear();
        multiChoiceQuestions.clear();

        for (Question q :
                qs) {
            if (q.getType().equals(QuestionType.SINGLE_CHOICE)) {
                singleChoiceQuestions.add(q);
            } else if (q.getType().equals(QuestionType.JUDGE)) {
                judgeQuestions.add(q);
            } else if (q.getType().equals(QuestionType.MULTI_CHOICE)) {
                multiChoiceQuestions.add(q);
            }
        }
    }

    private void generateExamQuestions() {
        categoryQuestions(questions);
        examQuestions.clear();

        switch (getSubject()) {
            case Subject1:
                for (int i = 0; i < 40; i++) {
                    examQuestions.add(judgeQuestions.remove(randomIndex(judgeQuestions)));
                }
                for (int i = 0; i < 60; i++) {
                    examQuestions.add(singleChoiceQuestions.remove(randomIndex(singleChoiceQuestions)));
                }
                break;
            case Subject4:
                for (int i = 0; i < 22; i++) {
                    examQuestions.add(judgeQuestions.remove(randomIndex(judgeQuestions)));
                }
                for (int i = 0; i < 23; i++) {
                    examQuestions.add(singleChoiceQuestions.remove(randomIndex(singleChoiceQuestions)));
                }
                for (int i = 0; i < 5; i++) {
                    examQuestions.add(multiChoiceQuestions.remove(randomIndex(multiChoiceQuestions)));
                }
                break;
            default:
                throw new RuntimeException("未知的模拟考试类型");
        }
    }

    private int randomIndex(List<?> list) {
        return (int) (Math.random() * list.size());
    }

    public ObservableField<String> getPageTitle() {
        return pageTitle;
    }

    public ObservableField<String> getHumanReadableTimeRemaining() {
        return humanReadableTimeRemaining;
    }

    public void btnCloseClickedHandler(View v) {
        getFragmentManager().popBackStack();
    }

    protected abstract List<Question> getSourceQuestions();

    protected abstract Subject getSubject();


    /**
     * 开始考试
     */
    private void restartExam() {
        questionIndex = 0;
        scoreGot = 0;
        generateExamQuestions();

        switch (getSubject()) {
            case Subject1:
                timeRemaining = 45 * 60;
                scorePerQuestion = 1;
                startTimer();
                showQuestion();
                break;
            case Subject4:
                timeRemaining = 30 * 60;
                scorePerQuestion = 2;
                startTimer();
                showQuestion();
                break;
            default:
                throw new RuntimeException("未知的模拟考试");
        }
    }

    private void showQuestion() {
        if (currentQuestion != null && currentQuestion.getImage() != null) {
            currentQuestion.recycleBitmap();
        }

        currentQuestion = examQuestions.get(questionIndex);
        getQuestionTitle().set("[" + (questionIndex + 1) + "/" + examQuestions.size() + "]" + currentQuestion.getTitle());
        currentAnswerFieldController = new AnswerFieldController(getContext(), currentQuestion);
        binding.answerContainer.removeAllViews();
        binding.answerContainer.addView(currentAnswerFieldController.getView());

        if (currentQuestion.getImage() != null) {
            binding.imageView.setVisibility(View.VISIBLE);
            binding.imageView.setImageBitmap(currentQuestion.getImageBitmap());
        } else {
            binding.imageView.setVisibility(View.GONE);
            binding.imageView.setImageBitmap(null);
        }

        if (currentQuestion.getVideo() != null) {
            binding.videoView.setVisibility(View.VISIBLE);
            InternalVideoTool.showAssetVideoTo(getContext(), currentQuestion.getBaseDir() + currentQuestion.getVideo(), "video.mp4", binding.videoView);
        } else {
            binding.videoView.setVisibility(View.GONE);
            binding.videoView.setVideoURI(null);
        }
    }


    private Timer timer;
    private boolean timerRunning = false;

    /**
     * 考试开始计时
     */
    private void startTimer() {
        if (!timerRunning) {
            syncToHumanReadableTimeRemaining();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timeRemaining--;
                    syncToHumanReadableTimeRemaining();

                    if (timeRemaining <= 0) {
                        stopTimer();

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (scoreGot >= 90) {
                                    showSuccessDialog();
                                } else {
                                    showFailDialog();
                                }
                            }
                        });
                    }
                }
            }, 1000, 1000);
            timerRunning = true;
        }
    }

    private void syncToHumanReadableTimeRemaining() {
        getHumanReadableTimeRemaining().set(formatTime(timeRemaining / 60) + ":" + formatTime(timeRemaining % 60));
    }

    private void stopTimer() {
        if (timerRunning) {
            timer.cancel();
            timerRunning = false;
        }
    }

    @Override
    public void onDestroyView() {
        stopTimer();
        super.onDestroyView();
    }

    private String formatTime(int time) {
        return (time >= 10 ? "" : "0") + time;
    }

    public ObservableField<String> getQuestionTitle() {
        return questionTitle;
    }

    public ObservableField<String> getNextBtnLabel() {
        return nextBtnLabel;
    }

    private void closeFragment() {
        getFragmentManager().popBackStack();
    }

    private void showFailDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("提示")
                .setMessage("扣分超过10分，考试失败")
                .setCancelable(false)
                .setPositiveButton("再考", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartExam();
                    }
                })
                .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        closeFragment();
                    }
                })
                .show();
    }

    public void btnNextClickedHandler(View v) {

        //在呈现下一题前计算扣分
        if (currentAnswerFieldController.isCorrect()) {
            scoreGot += scorePerQuestion;
        } else {
            scoreSubbed += scorePerQuestion;

            if (scoreSubbed >= 10) {
                stopTimer();
                showFailDialog();
            }
        }

        questionIndex++;
        if (questionIndex < examQuestions.size()) {
            showQuestion();
        } else {
            if (currentAnswerFieldController.isCorrect()) {
                scoreGot += scorePerQuestion;
            } else {
                scoreSubbed += scorePerQuestion;
            }

            if (scoreSubbed > 10) {
                stopTimer();
                showFailDialog();
            } else {
                showSuccessDialog();
            }
        }

        nextBtnLabel.set((questionIndex < examQuestions.size() - 1) ? "下一题" : "交卷");
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("恭喜")
                .setMessage("最终得分：" + scoreGot)
                .setPositiveButton("再考", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartExam();
                    }
                })
                .setNegativeButton("关闭", null)
                .show();
    }
}
