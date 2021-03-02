package com.cg.pm.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.cg.pm.exception.EventException;
import com.cg.pm.model.Event;

public interface EventDAO {
	String add(Event event) throws EventException;
	boolean delete(String id)throws EventException;
	Event get(String location) throws EventException;
	List<Event> getAll() throws EventException;
	List<Event> getAll1() throws EventException;
	//List<Event> getAll2() throws EventException;
	//List<Event> getAll3() throws EventException;
	Event get1(String datescheduled) throws EventException;
	boolean update(Event event) throws EventException;
	void persist() throws EventException;
	
}
