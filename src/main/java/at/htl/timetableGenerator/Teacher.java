package at.htl.timetableGenerator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Set;

/**
 * This class represents a teacher.
 * A teacher is defined by a name, a set of subjects they can teach, and a timetable of occupied
 * lessons.
 */
public class Teacher {
	private final String name;  // The name of the teacher
	private Set<Subject> subjects;  // The set of subjects this teacher can teach
	private Timetable occupiedLessons; // The timetable of lessons this teacher is occupied with

	/**
	 * Constructs a new Teacher with the specified name and subjects.
	 *
	 * @param name        the name of the teacher
	 * @param subjects    the set of subjects this teacher can teach
	 * @param hoursPerDay number of hours per day that the teacher teaches
	 * @param daysPerWeek number of days that the teacher teaches
	 */
	public Teacher(String name, Set<Subject> subjects, int hoursPerDay, int daysPerWeek) {
		this.name = name;
		this.subjects = subjects;
		this.occupiedLessons = new Timetable(daysPerWeek, hoursPerDay);
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
	public Set<Subject> getSubjects() {
		return subjects;
	}

	/**
	 * Sets the subjects this teacher can teach.
	 *
	 * @param subjects the set of subjects this teacher can teach
	 */
	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	/**
	 * Returns the timetable of lessons this teacher is occupied with.
	 *
	 * @return the timetable of lessons this teacher is occupied with
	 */
	public Timetable getTimetable() {
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
	 **/
	public Lesson getLesson(@NotNull TimeSlot timeSlot) {
		return occupiedLessons.getLesson(timeSlot);
	}

	/**
	 * Sets the lesson for this teacher in the occupiedLessons timetable.
	 * If the teacher of the lesson is not this teacher, it also sets this teacher to the lesson.
	 *
	 * @param lesson The lesson to set
	 */
	public void setLesson(@NotNull Lesson lesson) {
		this.occupiedLessons.setLesson(lesson);

		if (lesson.getTeacher() != this) {
			lesson.setTeacher(this);
		}
	}

	/**
	 * Returns a string representation of the teacher.
	 *
	 * @return The name of the teacher.
	 */
	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * Adds a subject to the set of subjects this teacher can teach.
	 *
	 * @param subject The subject to add.
	 */
	public void addSubject(Subject subject) {
		subjects.add(subject);
	}

	/**
	 * Removes a subject from the set of subjects this teacher can teach.
	 *
	 * @param subject The subject to remove.
	 */
	public void removeSubject(Subject subject) {
		subjects.remove(subject);
	}

	/**
	 * Checks if the given object is equal to this teacher.
	 * Two teachers are considered equal if their names are equal.
	 *
	 * @param o The object to compare this teacher against.
	 *
	 * @return true if the given object represents a teacher equivalent to this teacher,
	 * false otherwise.
	 */
	@Override
	public boolean equals(@Nullable Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Teacher teacher = (Teacher) o;
		return Objects.equals(name, teacher.name);
	}

	/**
	 * Returns a hash code value for this teacher.
	 * The hash code is computed based on the name of the teacher.
	 * This method is supported for the benefit of hash tables such as those provided by HashMap.
	 *
	 * @return a hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}