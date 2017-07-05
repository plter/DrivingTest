package top.yunp.drivingtest.controllers.subject1;

import java.util.List;

import top.yunp.drivingtest.controllers.TrainingFragment;
import top.yunp.drivingtest.controllers.TrainingType;
import top.yunp.drivingtest.reader.Question;
import top.yunp.drivingtest.reader.QuestionsReadUtil;

/**
 * Created by plter on 6/30/17.
 */

public class Subject1FlowTrainingFragment extends TrainingFragment {


    public Subject1FlowTrainingFragment() {
        getPageTitle().set("科目一顺序练题");
    }

    @Override
    protected String getQuestionsBaseDir() {
        return QuestionsReadUtil.SUBJECT1_PATH;
    }

    @Override
    protected TrainingType getTrainingType() {
        return TrainingType.FLOW;
    }

    @Override
    public List<Question> getSourceQuestions() {
        return QuestionsReadUtil.readSubject1(Subject1FlowTrainingFragment.this.getContext());
    }
}
