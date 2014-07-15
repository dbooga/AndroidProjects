package com.daviancorp.csgospray;

import java.util.ArrayList;

public final class GunData {
	private static GunData instance = null;
	private ArrayList<Gun> data = null;

	private GunData() {
		data.add(new Gun("p90","p_p90.gif","c_p90.gif","n_p90.gif",R.drawable.p90));
	}

	public static GunData getInstance() {
		if (instance == null) {
			instance = new GunData();
		}
		return instance;
	}
	
	public Gun getGun(int position){
		return data.get(position);
	}

}
