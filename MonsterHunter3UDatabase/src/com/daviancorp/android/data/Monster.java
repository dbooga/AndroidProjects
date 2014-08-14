package com.daviancorp.android.data;

public class Monster {

	private long id;
	private String name;
	private String monsterClass;
	private String trait;
	
	public Monster() {
		id = -1;
		name = "";
		trait = "";
	}
	
	public Monster(long id, String name, String monsterClass, String trait) {
		this.id = id;
		this.name = name;
		this.monsterClass = monsterClass;
		this.trait = trait;
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

	public String getMonsterClass() {
		return monsterClass;
	}

	public void setMonsterClass(String monsterClass) {
		this.monsterClass = monsterClass;
	}

	public String getTrait() {
		return trait;
	}

	public void setTrait(String trait) {
		this.trait = trait;
	}
	
}
