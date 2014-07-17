package com.daviancorp.csgospray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
	private GifWebView mGifWebView;
	WebView webviewActionView;
	private ArrayList<Gun> gunList;
	private Gun currGun;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gunList = GunData.getInstance().getData();

	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_spray, parent, false);

		webviewActionView = (WebView) v.findViewById(R.id.webviewActionView);
		webviewActionView.setWebViewClient(new MyWebViewClient());
		webviewActionView.getSettings().setJavaScriptEnabled(true);

		setGunView(gunList.get(0));

		((MainActivity) getActivity()).getDrawer().setTargetFragment(this,
				REQUEST_GUN);

		return v;

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK)
			return;
		if (requestCode == REQUEST_GUN) {
			int gun = data.getIntExtra(DrawerFragment.EXTRA_GUN, 0);
			setGunView(gunList.get(gun));
		}
	}
	
	public void updateGunView(){
		setGunView(currGun);
	}

	private void setGunView(Gun gunName) {
		currGun = gunName;
		String filename;

		switch (((MainActivity) getActivity()).getOption()) {
		case MainActivity.OPTION_PATTERN:
			filename = currGun.getPFile();
			break;
		case MainActivity.OPTION_COMPENSATION:
			filename = currGun.getCFile();
			break;
		case MainActivity.OPTION_INVERTED:
			filename = currGun.getNFile();
			break;
		default:
			filename = currGun.getPFile();
			break;
		}
		InputStream stream = null;
		try {
			stream = getActivity().getResources().getAssets().open(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		webviewActionView.removeView(mGifWebView);

		mGifWebView = null;
		System.gc();
		mGifWebView = new GifWebView(getActivity(), stream);
		webviewActionView.addView(mGifWebView);
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class MyWebViewClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}
	
}
