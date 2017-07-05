package top.yunp.drivingtest.controllers.exam.subject4;

import android.widget.ArrayAdapter;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.controllers.exam.EntryFragment;
import top.yunp.drivingtest.helpers.Operation;

/**
 * Created by plter on 7/3/17.
 */

public class Subject4EntryFragment extends EntryFragment {


    @Override
    protected ArrayAdapter<Operation> getMenuItemsAdapter() {
        return new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, new Operation[]{
                new Operation("随机练题", Operation.SHOW_RANDOM_TRAINING_FRAGMENT),
                new Operation("顺序练题", Operation.SHOW_FLOW_TRAINING_FRAGMENT),
                new Operation("模拟考试", Operation.SHOW_MOCK_EXAM_FRAGMENT)
        });
    }

    @Override
    protected void onSelectOperation(Operation item) {
        switch (item.getOperation()) {
            case Operation.SHOW_RANDOM_TRAINING_FRAGMENT:
                showFragment(R.id.subject4FragmentContainer, new Subject4RandomTrainingFragment());
                break;
            case Operation.SHOW_FLOW_TRAINING_FRAGMENT:
                showFragment(R.id.subject4FragmentContainer, new Subject4FlowTrainingFragment());
                break;
            case Operation.SHOW_MOCK_EXAM_FRAGMENT:
                showFragment(R.id.subject4FragmentContainer, new Subject4MockExamFragment());
                break;
        }
    }
}
