package at.htl.timetableGenerator;

import at.htl.timetableGenerator.constrains.NoMoreThanThreeInRowConstraint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SchoolTest {

	School school;

	@BeforeEach
	void setup(){
		Subject subjectE = new Subject("Englisch", "E");
		Subject subjectAM = new Subject("Angewandte Mathematik", "AM");
		WeeklySubject weeklySubject1 = new WeeklySubject(subjectE, 2);
		WeeklySubject weeklySubject2 = new WeeklySubject(subjectAM, 2);
		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		weeklySubjects.add(weeklySubject1);
        weeklySubjects.add(weeklySubject2);
		SchoolClass schoolClass = new SchoolClass("5AHIF", weeklySubjects);
		HashSet<SchoolClass> schoolClasses = new HashSet<>();
		schoolClasses.add(schoolClass);

		HashSet<Subject> wellischSubjects = new HashSet<>();
		wellischSubjects.add(subjectE);
		Teacher wellisch = new Teacher("Wellisch", wellischSubjects, 5, 5);

		HashSet<Subject> kerschnerSubjects = new HashSet<>();
		kerschnerSubjects.add(subjectAM);
		Teacher kerschner = new Teacher("Kerschner", kerschnerSubjects, 7, 3);

		HashSet<Teacher> teachers = new HashSet<>();
		teachers.add(wellisch);
		teachers.add(kerschner);

		school = new School("HTL Leonding", schoolClasses, teachers);
	}

	@Test
	void getName() {
		assertEquals("HTL Leonding", school.getName());
	}

	@Test
	void testConstructorWithName() {
		School htlLeonding = new School("HTL Leonding");
		assertEquals("HTL Leonding", htlLeonding.getName());
	}

	@Test
	void testConstraints() {
		HashSet<Constraint> constraints = new HashSet<>();
		Constraint constraint = new NoMoreThanThreeInRowConstraint();
		constraints.add(constraint);

		school.setConstraints(constraints);
		assertEquals(constraints, school.getConstraints());

		constraints.add(constraint);
		school.addConstraint(constraint);
		assertEquals(constraints, school.getConstraints());

		constraints.remove(constraint);
		school.removeConstraint(constraint);
		assertEquals(constraints, school.getConstraints());
	}
	@Test
	void testSetSchoolClasses() {
		assertThrows(IllegalArgumentException.class, () -> {
			HashSet<SchoolClass> schoolClassesNull = new HashSet<>();
			school.setSchoolClasses(schoolClassesNull);
		});

		Subject subjectE = new Subject("Englisch", "E");
		WeeklySubject weeklySubject = new WeeklySubject(subjectE, 2);
		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		weeklySubjects.add(weeklySubject);

		HashSet<SchoolClass> schoolClasses = new HashSet<>();
		SchoolClass newSchoolClass = new SchoolClass("5BHIF", weeklySubjects);
		schoolClasses.add(newSchoolClass);

		school.setSchoolClasses(schoolClasses);

		assertEquals("5BHIF", school.getSchoolClasses().iterator().next().getName());
	}

	@Test
	void testAddingRemovingAndSettingTeachers() {
		/**
		 * Test if teachers are null
		 */
		assertThrows(IllegalArgumentException.class, () -> {
			HashSet<Teacher> teachersNull = new HashSet<>();
			school.setTeachers(teachersNull);
		});

		/**
		 * Test if teacher is Wellisch when setting teachers to Wellisch
		 */
		HashSet<Subject> wellischSubjects = new HashSet<>();
		Subject subjectE = new Subject("Englisch", "E");
		wellischSubjects.add(subjectE);
		Teacher wellisch = new Teacher("Wellisch", wellischSubjects, 5, 5);

		HashSet<Teacher> teachers = new HashSet<>();
		teachers.add(wellisch);

		school.setTeachers(teachers);

		assertEquals("Wellisch", school.getTeachers().iterator().next().getName());

		/**
		 * Test if teachers are empty
		 */
		assertThrows(IllegalArgumentException.class, () -> {
			teachers.clear();
			school.setTeachers(teachers);
		});

		/**
		 * Test if teachers contains Kerschner when adding Kerschner
		 */
		HashSet<Subject> kerschnerSubjects = new HashSet<>();
		Subject subjectAM = new Subject("Angewandte Mathematik", "AM");
		kerschnerSubjects.add(subjectAM);
		Teacher kerschner = new Teacher("Kerschner", kerschnerSubjects, 7, 3);

		school.addTeacher(kerschner);
		assertTrue(school.getTeachers().contains(kerschner));

		/**
		 * Test if teachers doesn't contain kerschner when removing Kerschner
		 */

		school.removeTeacher(kerschner);
		assertFalse(school.getTeachers().contains(kerschner));
	}

	@Test
	void testAddAndRemoveSchoolClass() {
		Subject subjectE = new Subject("Englisch", "E");
		WeeklySubject weeklySubject = new WeeklySubject(subjectE, 2);
		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		weeklySubjects.add(weeklySubject);

		HashSet<SchoolClass> schoolClasses = new HashSet<>();
		SchoolClass newSchoolClass = new SchoolClass("5BHIF", weeklySubjects);
		schoolClasses.add(newSchoolClass);

		school.addSchoolClass(newSchoolClass);
		assertTrue(school.getSchoolClasses().contains(newSchoolClass));

		school.removeSchoolClass(newSchoolClass);
		assertFalse(school.getSchoolClasses().contains(newSchoolClass));
	}

	@Test
	void generateTimetables() {
		school.generateTimetables(5, 10);
	}

	@Test
	void exportAllTimetables() {

	}
}