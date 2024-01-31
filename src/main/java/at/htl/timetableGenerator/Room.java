package at.htl.timetableGenerator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * This class represents a room in a timetable.
 * A room has a name and a timetable.
 */
public class Room {
	private final String name;
	private final Timetable timetable = new Timetable(7, 24);

	/**
	 * Constructs a new Room with the specified name.
	 *
	 * @param name The name of the room
	 */
	public Room(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the room.
	 *
	 * @return The name of the room
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the timetable of the room.
	 *
	 * @return The timetable of the room
	 */
	public @NotNull Timetable getTimetable() {
		return timetable;
	}

	/**
	 * Returns the lesson at the specified time slot in the room's timetable.
	 *
	 * @param timeSlot The time slot of the lesson
	 *
	 * @return The lesson at the specified time slot
	 */
	public Lesson getLesson(@NotNull TimeSlot timeSlot) {
		return timetable.getLesson(timeSlot);
	}

	/**
	 * Sets the lesson at the specified time slot in the room's timetable.
	 * If the room of the lesson is not this room, it also sets the room of the lesson to this
	 * room.
	 *
	 * @param lesson The lesson to set
	 */
	public void setLesson(@NotNull Lesson lesson) {
		timetable.setLesson(lesson);

		if (lesson.getRoom() != this) {
			lesson.setRoom(this);
		}
	}

	/**
	 * Checks if this room is equal to another object.
	 * The comparison is based on the name of the room.
	 *
	 * @param o The object to compare this room to
	 *
	 * @return true if the object is a room and has the same name as this room; false otherwise
	 */
	@Override
	public boolean equals(@Nullable Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Room room = (Room) o;
		return Objects.equals(name, room.name);
	}

	/**
	 * Returns a hash code value for the room.
	 * The hash code is based on the name of the room.
	 *
	 * @return a hash code value for the room
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	/**
	 * Returns a string representation of the room.
	 * The string representation is the name of the room.
	 *
	 * @return a string representation of the room
	 */
	@Override
	public String toString() {
		return name;
	}
}
