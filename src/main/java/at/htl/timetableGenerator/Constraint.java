package at.htl.timetableGenerator;

import java.util.Set;

/**
 * This interface represents a constraint in a timetable.
 * A constraint is a condition that must be met when generating a timetable.
 * It provides a method to check if a given timetable, time slot, course, and set of teachers meet the constraint.
 * It also provides a default method to update the state of the constraint when the check is successful.
 */
public interface Constraint {

	/**
	 * Checks if the given timetable, time slot, course, and set of teachers meet this constraint.
	 *
	 * @param timetable the timetable to check
	 * @param timeSlot  the time slot to check
	 * @param subject   the course to check
	 * @param teachers  the set of teachers to check
	 *
	 * @return true if the constraint is met, false otherwise
	 */
	boolean check(Timetable timetable, TimeSlot timeSlot, Subject subject, Set<Teacher> teachers);

	/**
	 * Updates the state of this constraint when the check is successful.
	 * This is a default method and does nothing unless overridden by a concrete implementation of this interface.
	 *
	 * @param timetable the timetable that met the constraint
	 * @param timeSlot  the time slot that met the constraint
	 * @param subject   the course that met the constraint
	 * @param teachers  the set of teachers that met the constraint
	 */
	default void updateOnSuccess(Timetable timetable, TimeSlot timeSlot, Subject subject, Set<Teacher> teachers) {
	}
}
