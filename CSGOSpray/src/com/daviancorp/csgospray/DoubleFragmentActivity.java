package com.daviancorp.csgospray;

import com.example.csgospray.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class DoubleFragmentActivity extends FragmentActivity {
	protected abstract Fragment createFragmentOne();

	protected abstract Fragment createFragmentTwo();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment1 = fm.findFragmentById(R.id.fragment_container);
		Fragment fragment2 = fm.findFragmentById(R.id.fragment_container);
		if (fragment1 == null && fragment2 == null) {
			fragment1 = createFragmentOne();
			fragment2 = createFragmentTwo();
			fm.beginTransaction()
					.add(R.id.fragment_container, fragment1)
					.add(R.id.fragment_container, fragment2)
					.commit();
		}
	}
}
