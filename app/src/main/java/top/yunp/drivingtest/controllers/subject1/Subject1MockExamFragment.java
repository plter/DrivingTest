package top.yunp.drivingtest.controllers.subject1;

import java.util.List;

import top.yunp.drivingtest.controllers.MockExamFragment;
import top.yunp.drivingtest.controllers.Subject;
import top.yunp.drivingtest.reader.Question;
import top.yunp.drivingtest.reader.QuestionsReadUtil;

/**
 * Created by plter on 7/5/17.
 */

public class Subject1MockExamFragment extends MockExamFragment {


    public Subject1MockExamFragment() {
        getPageTitle().set("科目一模拟考试");
    }

    @Override
    protected List<Question> getSourceQuestions() {
        return QuestionsReadUtil.readSubject1(getContext());
    }

    @Override
    protected Subject getSubject() {
        return Subject.Subject1;
    }
}
