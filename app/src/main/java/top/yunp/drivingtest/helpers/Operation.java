package top.yunp.drivingtest.helpers;

/**
 * Created by plter on 7/3/17.
 */
public class Operation {

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
