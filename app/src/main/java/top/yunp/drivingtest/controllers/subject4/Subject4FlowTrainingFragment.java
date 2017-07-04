package top.yunp.drivingtest.controllers.subject4;

import java.util.List;

import top.yunp.drivingtest.controllers.TrainingFragment;
import top.yunp.drivingtest.reader.Question;
import top.yunp.drivingtest.reader.QuestionsReadUtil;

/**
 * Created by plter on 7/4/17.
 */

public class Subject4FlowTrainingFragment extends TrainingFragment {
    @Override
    protected String getQuestionsBaseDir() {
        return QuestionsReadUtil.SUBJECT4_PATH;
    }

    @Override
    protected TrainingType getTrainingType() {
        return TrainingType.FLOW;
    }

    @Override
    public List<Question> getSourceQuestions() {
        return QuestionsReadUtil.readSubject4(Subject4FlowTrainingFragment.this.getContext());
    }
}
