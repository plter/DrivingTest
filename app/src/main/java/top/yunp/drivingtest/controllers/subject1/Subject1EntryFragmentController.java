package top.yunp.drivingtest.controllers.subject1;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.databinding.FragmentSubject1EntryBinding;

/**
 * Created by plter on 6/30/17.
 */

public class Subject1EntryFragmentController implements AdapterView.OnItemClickListener {
    private final Subject1EntryFragment fragment;
    private final FragmentSubject1EntryBinding binding;

    public Subject1EntryFragmentController(FragmentSubject1EntryBinding binding, Subject1EntryFragment fragment) {
        this.fragment = fragment;
        this.binding = binding;

        this.binding.listView.setAdapter(new ArrayAdapter<>(fragment.getContext(), android.R.layout.simple_list_item_1, new Operation[]{
                new Operation("随机练题", Operation.SHOW_RANDOM_TRAINING_FRAGMENT),
                new Operation("顺序练题", Operation.SHOW_FLOW_TRAINING_FRAGMENT),
                new Operation("模拟考试", Operation.SHOW_MOCK_EXAM_FRAGMENT)
        }));

        this.binding.listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (((Operation) parent.getAdapter().getItem(position)).getOperation()) {
            case Operation.SHOW_RANDOM_TRAINING_FRAGMENT:
                showFragment(new Subject1RandomTrainingFragment());
                break;
            case Operation.SHOW_FLOW_TRAINING_FRAGMENT:
                showFragment(new Subject1FlowTrainingFragment());
                break;
            case Operation.SHOW_MOCK_EXAM_FRAGMENT:
                //TODO
                break;
        }
    }

    private void showFragment(Fragment newFragment) {
        fragment.getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.alpha_out, R.anim.alpha_in, R.anim.slide_out_to_bottom)
                .replace(R.id.subject1FragmentContainer, newFragment)
                .addToBackStack(null)
                .commit();
    }

    private static class Operation {

        public static final int SHOW_RANDOM_TRAINING_FRAGMENT = 1;
        public static final int SHOW_FLOW_TRAINING_FRAGMENT = 2;
        public static final int SHOW_MOCK_EXAM_FRAGMENT = 3;

        private String label;
        private int operation;

        public Operation(String label, int operation) {
            this.label = label;
            this.operation = operation;
        }

        public String getLabel() {
            return label;
        }

        public int getOperation() {
            return operation;
        }

        @Override
        public String toString() {
            return label;
        }
    }
}
