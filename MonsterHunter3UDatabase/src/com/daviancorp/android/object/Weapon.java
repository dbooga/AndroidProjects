package com.daviancorp.android.object;

/*
 * Note: No constructor with fields
 * Note: Add skills
 */
public class Weapon extends Item{

	private String wtype;
	private int creation_cost;
	private int upgrade_cost;
	private int attack;
	private int max_attack;
	private String elemental_attack;
	private String awakened_elemental_attack;
	private int defense;
	private String sharpness;
	private int affinity;
	private String horn_notes;
	private String shelling_type;
	private String charge_levels;
	private String allowed_coatings;
	private String recoil;
	private String reload_speed;
	private String rapid_fire;
	private String normal_shots;
	private String status_shots;
	private String elemental_shots;
	private String tool_shots;
	private int num_slots;
	private String sharpness_file;
	
	public Weapon() {
		super();
		
		this.wtype = "";
		this.creation_cost = -1;
		this.upgrade_cost = -1;
		this.attack = -1;
		this.max_attack = -1;
		this.elemental_attack = "";
		this.awakened_elemental_attack = "";
		this.defense = -1;
		this.sharpness = "";
		this.affinity = -1;
		this.horn_notes = "";
		this.shelling_type = "";
		this.charge_levels = "";
		this.allowed_coatings = "";
		this.recoil = "";
		this.reload_speed = "";
		this.rapid_fire = "";
		this.normal_shots = "";
		this.status_shots = "";
		this.elemental_shots = "";
		this.tool_shots = "";
		this.num_slots = -1;
		this.sharpness_file = "";
	}

	public String getWtype() {
		return wtype;
	}

	public void setWtype(String wtype) {
		this.wtype = wtype;
	}

	public int getCreationCost() {
		return creation_cost;
	}

	public void setCreationCost(int creation_cost) {
		this.creation_cost = creation_cost;
	}

	public int getUpgradeCost() {
		return upgrade_cost;
	}

	public void setUpgradeCost(int upgrade_cost) {
		this.upgrade_cost = upgrade_cost;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getMaxAttack() {
		return max_attack;
	}

	public void setMaxAttack(int max_attack) {
		this.max_attack = max_attack;
	}

	public String getElementalAttack() {
		return elemental_attack;
	}

	public void setElementalAttack(String elemental_attack) {
		this.elemental_attack = elemental_attack;
	}

	public String getAwakenedElementalAttack() {
		return awakened_elemental_attack;
	}

	public void setAwakenedElementalAttack(String awakened_elemental_attack) {
		this.awakened_elemental_attack = awakened_elemental_attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public String getSharpness() {
		return sharpness;
	}

	public void setSharpness(String sharpness) {
		this.sharpness = sharpness;
	}

	public int getAffinity() {
		return affinity;
	}

	public void setAffinity(int affinity) {
		this.affinity = affinity;
	}

	public String getHornNotes() {
		return horn_notes;
	}

	public void setHornNotes(String horn_notes) {
		this.horn_notes = horn_notes;
	}

	public String getShellingType() {
		return shelling_type;
	}

	public void setShellingType(String shelling_type) {
		this.shelling_type = shelling_type;
	}

	public String getChargeLevels() {
		return charge_levels;
	}

	public void setChargeLevels(String charge_levels) {
		this.charge_levels = charge_levels;
	}

	public String getAllowedCoatings() {
		return allowed_coatings;
	}

	public void setAllowedCoatings(String allowed_coatings) {
		this.allowed_coatings = allowed_coatings;
	}

	public String getRecoil() {
		return recoil;
	}

	public void setRecoil(String recoil) {
		this.recoil = recoil;
	}

	public String getReloadSpeed() {
		return reload_speed;
	}

	public void setReloadSpeed(String reload_speed) {
		this.reload_speed = reload_speed;
	}

	public String getRapidFire() {
		return rapid_fire;
	}

	public void setRapidFire(String rapid_fire) {
		this.rapid_fire = rapid_fire;
	}

	public String getNormalShots() {
		return normal_shots;
	}

	public void setNormalShots(String normal_shots) {
		this.normal_shots = normal_shots;
	}

	public String getStatusShots() {
		return status_shots;
	}

	public void setStatusShots(String status_shots) {
		this.status_shots = status_shots;
	}

	public String getElementalShots() {
		return elemental_shots;
	}

	public void setElementalShots(String elemental_shots) {
		this.elemental_shots = elemental_shots;
	}

	public String getToolShots() {
		return tool_shots;
	}

	public void setToolShots(String tool_shots) {
		this.tool_shots = tool_shots;
	}

	public int getNumSlots() {
		return num_slots;
	}

	public void setNumSlots(int num_slots) {
		this.num_slots = num_slots;
	}
	
	public String getSharpnessFile() {
		return sharpness_file;
	}
	
	public void setSharpnessFile(String sharpness_file) {
		this.sharpness_file = sharpness_file;
	}
}
