package at.htl.timetableGenerator.constraints.constraints;

import at.htl.timetableGenerator.constraints.Constraint;
import at.htl.timetableGenerator.model.Lesson;
import at.htl.timetableGenerator.model.Room;
import at.htl.timetableGenerator.model.Teacher;
import at.htl.timetableGenerator.model.Timetable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static at.htl.timetableGenerator.model.Timetable.FREISTUNDE;

/**
 * This class represents a constraint in a timetable where no course is placed before the current
 * one.
 * It implements the Constraint interface and overrides the check method.
 */
public class NonePlacedBeforeConstraint implements Constraint {

	/**
	 * Checks if the given timetable, time slot, course, and set of teachers meet this constraint.
	 * The constraint is met if there is no course placed before the current one in the timetable.
	 *
	 * @param timetable the timetable to check
	 * @param lesson    the lesson to check
	 * @param teachers  the set of teachers to check
	 *
	 * @return true if the constraint is met, false otherwise
	 */
	@Override
	public boolean check(@NotNull Timetable timetable, @NotNull Lesson lesson, Set<Teacher> teachers,
	                     Map<String, Room> rooms) {
		return timetable.getLesson(lesson.getTimeSlot()).getSubject() == FREISTUNDE;
	}

	@Override
	public boolean isRequired() {
		return true;
	}
}