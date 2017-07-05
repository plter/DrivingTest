package top.yunp.drivingtest.controllers.exam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by plter on 7/5/17.
 */

public class WebViewFragment extends SubjectFragment {

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        webView = new WebView(getContext());
        return webView;
    }

    public WebView getWebView() {
        return webView;
    }
}
