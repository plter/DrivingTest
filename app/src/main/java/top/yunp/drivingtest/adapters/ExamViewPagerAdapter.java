package top.yunp.drivingtest.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.fragments.Subject1Fragment;
import top.yunp.drivingtest.fragments.Subject2Fragment;
import top.yunp.drivingtest.fragments.Subject3Fragment;
import top.yunp.drivingtest.fragments.Subject4Fragment;
import top.yunp.drivingtest.fragments.SubjectFragment;

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
