package com.ritchey.simple;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ritchey.simple.Service.GreetingService;
import com.ritchey.simple.domain.powercampus.AcademicCalendar;

@SpringBootTest
class SimpleApplicationTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleApplicationTests.class);

	
	@Autowired
	private GreetingService service;

	/**
	 * mvn test -Dtest=SimpleApplicationTests#contexLoads
	 * @throws Exception
	 */
	@Test
	public void contexLoads() throws Exception {
		assertThat(service).isNotNull();
		List<AcademicCalendar> terms = service.getCurrentTerms();
		LOGGER.info("Current terms count = " + terms.size());
		for (AcademicCalendar ac: terms) {
			LOGGER.info("term: " + ac);
		}
	}

}
