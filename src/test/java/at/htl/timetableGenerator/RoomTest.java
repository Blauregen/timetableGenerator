package at.htl.timetableGenerator;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
	@Test public void testConstructorAndSetters(){
		String roomName = "Hildegards Lazarett";
		Room lazarett = new Room(roomName);
		Lesson lesson = new Lesson(new Subject("Medizine", "MED"), new TimeSlot(DayOfWeek.MONDAY, 0));

		assertEquals(roomName, lazarett.getName());

		assertEquals(7, lazarett.getTimetable().getNoOfDayPerWeek());
		assertEquals(24, lazarett.getTimetable().getMaxNoOfHoursPerDay());

		lazarett.setLesson(lesson);

		assertEquals(lesson, lazarett.getLesson(new TimeSlot(DayOfWeek.MONDAY, 0)));

	}

	@Test public void testHashCode(){
		String roomName = "Hildegards Lazarett";
		Room lazarett = new Room(roomName);
		assertEquals(Objects.hash(roomName), lazarett.hashCode());
	}

	@Test public void testToString(){
		String roomName = "Hildegards Lazarett";
		Room lazarett = new Room(roomName);
		assertEquals(roomName, lazarett.toString());
	}

	@Test public void testEquals(){
		Room lazarett = new Room("Hildegards Lazarett");
		Room modernClassroom = new Room("Hightech, Modern Classroom");

        assertEquals(lazarett, lazarett);

        assertNotEquals(lazarett, modernClassroom);

		assertNotEquals(lazarett, null);
	}

}