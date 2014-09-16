package com.daviancorp.android.ui.general;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.daviancorp.android.monsterhunter3udatabase.R;
import com.daviancorp.android.ui.dialog.AboutDialogFragment;

/*
 * Any subclass needs to:
 *  - override onCreate() to set title
 *  - override createFragmentOne() for detail fragments
 */

public abstract class GenericTabActivity extends ActionBarActivity {

	protected static final String DIALOG_ABOUT = "about";

	protected Fragment detail;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(R.string.app_name);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		setContentView(R.layout.activity_tab);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(GenericTabActivity.this,
					HomeActivity.class);
			startActivity(intent);
			return true;
		case R.id.about:
			FragmentManager fm = getSupportFragmentManager();
			AboutDialogFragment dialog = new AboutDialogFragment();
			dialog.show(fm, DIALOG_ABOUT);

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		for (int i = 0; i < menu.size(); i++) {
			MenuItem mi = menu.getItem(i);
			String title = mi.getTitle().toString();
			Spannable newTitle = new SpannableString(title);
			newTitle.setSpan(new ForegroundColorSpan(Color.WHITE), 0,
					newTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			mi.setTitle(newTitle);
		}
		return true;
	}

	public Fragment getDetail() {
		return detail;
	}
}
