package top.yunp.drivingtest.controllers.exam.subject4;

import java.util.List;

import top.yunp.drivingtest.controllers.exam.MockExamFragment;
import top.yunp.drivingtest.controllers.exam.Subject;
import top.yunp.drivingtest.reader.Question;
import top.yunp.drivingtest.reader.QuestionsReadUtil;

/**
 * Created by plter on 7/5/17.
 */

public class Subject4MockExamFragment extends MockExamFragment {

    public Subject4MockExamFragment() {
        getPageTitle().set("科目四模拟考试");
    }

    @Override
    protected List<Question> getSourceQuestions() {
        return QuestionsReadUtil.readSubject4(getContext());
    }

    @Override
    protected Subject getSubject() {
        return Subject.Subject4;
    }
}
