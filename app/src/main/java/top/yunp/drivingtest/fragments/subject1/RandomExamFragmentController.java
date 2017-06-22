package top.yunp.drivingtest.fragments.subject1;

import android.databinding.ObservableField;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.xml.sax.SAXException;

import java.util.List;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.anim.AnimationListenerAdapter;
import top.yunp.drivingtest.anim.Translate3D;
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

        String type = currentQuestion.getType();
        if (type.equals("single")) {
            binding.answerContainer.removeAllViews();

            currentSingleChoiceLayoutBinding = SingleChoiceLayoutBinding.inflate(LayoutInflater.from(fragment.getContext()));
            binding.answerContainer.addView(currentSingleChoiceLayoutBinding.getRoot());
            currentSingleChoiceLayoutBinding.rbA.setText(currentQuestion.getA());
            currentSingleChoiceLayoutBinding.rbB.setText(currentQuestion.getB());
            currentSingleChoiceLayoutBinding.rbC.setText(currentQuestion.getC());
            currentSingleChoiceLayoutBinding.rbD.setText(currentQuestion.getD());
        }
    }

    public void btnNextClickedHandler(View v) {
        if (currentQuestion.getType().equals("single")) {

            RadioGroup rg = currentSingleChoiceLayoutBinding.radioGroup;
            RadioButton rb = (RadioButton) rg.findViewById(rg.getCheckedRadioButtonId());
            String answer = "";
            if (rb == currentSingleChoiceLayoutBinding.rbA) {
                answer = "a";
            } else if (rb == currentSingleChoiceLayoutBinding.rbB) {
                answer = "b";
            } else if (rb == currentSingleChoiceLayoutBinding.rbC) {
                answer = "c";
            } else if (rb == currentSingleChoiceLayoutBinding.rbD) {
                answer = "d";
            }
            if (answer.equals(currentQuestion.getAnswer())) {
                Toast.makeText(fragment.getContext(), "正确", Toast.LENGTH_SHORT).show();
            } else {
                description.set(currentQuestion.getDescription());
            }
        }
    }

    public ObservableField<String> getDescription() {
        return description;
    }
}
