package at.htl.timetableGenerator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TimetableTest {
	Timetable timetable;

	@BeforeEach
	void setUp() {
		timetable = new Timetable(5, 10);
	}

	@AfterEach
	void tearDown() {
		timetable = null;
	}

	@Test
	void getTimetable() {
		HashMap<TimeSlot, Lesson> lessons = new HashMap<>();

		for (int i = 0; i < timetable.getNoOfDayPerWeek(); i++) {
			for (int j = 0; j < timetable.getMaxNoOfHoursPerDay(); j++) {
				TimeSlot slot = new TimeSlot(DayOfWeek.of(i + 1), j);
				lessons.put(slot, new Lesson(Timetable.FREISTUNDE, slot));
			}
		}

		timetable.setTimetable(lessons);

		assertEquals(lessons, timetable.getTimetable());
	}

	@Test
	void setTimetable() {
		Subject am = new Subject("Mathe", "AM");
		HashMap<TimeSlot, Lesson> lessons = new HashMap<>();
		lessons.put(new TimeSlot(DayOfWeek.MONDAY, 0), new Lesson(am, new TimeSlot(DayOfWeek.MONDAY, 0)));
		timetable.setTimetable(lessons);
		assertEquals(lessons, timetable.getTimetable());

		for (int i = 0; i < timetable.getNoOfDayPerWeek(); i++) {
			for (int j = 0; j < timetable.getMaxNoOfHoursPerDay(); j++) {
				TimeSlot slot = new TimeSlot(DayOfWeek.of(i + 1), j);
				lessons.put(slot, new Lesson(am, slot));
			}
		}

		timetable.setTimetable(am);
		assertEquals(lessons, timetable.getTimetable());
	}

	@Test
	void setAndGetLesson() {
		Subject am = new Subject("Mathe", "AM");
		TimeSlot slot = new TimeSlot(DayOfWeek.MONDAY, 0);
		timetable.setLesson(new Lesson(am, slot));
		assertEquals(timetable.getLesson(slot).getSubject(), am);
	}

	@Test
	void setAndGetLessonInvalidTime() {
		Subject am = new Subject("Mathe", "AM");
		TimeSlot slot = new TimeSlot(DayOfWeek.SUNDAY, 0);
		TimeSlot secondSlot = new TimeSlot(DayOfWeek.MONDAY, 11);
		TimeSlot thirdSlot = new TimeSlot(DayOfWeek.MONDAY, -1);
		TimeSlot fourthSlot = new TimeSlot(DayOfWeek.MONDAY, 11);

		assertThrows(IllegalArgumentException.class, () -> timetable.setLesson(new Lesson(am, slot)),
				"Day is " + "invalid");
		assertThrows(IllegalArgumentException.class, () -> timetable.setLesson(new Lesson(am, secondSlot)),
				"Time is invalid");
		assertThrows(IllegalArgumentException.class, () -> timetable.setLesson(new Lesson(am, thirdSlot)),
				"Time is invalid");
		assertEquals(new Lesson(Timetable.FREISTUNDE, fourthSlot), timetable.getLesson(fourthSlot));
	}

	@Test
	void getMaxNoOfHoursPerDay() {
		assertEquals(10, timetable.getMaxNoOfHoursPerDay());
	}

	@Test
	void setMaxNoOfHoursPerDay() {
		timetable.setMaxNoOfHoursPerDay(4);
		assertEquals(4, timetable.getMaxNoOfHoursPerDay());
	}

	@Test
	void setMaxNoOfHoursPerDayInvalid() {
		assertThrows(IllegalArgumentException.class, () -> timetable.setMaxNoOfHoursPerDay(0),
				"Max number of hours per day can't be smaller than " + Timetable.MIN_NUMBER_OF_HOURS_PER_DAY + "!");
	}

	@Test
	void getNoOfDayPerWeek() {
		assertEquals(5, timetable.getNoOfDayPerWeek());
	}

	@Test
	void setNoOfDayPerWeek() {
		timetable.setNoOfDayPerWeek(4);
		assertEquals(4, timetable.getNoOfDayPerWeek());
	}

	@Test
	void setNoOfDayPerWeekInvalid() {
		assertThrows(IllegalArgumentException.class, () -> timetable.setNoOfDayPerWeek(0),
				"Number of days per week can't be smaller than " + Timetable.MIN_NUMBER_OF_DAYS_PER_WEEK +
				" or bigger than 7!");
		assertThrows(IllegalArgumentException.class, () -> timetable.setNoOfDayPerWeek(8),
				"Number of days per week can't be smaller than " + Timetable.MIN_NUMBER_OF_DAYS_PER_WEEK +
				" or bigger than 7!");
	}

	@Test
	void contains() {
		Subject am = new Subject("Mathe", "AM");
		Subject d = new Subject("german", "D");
		TimeSlot slot = new TimeSlot(DayOfWeek.MONDAY, 0);
		timetable.setLesson(new Lesson(am, slot));
		assertTrue(timetable.contains(am));
		assertFalse(timetable.contains(d));
	}

	@Test
	void testToString() {
		Subject am = new Subject("Mathe", "AM");
		TimeSlot slot = new TimeSlot(DayOfWeek.MONDAY, 0);
		timetable.setLesson(new Lesson(am, slot));
		assertTrue(timetable.toString().contains("AM"));
	}
}