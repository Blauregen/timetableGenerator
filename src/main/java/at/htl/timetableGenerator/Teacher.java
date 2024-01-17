package at.htl.timetableGenerator;

import at.htl.timetableGenerator.exceptions.IncompatibleCourseException;

import java.util.Set;

/**
 * This class represents a teacher in a timetable.
 * A teacher is defined by a name, a set of subjects they can teach, and a timetable of occupied lessons.
 */
public class Teacher {
	private final String name;  // The name of the teacher
	private Set<Course> subjects;  // The set of subjects this teacher can teach
	private Timetable occupiedLessons; // The timetable of lessons this teacher is occupied with

	/**
	 * Constructs a new Teacher with the specified name and subjects.
	 *
	 * @param name            the name of the teacher
	 * @param subjects        the set of subjects this teacher can teach
	 * @param occupiedLessons a timetable with lessons that the teacher has assigned. Also determines the no of days
	 *                        and
	 *                        hours they can be assigned. Can be overwritten later.
	 */
	public Teacher(String name, Set<Course> subjects, Timetable occupiedLessons) {
		this.name = name;
		this.subjects = subjects;
		this.occupiedLessons = occupiedLessons;
	}

	/**
	 * Returns the name of this teacher.
	 *
	 * @return the name of this teacher
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the set of subjects this teacher can teach.
	 *
	 * @return the set of subjects this teacher can teach
	 */
	public Set<Course> getSubjects() {
		return subjects;
	}

	/**
	 * Sets the subjects this teacher can teach.
	 *
	 * @param subjects the set of subjects this teacher can teach
	 */
	public void setSubjects(Set<Course> subjects) {
		this.subjects = subjects;
	}

	/**
	 * Returns the timetable of lessons this teacher is occupied with.
	 *
	 * @return the timetable of lessons this teacher is occupied with
	 */
	public Timetable getOccupiedLessons() {
		return occupiedLessons;
	}

	/**
	 * Sets the timetable of lessons this teacher is occupied with.
	 *
	 * @param occupiedLessons the timetable of lessons this teacher is occupied with
	 */
	public void setOccupiedLessons(Timetable occupiedLessons) {
		this.occupiedLessons = occupiedLessons;
	}

	/**
	 * Returns the lesson at a given time slot.
	 *
	 * @param timeSlot The time slot to get the lesson for.
	 *
	 * @return The lesson at the given time slot.
	 *
	 * @throws IllegalArgumentException If the day or time of the time slot is invalid.
	 */
	public Lesson getLesson(TimeSlot timeSlot) {
		return occupiedLessons.getLesson(timeSlot);
	}

	/**
	 * Sets the lesson at a given time slot, and also sets this to be the teacher of said lesson
	 *
	 * @param timeSlot the time slot to set the lesson at
	 * @param course   the course to set
	 *
	 * @throws IncompatibleCourseException If the course is not a part of the subjects of the teacher
	 */
	public void setLesson(TimeSlot timeSlot, Course course) {
		occupiedLessons.setLesson(timeSlot, course);
	}
}