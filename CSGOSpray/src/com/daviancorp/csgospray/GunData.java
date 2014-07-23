package com.daviancorp.csgospray;

import java.util.ArrayList;

public final class GunData {
	private static GunData instance = null;
	private ArrayList<Gun> data = null;
	
	private GunData() {
		data = new ArrayList<Gun>();
		
		data.add(new Gun("P2000","p_p2000.gif","c_p2000.gif","n_p2000.gif",R.drawable.p2000));
		data.add(new Gun("USP-S","p_usps.gif","c_usps.gif","n_usps.gif",R.drawable.usps));
		data.add(new Gun("Glock-18","p_glock.gif","c_glock.gif","n_glock.gif",R.drawable.glock));
		data.add(new Gun("P250","p_p250.gif","c_p250.gif","n_p250.gif",R.drawable.p250));
		data.add(new Gun("CZ75-Auto","p_cz75a.gif","c_cz75a.gif","n_cz75a.gif",R.drawable.cz75a));
		data.add(new Gun("Tec-9","p_tec9.gif","c_tec9.gif","n_tec9.gif",R.drawable.tec9));
		data.add(new Gun("Dual Berettas","p_dualberettas.gif","c_dualberettas.gif","n_dualberettas.gif",R.drawable.dualberettas));
		data.add(new Gun("Five-Seven","p_fiveseven.gif","c_fiveseven.gif","n_fiveseven.gif",R.drawable.fiveseven));
		data.add(new Gun("Desert-Eagle","p_deagle.gif","c_deagle.gif","n_deagle.gif",R.drawable.deagle));
		
		data.add(new Gun("Sawed-Off","p_sawedoff.gif","c_sawedoff.gif","n_sawedoff.gif",R.drawable.sawedoff));
		data.add(new Gun("MAG-7","p_mag7.gif","c_mag7.gif","n_mag7.gif",R.drawable.mag7));
		data.add(new Gun("XM1014","p_xm1014.gif","c_xm1014.gif","n_xm1014.gif",R.drawable.xm1014));
		data.add(new Gun("Nova","p_nova.gif","c_nova.gif","n_nova.gif",R.drawable.nova));
		data.add(new Gun("M249","p_m249.gif","c_m249.gif","n_m249.gif",R.drawable.m249));
		data.add(new Gun("Negev","p_negev.gif","c_negev.gif","n_negev.gif",R.drawable.negev));
		
		data.add(new Gun("MAC-10","p_mac10.gif","c_mac10.gif","n_mac10.gif",R.drawable.mac10));
		data.add(new Gun("MP7","p_mp7.gif","c_mp7.gif","n_mp7.gif",R.drawable.mp7));
		data.add(new Gun("MP9","p_mp9.gif","c_mp9.gif","n_mp9.gif",R.drawable.mp9));
		data.add(new Gun("PP-Bizon","p_bizon.gif","c_bizon.gif","n_bizon.gif",R.drawable.bizon));
		data.add(new Gun("UMP-45","p_ump45.gif","c_ump45.gif","n_ump45.gif",R.drawable.ump45));
		data.add(new Gun("P90","p_p90.gif","c_p90.gif","n_p90.gif",R.drawable.p90));
		
		data.add(new Gun("Galil-AR","p_galil.gif","c_galil.gif","n_galil.gif",R.drawable.galil));
		data.add(new Gun("Famas","p_famas.gif","c_famas.gif","n_famas.gif",R.drawable.famas));
		data.add(new Gun("AK-47","p_ak47.gif","c_ak47.gif","n_ak47.gif",R.drawable.ak47));
		data.add(new Gun("M4A1-S","p_m4a1s.gif","c_m4a1s.gif","n_m4a1s.gif",R.drawable.m4a1s));
		data.add(new Gun("M4A4","p_m4a4.gif","c_m4a4.gif","n_m4a4.gif",R.drawable.m4a4));
		data.add(new Gun("AUG","p_aug.gif","c_aug.gif","n_aug.gif",R.drawable.aug));
		data.add(new Gun("SG 553","p_sg553.gif","c_sg553.gif","n_sg553.gif",R.drawable.sg553));
		data.add(new Gun("SSG 08","p_ssg08.gif","c_ssg08.gif","n_ssg08.gif",R.drawable.ssg08));
		data.add(new Gun("AWP","p_awp.gif","c_awp.gif","n_awp.gif",R.drawable.awp));
		data.add(new Gun("G3SG1","p_g3sg1.gif","c_g3sg1.gif","n_g3sg1.gif",R.drawable.g3sg1));
		data.add(new Gun("Scar-20","p_scar20.gif","c_scar20.gif","n_scar20.gif",R.drawable.scar20));
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
