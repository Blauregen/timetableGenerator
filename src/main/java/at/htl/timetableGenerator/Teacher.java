package at.htl.timetableGenerator;

import java.util.Set;

/**
 * This class represents a teacher in a timetable.
 * A teacher is defined by a name, a set of subjects they can teach, and a timetable of occupied lessons.
 */
public class Teacher {
	private final String name;  // The name of the teacher
	private Set<Course> subjects;  // The set of subjects this teacher can teach
	private Timetable occupiedLessons;  // The timetable of lessons this teacher is occupied with

	/**
	 * Constructs a new Teacher with the specified name and subjects.
	 *
	 * @param name     the name of the teacher
	 * @param subjects the set of subjects this teacher can teach
	 */
	public Teacher(String name, Set<Course> subjects) {
		this.name = name;
		this.subjects = subjects;
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
}