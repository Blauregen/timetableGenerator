package at.htl.timetableGenerator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SubjectTest {
	Subject subject;

	@BeforeEach
	void setup() {
		subject = new Subject("Angewandte Mathematik", "AM", 3);
	}

	@Test
	void testToString() {
		assertEquals("AM", subject.toString());
	}

	@Test
	void testEquals() {
		Subject sameSubject = new Subject("Angewandte Mathematik", "M", 3);
		Subject notSameSubject = new Subject("Mathe", "AM", 3);
		assertEquals(subject, subject);
		assertNotEquals(subject, null);
		assertNotEquals(subject, new Object());
		assertEquals(subject, sameSubject);
		assertNotEquals(subject, notSameSubject);
	}

	@Test
	void testHashCode() {
		assertEquals(Objects.hash(subject.name()), subject.hashCode());
	}

	@Test
	void testName() {
		assertEquals("Angewandte Mathematik", subject.name());
	}

	@Test
	void testShortName() {
		assertEquals("AM", subject.shortName());
	}
}