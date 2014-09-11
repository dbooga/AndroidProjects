package com.daviancorp.android.ui.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;

import com.daviancorp.android.monsterhunter3udatabase.R;
import com.daviancorp.android.ui.general.GenericActivity;

public class WeaponGridActivity extends GenericActivity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.weapons);
	}

	@Override
	protected Fragment createFragment() {
		super.detail = new WeaponGridFragment();
		return super.detail;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		return true;
	}

}
