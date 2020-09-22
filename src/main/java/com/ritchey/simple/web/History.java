package com.ritchey.simple.web;

import java.util.List;
import java.util.Map;

public class History {
	private List<Map> presentDates;
	private Double presentSum;
	private List<Map> tardyDates;
	private Double tardySum;
	
	private Integer goal;

	public List<Map> getPresentDates() {
		return presentDates;
	}

	public void setPresentDates(List<Map> presentDates) {
		this.presentDates = presentDates;
	}

	public Double getPresentSum() {
		return presentSum;
	}

	public void setPresentSum(Double presentSum) {
		this.presentSum = presentSum;
	}

	public List<Map> getTardyDates() {
		return tardyDates;
	}

	public void setTardyDates(List<Map> tardyDates) {
		this.tardyDates = tardyDates;
	}

	public Double getTardySum() {
		return tardySum;
	}

	public void setTardySum(Double tardySum) {
		this.tardySum = tardySum;
	}

	public Integer getGoal() {
		return goal;
	}

	public void setGoal(Integer goal) {
		this.goal = goal;
	}
	
	
}
