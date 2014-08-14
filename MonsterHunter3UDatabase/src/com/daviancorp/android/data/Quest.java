package com.daviancorp.android.data;

public class Quest {
	
	private long id;
	private String name;
	private String goal;
	private String hub;
	private String type;
	private String stars;
	private int location_id;
	private int time_limit;
	private int fee;
	private int reward;
	private int hrp;
	
	public Quest() {
		this.id = -1;
		this.name = "";
		this.goal = "";
		this.hub = "";
		this.type = "";
		this.stars = "";
		this.location_id = -1;
		this.time_limit = -1;
		this.fee = -1;
		this.reward = -1;
		this.hrp = -1;
	}

	public Quest(long id, String name, String goal, String hub, String type,
			String stars, int location_id, int time_limit, int fee, int reward,
			int hrp) {
		this.id = id;
		this.name = name;
		this.goal = goal;
		this.hub = hub;
		this.type = type;
		this.stars = stars;
		this.location_id = location_id;
		this.time_limit = time_limit;
		this.fee = fee;
		this.reward = reward;
		this.hrp = hrp;
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

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getHub() {
		return hub;
	}

	public void setHub(String hub) {
		this.hub = hub;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public int getLocationId() {
		return location_id;
	}

	public void setLocationId(int location_id) {
		this.location_id = location_id;
	}

	public int getTimeLimit() {
		return time_limit;
	}

	public void setTimeLimit(int time_limit) {
		this.time_limit = time_limit;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public int getHrp() {
		return hrp;
	}

	public void setHrp(int hrp) {
		this.hrp = hrp;
	}
	
	
	
}
