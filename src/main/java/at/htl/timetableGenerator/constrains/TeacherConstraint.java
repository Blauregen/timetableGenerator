package at.htl.timetableGenerator.constrains;

import at.htl.timetableGenerator.*;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static at.htl.timetableGenerator.Timetable.FREISTUNDE;

/**
 * This class represents a teacher constraint in a timetable.
 * A teacher constraint checks if a teacher is available for a given course at a given time slot.
 * It implements the Constraint interface and overrides the check and updateOnSuccess methods.
 */
public class TeacherConstraint implements Constraint {

	/**
	 * Checks if the given timetable, time slot, course, and set of teachers meet this teacher constraint.
	 * The constraint is met if there is a teacher who can teach the course and is available at the given time slot.
	 *
	 * @param timetable the timetable to check
	 * @param timeSlot  the time slot to check
	 * @param subject   the course to check
	 * @param teachers  the set of teachers to check
	 *
	 * @return true if the constraint is met, false otherwise
	 */
	@Override
	public boolean check(Timetable timetable, TimeSlot timeSlot, Subject subject, @NotNull Set<Teacher> teachers) {
		for (Teacher teacher : teachers) {
			if (teacher.getSubjects().contains(subject) && teacher.getLesson(timeSlot).getSubject() == FREISTUNDE) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Updates the state of this constraint when the check is successful.
	 * The state is updated by setting the course at the given time slot in the timetable of the teacher who can teach
	 * the course and is available.
	 *
	 * @param timetable the timetable that met the constraint
	 * @param timeSlot  the time slot that met the constraint
	 * @param subject   the course that met the constraint
	 * @param teachers  the set of teachers that met the constraint
	 */
	@Override
	public void updateOnSuccess(Timetable timetable, TimeSlot timeSlot, Subject subject,
	                            @NotNull Set<Teacher> teachers) {
		for (Teacher teacher : teachers) {
			if (teacher.getSubjects().contains(subject) && teacher.getLesson(timeSlot).getSubject() == FREISTUNDE) {
				teacher.setLesson(new Lesson(subject, timeSlot));
				return;
			}
		}
	}
}