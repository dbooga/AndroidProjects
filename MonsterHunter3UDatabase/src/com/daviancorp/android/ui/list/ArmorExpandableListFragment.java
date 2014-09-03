package com.daviancorp.android.ui.list;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.daviancorp.android.data.database.DataManager;
import com.daviancorp.android.data.object.Armor;
import com.daviancorp.android.data.object.Item;
import com.daviancorp.android.monsterhunter3udatabase.R;

/**
 * Pieced together from: Android samples:
 * com.example.android.apis.view.ExpandableList1
 * http://androidword.blogspot.com/2012/01/how-to-use-expandablelistview.html
 * http://stackoverflow.com/questions/6938560/android-fragments-setcontentview-
 * alternative
 * http://stackoverflow.com/questions/6495898/findviewbyid-in-fragment-android
 */
public class ArmorExpandableListFragment extends Fragment {
	private String mType;
	private static final String ARG_TYPE = "ARMOR_TYPE";
	private ArrayList<Armor> armors;

	private String[] slots = { "Head", "Body", "Arms", "Waist", "Legs" };

	private ArrayList<ArrayList<Armor>> children;

	public static ArmorExpandableListFragment newInstance(String type) {
		Bundle args = new Bundle();
		args.putString(ARG_TYPE, type);
		ArmorExpandableListFragment f = new ArmorExpandableListFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mType = null;
		Bundle args = getArguments();
		if (args != null) {
			mType = args.getString(ARG_TYPE);
		}
		populateList();

	}

	private void populateList() {
		children = new ArrayList<ArrayList<Armor>>();
		armors = DataManager.get(getActivity()).queryArmorArrayType(mType);

		ArrayList<Armor> g1 = new ArrayList<Armor>();
		ArrayList<Armor> g2 = new ArrayList<Armor>();
		ArrayList<Armor> g3 = new ArrayList<Armor>();
		ArrayList<Armor> g4 = new ArrayList<Armor>();
		ArrayList<Armor> g5 = new ArrayList<Armor>();

		for (int i = 0; i < armors.size(); i++) {
			Log.d("armor", "Type = " + armors.get(i).getSlot() + " "
					+ armors.get(i).getName());
			switch (armors.get(i).getSlot()) {

			case "Head":
				g1.add(armors.get(i));
				break;
			case "Body":
				g2.add(armors.get(i));
				break;
			case "Arms":
				g3.add(armors.get(i));
				break;
			case "Waist":
				g4.add(armors.get(i));
				break;
			case "Legs":
				g5.add(armors.get(i));
				break;
			default:
				break;
			}
		}
		children.add(g1);
		children.add(g2);
		children.add(g3);
		children.add(g4);
		children.add(g5);

		Log.d("armor", "size of g1 = " + g1.size());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		View v = inflater.inflate(R.layout.fragment_armor_expandablelist, null);
		ExpandableListView elv = (ExpandableListView) v
				.findViewById(R.id.expandableListView);

		elv.setAdapter(new ArmorListAdapter(slots));
		return v;
	}

	public class ArmorListAdapter extends BaseExpandableListAdapter {

		private String[] armors;

		public ArmorListAdapter(String[] armors) {
			super();
			this.armors = armors;

		}

		@Override
		public int getGroupCount() {
			return armors.length;
		}

		@Override
		public int getChildrenCount(int i) {
			return children.get(i).size();
		}

		@Override
		public Object getGroup(int i) {
			return armors[i];
		}

		@Override
		public Object getChild(int i, int i1) {
			return children.get(i).get(i1);
		}

		@Override
		public long getGroupId(int i) {
			return i;
		}

		@Override
		public long getChildId(int i, int i1) {
			return i1;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public View getGroupView(int i, boolean b, View view,
				ViewGroup viewGroup) {
			TextView textView = new TextView(
					ArmorExpandableListFragment.this.getActivity());
			textView.setText(getGroup(i).toString());
			return textView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {

//			View v = convertView;
//			Context context = parent.getContext();
//			LayoutInflater inflater = (LayoutInflater) context
//					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			v = inflater.inflate(android.R.layout.activity_list_item, parent,
//					false);

//			TextView armorTextView = (TextView) v
//					.findViewById(android.R.id.text1);
//			ImageView armorImageView = (ImageView) v
//					.findViewById(android.R.id.icon);

//			armorTextView.setText(getChild(groupPosition, childPosition)
//					.toString());

//			String slot = ((Armor) getChild(groupPosition, childPosition))
//					.getSlot();
//
//			String cellImage = "icons_armor/icons_"
//					+ slot.toLowerCase()
//					+ "/"
//					+ slot.toLowerCase()
//					+ ((Item) getChild(groupPosition, childPosition))
//							.getRarity() + ".png";
//
//			Drawable armorImage = null;
//
//			try {
//				armorImage = Drawable.createFromStream(context.getAssets()
//						.open(cellImage), null);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			armorImageView.setImageDrawable(armorImage);
			
//			return v;
			
			TextView textView = new TextView(
					ArmorExpandableListFragment.this.getActivity());
			textView.setText(getChild(groupPosition, childPosition).toString());
			return textView;


		}

		@Override
		public boolean isChildSelectable(int i, int i1) {
			return true;
		}

	}

}