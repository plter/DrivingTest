package top.yunp.drivingtest.controllers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.databinding.FragmentSubject1EntryBinding;
import top.yunp.drivingtest.helpers.Operation;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class EntryFragment extends Fragment implements AdapterView.OnItemClickListener {


    private FragmentSubject1EntryBinding binding;

    public EntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubject1EntryBinding.inflate(inflater);
        binding.setController(this);

        binding.listView.setAdapter(getMenuItemsAdapter());
        binding.listView.setOnItemClickListener(this);

        return binding.getRoot();
    }

    protected abstract ArrayAdapter<Operation> getMenuItemsAdapter();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Operation item = (Operation) parent.getAdapter().getItem(position);
        onSelectOperation(item);
    }

    protected abstract void onSelectOperation(Operation item);

    public void showFragment(int containerId, Fragment newFragment) {
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.alpha_out, R.anim.alpha_in, R.anim.slide_out_to_bottom)
                .replace(containerId, newFragment)
                .addToBackStack(null)
                .commit();
    }

}
