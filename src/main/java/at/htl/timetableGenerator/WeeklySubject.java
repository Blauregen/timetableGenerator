package at.htl.timetableGenerator;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * This class represents a subject that is taught a certain number of times per week.
 * A WeeklySubjects object is defined by a subject and the number of times it is taught per week.
 */
public class WeeklySubject {
	private Subject subject;  // The subject that is taught
	private int noPerWeek;  // The number of times the subject is taught per week

	/**
	 * Constructs a new WeeklySubjects with the given subject and number of times it is taught per
	 * week.
	 *
	 * @param subject   The subject that is taught.
	 * @param noPerWeek The number of times the subject is taught per week.
	 */
	public WeeklySubject(Subject subject, int noPerWeek) {
		this.subject = subject;
		this.noPerWeek = noPerWeek;
	}

	/**
	 * Checks if the given object is equal to this WeeklySubjects.
	 * Two WeeklySubjects are considered equal if their subjects and amount of them are equal .
	 *
	 * @param o The object to compare this WeeklySubjects against.
	 *
	 * @return true if the given object represents a WeeklySubjects with the same subject and
	 * noPerWeek as this
	 * WeeklySubjects, false otherwise.
	 */
	@Override
	public boolean equals(@Nullable Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		WeeklySubject that = (WeeklySubject) o;
		return Objects.equals(subject, that.subject) &&
		       this.noPerWeek == ((WeeklySubject) o).noPerWeek;
	}

	/**
	 * Returns a hash code value for this WeeklySubjects.
	 * This method is supported for the benefit of hash tables such as those provided by HashMap.
	 *
	 * @return a hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(subject);
	}

	/**
	 * Returns the subject that is taught.
	 *
	 * @return The subject that is taught.
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * Sets the subject that is taught.
	 *
	 * @param subject The subject to set.
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * Returns the number of times the subject is taught per week.
	 *
	 * @return The number of times the subject is taught per week.
	 */
	public int getNoPerWeek() {
		return noPerWeek;
	}

	/**
	 * Sets the number of times the subject is taught per week.
	 *
	 * @param noPerWeek The number of times to set.
	 */
	public void setNoPerWeek(int noPerWeek) {
		this.noPerWeek = noPerWeek;
	}

	/**
	 * Returns a string representation of this WeeklySubjects.
	 * The string representation is the subject and the number of times it is taught per week.
	 *
	 * @return a string representation of this WeeklySubjects.
	 */
	@Override
	public String toString() {
		return String.format("%s: %d", subject, noPerWeek);
	}
}