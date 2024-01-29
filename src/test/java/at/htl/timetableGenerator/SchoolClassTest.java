package at.htl.timetableGenerator;

import at.htl.timetableGenerator.constrains.DoubleHourConstraint;
import at.htl.timetableGenerator.constrains.NonePlacedBeforeConstraint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SchoolClassTest {

	SchoolClass schoolClass;

	@BeforeEach
	void setup(){
		Subject subject1 = new Subject("Angewandte Mathematik", "AM");
		Subject subject2 = new Subject("Englisch", "E");
		Subject subject3 = new Subject("Software Entwicklung", "SEW");

		WeeklySubject weeklySubject1 = new WeeklySubject(subject1, 5);
		WeeklySubject weeklySubject2 = new WeeklySubject(subject2, 2);
		WeeklySubject weeklySubject3 = new WeeklySubject(subject3, 3);

		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		weeklySubjects.add(weeklySubject1);
		weeklySubjects.add(weeklySubject2);
		weeklySubjects.add(weeklySubject3);


		schoolClass = new SchoolClass("3BHITM", weeklySubjects);
	}

	@Test
	void getAndSetFormTeacher() {
		Subject subject2 = new Subject("Englisch", "E");
		HashSet<Subject> subjectsWellisch = new HashSet<>();
		subjectsWellisch.add(subject2);
		Teacher wellisch = new Teacher("Alexandra Wellisch", subjectsWellisch, 6, 4);

		schoolClass.setFormTeacher(wellisch);

		assertEquals(wellisch, schoolClass.getFormTeacher());
	}

	@Test
	void generateTimetable() {
		/**
		 * CREATE TEACHERS BEFORE CREATING TIMETABLE
		 **/
		Subject subject1 = new Subject("Angewandte Mathematik", "AM");
		HashSet<Subject> subjectsKerschner = new HashSet<>();
		subjectsKerschner.add(subject1);
		Teacher kerschner = new Teacher("Martin Kerschner", subjectsKerschner, 5, 5);

		Subject subject2 = new Subject("Englisch", "E");
		HashSet<Subject> subjectsWellisch = new HashSet<>();
		subjectsWellisch.add(subject2);
		Teacher wellisch = new Teacher("Alexandra Wellisch", subjectsWellisch, 6, 4);

		Subject subject3 = new Subject("Software Entwicklung", "SEW");
		HashSet<Subject> subjectsKarpfen = new HashSet<>();
		subjectsKarpfen.add(subject3);
		Teacher karpfen = new Teacher("Michal Karpowicz", subjectsKarpfen, 4, 3);

		Set<Teacher> teachers = new HashSet<>();
		teachers.add(kerschner);
		teachers.add(wellisch);
		teachers.add(karpfen);

		schoolClass.generateTimetable(5, 9, teachers);

		assertEquals(9, schoolClass.getTimetable().getMaxNoOfHoursPerDay());
		assertEquals(5, schoolClass.getTimetable().getNoOfDayPerWeek());
		assertEquals(45, schoolClass.getTimetable().getTimetable().size());
	}

	@Test
	void getName() {
		assertEquals("3BHITM", schoolClass.getName());
	}

	@Test
	void getAndSetWeeklySubjects() {
		assertEquals(3, schoolClass.getWeeklySubjects().size());

		Subject subject4 = new Subject("Deutsch", "D");
		WeeklySubject weeklySubject4 = new WeeklySubject(subject4, 4);
		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		weeklySubjects.add(weeklySubject4);

		schoolClass.setWeeklySubjects(weeklySubjects);
		assertEquals(1, schoolClass.getWeeklySubjects().size());
	}

	@Test
	void getAndSetWeeklySubjectsNullOrEmpty() {
		assertEquals(3, schoolClass.getWeeklySubjects().size());

		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();

		assertThrows(IllegalArgumentException.class, () -> {
			schoolClass.setWeeklySubjects(weeklySubjects);
		});
	}

	@Test
	void getAndSetLesson() {

		Subject subject3 = new Subject("Software Entwicklung", "SEW");
		HashSet<Subject> subjectsKarpfen = new HashSet<>();
		subjectsKarpfen.add(subject3);
		Teacher karpfen = new Teacher("Michal Karpowicz", subjectsKarpfen, 4, 3);

		Set<Teacher> teachers = new HashSet<>();
		teachers.add(karpfen);

		TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, 0);
		Subject subject = new Subject("Englisch", "E");
		Lesson lesson = new Lesson(subject, timeSlot);

		schoolClass.setLesson(lesson);
		assertEquals(null, schoolClass.getLesson(timeSlot));

		schoolClass.generateTimetable(5, 9, teachers);

		schoolClass.setLesson(lesson);
		assertEquals(lesson.toString(), schoolClass.getLesson(timeSlot).toString());
	}

	@Test
	void testToString() {
		assertEquals("3BHITM", schoolClass.toString());
	}

	@Test
	void setTimetable() {
		schoolClass.setTimetable(new Timetable(3, 9));
		assertEquals(9, schoolClass.getTimetable().getMaxNoOfHoursPerDay());
		assertEquals(3, schoolClass.getTimetable().getNoOfDayPerWeek());
		assertEquals(27, schoolClass.getTimetable().getTimetable().size());
	}

	@Test
	void addAndRemoveConstraint() {
		Constraint constraint = new DoubleHourConstraint();

		assertEquals(1, schoolClass.getConstraints().size());

		schoolClass.addConstraint(constraint);

		assertEquals(2, schoolClass.getConstraints().size());

		schoolClass.removeConstraint(constraint);

		assertEquals(1, schoolClass.getConstraints().size());
	}

	@Test
	void testGetBestAvailableLesson() {
		Subject subject3 = new Subject("Software Entwicklung", "SEW");
		HashSet<Subject> subjectsKarpfen = new HashSet<>();
		subjectsKarpfen.add(subject3);
		Teacher karpfen = new Teacher("Michal Karpowicz", subjectsKarpfen, 5, 3);

		Set<Teacher> teachers = new HashSet<>();
		teachers.add(karpfen);

		assertThrows(IllegalArgumentException.class, () -> {
			schoolClass.generateTimetable(2, 2, teachers);
		});
	}
}