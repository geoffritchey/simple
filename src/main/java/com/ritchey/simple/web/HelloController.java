package com.ritchey.simple.web;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ritchey.simple.Service.Greeting;
import com.ritchey.simple.Service.GreetingService;
import com.ritchey.simple.Service.ListCount;

@RestController
public class HelloController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);
	
	@Autowired
	GreetingService service;
	
	@RequestMapping("/rest")
	public Greeting greeting(@RequestParam(value = "campusid", defaultValue = "000000000") String campusId
	) {
		
		Greeting ret = new Greeting();
		Map<String, Date> term = service.getStartTerm();

		ListCount<Map> present = service.getPresent(campusId, term, 0, 100);
		ret.setPresent(present);
		
		ListCount<Map> tardies = service.getTardies(campusId, term, 0, 100);
		ret.setTardy(tardies);
		return ret;
	}
}
