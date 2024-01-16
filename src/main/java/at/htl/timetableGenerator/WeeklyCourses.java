package at.htl.timetableGenerator;

import java.util.Objects;

/**
 * This class represents a course that is taught a certain number of times per week.
 * A WeeklyCourses object is defined by a course and the number of times it is taught per week.
 */
public class WeeklyCourses {
	private Course course;  // The course that is taught
	private int noPerWeek;  // The number of times the course is taught per week

	/**
	 * Constructs a new WeeklyCourses with the given course and number of times it is taught per week.
	 *
	 * @param course    The course that is taught.
	 * @param noPerWeek The number of times the course is taught per week.
	 */
	public WeeklyCourses(Course course, int noPerWeek) {
		this.course = course;
		this.noPerWeek = noPerWeek;
	}

	/**
	 * Checks if the given object is equal to this WeeklyCourses.
	 * Two WeeklyCourses are considered equal if their courses are equal.
	 *
	 * @param o The object to compare this WeeklyCourses against.
	 *
	 * @return true if the given object represents a WeeklyCourses equivalent to this WeeklyCourses, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		WeeklyCourses that = (WeeklyCourses) o;
		return Objects.equals(course, that.course);
	}

	/**
	 * Returns a hash code value for this WeeklyCourses.
	 * This method is supported for the benefit of hash tables such as those provided by HashMap.
	 *
	 * @return a hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(course);
	}

	/**
	 * Returns the course that is taught.
	 *
	 * @return The course that is taught.
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * Sets the course that is taught.
	 *
	 * @param course The course to set.
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * Returns the number of times the course is taught per week.
	 *
	 * @return The number of times the course is taught per week.
	 */
	public int getNoPerWeek() {
		return noPerWeek;
	}

	/**
	 * Sets the number of times the course is taught per week.
	 *
	 * @param noPerWeek The number of times to set.
	 */
	public void setNoPerWeek(int noPerWeek) {
		this.noPerWeek = noPerWeek;
	}

	/**
	 * Returns a string representation of this WeeklyCourses.
	 * The string representation is the course and the number of times it is taught per week.
	 *
	 * @return a string representation of this WeeklyCourses.
	 */
	@Override
	public String toString() {
		return String.format("%s: %d", course, noPerWeek);
	}
}