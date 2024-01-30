package at.htl.timetableGenerator.constrains;

import at.htl.timetableGenerator.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static at.htl.timetableGenerator.Timetable.FREISTUNDE;

/**
 * This class represents a teacher constraint in a timetable.
 * A teacher constraint checks if a teacher is available for a given course at a given time slot.
 * It implements the Constraint interface and overrides the check and updateOnSuccess methods.
 */
public class RoomConstraint implements Constraint {

	/**
	 * Checks if the given timetable, time slot, course, and set of teachers meet this teacher
	 * constraint.
	 * The constraint is met if there is a teacher who can teach the course and is available at the
	 * given time slot.
	 *
	 * @param timetable the timetable to check
	 * @param lesson    the lesson to check
	 * @param teachers  the set of teachers to check
	 *
	 * @return true if the constraint is met, false otherwise
	 */
	@Override
	public boolean check(Timetable timetable, @NotNull Lesson lesson,
	                     @NotNull Set<Teacher> teachers, @NotNull Map<String, Room> rooms) {
		for (Room room : rooms.values()) {
			if (room.getLesson(lesson.getTimeSlot()).getSubject() == FREISTUNDE) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Updates the state of this constraint when the check is successful.
	 * The state is updated by setting the course at the given time slot in the timetable of the
	 * teacher who can teach
	 * the course and is available.
	 *
	 * @param timetable the timetable that met the constraint
	 * @param lesson    the lesson to check
	 * @param teachers  the set of teachers that met the constraint
	 */
	@Override
	public void updateOnSuccess(Timetable timetable, @NotNull Lesson lesson,
	                            @NotNull Set<Teacher> teachers, @NotNull Map<String, Room> rooms) {
		Subject subject = lesson.getSubject();
		TimeSlot timeSlot = lesson.getTimeSlot();

		for (Room room : rooms.values()) {
			if (room.getLesson(timeSlot).getSubject() == FREISTUNDE) {
				room.setLesson(lesson);
				return;
			}
		}
	}
}