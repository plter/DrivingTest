package top.yunp.drivingtest.controllers.exam.subject1;

import top.yunp.drivingtest.controllers.exam.TrainingType;

/**
 * Created by plter on 6/30/17.
 */

public class Subject1RandomTrainingFragment extends Subject1FlowTrainingFragment {

    public Subject1RandomTrainingFragment() {
        getPageTitle().set("科目一随机练题");
    }

    @Override
    public TrainingType getTrainingType() {
        return TrainingType.RANDOM;
    }
}
