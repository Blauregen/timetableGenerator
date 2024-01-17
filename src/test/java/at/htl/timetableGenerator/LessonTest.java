package at.htl.timetableGenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LessonTest {

	@BeforeEach
	void setup(){

	}
	@Test
	void testConstructor() {
		Course course = new Course("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.FRIDAY, 8);
		Lesson lesson = new Lesson(course, timeslot);

		assertEquals(lesson.getCourse(), course);
		assertEquals(lesson.getTimeSlot(), timeslot);
	}

	@Test
	void testSetters(){
		Course course = new Course("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.SATURDAY, 9);
		Lesson lesson = new Lesson(course, timeslot);
		Course newCourse = new Course("English", "E");
		TimeSlot newTimeSlot = new TimeSlot(DayOfWeek.MONDAY, 7);

		lesson.setCourse(newCourse);
		lesson.setTimeSlot(newTimeSlot);

		assertEquals(newCourse, lesson.getCourse());
		assertEquals(newTimeSlot, lesson.getTimeSlot());
	}

	@Test
	void testToString(){
		Course course = new Course("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.SATURDAY, 9);
		Lesson lesson = new Lesson(course, timeslot);

		assertEquals("SA 9: M", lesson.toString());
	}

}