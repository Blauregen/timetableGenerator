package at.htl.timetableGenerator.constraints.constraints;

import at.htl.timetableGenerator.constraints.Constraint;
import at.htl.timetableGenerator.model.Lesson;
import at.htl.timetableGenerator.model.Room;
import at.htl.timetableGenerator.model.Teacher;
import at.htl.timetableGenerator.model.Timetable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

/**
 * This class represents a double hour constraint in a timetable.
 * A double hour constraint checks if a course is scheduled for two consecutive hours.
 * It implements the Constraint interface and overrides the check method.
 */
public class DoubleHourConstraint implements Constraint {

	/**
	 * Checks if the given timetable, time slot, course, and set of teachers meet this double hour
	 * constraint.
	 * The constraint is met if the course is scheduled for two consecutive hours.
	 *
	 * @param timetable the timetable to check
	 * @param lesson    the lesson to check
	 * @param teachers  the set of teachers to check
	 *
	 * @return true if the constraint is met, false otherwise
	 */
	@Override
	public boolean check(@NotNull Timetable timetable, @NotNull Lesson lesson,
	                     Set<Teacher> teachers, Map<String, Room> rooms) {
		//If there is no spot for a Double Hour available, return true for this constraint, since
		// this restraint
		// becomes irrelevant in that case
		if (!timetable.hasAvailableDoubleHourSpot(lesson.getSubject())) {
			return true;
		}

		//If there is a spot for a Double Hour available, and the current Lesson is in the first
		// hour, return
		//false, since it can't be the double hour spot
		if (lesson.getTimeSlot().getHour() == 0) {
			return false;
		}

		//Otherwise return true if the previous lesson is the same subject as this lesson (i.e. it
		// would form a double
		//hour)
		return timetable.getLesson(lesson.getTimeSlot().prevHour()).getSubject() ==
		       lesson.getSubject();
	}
}