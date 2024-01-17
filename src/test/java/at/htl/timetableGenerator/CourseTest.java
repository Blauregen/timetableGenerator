package at.htl.timetableGenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CourseTest {
	Course course;

	@BeforeEach
	void setup() {
		course = new Course("Angewandte Mathematik", "AM");
	}

	@Test
	void testToString() {
		assertEquals("AM", course.toString());
	}

	@Test
	void testEquals() {
		Course sameCourse = new Course("Angewandte Mathematik", "M");
		Course notSameCourse = new Course("Mathe", "AM");
		assertEquals(course, course);
		assertNotEquals(course, null);
		assertNotEquals(course, new Object());
		assertEquals(course, sameCourse);
		assertNotEquals(course, notSameCourse);
	}

	@Test
	void testHashCode() {
		assertEquals(2026504968, course.hashCode());
	}

	@Test
	void name() {
		assertEquals("Angewandte Mathematik", course.name());
	}

	@Test
	void shortName() {
		assertEquals("AM", course.shortName());
	}
}