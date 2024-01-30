package at.htl.timetableGenerator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * This class represents a lesson in a timetable.
 * A lesson has a subject, a time slot, and optionally a teacher.
 */
public class Lesson {
	private Subject subject;
	private TimeSlot timeSlot;
	private Teacher teacher;
	private SchoolClass schoolClass;
	private Room room;

	/**
	 * Constructs a new Lesson with the specified subject and time slot.
	 *
	 * @param subject  The subject of the lesson
	 * @param timeSlot The time slot of the lesson
	 */
	public Lesson(Subject subject, TimeSlot timeSlot) {
		this.subject = subject;
		this.timeSlot = timeSlot;
	}

	/**
	 * Returns the room of the lesson.
	 *
	 * @return The room of the lesson
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * Sets the room of the lesson.
	 * If the room's current lesson at this time slot is not this lesson, it also sets this lesson
	 * to the room.
	 *
	 * @param room The room to set
	 */
	public void setRoom(@NotNull Room room) {
		this.room = room;

		if (room.getLesson(timeSlot) != this) {
			room.setLesson(this);
		}
	}

	/**
	 * Returns the school class of the lesson.
	 *
	 * @return The school class of the lesson
	 */
	public SchoolClass getSchoolClass() {
		return schoolClass;
	}

	/**
	 * Sets the school class of the lesson.
	 * If the school class's current lesson at this time slot is not this lesson, it also sets this
	 * lesson to the school class.
	 *
	 * @param schoolClass The school class to set
	 */
	public void setSchoolClass(@NotNull SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
		if (schoolClass.getLesson(this.timeSlot) != this) {
			schoolClass.setLesson(this);
		}
	}

	/**
	 * Returns the subject of the lesson.
	 *
	 * @return The subject of the lesson
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * Sets the subject of the lesson.
	 *
	 * @param subject The subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * Checks if this lesson is equal to another object.
	 * The comparison is based on the subject, time slot, teacher, school class, and room of the
	 * lesson.
	 *
	 * @param o The object to compare this lesson to
	 *
	 * @return true if the object is a lesson and has the same subject, time slot, teacher, school
	 * class, and room as this lesson; false otherwise
	 */
	@Override
	public boolean equals(@Nullable Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Lesson lesson = (Lesson) o;
		return Objects.equals(subject, lesson.subject) &&
		       Objects.equals(timeSlot, lesson.timeSlot) &&
		       Objects.equals(teacher, lesson.teacher) &&
		       Objects.equals(schoolClass, lesson.schoolClass) && Objects.equals(room,
				lesson.room);
	}

	@Override
	public int hashCode() {
		return Objects.hash(subject, timeSlot, teacher, schoolClass, room);
	}

	/**
	 * Returns the time slot of the lesson.
	 *
	 * @return The time slot of the lesson
	 */
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	/**
	 * Sets the time slot of the lesson.
	 *
	 * @param timeSlot The time slot to set
	 */
	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	/**
	 * Returns a string representation of the lesson.
	 *
	 * @return A string representation of the lesson
	 */
	@Override
	public String toString() {
		return String.format("%s: %s", timeSlot, subject);
	}

	/**
	 * Returns the teacher of the lesson.
	 *
	 * @return The teacher of the lesson
	 */
	public Teacher getTeacher() {
		return teacher;
	}

	/**
	 * Sets the teacher of the lesson.
	 * If the teacher's occupied lessons do not contain this lesson, it also sets this lesson to
	 * the
	 * teacher.
	 *
	 * @param teacher The teacher to set
	 */
	public void setTeacher(@NotNull Teacher teacher) {
		this.teacher = teacher;

		if (teacher.getTimetable().getLesson(this.timeSlot) != this) {
			teacher.setLesson(this);
		}
	}
}