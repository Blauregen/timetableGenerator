package at.htl.timetableGenerator;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class WeeklyCoursesTest {
	@Test
	void testConstructor(){
		Course mathCourse = new Course("Maths", "AM");
		int noPerWeek = 10;
		WeeklyCourses weeklyCourses = new WeeklyCourses(mathCourse, 10);

		assertEquals(noPerWeek, weeklyCourses.getNoPerWeek());
		assertEquals(mathCourse, weeklyCourses.getCourse());
	}

	@Test
	void testHashCode(){
		WeeklyCourses weeklyCourses = new WeeklyCourses(new Course("Maths", "AM"), 10);
		assertEquals(Objects.hash(weeklyCourses.getCourse()), weeklyCourses.hashCode());
	}

	@Test
	void testSetters(){
		WeeklyCourses weeklyCourses = new WeeklyCourses(new Course("Maths", "AM"), 10);
		int newNumbersPerWeek = 20;
		Course newCourse = new Course("Maths", "M");

		weeklyCourses.setCourse(newCourse);
		weeklyCourses.setNoPerWeek(newNumbersPerWeek);

		assertEquals(newCourse, weeklyCourses.getCourse());
		assertEquals(newNumbersPerWeek, weeklyCourses.getNoPerWeek());
	}

	@Test
	void testToString(){
		WeeklyCourses weeklyCourses = new WeeklyCourses(new Course("Maths", "AM"), 10);

		assertEquals("AM: 10", weeklyCourses.toString());
	}
}
