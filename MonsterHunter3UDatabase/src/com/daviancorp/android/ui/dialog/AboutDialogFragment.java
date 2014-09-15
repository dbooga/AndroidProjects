package com.daviancorp.android.ui.dialog;

import com.daviancorp.android.monsterhunter3udatabase.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AboutDialogFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		final TextView message = new TextView(getActivity());
		
		final SpannableString s =
				new SpannableString(getActivity().getText(R.string.about_message));
		
		message.setText(s);
		message.setMovementMethod(LinkMovementMethod.getInstance());;
		message.setPadding(50, 10, 2, 10);
		message.setTextSize(18);
		
		return new AlertDialog.Builder(getActivity())
		.setTitle(R.string.about)
		.setPositiveButton(R.string.alert_button, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int id){
		    	dialog.dismiss();
		    }
		})
		.setView(message)
		.create();
		
	}
}