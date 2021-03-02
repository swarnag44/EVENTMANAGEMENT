package com.cg.pm.ui;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import com.cg.pm.exception.EventException;
import com.cg.pm.model.Event;
import com.cg.pm.model.EventStoreAppMenu;
import com.cg.pm.service.EventService;
import com.cg.pm.service.EventServiceImpl;

public class EventUI implements Serializable{
	private static EventServiceImpl eventService;
	private static Scanner scan;
	private static DateTimeFormatter dtFormater;

	public static void main(String[] args) throws ParseException {

		try {
			eventService = new EventServiceImpl();
		} catch (EventException e) {
			e.printStackTrace();
		}


		scan = new Scanner(System.in);
		dtFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		EventStoreAppMenu menu = null;

		while (menu !=EventStoreAppMenu.QUIT) {

			System.out.println("Choice\tMenu Item");
			System.out.println("===========================");
			for (EventStoreAppMenu m : EventStoreAppMenu.values()) {
				System.out.println(m.ordinal() + "\t" + m.name());
			}
			System.out.print("Choice: ");
			int choice = -1;
			if (scan.hasNextInt())
				choice = scan.nextInt();
			else {
				scan.next();
				System.out.println("Pleae choose a choice displayed");
				continue;
			}

			if (choice < 0 || choice >= EventStoreAppMenu.values().length) {
				System.out.println("Invalid Choice");
			} else {
				menu = EventStoreAppMenu.values()[choice];
				switch (menu) {
				case ADD:
					doAdd();
					break;
				case LIST:
					doList();
					break;
				case PARTLOC:
					partloc();
					break;
				case REMOVE:
					doRemove();
					break;
				case DOSORTED:
					sorted();
					break;
				case PARTDATE:
					partdate();
					break;
				case QUIT:
					System.out.println("ThanQ Come Again!");
					break;
				}
			}

		}

		scan.close();
		try {
			eventService.persist();
		}catch(EventException e) {
			e.printStackTrace();
		}
	}

	private static void doAdd() {
		try {
			Event event = new Event();
			System.out.print("id: ");
			event.setId(scan.next());
			System.out.print("Title: ");
			event.setTitle(scan.next());
			System.out.print("Datescheduled(yyyy-MM-dd): ");
			String DtScd = scan.next();
			System.out.print("location:");
			event.setLocation(scan.next());
			//System.out.print("cost");
			//int cost=scan.nextInt();

			try {
				event.setDatescheduled(LocalDate.parse(DtScd, dtFormater));
			} catch (DateTimeException exp) {
				throw new EventException(
						"Date must be in the format day(yyyy)-month(MM)-year(dd)");
			}
			System.out.print("cost: ");
			if (scan.hasNextDouble())
				event.setCost(scan.nextDouble());
			else {
				scan.next();
				throw new EventException("cost is a double");
			}

			String id = eventService.add(event);
			System.out.println("Event is Added with code: "+id);
		} catch (EventException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doList() {
		List<Event> events;
		try {
			events = eventService.getAll();
			if (events.isEmpty()) {
				System.out.println("No Events To display");
			} else {
				for (Event b : events)
					System.out.println(b);
			}
		} catch (EventException exp) {
			System.out.println(exp.getMessage());
		}
	}
	private static void partloc() {
	System.out.print("location: ");
	String location = scan.next();

	try {
		Event event = eventService.get(location);
		if (event != null) {
			System.out.println(event);
		} else {
			System.out.println("No Such Event");
		}
	} catch (EventException exp) {
		System.out.println(exp.getMessage());
	}
}

	private static void doRemove() {
		System.out.print("id: ");
		String id = scan.next();
		try {
			boolean isDone = eventService.delete(id);
			if (isDone) {
				System.out.println("event is Deleted");
			} else {
				System.out.println("No Such event");
			}
		} catch (EventException exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	private static void sorted() {
		List<Event>events;
		try {
			events = eventService.getAll1();
			if (events.isEmpty()) {
				System.out.println("No Events To display");
			} else {
				for (Event b : events)
					System.out.println(b);
			}
		} catch (EventException exp) {
			System.out.println(exp.getMessage());
		}
	}
		/*private static void partdate() {
			List<Event>events;
			try {
				events = eventService.getAll3();
				if (events.isEmpty()) {
					System.out.println("No Events To display");
				} else {
					for (Event b : events)
						System.out.println(b);
				}
			} catch (EventException exp) {
				System.out.println(exp.getMessage());
			}
	}*/
	private static void partdate() throws ParseException {
		System.out.print("date: ");
		String datescheduled = scan.next();
		Date date =new SimpleDateFormat("yyyy-MM-dd").parse(datescheduled);

		try {
			Event event = eventService.get1(date);
			if (event != null) {
				System.out.println(event);
			} else {
				System.out.println("No Such Event");
			}
		} catch (EventException exp) {
			System.out.println(exp.getMessage());
		}
	}

}
