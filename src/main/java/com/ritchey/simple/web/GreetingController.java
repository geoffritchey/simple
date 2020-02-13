package com.ritchey.simple.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ritchey.simple.Service.GreetingService;
import com.ritchey.simple.Service.ListCount;
import com.ritchey.simple.domain.chapel.ChapelPerson;

@Controller
public class GreetingController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

	Map<String, Date> term;
	int presentOffset = 0;
	int tardyOffset = 0;
	int limit = 10;
	String fullname;
	String campusId;
	ChapelPerson person;
	
	@Autowired
	GreetingService service;
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="peopleid", required=false, defaultValue="World") String peopleId, Model model) {

		if (term == null || !peopleId.equals(campusId)) 
			term = service.getStartTerm();
		if (person == null || !peopleId.equals(campusId)) {
			person = service.getChapelPerson(peopleId);
		}
		campusId = peopleId;
		fullname = person.getFirstname() + " " + person.getLastname();

		LOGGER.info("fullname = " + fullname);
		LOGGER.info("term = " + term);
		
		ListCount<Map> present = service.getPresent(peopleId, term, presentOffset, limit);
		LOGGER.info("present = " + present.getCount());
		for (Map x: present.getData()) {
			LOGGER.info("x = " + x);
		}
		
		ListCount<Map> tardy = service.getTardies(peopleId, term, tardyOffset, limit);
		LOGGER.info("tardy = " + tardy.getCount());
		for (Map x: tardy.getData()) {
			LOGGER.info("x = " + x);
		}
		
		List<Map> presentDates = present.getData();
		List<Map> tardyDates = tardy.getData();
		
		int presentCount = present.getCount();
		int tardyCount = tardy.getCount();
		
		Map env = present.getEnv();
		
		model.addAttribute("present_dates", presentDates);
		model.addAttribute("tardy_dates", tardyDates);
		
		model.addAttribute("total",presentCount+tardyCount);
		model.addAttribute("goal", env.get("goal"));
		model.addAttribute("present", presentCount);
		model.addAttribute("tardy", tardyCount);
		model.addAttribute("fullname", fullname);
		
		
		return "greeting";
	}


}
