package at.htl.timetableGenerator;

import java.util.Objects;

/**
 * Represents a course with a name and a short name.
 */
public record Course(String name, String shortName) {

	/**
	 * Returns a string representation of the course.
	 *
	 * @return The short name of the course.
	 */
	@Override
	public String toString() {
		return this.shortName();
	}

	/**
	 * Checks if the given object is equal to this Course.
	 * Two Courses are considered equal if their names are equal.
	 *
	 * @param o The object to compare this Course against.
	 *
	 * @return true if the given object represents a Course equivalent to this Course, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Course course = (Course) o;
		return Objects.equals(name, course.name);
	}

	/**
	 * Returns a hash code value for this Course.
	 * This method is supported for the benefit of hash tables such as those provided by HashMap.
	 *
	 * @return a hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
