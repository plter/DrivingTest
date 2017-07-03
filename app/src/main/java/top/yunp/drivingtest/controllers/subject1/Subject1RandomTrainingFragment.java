package top.yunp.drivingtest.controllers.subject1;

import android.support.v7.app.AlertDialog;

import org.xml.sax.SAXException;

import java.util.List;

import top.yunp.drivingtest.controllers.TrainingFragment;
import top.yunp.drivingtest.reader.Question;
import top.yunp.drivingtest.reader.QuestionsReadUtil;

/**
 * Created by plter on 6/30/17.
 */

public class Subject1RandomTrainingFragment extends TrainingFragment {


    @Override
    public List<Question> getSourceQuestions() {
        try {
            return QuestionsReadUtil.readSubject1(Subject1RandomTrainingFragment.this.getContext());
        } catch (SAXException e) {
            e.printStackTrace();
            new AlertDialog.Builder(Subject1RandomTrainingFragment.this.getContext())
                    .setTitle("警告")
                    .setMessage("数据格式错误，可能软件已经损坏，请联系开发者")
                    .setPositiveButton("好的", null)
                    .show();
            return null;
        }
    }

    @Override
    public TrainingType getTrainingType() {
        return TrainingType.RANDOM;
    }
}