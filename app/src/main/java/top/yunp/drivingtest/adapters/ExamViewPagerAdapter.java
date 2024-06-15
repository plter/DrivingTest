package top.yunp.drivingtest.adapters;

import android.content.Context;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.controllers.exam.subject1.Subject1Fragment;
import top.yunp.drivingtest.controllers.exam.Subject2Fragment;
import top.yunp.drivingtest.controllers.exam.Subject3Fragment;
import top.yunp.drivingtest.controllers.exam.subject4.Subject4Fragment;
import top.yunp.drivingtest.controllers.exam.SubjectFragment;

/**
 * Created by plter on 6/22/17.
 */

public class ExamViewPagerAdapter extends FragmentPagerAdapter {

    private List<SubjectFragment> fragments = new ArrayList<>();


    public ExamViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);

        fragments.add(new Subject1Fragment().setTitle(context.getString(R.string.subject1)));
        fragments.add(new Subject2Fragment().setTitle(context.getString(R.string.subject2)));
        fragments.add(new Subject3Fragment().setTitle(context.getString(R.string.subject3)));
        fragments.add(new Subject4Fragment().setTitle(context.getString(R.string.subject4)));
    }

    @Override
    public SubjectFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
