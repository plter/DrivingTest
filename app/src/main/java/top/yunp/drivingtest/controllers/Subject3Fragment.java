package top.yunp.drivingtest.controllers;


import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Subject3Fragment extends WebViewFragment {


    public Subject3Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        getWebView().loadUrl("http://www.soku.com/m/y/video?q=%E7%A7%91%E7%9B%AE%E4%B8%89");
        super.onResume();
    }
}
