package at.htl.timetableGenerator.Model;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * This record represents a subject with a name and a short name.
 */
public record Subject(String name, String shortName) {

	/**
	 * Returns a string representation of the subject.
	 *
	 * @return The short name of the subject.
	 */
	@Override
	public String toString() {
		return this.shortName();
	}

	/**
	 * Checks if the given object is equal to this subject.
	 * Two subjects are considered equal if their names are equal.
	 *
	 * @param o The object to compare this subject against.
	 *
	 * @return true if the given object represents a subject with the same name as this subject,
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
		Subject subject = (Subject) o;
		return Objects.equals(name, subject.name);
	}

	/**
	 * Returns a hash code value for this subject.
	 * This method is supported for the benefit of hash tables such as those provided by HashMap.
	 *
	 * @return a hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
