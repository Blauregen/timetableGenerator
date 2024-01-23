package at.htl.timetableGenerator;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WeeklySubjectsTest {
	@Test
	void testConstructor() {
		Subject mathSubject = new Subject("Maths", "AM");
		int noPerWeek = 10;
		WeeklySubjects weeklySubjects = new WeeklySubjects(mathSubject, 10);

		assertEquals(noPerWeek, weeklySubjects.getNoPerWeek());
		assertEquals(mathSubject, weeklySubjects.getSubject());
	}

	@Test
	void testHashCode() {
		WeeklySubjects weeklySubjects = new WeeklySubjects(new Subject("Maths", "AM"), 10);
		assertEquals(Objects.hash(weeklySubjects.getSubject()), weeklySubjects.hashCode());
	}

	@Test
	void testSetters() {
		WeeklySubjects weeklySubjects = new WeeklySubjects(new Subject("Maths", "AM"), 10);
		int newNumbersPerWeek = 20;
		Subject newSubject = new Subject("Maths", "M");

		weeklySubjects.setSubject(newSubject);
		weeklySubjects.setNoPerWeek(newNumbersPerWeek);

		assertEquals(newSubject, weeklySubjects.getSubject());
		assertEquals(newNumbersPerWeek, weeklySubjects.getNoPerWeek());
	}

	@Test
	void testToString() {
		WeeklySubjects weeklySubjects = new WeeklySubjects(new Subject("Maths", "AM"), 10);

		assertEquals("AM: 10", weeklySubjects.toString());
	}

	@Test
	void testEquals() {
		WeeklySubjects weeklySubjects = new WeeklySubjects(new Subject("Maths", "AM"), 10);
		WeeklySubjects sameWeeklySubjects = new WeeklySubjects(new Subject("Maths", "D"), 10);
		WeeklySubjects differentWeeklySubjects = new WeeklySubjects(new Subject("German", "AM"), 10);
		WeeklySubjects alsoDifferentWeeklySubjects = new WeeklySubjects(new Subject("Maths", "AM"), 5);
		WeeklySubjects stillDifferentWeeklySubjects = new WeeklySubjects(new Subject("German", "AM"), 5);

		assertEquals(weeklySubjects, weeklySubjects);
		assertEquals(weeklySubjects, sameWeeklySubjects);
		assertNotEquals(weeklySubjects, differentWeeklySubjects);
		assertNotEquals(weeklySubjects, alsoDifferentWeeklySubjects);
		assertNotEquals(weeklySubjects, stillDifferentWeeklySubjects);
		assertNotEquals(weeklySubjects, null);
		assertNotEquals(weeklySubjects, new Object());
	}
}
