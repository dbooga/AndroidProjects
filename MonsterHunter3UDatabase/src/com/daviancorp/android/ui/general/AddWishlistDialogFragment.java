package com.daviancorp.android.ui.general;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.daviancorp.android.monsterhunter3udatabase.R;

public class AddWishlistDialogFragment extends DialogFragment {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
			.setTitle(R.string.option_add)
			.setPositiveButton(android.R.string.ok, null)
			.create();
	}
}
