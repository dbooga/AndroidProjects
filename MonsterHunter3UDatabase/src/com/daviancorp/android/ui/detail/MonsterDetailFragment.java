package com.daviancorp.android.ui.detail;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daviancorp.android.data.object.Monster;
import com.daviancorp.android.loader.MonsterLoader;
import com.daviancorp.android.monsterhunter3udatabase.R;

public class MonsterDetailFragment extends Fragment {
	private static final String ARG_MONSTER_ID = "MONSTER_ID";
	private static final int LOAD_MONSTER = 1;
	
	private Monster mMonster;
	
	private TextView mMonsterLabelTextView;
	private ImageView mMonsterIconImageView;

	public static MonsterDetailFragment newInstance(long monsterId) {
		Bundle args = new Bundle();
		args.putLong(ARG_MONSTER_ID, monsterId);
		MonsterDetailFragment f = new MonsterDetailFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRetainInstance(true);
		
		// Check for a Monster ID as an argument, and find the monster
		Bundle args = getArguments();
		if (args != null) {
			long monsterId = args.getLong(ARG_MONSTER_ID, -1);
			if (monsterId != -1) {
				LoaderManager lm = getLoaderManager();
				lm.initLoader(LOAD_MONSTER, args, new MonsterLoaderCallbacks());
			}
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_monster_detail, container, false);
		
		mMonsterLabelTextView = (TextView) view.findViewById(R.id.detail_monster_label);
		mMonsterIconImageView = (ImageView) view.findViewById(R.id.detail_monster_image);
	
		return view;
	}
	
	private void updateUI() {
		String cellText = mMonster.getName();
		String cellImage = "icons_monster/" + mMonster.getFileLocation();
		
		mMonsterLabelTextView.setText(cellText);
		
		// Read a Bitmap from Assets
        AssetManager manager = getActivity().getAssets();
        try {
            InputStream open = manager.open(cellImage);
            Bitmap bitmap = BitmapFactory.decodeStream(open);
            // Assign the bitmap to an ImageView in this layout
            mMonsterIconImageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
	private class MonsterLoaderCallbacks implements LoaderCallbacks<Monster> {
		
		@Override
		public Loader<Monster> onCreateLoader(int id, Bundle args) {
			return new MonsterLoader(getActivity(), args.getLong(ARG_MONSTER_ID));
		}
		
		@Override
		public void onLoadFinished(Loader<Monster> loader, Monster run) {
			mMonster = run;
			updateUI();
		}
		
		@Override
		public void onLoaderReset(Loader<Monster> loader) {
			// Do nothing
		}
	}
}
