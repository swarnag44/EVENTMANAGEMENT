package com.cg.pm.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Event implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private LocalDate datescheduled;
	private String location;
	private double cost;
	
	public Event() {
		
	}
	
	public Event(String id, String title,LocalDate datescheduled, String location, double cost) {
		super();
		this.id = id;
		this.title = title;
		this.datescheduled = datescheduled;
		this.location = location;
		this.cost = cost;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public LocalDate getDatescheduled() {
		return datescheduled;
	}


	public void setDatescheduled(LocalDate datescheduled) {
		this.datescheduled = datescheduled;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public double getCost() {
		return cost;
	}


	public void setCost(double d) {
		this.cost = d;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder output=new StringBuilder("id:");
		output.append(id);
		output.append("\tTitle:");
		output.append(title);
		output.append("\tdatescheduled:");
		output.append(datescheduled);
		output.append("\tlocation:");
		output.append(location);
		output.append("\tCost:");
		output.append(cost);
		return output.toString();
	}

}
