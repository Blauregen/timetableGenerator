package at.htl.timetableGenerator.model;

import at.htl.timetableGenerator.output.ExportData;
import at.htl.timetableGenerator.output.ExportFormat;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SchoolTest {

	School school;

	@NotNull
	private static SchoolClass getSchoolClass() {
		Subject subject1 = new Subject("Angewandte Mathematik", "AM", 3);
		Subject subject2 = new Subject("Englisch", "E", 2);
		Subject subject3 = new Subject("Software Entwicklung", "SEW", 2);

		WeeklySubject weeklySubject1 = new WeeklySubject(subject1, 5);
		WeeklySubject weeklySubject2 = new WeeklySubject(subject2, 2);
		WeeklySubject weeklySubject3 = new WeeklySubject(subject3, 3);

		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		weeklySubjects.add(weeklySubject1);
		weeklySubjects.add(weeklySubject2);
		weeklySubjects.add(weeklySubject3);

		return new SchoolClass("3BHITM", weeklySubjects);
	}

	@BeforeEach
	void setup() {
		Subject subjectE = new Subject("Englisch", "E", 2);
		Subject subjectAM = new Subject("Angewandte Mathematik", "AM", 3);
		WeeklySubject weeklySubject1 = new WeeklySubject(subjectE, 2);
		WeeklySubject weeklySubject2 = new WeeklySubject(subjectAM, 1);
		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		HashSet<WeeklySubject> weeklySubjects2 = new HashSet<>();
		weeklySubjects.add(weeklySubject1);
		weeklySubjects.add(weeklySubject2);
		weeklySubjects2.add(weeklySubject2);
		SchoolClass schoolClass = new SchoolClass("5AHIF", weeklySubjects);
		SchoolClass schoolClass2 = new SchoolClass("5AHIF", weeklySubjects2);
		HashSet<SchoolClass> schoolClasses = new HashSet<>();
		schoolClasses.add(schoolClass);
		schoolClasses.add(schoolClass2);

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
	void testSetSchoolClasses() {
		assertThrows(IllegalArgumentException.class, () -> {
			HashSet<SchoolClass> empty = new HashSet<>();
			school.setSchoolClasses(empty);
		});

		Subject subjectE = new Subject("Englisch", "E", 2);
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
		/*
		 * Test if teachers are null
		 */
		assertThrows(IllegalArgumentException.class, () -> {
			HashSet<Teacher> empty = new HashSet<>();
			school.setTeachers(empty);
		});

		/*
		 * Test if teacher is Wellisch when setting teachers to Wellisch
		 */
		HashSet<Subject> wellischSubjects = new HashSet<>();
		Subject subjectE = new Subject("Englisch", "E", 2);
		wellischSubjects.add(subjectE);
		Teacher wellisch = new Teacher("Wellisch", wellischSubjects, 5, 5);

		HashSet<Teacher> teachers = new HashSet<>();
		teachers.add(wellisch);

		school.setTeachers(teachers);

		assertEquals("Wellisch", school.getTeachers().iterator().next().getName());

		/*
		 * Test if teachers are empty
		 */
		assertThrows(IllegalArgumentException.class, () -> {
			teachers.clear();
			school.setTeachers(teachers);
		});

		/*
		 * Test if teachers contains Kerschner when adding Kerschner
		 */
		HashSet<Subject> kerschnerSubjects = new HashSet<>();
		Subject subjectAM = new Subject("Angewandte Mathematik", "AM", 3);
		kerschnerSubjects.add(subjectAM);
		Teacher kerschner = new Teacher("Kerschner", kerschnerSubjects, 7, 3);

		school.addTeacher(kerschner);
		assertTrue(school.getTeachers().contains(kerschner));

		/*
		 * Test if teachers doesn't contain kerschner when removing Kerschner
		 */

		school.removeTeacher(kerschner);
		assertFalse(school.getTeachers().contains(kerschner));
	}

	@Test
	void testAddAndRemoveSchoolClass() {
		Subject subjectE = new Subject("Englisch", "E", 2);
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
	void testAddAndRemoveRooms() {
		School htl = new School("HTBLA Leonding");
		Room diningHall = new Room("Dining Hall");
		htl.addRoom(diningHall);
		HashMap<String, Room> rooms = new HashMap<>();
		rooms.put("Dining Hall", diningHall);

		assertEquals(diningHall, htl.getRoom("Dining Hall"));
		assertEquals(diningHall, htl.getRooms().get("Dining Hall"));

		htl.setRooms(rooms);
		assertEquals(diningHall, htl.getRoom("Dining Hall"));
	}

	@Test
	void testGenerateTimetables() {
		school.generateTimetables(5, 10, 1);
	}

	@Test
	void testGenerateTimetablesInvalid() {
		assertThrows(IllegalArgumentException.class, () -> school.generateTimetables(1, 1, 1));
	}

	@Test
	void exportAllTimetables() {
		SchoolClass bhitm3 = getSchoolClass();

		HashSet<ExportData> exportData = new HashSet<>();
		exportData.add(ExportData.CLASSES);
		exportData.add(ExportData.TEACHERS);
		exportData.add(ExportData.ROOMS);

		HashSet<ExportFormat> exportFormats = new HashSet<>();
		exportFormats.add(ExportFormat.EXCEL);
		exportFormats.add(ExportFormat.CSV);
		exportFormats.add(ExportFormat.CSV_MULTIPLE);
		school.addSchoolClass(bhitm3);
		school.addRoom(new Room("101"));
		school.generateTimetables(5, 10, 1_000_000);

		exportData.remove(ExportData.CLASSES);
		exportData.remove(ExportData.TEACHERS);
		exportData.remove(ExportData.ROOMS);
		exportFormats.remove(ExportFormat.EXCEL);
		exportFormats.remove(ExportFormat.CSV);
		exportFormats.remove(ExportFormat.CSV_MULTIPLE);
	}
}