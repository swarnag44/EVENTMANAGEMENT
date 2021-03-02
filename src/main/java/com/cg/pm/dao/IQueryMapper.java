package com.cg.pm.dao;

public interface IQueryMapper {
	public static final String ADD_EVENT_QRY=
			"INSERT INTO events VALUES(?,?,?,?,?)";
	public static final String DOSORTED_EVENT_QRY=
			"SELECT * FROM events ORDER BY location ASC";
	public static final String DEL_EVENT_QRY=
			"DELETE FROM events WHERE id=?";
	public static final String GET_ALL_EVENTS_QRY=
			"SELECT * FROM events ORDER BY datescheduled ASC";
	public static final String GET_EVENT_QRY=
			"SELECT * FROM events WHERE location=?";
	public static final String GET_EVENTDATE_QRY=
			"SELECT * FROM events WHERE datescheduled=?";
		
	

}
