package com.cg.pm.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cg.pm.exception.EventException;
import com.cg.pm.model.Event;
import com.cg.pm.util.ConnectionProvider;

public class EventDAOJDBCImpl implements EventDAO {
	ConnectionProvider conProvider;
	public EventDAOJDBCImpl() throws EventException {
		try {
			conProvider = ConnectionProvider.getInstance();
		} catch (ClassNotFoundException | IOException exp) {
			throw new EventException("Database is not reachable");
		}
	}

	@Override
	public String add(Event event) throws EventException {
		String id = null;
		if (event != null) {
			try (Connection con = conProvider.getConnection();
					PreparedStatement pInsert = con.prepareStatement(IQueryMapper.ADD_EVENT_QRY)) {

				pInsert.setString(1, event.getId());
				pInsert.setString(2, event.getTitle());
				pInsert.setDate(3, Date.valueOf(event.getDatescheduled()));
				pInsert.setString(4, event.getLocation());
				pInsert.setDouble(5, event.getCost());

				int rowCount = pInsert.executeUpdate();

				if (rowCount == 1) {
					id = event.getId();
				}
			} catch (SQLException exp) {
				throw new EventException("Book is not inserted");
			}
		}
		return id;
	}

	@Override
	public boolean delete(String id) throws EventException {
		boolean isDone = false;

		try (Connection con = conProvider.getConnection();
				PreparedStatement pDelete = con.prepareStatement(IQueryMapper.DEL_EVENT_QRY)) {
			pDelete.setString(1, id);
			int rowCount = pDelete.executeUpdate();
			if (rowCount == 1) {
				isDone = true;
			}
		} catch (SQLException exp) {
			throw new EventException("Book is not deleted");
		}

		return isDone;
	}
	@Override
	public Event get(String id) throws EventException {
		Event event=null;
		try (Connection con = conProvider.getConnection();
			PreparedStatement pSelect = con.prepareStatement(IQueryMapper.GET_EVENT_QRY)) {
			pSelect.setString(1,id);
			ResultSet rs = pSelect.executeQuery();
			if(rs.next()){
				event = new Event();
				event.setId(rs.getString("id"));
				event.setTitle(rs.getString("title"));
				event.setCost(rs.getDouble("cost"));
				event.setDatescheduled(rs.getDate("datescheduled").toLocalDate());
				event.setLocation(rs.getString("location"));
			}
			
		} catch (SQLException exp) {
			throw new EventException("Book is not available"+exp.getMessage());
		}		
		return event;
	}

	@Override
	public List<Event> getAll() throws EventException {
		List<Event> events=null;
		try (Connection con = conProvider.getConnection();
			PreparedStatement pSelect = con.prepareStatement(IQueryMapper.GET_ALL_EVENTS_QRY)) {
			ResultSet rs = pSelect.executeQuery();
			events = new ArrayList<Event>();
			while(rs.next()){
				Event event = new Event();
				event.setId(rs.getString("id"));
				event.setTitle(rs.getString("title"));
				event.setCost(rs.getDouble("cost"));
				event.setDatescheduled(rs.getDate("datescheduled").toLocalDate());
				event.setLocation(rs.getString("location"));
				events.add(event);
			}
			
		} catch (SQLException exp) {
			throw new EventException("No Books are available.");
		}		
		return events;	
	}

	@Override
	public boolean update(Event event) throws EventException {
		boolean isDone = false;
		if (event != null) {
			try (Connection con = conProvider.getConnection();
				PreparedStatement pUpdate = con.prepareStatement(IQueryMapper.DOSORTED_EVENT_QRY)) {
				pUpdate.setString(1, event.getTitle());
				pUpdate.setDouble(2, event.getCost());
				pUpdate.setDate(3, Date.valueOf(event.getDatescheduled()));
				pUpdate.setString(4, event.getId());
				pUpdate.setString(5, event.getLocation());
				int rowCount = pUpdate.executeUpdate();

				if (rowCount == 1) {
					isDone = true;
				}
			} catch (SQLException exp) {
				throw new EventException("Book is not updated.");
			}
		}
		return isDone;
	}

	@Override
	public void persist() throws EventException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Event> getAll1() throws EventException {
		List<Event> events=null;
		try (Connection con = conProvider.getConnection();
			PreparedStatement pSelect = con.prepareStatement(IQueryMapper.DOSORTED_EVENT_QRY)) {
			ResultSet rs = pSelect.executeQuery();
			events = new ArrayList<Event>();
			while(rs.next()){
				Event event = new Event();
				event.setId(rs.getString("id"));
				event.setTitle(rs.getString("title"));
				event.setCost(rs.getDouble("cost"));
				event.setDatescheduled(rs.getDate("datescheduled").toLocalDate());
				event.setLocation(rs.getString("location"));
				events.add(event);
			}
			
		} catch (SQLException exp) {
			throw new EventException("No Books are available.");
		}		
		return events;
	}
	@Override
	public Event get1(String datescheduled) throws EventException {
		Event event=null;
		try (Connection con = conProvider.getConnection();
			PreparedStatement pSelect = con.prepareStatement(IQueryMapper.GET_EVENTDATE_QRY)) {
			pSelect.setString(1,datescheduled);
			ResultSet rs = pSelect.executeQuery();
			if(rs.next()){
				event = new Event();
				event.setId(rs.getString("id"));
				event.setTitle(rs.getString("title"));
				event.setCost(rs.getDouble("cost"));
				event.setDatescheduled(rs.getDate("datescheduled").toLocalDate());
				event.setLocation(rs.getString("location"));
			}
			
		} catch (SQLException exp) {
			throw new EventException("Book is not available"+exp.getMessage());
		}		
		return event;
	}
	

}
