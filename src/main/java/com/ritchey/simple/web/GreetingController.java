package com.ritchey.simple.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ritchey.simple.Service.GreetingService;
import com.ritchey.simple.Service.ListCount;
import com.ritchey.simple.domain.chapel.ChapelPerson;

@Controller
@Scope(value="session")
public class GreetingController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);
	
	
	Map<String, Date> term = null;
	String fullname = null;
	String campusId = null;
	
	@Autowired
	GreetingService service;
	
	@GetMapping("/greeting")
	public String greeting(HttpServletRequest request
			, @RequestParam(name="peopleid", required=false, defaultValue="World") String peopleId
			, Model model) {

		int presentOffset = 0;
		int tardyOffset = 0;
		int limit = 10;
		
		Boolean load = campusId != null && "World".equals(peopleId);

		LOGGER.info("campusId = " + campusId);
		LOGGER.info("peopleId = " + peopleId);	
		LOGGER.info("load = " + load);	
		if (!load) {
			if ("World".equals(peopleId)) {
				return "hello";
			}
			else {
				ChapelPerson person = service.getChapelPerson(peopleId);
				
				campusId = peopleId;
				if (person == null) {
					return "hello";
				}
				fullname = person.getFirstname() + " " + person.getLastname();
			}
		}
		
		if (term == null) 
			term = service.getStartTerm();
		
		LOGGER.info("fullname = " + fullname);
		LOGGER.info("term = " + term);
		List<Map> presentDates = new ArrayList<Map>();
		List<Map> tardyDates = new ArrayList<Map>();
		int presentCount = 0;
		int tardyCount = 0;
		Integer goal = 0;
		
		if (load) {
			ListCount<Map> present = service.getPresent(campusId, term, presentOffset, limit);
			LOGGER.info("present = " + present);
			LOGGER.info("present = " + present.getCount());
			for (Map x: present.getData()) {
				LOGGER.info("x = " + x);
			}
			
			ListCount<Map> tardy = service.getTardies(campusId, term, tardyOffset, limit);
			LOGGER.info("tardy = " + tardy.getCount());
			for (Map x: tardy.getData()) {
				LOGGER.info("x = " + x);
			}
			
			presentDates = present.getData();
			tardyDates = tardy.getData();
			
			presentCount = present.getCount();
			tardyCount = tardy.getCount();
			
			Map env = present.getEnv();
			goal = (Integer) env.get("goal");
		}
		model.addAttribute("present_dates", presentDates);
		model.addAttribute("tardy_dates", tardyDates);
		
		model.addAttribute("total",presentCount+tardyCount);
		model.addAttribute("goal", goal);
		model.addAttribute("present", presentCount);
		model.addAttribute("tardy", tardyCount);
		model.addAttribute("fullname", fullname);
		
		
		return "greeting";
	}


}
