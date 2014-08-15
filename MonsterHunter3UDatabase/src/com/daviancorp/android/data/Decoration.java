package com.daviancorp.android.data;

/*
 * Note: No constructor with fields
 * Note: Add skills
 */
public class Decoration{

	private long id;
	private String name;
	private int num_slots;
	private int price;
	private String skill_1;
	private int skill_1_point;
	private String skill_2;
	private int skill_2_point;
	
	public Decoration() {
		
		this.id = -1;
		this.name = "";
		this.num_slots = -1;
		this.skill_1 = "";
		this.skill_1_point = -1;
		this.skill_2 = "";
		this.skill_2_point = -1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
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
