package at.htl.timetableGenerator;

import org.jetbrains.annotations.NotNull;

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

	public SchoolClass getSchoolClass() {
		return schoolClass;
	}

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
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param o The reference object with which to compare
	 *
	 * @return True if this object has the same subject and timeSlot as the obj argument; false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Lesson lesson = (Lesson) o;
		return Objects.equals(subject, lesson.subject) && Objects.equals(timeSlot, lesson.timeSlot);
	}

	/**
	 * Returns a hash code value for the object.
	 *
	 * @return A hash code value for this object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(subject, timeSlot);
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
	 * If the teacher's occupied lessons do not contain this lesson, it also sets this lesson to the teacher.
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