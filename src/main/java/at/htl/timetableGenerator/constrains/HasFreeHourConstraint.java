package at.htl.timetableGenerator.constrains;

import at.htl.timetableGenerator.*;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * This class represents a free hour constraint in a timetable.
 * A free hour constraint checks if a course is scheduled for a free hour.
 * It implements the Constraint interface and overrides the check method.
 */
public class HasFreeHourConstraint implements Constraint {

	/**
	 * Checks if the given timetable, time slot, course, and set of teachers meet this free hour constraint.
	 * The constraint is met if the course is scheduled for a free hour.
	 *
	 * @param timetable the timetable to check
	 * @param timeSlot  the time slot to check
	 * @param subject   the course to check
	 * @param teachers  the set of teachers to check
	 *
	 * @return true if the constraint is met, false otherwise
	 */
	@Override
	public boolean check(Timetable timetable, @NotNull TimeSlot timeSlot, Subject subject, Set<Teacher> teachers) {
		return timeSlot.getHour() != 3;
	}
}