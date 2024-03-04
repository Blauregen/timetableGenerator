package at.htl.timetableGenerator.constraints.constraints;

import at.htl.timetableGenerator.constraints.Constraint;
import at.htl.timetableGenerator.model.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

/**
 * This class represents a constraint that ensures no more than three consecutive lessons
 * of the same subject are scheduled in a row.
 * It implements the Constraint interface.
 */
public class NoMoreThanTwoInRowConstraint implements Constraint {

	/**
	 * Checks if the given lesson violates the constraint in the given timetable.
	 * The constraint is violated if there are more than three consecutive lessons of the same
	 * subject.
	 *
	 * @param timetable The timetable to check the constraint against.
	 * @param lesson    The lesson to check the constraint for.
	 * @param teachers  The set of teachers. This parameter is not used in this method.
	 * @param rooms     The map of room names to rooms. This parameter is not used in this method.
	 *
	 * @return true if the constraint is satisfied, false otherwise.
	 */
	@Override
	public boolean check(@NotNull Timetable timetable, @NotNull Lesson lesson,
	                     Set<Teacher> teachers, Map<String, Room> rooms) {
		TimeSlot timeSlot = lesson.getTimeSlot();
		Subject subject = lesson.getSubject();

		if (timeSlot.getHour() < 3) {
			return true;
		}

		return timetable.getLesson(timeSlot.prevHour()).getSubject() != subject ||
		       timetable.getLesson(timeSlot.prevHour().prevHour()).getSubject() != subject;
	}
}
