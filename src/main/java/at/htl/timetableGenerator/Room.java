package at.htl.timetableGenerator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Room {
	private final String name;
	private final Timetable timetable = new Timetable(7, 24);

	public Room(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public @NotNull Timetable getTimetable() {
		return timetable;
	}

	public Lesson getLesson(@NotNull TimeSlot timeSlot) {
		return timetable.getLesson(timeSlot);
	}

	public void setLesson(@NotNull Lesson lesson) {
		timetable.setLesson(lesson);

		if (lesson.getRoom() != this) {
			lesson.setRoom(this);
		}
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return name;
	}
}
