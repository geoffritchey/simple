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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ritchey.ldap.LdapUserDetails;
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
	
	Double presentSum = 0D;
	Double tardySum = 0D;
	Integer goal = 0;
	

	int presentOffset = 0;
	int tardyOffset = 0;
	int limit = 10;

	
	@Autowired
	GreetingService service;
	
	@GetMapping("/greeting")
	public String create(HttpServletRequest request
			, @RequestParam(name="peopleid", required=false, defaultValue="") String peopleId
			, Model model) {

		LOGGER.debug("people_id = " + peopleId);
		if (!peopleId.matches("P?[0-9]{9}")) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!(authentication instanceof AnonymousAuthenticationToken) && peopleId.equals("")) {
				LOGGER.debug("Not Anonymous");
			    String currentUserName = authentication.getName();
			    LdapUserDetails ldap = ((LdapUserDetails) authentication.getPrincipal());
			    campusId = peopleId = ldap.getEmployeeId();
			    LOGGER.debug("people id = " + peopleId);
			}
			else {
					return "hello";
				}
		}

		ChapelPerson person = service.getChapelPerson(peopleId);
		
		campusId = peopleId;
		if (person == null) {
			return "hello";
		}
		fullname = person.getFirstname() + " " + person.getLastname();
			
		
		if (term == null) 
			term = service.getStartTerm();
		
		LOGGER.info("fullname = " + fullname);
		LOGGER.info("term = " + term);
		List<Map> presentDates = new ArrayList<Map>();
		List<Map> tardyDates = new ArrayList<Map>();


		model.addAttribute("present_dates", presentDates);
		model.addAttribute("tardy_dates", tardyDates);
		
		model.addAttribute("total", 0);
		model.addAttribute("goal", 0);
		model.addAttribute("present", 0);
		model.addAttribute("tardy", 0);
		model.addAttribute("fullname", fullname);
		
		
		return "greeting";
	}

	
	@GetMapping("/greetingload")
	public String load(HttpServletRequest request
			, Model model) {
		ListCount<Map> present = service.getPresent(campusId, term, presentOffset, limit);
		LOGGER.info("present = " + present);
		LOGGER.info("present = " + present.getCount());
		presentSum = 0D;
		for (Map x: present.getData()) {
			LOGGER.info("x = " + x);
			presentSum += (Double) x.get("value");
		}
		
		ListCount<Map> tardy = service.getTardies(campusId, term, tardyOffset, limit);
		LOGGER.info("tardy = " + tardy.getCount());
		tardySum = 0D;
		for (Map x: tardy.getData()) {
			LOGGER.info("x = " + x);
			tardySum += (Double) x.get("value");
		}
		
		List<Map> presentDates = present.getData();
		List<Map> tardyDates = tardy.getData();
	
		
		Map env = present.getEnv();
		goal = (Integer) env.get("goal");
		
		model.addAttribute("present_dates", presentDates);
		model.addAttribute("tardy_dates", tardyDates);
		
		model.addAttribute("total",presentSum+tardySum);
		model.addAttribute("goal", goal);
		model.addAttribute("present", presentSum);
		model.addAttribute("tardy", tardySum);
		model.addAttribute("fullname", fullname);
		
		return "greeting";
	}

}
