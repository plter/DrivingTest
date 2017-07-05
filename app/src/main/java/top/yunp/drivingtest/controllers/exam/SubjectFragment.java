package top.yunp.drivingtest.controllers.exam;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by plter on 6/22/17.
 */

public class SubjectFragment extends Fragment {

    public static final java.lang.String KEY_TITLE = "title";


    public SubjectFragment() {
        setArguments(new Bundle());
    }

    public SubjectFragment setTitle(String title) {
        getArguments().putString(KEY_TITLE, title);
        return this;
    }

    public String getTitle() {
        return getArguments().getString(KEY_TITLE, "None");
    }
}
