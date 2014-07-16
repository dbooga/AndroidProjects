package com.daviancorp.csgospray;

import java.util.ArrayList;

public final class GunData {
	private static GunData instance = null;
	private ArrayList<Gun> data = null;
	
	private GunData() {
		data = new ArrayList<Gun>();
		
		data.add(new Gun("P2000","p_p2000.gif","c_p2000.gif","n_p2000.gif",R.drawable.p2000));
		data.add(new Gun("USP-S","p_usps.gif","c_usps.gif","n_usps.gif",R.drawable.usps));
		data.add(new Gun("Glock","p_glock.gif","c_glock.gif","n_glock.gif",R.drawable.glock));
		data.add(new Gun("P250","p_p250.gif","c_p250.gif","n_p250.gif",R.drawable.p250));
		data.add(new Gun("CZ75A","p_cz75a.gif","c_cz75a.gif","n_cz75a.gif",R.drawable.cz75a));
		data.add(new Gun("Tec-9","p_tec9.gif","c_tec9.gif","n_tec9.gif",R.drawable.tec9));
		data.add(new Gun("Dual Berettas","p_dualberettas.gif","c_dualberettas.gif","n_dualberettas.gif",R.drawable.dualberettas));
		data.add(new Gun("Five-Seven","p_fiveseven.gif","c_fiveseven.gif","n_fiveseven.gif",R.drawable.fiveseven));
		data.add(new Gun("Deagle","p_deagle.gif","c_deagle.gif","n_deagle.gif",R.drawable.deagle));
	}

	public static GunData getInstance() {
		if (instance == null) {
			instance = new GunData();
		}
		return instance;
	}

	public ArrayList<Gun> getData() {
		return data;
	}

}
