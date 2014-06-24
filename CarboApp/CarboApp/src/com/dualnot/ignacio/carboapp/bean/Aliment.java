package com.dualnot.ignacio.carboapp.bean;

public class Aliment {
	
	public Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String name="";
	public Integer grams_per_ration = 0;
	public Integer rations_in_picture = 0;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGrams_per_ration() {
		return grams_per_ration;
	}
	public void setGrams_per_ration(Integer grams_per_ration) {
		this.grams_per_ration = grams_per_ration;
	}
	public Integer getRations_in_picture() {
		return rations_in_picture;
	}
	public void setRations_in_picture(Integer rations_in_picture) {
		this.rations_in_picture = rations_in_picture;
	}
	public Aliment(Long id, String name, Integer grams_per_ration,
			Integer rations_in_picture) {
		super();
		this.id = id;
		this.name = name;
		this.grams_per_ration = grams_per_ration;
		this.rations_in_picture = rations_in_picture;
	}
	public Aliment(){}
	
	
	
	

}
