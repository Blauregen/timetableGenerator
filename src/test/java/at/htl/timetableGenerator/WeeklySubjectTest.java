package at.htl.timetableGenerator;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WeeklySubjectTest {
	@Test
	void testConstructor() {
		Subject mathSubject = new Subject("Maths", "AM");
		int noPerWeek = 10;
		WeeklySubject weeklySubject = new WeeklySubject(mathSubject, 10);

		assertEquals(noPerWeek, weeklySubject.getNoPerWeek());
		assertEquals(mathSubject, weeklySubject.getSubject());
	}

	@Test
	void testHashCode() {
		WeeklySubject weeklySubject = new WeeklySubject(new Subject("Maths", "AM"), 10);
		assertEquals(Objects.hash(weeklySubject.getSubject()), weeklySubject.hashCode());
	}

	@Test
	void testSetters() {
		WeeklySubject weeklySubject = new WeeklySubject(new Subject("Maths", "AM"), 10);
		int newNumbersPerWeek = 20;
		Subject newSubject = new Subject("Maths", "M");

		weeklySubject.setSubject(newSubject);
		weeklySubject.setNoPerWeek(newNumbersPerWeek);

		assertEquals(newSubject, weeklySubject.getSubject());
		assertEquals(newNumbersPerWeek, weeklySubject.getNoPerWeek());
	}

	@Test
	void testToString() {
		WeeklySubject weeklySubject = new WeeklySubject(new Subject("Maths", "AM"), 10);

		assertEquals("AM: 10", weeklySubject.toString());
	}

	@Test
	void testEquals() {
		WeeklySubject weeklySubject = new WeeklySubject(new Subject("Maths", "AM"), 10);
		WeeklySubject sameWeeklySubject = new WeeklySubject(new Subject("Maths", "D"), 10);
		WeeklySubject differentWeeklySubject = new WeeklySubject(new Subject("German", "AM"), 10);
		WeeklySubject alsoDifferentWeeklySubject = new WeeklySubject(new Subject("Maths", "AM"),
				5);
		WeeklySubject stillDifferentWeeklySubject =
				new WeeklySubject(new Subject("German", "AM"), 5);

		assertEquals(weeklySubject, weeklySubject);
		assertEquals(weeklySubject, sameWeeklySubject);
		assertNotEquals(weeklySubject, differentWeeklySubject);
		assertNotEquals(weeklySubject, alsoDifferentWeeklySubject);
		assertNotEquals(weeklySubject, stillDifferentWeeklySubject);
		assertNotEquals(weeklySubject, null);
		assertNotEquals(weeklySubject, new Object());
	}
}
