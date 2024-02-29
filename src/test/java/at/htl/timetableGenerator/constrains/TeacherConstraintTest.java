package at.htl.timetableGenerator.constrains;

import at.htl.timetableGenerator.*;
import at.htl.timetableGenerator.constrains.constraints.TeacherConstraint;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class TeacherConstraintTest {

	@Test
	void check() {
		TeacherConstraint constraint = new TeacherConstraint();
		Timetable timetable = new Timetable(1, 1);
		Subject math = new Subject("Maths", "AM");
		Subject german = new Subject("German", "D");
		Lesson maths = new Lesson(math, new TimeSlot(DayOfWeek.MONDAY, 0));
		Lesson germans = new Lesson(german, new TimeSlot(DayOfWeek.MONDAY, 0));
		HashSet<Subject> subjects = new HashSet<>();
		subjects.add(math);

		Teacher kerschi = new Teacher("Kerschner", subjects, 1, 1);
		HashSet<Teacher> teachers = new HashSet<>();
		teachers.add(kerschi);

		HashMap<String, Room> rooms = new HashMap<>();
		rooms.put("123", new Room("123"));
		rooms.put("456", new Room("456"));
		rooms.put("789", new Room("789"));
		rooms.put("101112", new Room("101112"));

		assertTrue(constraint.check(timetable, maths, teachers, rooms));
		assertFalse(constraint.check(timetable, germans, teachers, rooms));

		maths.setTeacher(kerschi);
		timetable.setLesson(maths);

		assertFalse(constraint.check(timetable, maths, teachers, rooms));
	}

	@Test
	void updateOnSuccess() {
		TeacherConstraint constraint = new TeacherConstraint();
		Timetable timetable = new Timetable(1, 1);
		Subject math = new Subject("Maths", "AM");
		Subject german = new Subject("German", "D");
		Subject itp = new Subject("ITP", "itp");
		Lesson maths = new Lesson(math, new TimeSlot(DayOfWeek.MONDAY, 0));
		Lesson germans = new Lesson(german, new TimeSlot(DayOfWeek.MONDAY, 0));
		Lesson itps = new Lesson(itp, new TimeSlot(DayOfWeek.MONDAY, 0));
		HashSet<Subject> kerschiSubjects = new HashSet<>();
		kerschiSubjects.add(math);

		HashSet<Subject> lugerSubjects = new HashSet<>();
		lugerSubjects.add(german);
		lugerSubjects.add(itp);

		Teacher kerschi = new Teacher("Kerschner", kerschiSubjects, 1, 1);
		Teacher luger = new Teacher("Lugner", lugerSubjects, 1, 1);
		HashSet<Teacher> teachers = new HashSet<>();
		teachers.add(luger);
		teachers.add(kerschi);

		HashMap<String, Room> rooms = new HashMap<>();
		rooms.put("123", new Room("123"));
		rooms.put("456", new Room("456"));
		rooms.put("789", new Room("789"));
		rooms.put("101112", new Room("101112"));

		constraint.updateOnSuccess(timetable, maths, teachers, rooms);
		timetable.setLesson(maths);

		assertEquals(timetable.getLesson(new TimeSlot(DayOfWeek.MONDAY, 0)),
		             kerschi.getLesson(new TimeSlot(DayOfWeek.MONDAY, 0)));

		assertNotEquals(timetable.getLesson(new TimeSlot(DayOfWeek.MONDAY, 0)),
		                luger.getLesson(new TimeSlot(DayOfWeek.MONDAY, 0)));

		constraint.updateOnSuccess(timetable, germans, teachers, rooms);
		timetable.setLesson(germans);

		assertEquals(timetable.getLesson(new TimeSlot(DayOfWeek.MONDAY, 0)),
		             luger.getLesson(new TimeSlot(DayOfWeek.MONDAY, 0)));

		constraint.updateOnSuccess(timetable, itps, teachers, rooms);
		timetable.setLesson(itps);

		assertNotEquals(timetable.getLesson(new TimeSlot(DayOfWeek.MONDAY, 0)),
		                kerschi.getLesson(new TimeSlot(DayOfWeek.MONDAY, 0)));
	}
}