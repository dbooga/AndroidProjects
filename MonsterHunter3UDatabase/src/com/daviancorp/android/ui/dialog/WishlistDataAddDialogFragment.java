package com.daviancorp.android.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.daviancorp.android.data.database.DataManager;
import com.daviancorp.android.data.database.WishlistCursor;
import com.daviancorp.android.data.object.Wishlist;
import com.daviancorp.android.monsterhunter3udatabase.R;

public class WishlistDataAddDialogFragment extends DialogFragment {
	public static final String EXTRA_ADD =
			"com.daviancorp.android.ui.general.wishlist_data_add";
	
	private static final String ARG_WISHLIST_DATA_ID = "WISHLIST_DATA_ID";
	private long wishlistId = -1;
	private String wishlistName = "";

	public static WishlistDataAddDialogFragment newInstance(long id) {
		Bundle args = new Bundle();
		args.putLong(ARG_WISHLIST_DATA_ID, id);
		WishlistDataAddDialogFragment f = new WishlistDataAddDialogFragment();
		f.setArguments(args);
		return f;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {			
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		View v = inflater.inflate(R.layout.dialog_wishlist_data_add, null);
		final EditText quantityInput = (EditText) v.findViewById(R.id.add);
		quantityInput.setText("1");
		
		final WishlistCursor cursor = DataManager.get(getActivity()).queryWishlists();
		
		return new AlertDialog.Builder(getActivity())
			.setTitle("Add to which wishlist?")
			.setView(v)
			.setSingleChoiceItems(cursor, -1, "name", new DialogInterface.OnClickListener() {
				
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	   cursor.moveToPosition(id);
	            	   Wishlist wishlist = cursor.getWishlist();
	            	   wishlistId = wishlist.getId();
	            	   wishlistName = wishlist.getName();
	               }
			})
			.setNegativeButton(android.R.string.cancel, null)
			.setPositiveButton(android.R.string.ok,  new DialogInterface.OnClickListener() {
				
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	   
	            	   if (wishlistId == -1) {
	            		   Toast.makeText(getActivity(), "Please select a wishlist!", 
		   						   Toast.LENGTH_SHORT).show();
		   				   return;
	            	   }
	            	   
	            	   String input = quantityInput.getText().toString();
	            	   
	            	   if (input.equals("") || input.equals("0")) {
		   				   Toast.makeText(getActivity(), "Please put a quantity!", 
		   						   Toast.LENGTH_SHORT).show();
		   				   return;
	            	   }
	            	   
	            	   int quantity = Integer.parseInt(input);
	            	   
	         		   if (quantity > 99) {
	         			   quantity = 99;
	         		   }
		            		   
		            	   
	            	   Bundle args = getArguments();
	            	   DataManager.get(getActivity()).queryAddWishlistData(
	            			   wishlistId,
	            			   args.getLong(ARG_WISHLIST_DATA_ID),
	            			   quantity);
	            	   
	   				   Toast.makeText(getActivity(), 
	   						   "Added to '" + wishlistName + "' wishlist", 
	   						   Toast.LENGTH_SHORT).show();
	   			 	}
			})
			.create();
	}
}
