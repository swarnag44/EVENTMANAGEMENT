 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cg.pm.exception.EventException;
import com.cg.pm.model.Event;
import com.cg.pm.service.EventServiceImpl;
public class EventServiceImplTest {
	
	EventServiceImpl service;
	
	@BeforeEach
	void runBeforeAnyTestCase() {
		try {
			service=new EventServiceImpl();
		} catch (EventException e) {
			e.printStackTrace();
		}
		
	}
	@AfterEach
	void cleanAfterEachTestCase() {
		service=null;
	}
	
	@Test
	@DisplayName("book should not added")
	void addtest()throws EventException {
		String expected="V007";
		String actual=service.add(new Event("V007","Marriage",LocalDate.parse("1999-05-10"),"PUNE",87.5));
		assertEquals(expected,actual);
		
	}
	@Test
	@DisplayName("book deleted")
	void deletetest() throws EventException{
		boolean actual =service.delete("V002");
		assertTrue(actual);
	}
	

	@Test
	@DisplayName("book should not added")
	void addteST()throws EventException {
		String expected="V001";
		String actual=service.add(new Event("V001","Engah",LocalDate.parse("1999-05-10"),"PUNE",87.5));
		assertEquals(expected,actual);
		
	}
	@Test
	@DisplayName("book deleted")
	void deletetEST() throws EventException{
		boolean actual =service.delete("V005");
		assertTrue(actual);
	}
	
	

}
