package at.htl.timetableGenerator;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LessonTest {

	@Test
	void testConstructor() {
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.FRIDAY, 8);
		Lesson lesson = new Lesson(subject, timeslot);

		assertEquals(lesson.getSubject(), subject);
		assertEquals(lesson.getTimeSlot(), timeslot);
	}

	@Test
	void testSetters() {
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.SATURDAY, 9);
		Lesson lesson = new Lesson(subject, timeslot);
		Subject newSubject = new Subject("English", "E");
		TimeSlot newTimeSlot = new TimeSlot(DayOfWeek.MONDAY, 7);
		Room lazarett = new Room("Hildegards Lazarett");

		lesson.setSubject(newSubject);
		lesson.setTimeSlot(newTimeSlot);
		lesson.setRoom(lazarett);

		assertEquals(newSubject, lesson.getSubject());
		assertEquals(newTimeSlot, lesson.getTimeSlot());
		assertEquals(lazarett, lesson.getRoom());
	}

	@Test
	void testToString() {
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.SATURDAY, 9);
		Lesson lesson = new Lesson(subject, timeslot);

		assertEquals("SA 9: M", lesson.toString());
	}

	@Test
	void equalsReturnsTrueWhenAllFieldsAreEqual() {
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, 1);
		Teacher teacher = new Teacher("Friedrich II. (Leiningen)", new HashSet<>(), 5, 5);

		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		weeklySubjects.add(new WeeklySubject(subject, 4));
		SchoolClass schoolClass = new SchoolClass("1A", weeklySubjects);
		Room room = new Room("101");

		Lesson lesson1 = new Lesson(subject, timeSlot);
		lesson1.setTeacher(teacher);
		lesson1.setSchoolClass(schoolClass);
		lesson1.setRoom(room);

		Lesson lesson2 = new Lesson(subject, timeSlot);
		lesson2.setTeacher(teacher);
		lesson2.setSchoolClass(schoolClass);
		lesson2.setRoom(room);

		assertEquals(lesson1, lesson2);
	}

	@Test
	void equalsReturnsFalseWhenSubjectsAreDifferent() {
		Subject subject1 = new Subject("Maths", "M");
		Subject subject2 = new Subject("English", "E");
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, 1);
		Teacher teacher = new Teacher("Neidhart", new HashSet<>(), 5, 5);
		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		weeklySubjects.add(new WeeklySubject(subject1, 4));
		SchoolClass schoolClass = new SchoolClass("1A", weeklySubjects);
		Room room = new Room("101");

		Lesson lesson1 = new Lesson(subject1, timeSlot);
		lesson1.setTeacher(teacher);
		lesson1.setSchoolClass(schoolClass);
		lesson1.setRoom(room);

		Lesson lesson2 = new Lesson(subject2, timeSlot);
		lesson2.setTeacher(teacher);
		lesson2.setSchoolClass(schoolClass);
		lesson2.setRoom(room);

		assertNotEquals(lesson1, lesson2);
	}

	@Test
	void equalsReturnsFalseWhenRoomisDifferent() {
		Subject subject1 = new Subject("Maths", "M");
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, 1);
		Teacher teacher = new Teacher("Neidhart", new HashSet<>(), 5, 5);
		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		weeklySubjects.add(new WeeklySubject(subject1, 4));
		SchoolClass schoolClass = new SchoolClass("1A", weeklySubjects);
		Room room = new Room("101");
		Room room2 = new Room("102");

		Lesson lesson1 = new Lesson(subject1, timeSlot);
		lesson1.setTeacher(teacher);
		lesson1.setSchoolClass(schoolClass);
		lesson1.setRoom(room);

		Lesson lesson2 = new Lesson(subject1, timeSlot);
		lesson2.setTeacher(teacher);
		lesson2.setSchoolClass(schoolClass);
		lesson2.setRoom(room2);

		assertNotEquals(lesson1, lesson2);
	}

	@Test
	void equalsReturnsFalseWhenTimeslotsAreDifferent() {
		Subject subject1 = new Subject("Maths", "M");
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, 1);
		TimeSlot timeSlot2 = new TimeSlot(DayOfWeek.MONDAY, 2);
		Teacher teacher = new Teacher("Neidhart", new HashSet<>(), 5, 5);
		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		weeklySubjects.add(new WeeklySubject(subject1, 4));
		SchoolClass schoolClass = new SchoolClass("1A", weeklySubjects);
		Room room = new Room("101");

		Lesson lesson1 = new Lesson(subject1, timeSlot);
		lesson1.setTeacher(teacher);
		lesson1.setSchoolClass(schoolClass);
		lesson1.setRoom(room);

		Lesson lesson2 = new Lesson(subject1, timeSlot2);
		lesson2.setTeacher(teacher);
		lesson2.setSchoolClass(schoolClass);
		lesson2.setRoom(room);

		assertNotEquals(lesson1, lesson2);
	}

	@Test
	void equalsReturnsFalseWhenSchoolClassAreDifferent() {
		Subject subject1 = new Subject("Maths", "M");
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, 1);
		Teacher teacher = new Teacher("Neidhart", new HashSet<>(), 5, 5);
		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		weeklySubjects.add(new WeeklySubject(subject1, 4));
		SchoolClass schoolClass = new SchoolClass("1A", weeklySubjects);
		SchoolClass schoolClass2 = new SchoolClass("1B", weeklySubjects);
		Room room = new Room("101");

		Lesson lesson1 = new Lesson(subject1, timeSlot);
		lesson1.setTeacher(teacher);
		lesson1.setSchoolClass(schoolClass);
		lesson1.setRoom(room);

		Lesson lesson2 = new Lesson(subject1, timeSlot);
		lesson2.setTeacher(teacher);
		lesson2.setSchoolClass(schoolClass2);
		lesson2.setRoom(room);

		assertNotEquals(lesson1, lesson2);
	}

	@Test
	void equalsReturnsFalseWhenTeachersAreDifferent() {
		Subject subject1 = new Subject("Maths", "M");
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, 1);
		Teacher teacher = new Teacher("Neidhart", new HashSet<>(), 5, 5);
		Teacher teacher2 = new Teacher("Otto von Botenlauben", new HashSet<>(), 5, 5);
		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		weeklySubjects.add(new WeeklySubject(subject1, 4));
		SchoolClass schoolClass = new SchoolClass("1A", weeklySubjects);
		Room room = new Room("101");

		Lesson lesson1 = new Lesson(subject1, timeSlot);
		lesson1.setTeacher(teacher);
		lesson1.setSchoolClass(schoolClass);
		lesson1.setRoom(room);

		Lesson lesson2 = new Lesson(subject1, timeSlot);
		lesson2.setTeacher(teacher2);
		lesson2.setSchoolClass(schoolClass);
		lesson2.setRoom(room);

		assertNotEquals(lesson1, lesson2);
	}

	@Test
	void equalsReturnsFalseWhenComparedWithNull() {
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, 8);
		Lesson lesson = new Lesson(subject, timeSlot);

		assertNotEquals(lesson, null);
	}

	@Test
	void equalsReturnsFalseWhenComparedWithDifferentClass() {
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, 8);
		Lesson lesson = new Lesson(subject, timeSlot);

		assertNotEquals(lesson, new Object());
	}

	@Test
	void testHashCode() {
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.SATURDAY, 9);
		Lesson lesson = new Lesson(subject, timeslot);

		assertEquals(Objects.hash(subject, timeslot, null, null, null), lesson.hashCode());
	}

	@Test
	void testSetAndGetTeacher() {
		Set<Subject> subjects = new HashSet<>();
		Subject german = new Subject("German", "GE");
		Subject minnegesang = new Subject("Minnegesang", "MG");
		subjects.add(german);
		subjects.add(minnegesang);
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.THURSDAY, 7);

		Teacher derKuerenberger = new Teacher("Der Kürenberger", subjects, 10, 5);

		Lesson lesson = new Lesson(minnegesang, timeSlot);
		lesson.setTeacher(derKuerenberger);

		assertEquals(derKuerenberger, lesson.getTeacher());
		assertEquals(lesson, derKuerenberger.getLesson(timeSlot));
	}
}