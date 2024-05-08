package at.htl.timetableGenerator.model;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WeeklySubjectTest {
	@Test
	void testConstructor() {
		Subject mathSubject = new Subject(0L,  "Maths", "AM", 3);
		int noPerWeek = 10;
		WeeklySubject weeklySubject = new WeeklySubject(mathSubject, 10);

		assertEquals(noPerWeek, weeklySubject.getTimesPerWeek());
		assertEquals(mathSubject, weeklySubject.getSubject());
	}

	@Test
	void testHashCode() {
		WeeklySubject weeklySubject = new WeeklySubject(new Subject(0L,  "Maths", "AM", 3), 10);
		assertEquals(Objects.hash(weeklySubject.getSubject()), weeklySubject.hashCode());
	}

	@Test
	void testSetters() {
		WeeklySubject weeklySubject = new WeeklySubject(new Subject(0L,  "Maths", "AM", 3), 10);
		int newNumbersPerWeek = 20;
		Subject newSubject = new Subject(0L,  "Maths", "M", 3);

		weeklySubject.setSubject(newSubject);
		weeklySubject.setTimesPerWeek(newNumbersPerWeek);

		assertEquals(newSubject, weeklySubject.getSubject());
		assertEquals(newNumbersPerWeek, weeklySubject.getTimesPerWeek());
	}

	@Test
	void testToString() {
		WeeklySubject weeklySubject = new WeeklySubject(new Subject(0L,  "Maths", "AM", 3), 10);

		assertEquals("AM: 10", weeklySubject.toString());
	}

	@Test
	void testEquals() {
		WeeklySubject weeklySubject = new WeeklySubject(new Subject(0L,  "Maths", "AM", 3), 10);
		WeeklySubject sameWeeklySubject = new WeeklySubject(new Subject(0L,  "Maths", "D", 3), 10);
		WeeklySubject differentWeeklySubject = new WeeklySubject(new Subject(0L,  "German", "AM", 3), 10);
		WeeklySubject alsoDifferentWeeklySubject = new WeeklySubject(new Subject(0L,  "Maths", "AM", 3), 5);
		WeeklySubject stillDifferentWeeklySubject = new WeeklySubject(new Subject(0L,  "German", "AM", 3), 5);

		assertEquals(weeklySubject, weeklySubject);
		assertEquals(weeklySubject, sameWeeklySubject);
		assertNotEquals(weeklySubject, differentWeeklySubject);
		assertNotEquals(weeklySubject, alsoDifferentWeeklySubject);
		assertNotEquals(weeklySubject, stillDifferentWeeklySubject);
		assertNotEquals(weeklySubject, null);
		assertNotEquals(weeklySubject, new Object());
	}
}
