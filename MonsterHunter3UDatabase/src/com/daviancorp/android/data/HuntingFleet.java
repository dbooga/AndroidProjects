package com.daviancorp.android.data;

public class HuntingFleet {
	
	private long id;
	private String type;
	private int level;
	private String location;
	
	public HuntingFleet() {
		this.id = -1;
		this.type = "";
		this.level = -1;
		this.location = "";
	}
	
	public HuntingFleet(long id, String type, int level,
			String location) {
		this.id = id;
		this.type = type;
		this.level = level;
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
