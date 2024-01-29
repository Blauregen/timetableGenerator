package at.htl.timetableGenerator.constrains;

import at.htl.timetableGenerator.Lesson;
import at.htl.timetableGenerator.Subject;
import at.htl.timetableGenerator.TimeSlot;
import at.htl.timetableGenerator.Timetable;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NoMoreThanThreeInRowConstraintTest {

	@Test
	void check() {
		NoMoreThanThreeInRowConstraint constraint = new NoMoreThanThreeInRowConstraint();
		Timetable timetable = new Timetable(1, 4);
		Subject math = new Subject("Maths", "AM");
		Subject german = new Subject("German", "D");
		Lesson maths = new Lesson(math, new TimeSlot(DayOfWeek.MONDAY, 0));
		Lesson secondMaths = new Lesson(math, new TimeSlot(DayOfWeek.MONDAY, 1));
		Lesson thirdMaths = new Lesson(math, new TimeSlot(DayOfWeek.MONDAY, 2));
		Lesson fourthMaths = new Lesson(math, new TimeSlot(DayOfWeek.MONDAY, 3));
		Lesson germans = new Lesson(german, new TimeSlot(DayOfWeek.MONDAY, 0));
		Lesson secondGermans = new Lesson(german, new TimeSlot(DayOfWeek.MONDAY, 1));
		Lesson thirdGerman = new Lesson(german, new TimeSlot(DayOfWeek.MONDAY, 2));

		timetable.setLesson(maths);
		timetable.setLesson(secondMaths);

		//If the lesson is set for an hour <= 3, returns true, since it can't be more than three in row
		assertTrue(constraint.check(timetable, thirdMaths, new HashSet<>()));

		timetable.setLesson(thirdMaths);

		//if the lesson would be the fourth in a row of the same subject, return false
		assertFalse(constraint.check(timetable, fourthMaths, new HashSet<>()));

		timetable.setLesson(thirdGerman);

		//if the lesson is not the fourth in a row, because we changed the lesson before it, return true
		assertTrue(constraint.check(timetable, fourthMaths, new HashSet<>()));

		timetable.setLesson(secondGermans);
		timetable.setLesson(thirdMaths);

		assertTrue(constraint.check(timetable, fourthMaths, new HashSet<>()));
		timetable.setLesson(secondMaths);
		timetable.setLesson(germans);

		assertTrue(constraint.check(timetable, fourthMaths, new HashSet<>()));
	}
}