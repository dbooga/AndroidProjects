package com.daviancorp.android.data;

/*
 * Note: No constructor with fields
 * Note: Add skills
 */
public class Decoration extends Item{

	private int num_slots;
	private String skill_1;
	private int skill_1_point;
	private String skill_2;
	private int skill_2_point;
	
	public Decoration() {
		super();
		
		this.num_slots = -1;
		skill_1 = "";
		skill_1_point = -1;
		skill_2 = "";
		skill_2_point = -1;
	}

	public int getNumSlots() {
		return num_slots;
	}

	public void setNumSlots(int num_slots) {
		this.num_slots = num_slots;
	}

	public String getSkill1() {
		return skill_1;
	}

	public void setSkill1(String skill_1) {
		this.skill_1 = skill_1;
	}

	public int getSkill1Point() {
		return skill_1_point;
	}

	public void setSkill1Point(int skill_1_point) {
		this.skill_1_point = skill_1_point;
	}

	public String getSkill2() {
		return skill_2;
	}

	public void setSkill2(String skill_2) {
		this.skill_2 = skill_2;
	}

	public int getSkill2Point() {
		return skill_2_point;
	}

	public void setSkill2Point(int skill_2_point) {
		this.skill_2_point = skill_2_point;
	}
	
	
}
