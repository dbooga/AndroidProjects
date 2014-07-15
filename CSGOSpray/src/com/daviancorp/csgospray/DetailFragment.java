package com.daviancorp.csgospray;

import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailFragment extends Fragment {
	
	private static int REQUEST_GUN = 0;
	
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
		
        webviewActionView = (WebView)v.findViewById(R.id.webviewActionView);
        
        setGunView("p90recoil.gif");
        
        ((MainActivity) getActivity()).getDrawer().setTargetFragment(this, REQUEST_GUN);
        
		return v;

	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if (resultCode != Activity.RESULT_OK) return;
		if (requestCode == REQUEST_GUN){
			String gun = (String)data.getSerializableExtra(DrawerFragment.EXTRA_GUN);
			setGunView(gun);
		}
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	private void setGunView(String filename){
		InputStream stream = null;
        try {
            stream = getActivity().getResources().getAssets().open(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        webviewActionView.setWebViewClient(new MyWebViewClient());
        webviewActionView.getSettings().setJavaScriptEnabled(true);

        GifWebView view = new GifWebView(getActivity(), stream);
        webviewActionView.addView(view);
	}
	
    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
