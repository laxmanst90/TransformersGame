package com.transformers.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

public class Transformers implements Comparable<Transformers>{

	
	private int transformerId;
	private int strength;
	private int intelligence;
	private int speed;
	private int endurance;
	private int rank;
	private int courage;
	private int firepower;
	private int skill;
	private int overallRating;
	private String type;
	private String name;
	private boolean fightResult;
	private String survivor;
	private List<Transformers> winningTeamList;
	private int numberOfBattles;
	
	public Transformers(){
		
	}
	
	public Transformers(String survivor,ArrayList<Transformers> winningTeamList, int numberOfBattles ){
		this.survivor = survivor;
		this.winningTeamList = winningTeamList;
		this.numberOfBattles = numberOfBattles;
	}
	
	
	public Transformers(int transformerId,int strength, int intelligence,int speed, int endurance, int rank, int courage, int firepower, int skill,String type,String name,boolean fightResult){
		this.transformerId = transformerId;
		this.strength = strength;
		this.intelligence = intelligence;
		this.speed = speed;
		this.endurance = endurance;
		this.rank = rank;
		this.courage = courage;
		this.firepower = firepower;
		this.skill = skill;
		this.type = type;
		this.overallRating = strength + intelligence + speed + endurance + firepower;
		this.name = name;
		this.fightResult = fightResult;
	}
	
	
	
	public boolean isFightResult() {
		return fightResult;
	}

	public void setFightResult(boolean fightResult) {
		this.fightResult = fightResult;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTransformerId() {
		return transformerId;
	}
	public void setTransformerId(int transformerId) {
		this.transformerId = transformerId;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getEndurance() {
		return endurance;
	}
	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getCourage() {
		return courage;
	}
	public void setCourage(int courage) {
		this.courage = courage;
	}
	public int getFirepower() {
		return firepower;
	}
	public void setFirepower(int firepower) {
		this.firepower = firepower;
	}
	public int getSkill() {
		return skill;
	}
	public void setSkill(int skill) {
		this.skill = skill;
	}
	public int getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(int overallRating) {
		this.overallRating = overallRating;
	}

	@Override
	public int compareTo(Transformers o) {
		System.out.println("I am sorting with current obj as : "+this.rank + " and transformer rank as : "+o.rank);
			return this.rank - o.rank;
	}
	
}
