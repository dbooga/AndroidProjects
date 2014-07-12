package com.daviancorp.csgospray;

import java.io.InputStream;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.daviancorp.csgospray.R;

public class DetailFragment extends Fragment {
	WebView webviewActionView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	//@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_spray, parent, false);
		
		InputStream stream = null;
        try {
            stream = getActivity().getResources().getAssets().open("p90recoil.gif");
        } catch (Exception e) {
            e.printStackTrace();
        }

        webviewActionView = (WebView)v.findViewById(R.id.webviewActionView);
        webviewActionView.setWebViewClient(new MyWebViewClient());
        webviewActionView.getSettings().setJavaScriptEnabled(true);

        GifWebView view = new GifWebView(getActivity(), stream);
        webviewActionView.addView(view);
        
		return v;

	}
	
    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
