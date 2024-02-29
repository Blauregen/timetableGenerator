package at.htl.timetableGenerator.constrains;

import at.htl.timetableGenerator.*;
import at.htl.timetableGenerator.constrains.constraints.RoomConstraint;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RoomConstraintTest {
	@Test
	void roomConstraintCheckReturnsTrueWhenRoomIsFree() {
		Timetable timetable = new Timetable(5, 10);
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, 8);
		Lesson lesson = new Lesson(subject, timeSlot);
		Set<Teacher> teachers = new HashSet<>();
		Map<String, Room> rooms = new HashMap<>();
		Room room = new Room("101");
		room.setLesson(new Lesson(Timetable.FREISTUNDE, timeSlot));
		rooms.put("101", room);

		RoomConstraint roomConstraint = new RoomConstraint();

		assertTrue(roomConstraint.check(timetable, lesson, teachers, rooms));
	}

	@Test
	void roomConstraintCheckReturnsFalseWhenNoRoomIsFree() {
		Timetable timetable = new Timetable(5, 10);
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, 8);
		Lesson lesson = new Lesson(subject, timeSlot);
		Set<Teacher> teachers = new HashSet<>();
		Map<String, Room> rooms = new HashMap<>();
		Room room = new Room("101");
		room.setLesson(lesson);
		rooms.put("101", room);

		RoomConstraint roomConstraint = new RoomConstraint();

		assertFalse(roomConstraint.check(timetable, lesson, teachers, rooms));
	}

	@Test
	void roomConstraintUpdatesRoomWhenFreeSlotExists() {
		Timetable timetable = new Timetable(5, 10);
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, 8);
		Lesson lesson = new Lesson(subject, timeSlot);
		Set<Teacher> teachers = new HashSet<>();
		Map<String, Room> rooms = new HashMap<>();
		Room room = new Room("101");
		room.setLesson(new Lesson(Timetable.FREISTUNDE, timeSlot));
		rooms.put("101", room);

		RoomConstraint roomConstraint = new RoomConstraint();
		roomConstraint.updateOnSuccess(timetable, lesson, teachers, rooms);

		assertEquals(lesson, room.getLesson(timeSlot));
	}

	@Test
	void roomConstraintDoesNotUpdateRoomWhenNoFreeSlotExists() {
		Timetable timetable = new Timetable(1, 2);
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.MONDAY, 0);
		Lesson lesson = new Lesson(subject, timeSlot);
		Lesson lesson2 = new Lesson(new Subject("German", "D"), timeSlot.nextHour());
		Set<Teacher> teachers = new HashSet<>();
		Map<String, Room> rooms = new HashMap<>();
		Room room = new Room("101");
		room.setLesson(lesson);
		room.setLesson(lesson2);
		rooms.put("101", room);

		RoomConstraint roomConstraint = new RoomConstraint();
		roomConstraint.updateOnSuccess(timetable, lesson2, teachers, rooms);

		assertNotEquals(lesson2, room.getLesson(timeSlot));
	}
}