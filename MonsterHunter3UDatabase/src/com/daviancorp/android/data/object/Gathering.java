package com.daviancorp.android.data.object;

public class Gathering {

	private long id;
	private Item item;
	private Location location;
	private String area;
	private String site;
	private int site_set;
	private int site_set_percentage;
	private int site_set_gathers_min;
	private int site_set_gathers_max;
	private String rank;
	private int percentage;
	
	public Gathering() {
		this.id = -1;
		this.item = null;
		this.location = null;
		this.area = "";
		this.site = "";
		this.site_set = -1;
		this.site_set_percentage = -1;
		this.site_set_gathers_min = -1;
		this.site_set_gathers_max = -1;
		this.rank = "";
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public int getSiteSet() {
		return site_set;
	}

	public void setSiteSet(int site_set) {
		this.site_set = site_set;
	}

	public int getSiteSetPercentage() {
		return site_set_percentage;
	}

	public void setSiteSetPercentage(int site_set_percentage) {
		this.site_set_percentage = site_set_percentage;
	}

	public int getSiteSetGathersMin() {
		return site_set_gathers_min;
	}

	public void setSiteSetGathersMin(int site_set_gathers_min) {
		this.site_set_gathers_min = site_set_gathers_min;
	}

	public int getSiteSetGathersMax() {
		return site_set_gathers_max;
	}

	public void setSiteSetGathersMax(int site_set_gathers_max) {
		this.site_set_gathers_max = site_set_gathers_max;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
}
