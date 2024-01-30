package at.htl.timetableGenerator.constrains;

import at.htl.timetableGenerator.*;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoubleHourConstraintTest {

	@Test
	void check() {
		DoubleHourConstraint constraint = new DoubleHourConstraint();
		Timetable timetable = new Timetable(2, 2);
		Subject math = new Subject("Maths", "AM");
		Subject german = new Subject("German", "D");
		Lesson maths = new Lesson(math, new TimeSlot(DayOfWeek.MONDAY, 0));
		Lesson secondMath = new Lesson(math, new TimeSlot(DayOfWeek.MONDAY, 1));
		Lesson germans = new Lesson(german, new TimeSlot(DayOfWeek.TUESDAY, 0));
		Lesson secondGermans = new Lesson(german, new TimeSlot(DayOfWeek.MONDAY, 1));

		HashMap<String, Room> rooms = new HashMap<>();
		rooms.put("123", new Room("123"));
		rooms.put("456", new Room("456"));
		rooms.put("789", new Room("789"));
		rooms.put("101112", new Room("101112"));

		//If nothing in the timetable, every hour is the best way to create a double hour
		assertTrue(constraint.check(timetable, maths, new HashSet<>(), rooms));
		timetable.setLesson(maths);

		//If the first lesson is maths, then checking the second lesson for maths would create a
		// double hour, and
		//would therefore be the best possible spot
		assertTrue(constraint.check(timetable, secondMath, new HashSet<>(), rooms));

		//If the first lesson is maths, then checking the second lesson for german would not
		// create a double hour, but
		//would still be the best available slot, since there is no other german lesson available,
		// that would create a
		//double hour
		assertTrue(constraint.check(timetable, germans, new HashSet<>(), rooms));

		timetable.setLesson(germans);

		//If the first lesson is maths, and we check for it again, it would not create a double
		// hour even though a
		//double hour is possible, and would therefore not be the best slot
		assertFalse(constraint.check(timetable, maths, new HashSet<>(), rooms));

		//If we check german for Monday 2nd lesson, while already having a german lesson on
		// Tuesday 1st, it would not
		//be the best available slot, and would therefore return false
		assertFalse(constraint.check(timetable, secondGermans, new HashSet<>(), rooms));
	}
}