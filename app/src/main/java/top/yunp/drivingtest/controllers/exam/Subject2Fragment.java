package top.yunp.drivingtest.controllers.exam;



/**
 * A simple {@link Fragment} subclass.
 */
public class Subject2Fragment extends WebViewFragment {


    public Subject2Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        getWebView().loadUrl("http://www.soku.com/m/y/video?q=%E7%A7%91%E7%9B%AE%E4%BA%8C");
        super.onResume();
    }
}
