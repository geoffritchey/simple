package com.ritchey.simple.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Student {
	List<Map> presentDates = new ArrayList<Map>();
	List<Map> tardyDates = new ArrayList<Map>();
	Double total;
	Integer goal;
	Double present;
	Double tardy;
	
	public List<Map> getPresentDates() {
		return presentDates;
	}

	public void setPresentDates(List<Map> presentDates) {
		this.presentDates = presentDates;
	}

	public List<Map> getTardyDates() {
		return tardyDates;
	}

	public void setTardyDates(List<Map> tardyDates) {
		this.tardyDates = tardyDates;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getGoal() {
		return goal;
	}

	public void setGoal(Integer goal) {
		this.goal = goal;
	}

	public Double getPresent() {
		return present;
	}

	public void setPresent(Double present) {
		this.present = present;
	}

	public Double getTardy() {
		return tardy;
	}

	public void setTardy(Double tardy) {
		this.tardy = tardy;
	}

	/**
	 * 
	 */
	public Student() {
		
	}

	/**
	 * @param presentDates
	 * @param tardyDates
	 * @param total
	 * @param goal
	 * @param present
	 * @param tardy
	 */
	public Student(List<Map> presentDates, List<Map> tardyDates, Double total, Integer goal, Double present,
			Double tardy) {
		super();
		this.presentDates = presentDates;
		this.tardyDates = tardyDates;
		this.total = total;
		this.goal = goal;
		this.present = present;
		this.tardy = tardy;
	}
	
}
