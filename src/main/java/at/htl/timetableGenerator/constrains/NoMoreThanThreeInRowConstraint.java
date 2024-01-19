package at.htl.timetableGenerator.constrains;

import at.htl.timetableGenerator.*;

import java.util.Set;

public class NoMoreThanThreeInRowConstraint implements Constraint {
	@Override
	public boolean check(Timetable timetable, TimeSlot timeSlot, Subject subject, Set<Teacher> teachers) {
		if (timeSlot.getHour() < 3) {
			return true;
		}

		return timetable.getLesson(timeSlot.prevHour()).getSubject() != subject ||
		       timetable.getLesson(timeSlot.prevHour().prevHour()).getSubject() != subject ||
		       timetable.getLesson(timeSlot.prevHour().prevHour().prevHour()).getSubject() != subject;
	}
}
