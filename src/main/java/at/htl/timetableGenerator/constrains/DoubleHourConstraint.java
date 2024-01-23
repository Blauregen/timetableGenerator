package at.htl.timetableGenerator.constrains;

import at.htl.timetableGenerator.*;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * This class represents a double hour constraint in a timetable.
 * A double hour constraint checks if a course is scheduled for two consecutive hours.
 * It implements the Constraint interface and overrides the check method.
 */
public class DoubleHourConstraint implements Constraint {

	/**
	 * Checks if the given timetable, time slot, course, and set of teachers meet this double hour constraint.
	 * The constraint is met if the course is scheduled for two consecutive hours.
	 *
	 * @param timetable the timetable to check
	 * @param timeSlot  the time slot to check
	 * @param course    the course to check
	 * @param teachers  the set of teachers to check
	 *
	 * @return true if the constraint is met, false otherwise
	 */
	@Override
	public boolean check(@NotNull Timetable timetable, TimeSlot timeSlot, Subject course, Set<Teacher> teachers) {
		try {
			if (timetable.hasAvailableDoubleHourSpot(course)) {
				if (timeSlot.getHour() != 0) {
					return timetable.getLesson(timeSlot.prevHour()).getSubject() == course;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
}