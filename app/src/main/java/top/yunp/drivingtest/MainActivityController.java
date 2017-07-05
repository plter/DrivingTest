package top.yunp.drivingtest;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import top.yunp.drivingtest.controllers.AboutFragment;
import top.yunp.drivingtest.controllers.exam.ExamFragment;
import top.yunp.drivingtest.databinding.ActivityMainBinding;

/**
 * Created by plter on 6/22/17.
 */

public class MainActivityController {

    private MainActivity activity;
    private ActivityMainBinding binding;
    private int currentItemId;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();
            if (currentItemId == itemId) {
                return false;
            }

            currentItemId = itemId;

            switch (itemId) {
                case R.id.navigation_exam:
                    addExamFragment();
                    return true;
                case R.id.navigation_settings:
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, new AboutFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    public MainActivityController(MainActivity activity, ActivityMainBinding binding) {
        this.activity = activity;
        this.binding = binding;

        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_directions_car_black_24dp);

        addExamFragment();
        addListeners();
    }

    private void addExamFragment() {
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, new ExamFragment()).commit();
        currentItemId = R.id.navigation_exam;
    }

    private void addListeners() {
        binding.navigation.setOnNavigationItemSelectedListener(navigationSelectedListener);
    }
}
