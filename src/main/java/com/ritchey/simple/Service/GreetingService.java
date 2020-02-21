package com.ritchey.simple.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ritchey.simple.domain.chapel.ChapelPerson;
import com.ritchey.simple.domain.chapel.ChapelPersonExample;
import com.ritchey.simple.domain.chapel.Exemptions;
import com.ritchey.simple.domain.chapel.ExemptionsExample;
import com.ritchey.simple.domain.powercampus.AcademicCalendar;
import com.ritchey.simple.domain.powercampus.AcademicCalendarExample;
import com.ritchey.simple.mapper.chapel.ChapelPersonMapper;
import com.ritchey.simple.mapper.chapel.ExemptionsMapper;
import com.ritchey.simple.mapper.chapel.PunchMapper;
import com.ritchey.simple.mapper.powercampus.AcademicCalendarMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class GreetingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingService.class);

    @Autowired
    private AcademicCalendarMapper calendar;
    
    @Autowired
	private ChapelPersonMapper chapelPersonMapper;
    
    @Autowired
	private ExemptionsMapper exemptionsMapper;
    
    @Autowired
    private PunchMapper punchMapper;
 
    public List<AcademicCalendar> getCurrentTerms() throws Exception {
    	Date now = new Date();
    	AcademicCalendarExample example = new AcademicCalendarExample();
    	example.createCriteria().andStartDateLessThan(now)
    		.andEndDateGreaterThanOrEqualTo(now);
    	List<AcademicCalendar> ret = calendar.selectByExample(example);
    	return ret;
    }
    
	public Map<String, Date> getStartTerm() {
		Map<String, Date> ret = new HashMap<String, Date>();
		try {
			AcademicCalendarExample example = new AcademicCalendarExample();
			Date now = new Date();

			example.createCriteria().andStartDateLessThanOrEqualTo(now).andEndDateGreaterThanOrEqualTo(now);
			List<AcademicCalendar> list = calendar.selectByExample(example);

			// Get first (smallest) start date of all terms that are currently active
			
			for (AcademicCalendar c: list) {
				if (now.after(c.getStartDate())) {
					now = c.getStartDate();
					ret.put("startDate", c.getStartDate());
					ret.put("endDate", c.getEndDate());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
    
	public ListCount<Map> getPresent(String peopleCodeId, Map<String, Date> term, int offset, int limit) {
		try {
			String campusId = peopleCodeId.replaceAll("[Pp]", "");

			if (term == null) {
				term = getStartTerm();
			}
			Date termStart = term.get("startDate");
			Date termEnd = term.get("endDate");
			
			ChapelPersonExample example = new ChapelPersonExample();
			example.createCriteria().andPeopleIdEqualTo(campusId);
			List<ChapelPerson> x = chapelPersonMapper.selectByExample(example);
			
			
			Map exemptionsMap = exemptionsMapper.getExemptions(campusId, term.get("endDate"));
			Integer exempt = 0;
			Integer hours = 45; //FIX ME
			if (exemptionsMap != null) {
				exempt = (Integer) exemptionsMap.get("exemptions");
				hours = (Integer) exemptionsMap.get("hoursRequired");
			}
			
			List<Map> list = punchMapper.selectPresent(campusId, termStart);
			double total = 0;
			for (Map m: list) {
				total = total + (Double) m.get("value");
			}
			
			//Map m = punchMapper.selectPresentCount(campusId, termStart);
			Map env = new HashMap();
			ListCount<Map> ret = new ListCount(list, list.size(), env);
			ret.getEnv().put("total", total);
			ret.getEnv().put("goal", hours);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public Integer getExempt(ChapelPerson chapelPerson) {
		Integer ret = 0;
		ExemptionsExample example = new ExemptionsExample();
		example.createCriteria().andPersonidEqualTo(chapelPerson.getId()).andEndtermGreaterThanOrEqualTo(new java.sql.Date(new Date().getTime()));
		List<Exemptions> x = exemptionsMapper.selectByExample(example);
		if (x.size() > 0) {
			return x.get(0).getExempt();
		}
		return ret;
	}

	public ListCount<Map> getTardies(String peopleCodeId, Map<String, Date> term, int offset, int limit) {
		if (term == null) {
			term = getStartTerm();
		}
		Date termStart = term.get("startDate");
		Date termEnd = term.get("endDate");
		try {
			String campusId = peopleCodeId.replaceAll("[Pp]", "");
			List<Map> list = punchMapper.selectTardy(campusId, termStart);
			//Map m = punchMapper.selectTardyCount(campusId, termStart);
			double total = 0;
			for (Map m: list) {
				total = (Double) m.get("value") + total;
			}
			Map env = new HashMap();
			ListCount<Map> ret = new ListCount(list, list.size(), env);
			ret.getEnv().put("total", total);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ChapelPerson getChapelPerson(String peopleId) {
		try {
			LOGGER.debug("getName  " + peopleId);
			if (peopleId.startsWith("P")) {
				peopleId = peopleId.substring(1);
			}
			ChapelPersonExample example = new ChapelPersonExample();
			example.createCriteria().andPeopleIdEqualTo(peopleId);
			List<ChapelPerson> persons = chapelPersonMapper.selectByExample(example);
			if (persons.size() > 0) {
				return persons.get(0);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
