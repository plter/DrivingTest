package top.yunp.drivingtest.controllers.exam.subject1;


import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.controllers.exam.EntryFragment;
import top.yunp.drivingtest.helpers.Operation;

/**
 * A simple {@link Fragment} subclass.
 */
public class Subject1EntryFragment extends EntryFragment {



    public Subject1EntryFragment() {
        // Required empty public constructor
    }

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
                showFragment(R.id.subject1FragmentContainer, new Subject1RandomTrainingFragment());
                break;
            case Operation.SHOW_FLOW_TRAINING_FRAGMENT:
                showFragment(R.id.subject1FragmentContainer, new Subject1FlowTrainingFragment());
                break;
            case Operation.SHOW_MOCK_EXAM_FRAGMENT:
                showFragment(R.id.subject1FragmentContainer, new Subject1MockExamFragment());
                break;
        }
    }
}
