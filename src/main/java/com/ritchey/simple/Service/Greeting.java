package com.ritchey.simple.Service;

import java.util.Map;

public class Greeting {
	
	ListCount<Map> present;
	ListCount<Map> tardies;


	public ListCount<Map> getPresent() {
		return present;
	}

	public void setPresent(ListCount<Map> present) {
		this.present = present;
	}

	public void setTardy(ListCount<Map> tardies) {
		this.tardies = tardies;
	}
	
	public ListCount<Map> getTardies() {
		return tardies;
	}

	public Double getPresentSum() {
		double presentSum = 0D;
		for (Map x: present.getData()) {
			presentSum += (Double) x.get("value");
		}
		return presentSum;
	}

	public Double getTardySum() {
			double tardiesSum = 0D;
			for (Map x: tardies.getData()) {
				tardiesSum += (Double) x.get("value");
			}
			return tardiesSum;
	}

}
