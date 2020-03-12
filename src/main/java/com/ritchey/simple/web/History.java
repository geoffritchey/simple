package com.ritchey.simple.web;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class History {
	private List<Map> presentDates;
	private Double presentSum;
	private List<Map> tardyDates;
	private Double tardySum;
	
	private Integer goal;
}
