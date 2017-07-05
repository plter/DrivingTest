package top.yunp.drivingtest.controllers.subject4;

import top.yunp.drivingtest.controllers.TrainingType;

/**
 * Created by plter on 7/4/17.
 */

public class Subject4RandomTrainingFragment extends Subject4FlowTrainingFragment {


    public Subject4RandomTrainingFragment() {
        getPageTitle().set("科目四随机练题");
    }

    @Override
    protected TrainingType getTrainingType() {
        return TrainingType.RANDOM;
    }
}
