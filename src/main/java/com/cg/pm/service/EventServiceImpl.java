package com.cg.pm.service;

//import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.cg.pm.dao.EventDAO;
import com.cg.pm.dao.EventDAOJDBCImpl;
//import com.cg.pm.dao.EventDAOIOImpl;
import com.cg.pm.exception.EventException;
import com.cg.pm.model.Event;

public class EventServiceImpl implements EventService {
	private EventDAO eventDao;

	public EventDAO getDAO(){
		return eventDao;
	}
	
	public EventServiceImpl() throws EventException {
		eventDao = new  EventDAOJDBCImpl();
	}
	
	public boolean isValidId(String id){
		Pattern idPattern = Pattern.compile("[A-Z]\\d{3}");
		Matcher idMatcher = idPattern.matcher(id);
		
		return idMatcher.matches();
	}
	
	public boolean isValidTitle(String title){
		 //First Letter should be capital
		 //Minimum length is 4 chars
		 //Maximum length is 20 chars.		
		Pattern titlePattern = Pattern.compile("[A-Z]\\w{3,19}");
		Matcher titleMatcher = titlePattern.matcher(title);
		
		return titleMatcher.matches();
	}
	
	public boolean isValidCost(double cost){
		//Price is between 5 and 5000
		return cost>=5.0 && cost<=5000.00;
	}
	
	public boolean isValidDatescheduled(LocalDate datescheduled){
		LocalDate today = LocalDate.now();
		return today.isAfter(datescheduled) || today.equals(datescheduled);
	}
	
	public boolean isValidEvent(Event event) throws EventException{
		boolean isValid=false;
		
		List<String> errMsgs = new ArrayList<>();
		
		if(!isValidId(event.getId()))
			errMsgs.add("id should start with a capital alphabet followed by 3 digits");
		
		if(!isValidTitle(event.getTitle()))
			errMsgs.add("Title must start with capital and must be in between 4 to 20 chars in length");
		
		if(!isValidCost(event.getCost()))
			errMsgs.add("Price must be between INR.5 and INR.5000");
		
		if(!isValidDatescheduled(event.getDatescheduled()))
			errMsgs.add("Publish Date should not be a future date");
		
		if(errMsgs.isEmpty())
			isValid=true;
		else
			throw new EventException(errMsgs.toString());
		
		return isValid;
	}
    @Override
	public String add(Event event) throws EventException {
		String id=null;
		if(event!=null&& isValidEvent(event)){
			id=eventDao.add(event);
		}
		return id;
	}
	public boolean delete(String id) throws EventException {
		boolean isDone=false;
		if(id!=null && isValidId(id)){
			eventDao.delete(id);
			isDone = true;
		} else{
			throw new EventException("id should be digits");
		}
		return isDone;
	}

	public Event get(String location) throws EventException {
		return eventDao.get(location);
	}
	public List<Event> getAll() throws EventException{
		return eventDao.getAll();
	}
	
	public List<Event> getAll1() throws EventException{
		return eventDao.getAll1();
	}
	//public List<Event> getAll2() throws EventException{
		//return eventDao.getAll2();
	//}
	//public List<Event> getAll3() throws EventException{
		//return eventDao.getAll3();
	//}
	//public Event get1(String location) throws EventException {
		//return eventDao.get(location);
	//}
	public boolean update(Event event) throws EventException {
		boolean isDone = false;
		
		if(event!=null && isValidEvent(event)){
			isDone = eventDao.update(event);
		}
		
		return isDone;
	}
	public void persist() throws EventException{
}

	@Override
	public Event get1(Date date) throws EventException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
