package at.htl.timetableGenerator;

import at.htl.timetableGenerator.model.TimeSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TimeSlotTest {
	TimeSlot slot;

	@BeforeEach
	void setUp() {
		slot = new TimeSlot(DayOfWeek.MONDAY, 0);
	}

	@Test
	void constructor() {
		assertEquals(DayOfWeek.MONDAY, slot.getDay());
		assertEquals(0, slot.getHour());
	}

	@Test
	void setDay() {
		slot.setDay(DayOfWeek.FRIDAY);
		assertEquals(DayOfWeek.FRIDAY, slot.getDay());
	}

	@Test
	void setHour() {
		slot.setHour(5);
		assertEquals(5, slot.getHour());
	}

	@Test
	void testEquals() {
		TimeSlot sameSlot = new TimeSlot(DayOfWeek.MONDAY, 0);
		TimeSlot notSameSlot = new TimeSlot(DayOfWeek.MONDAY, 1);
		TimeSlot alsoNotSameSlot = new TimeSlot(DayOfWeek.FRIDAY, 0);
		TimeSlot stillNotSameSlot = new TimeSlot(DayOfWeek.FRIDAY, 1);

		assertEquals(slot, slot);
		assertEquals(slot, sameSlot);
		assertNotEquals(slot, notSameSlot);
		assertNotEquals(slot, alsoNotSameSlot);
		assertNotEquals(slot, stillNotSameSlot);
		assertNotEquals(slot, null);
		assertNotEquals(slot, new Object());
	}

	@Test
	void testHashCode() {
		assertEquals(Objects.hash(DayOfWeek.MONDAY, 0), slot.hashCode());
	}

	@Test
	void testToString() {
		assertEquals("MO 0", slot.toString());
	}

	@Test
	void nextDay() {
		assertEquals(new TimeSlot(DayOfWeek.TUESDAY, 0), slot.nextDay());
	}

	@Test
	void prevDay() {
		assertEquals(new TimeSlot(DayOfWeek.SUNDAY, 0), slot.prevDay());
	}

	@Test
	void nextHour() {
		assertEquals(new TimeSlot(DayOfWeek.MONDAY, 1), slot.nextHour());
	}

	@Test
	void prevHour() {
		assertThrows(IllegalArgumentException.class, () -> slot.prevHour(),
		             "Trying to get timeslot for negative " + "time");

		slot.setHour(2);
		assertEquals(new TimeSlot(DayOfWeek.MONDAY, 1), slot.prevHour());
	}
}