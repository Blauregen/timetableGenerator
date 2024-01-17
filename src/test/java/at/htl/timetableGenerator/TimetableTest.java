package at.htl.timetableGenerator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

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
		Lesson[][] lessons = new Lesson[5][10];

		for (int i = 0; i < lessons.length; i++) {
			for (int j = 0; j < timetable.getMaxNoOfHoursPerDay(); j++) {
				lessons[i][j] = new Lesson(Timetable.FREISTUNDE, new TimeSlot(DayOfWeek.of(i + 1), j));
			}
		}

		assertArrayEquals(lessons, timetable.getTimetable());
	}

	@Test
	void setTimetable() {
		Course am = new Course("Mathe", "AM");
		Lesson[][] lessons = new Lesson[5][10];
		lessons[1][1] = new Lesson(am, new TimeSlot(DayOfWeek.THURSDAY, 1));
		timetable.setTimetable(lessons);
		assertArrayEquals(lessons, timetable.getTimetable());

		for (int i = 0; i < lessons.length; i++) {
			for (int j = 0; j < timetable.getMaxNoOfHoursPerDay(); j++) {
				lessons[i][j] = new Lesson(am, new TimeSlot(DayOfWeek.of(i + 1), j));
			}
		}

		timetable.setTimetable(am);
		assertArrayEquals(lessons, timetable.getTimetable());
	}

	@Test
	void setTimetableInvalidDays() {
		Lesson[][] lessons = new Lesson[6][10];

		assertThrows(IllegalArgumentException.class, () -> timetable.setTimetable(lessons),
				"Too few/many days in the timetable");
	}

	@Test
	void setTimetableInvalidHours() {
		Lesson[][] lessons = new Lesson[5][11];

		assertThrows(IllegalArgumentException.class, () -> timetable.setTimetable(lessons),
				"Too few/many hours in the timetable");
	}

	@Test
	void setAndGetLesson() {
		Course am = new Course("Mathe", "AM");
		TimeSlot slot = new TimeSlot(DayOfWeek.MONDAY, 0);
		timetable.setLesson(slot, am);
		assertEquals(timetable.getLesson(slot).getCourse(), am);
	}

	@Test
	void setAndGetLessonInvalidTime() {
		Course am = new Course("Mathe", "AM");
		TimeSlot slot = new TimeSlot(DayOfWeek.SUNDAY, 0);
		TimeSlot secondSlot = new TimeSlot(DayOfWeek.MONDAY, 11);
		TimeSlot thirdSlot = new TimeSlot(DayOfWeek.MONDAY, -1);
		TimeSlot fourthSlot = new TimeSlot(DayOfWeek.MONDAY, 11);

		assertThrows(IllegalArgumentException.class, () -> timetable.setLesson(slot, am), "Day is invalid");
		assertThrows(IllegalArgumentException.class, () -> timetable.setLesson(secondSlot, am), "Time is invalid");
		assertThrows(IllegalArgumentException.class, () -> timetable.setLesson(thirdSlot, am), "Time is invalid");
		assertThrows(IllegalArgumentException.class, () -> timetable.getLesson(fourthSlot),
				"Tried to get Lesson with invalid day or time");
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
		Course am = new Course("Mathe", "AM");
		Course d = new Course("german", "D");
		TimeSlot slot = new TimeSlot(DayOfWeek.MONDAY, 0);
		timetable.setLesson(slot, am);
		assertTrue(timetable.contains(am));
		assertFalse(timetable.contains(d));
	}

	@Test
	void testToString() {
		Course am = new Course("Mathe", "AM");
		TimeSlot slot = new TimeSlot(DayOfWeek.MONDAY, 0);
		timetable.setLesson(slot, am);
		assertTrue(timetable.toString().contains("AM"));
	}
}