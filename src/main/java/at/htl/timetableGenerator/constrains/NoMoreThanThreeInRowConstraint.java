package at.htl.timetableGenerator.constrains;

import at.htl.timetableGenerator.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class NoMoreThanThreeInRowConstraint implements Constraint {
	@Override
	public boolean check(@NotNull Timetable timetable, @NotNull Lesson lesson,
	                     Set<Teacher> teachers, Map<String, Room> rooms) {
		TimeSlot timeSlot = lesson.getTimeSlot();
		Subject subject = lesson.getSubject();

		if (timeSlot.getHour() < 3) {
			return true;
		}

		return timetable.getLesson(timeSlot.prevHour()).getSubject() != subject ||
		       timetable.getLesson(timeSlot.prevHour().prevHour()).getSubject() != subject ||
		       timetable.getLesson(timeSlot.prevHour().prevHour().prevHour()).getSubject() !=
		       subject;
	}
}
