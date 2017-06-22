package top.yunp.drivingtest.controllers.subject1;

import android.databinding.ObservableField;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import org.xml.sax.SAXException;

import java.util.List;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.anim.AnimationListenerAdapter;
import top.yunp.drivingtest.anim.Translate3D;
import top.yunp.drivingtest.controllers.AnswerFieldController;
import top.yunp.drivingtest.databinding.FragmentRandomExamBinding;
import top.yunp.drivingtest.databinding.SingleChoiceLayoutBinding;
import top.yunp.drivingtest.reader.Question;
import top.yunp.drivingtest.reader.QuestionsReadUtil;

/**
 * Created by plter on 6/22/17.
 */

public class RandomExamFragmentController {
    private final FragmentRandomExamBinding binding;
    private final RandomExamFragment fragment;
    private ObservableField<String> title = new ObservableField<>("");
    private ObservableField<String> description = new ObservableField<>("");
    private List<Question> questions;
    private Question currentQuestion;
    private SingleChoiceLayoutBinding currentSingleChoiceLayoutBinding;
    private AnswerFieldController currentAnswerFieldController = null;

    public RandomExamFragmentController(RandomExamFragment fragment, FragmentRandomExamBinding binding) {
        this.binding = binding;
        this.fragment = fragment;

        try {
            questions = QuestionsReadUtil.read(fragment.getContext());
            showRandomQuestion();
        } catch (SAXException e) {
            e.printStackTrace();
            Toast.makeText(fragment.getContext(), "XML error", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnNavigateToMockFragmentClickedHandler(View v) {

        fragment.getView().startAnimation(new Translate3D(0, 90, true, 350, new AnimationListenerAdapter() {
            @Override
            public void onAnimationEnd(Animation animation) {
                MockExamFragment mockExamFragment = new MockExamFragment();
                mockExamFragment.setInitAnimation(new Translate3D(-90, 0, false, 350, null));
                fragment.getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.examContainer, mockExamFragment)
                        .commit();
            }
        }));


    }

    public ObservableField<String> getTitle() {
        return title;
    }

    private void showRandomQuestion() {
        description.set("");

        currentQuestion = questions.get((int) (Math.random() * questions.size()));

        title.set(currentQuestion.getTitle());

        binding.answerContainer.removeAllViews();
        currentAnswerFieldController = new AnswerFieldController(fragment.getContext(), currentQuestion);
        binding.answerContainer.addView(currentAnswerFieldController.getView());
    }

    public void btnNextClickedHandler(View v) {
        if (currentAnswerFieldController.isCorrect()) {
            showRandomQuestion();
            description.set("");
        } else {
            description.set(currentQuestion.getDescription());
        }
    }

    public ObservableField<String> getDescription() {
        return description;
    }
}
