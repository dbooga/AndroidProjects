package com.daviancorp.android.data.object;

public class Carve {

	private long id;
	private Item item;
	private Monster monster;
	private String rank;
	private String location;
	private int num_carves;
	private int percentage;
	
	public Carve() {
		this.id = -1;
		this.item = null;
		this.monster = null;
		this.rank = "";
		this.location = "";
		this.num_carves = -1;
		this.percentage = -1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNumCarves() {
		return num_carves;
	}

	public void setNumCarves(int num_carves) {
		this.num_carves = num_carves;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
}
