package com.ritchey.simple.mapper.chapel;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ritchey.simple.Service.GreetingService;

@SpringBootTest
class SimpleMapperTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMapperTests.class);
	private static final String conf = "mybatis.conf.xml";
	
	@Autowired
	private GreetingService service;
	
	static private SqlSessionFactoryBuilder builder;
	static private SqlSessionFactory sessionFactory;
	static Reader reader;
	static private SqlSession session;
	static private ScriptRunner runner;
	
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@BeforeAll
	static public void runBeforeAllTests() {
		builder = new SqlSessionFactoryBuilder();
		
		try {
			reader = Resources.getResourceAsReader(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        sessionFactory = builder.build(reader);
         
        session = sessionFactory.openSession();
        runner = new ScriptRunner(session.getConnection());
        runner.setAutoCommit(true);
        runner.setStopOnError(true);
        
	}
	
	@BeforeEach
	public void runBeforeTestMethod() {
		
	}
	
	@AfterEach
	public void runAfterEach() {
		Reader clear = new StringReader("delete from scheduledEvent;");
		runner.runScript(clear);
	}
	
	@AfterAll
	static public void afterAllTests() {
        session.close();
	}
	/**
	 *  mvn test -Dtest=SimpleMapperTests#selectPresent
	 * @throws Exception
	 */
	@Test
	public void selectPresent() throws Exception {
		
		/* Test split session that a person gets allcredit credits even if he's never punched in to chapel.
		 *   split sessions count as 1.  Even though Jan 11 and Jan 12 are marked all-credit, there should be only one all credit award;
		 * preferably on the day the student is scheduled to attend (M or T).  Jan 18 mark should make sure either 18 or 19 is awarded.
		 */
		Reader populate = new StringReader("insert into scheduledEvent(event, starttime, endtime, template, recordTardy, recordLate, allcredit, split, splitByInstructor)\n"
				+ "values (123, '2021-01-11 09:30:00.000', '2021-01-11 11:00:00.000', '577381d0-7957-46c5-9081-54f2096aa738:W1:MT', 0,0, 1,1,0);\n"
				+ "\n"
				+ "insert into scheduledEvent(event, starttime, endtime, template, recordTardy, recordLate, allcredit, split, splitByInstructor)\n"
				+ "values (123, '2021-01-12 09:30:00.000', '2021-01-12 11:00:00.000', '577381d0-7957-46c5-9081-54f2096aa738:W1:MT', 0,0, 1,1,0);\n"
				+ "\n"
				+ "insert into scheduledEvent(event, starttime, endtime, template, recordTardy, recordLate, allcredit, split, splitByInstructor)\n"
				+ "values (123, '2021-01-18 09:30:00.000', '2021-01-18 11:00:00.000', '577381d0-7957-46c5-9081-54f2096aa738:W1:MT', 0,0, 1,1,0);\n"
				+ "\n"
				+ "insert into scheduledEvent(event, starttime, endtime, template, recordTardy, recordLate, allcredit, split, splitByInstructor)\n"
				+ "values (123, '2021-02-15 09:30:00.000', '2021-02-15 11:00:00.000', '577381d0-7957-46c5-9081-54f2096aa738:W1:MT', 0,0, 1,1,0);\n"
				+ "\n"
				+ "insert into scheduledEvent(event, starttime, endtime, template, recordTardy, recordLate, allcredit, split, splitByInstructor)\n"
				+ "values (123, '2021-02-16 09:30:00.000', '2021-02-16 11:00:00.000', '577381d0-7957-46c5-9081-54f2096aa738:W1:MT', 0,0, 1,1,0);\n"
				+ "\n"
				+ "insert into event (id, version, description, tardyValue, attendanceValue, active) \n"
				+ "values (123, 0,  'Daily Chapel', 0.75, 1,1);\n"
				+ "");
        
		runner.runScript(populate);
        PunchMapper mapper = session.getMapper(PunchMapper.class);
        
        List<Map> x = mapper.selectPresent("000244931", df.parse("2021-01-09"));
        assertTrue(x.size() == 3);
        assertTrue(x.get(0).get("DAY").equals(df.parse("2021-02-15")));
        assertTrue(x.get(1).get("DAY").equals(df.parse("2021-01-18")));
        assertTrue(x.get(2).get("DAY").equals(df.parse("2021-01-11")));
	}

}
