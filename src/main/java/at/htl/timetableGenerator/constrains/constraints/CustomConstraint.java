package at.htl.timetableGenerator.constrains.constraints;

import at.htl.timetableGenerator.Model.Lesson;
import at.htl.timetableGenerator.Model.Room;
import at.htl.timetableGenerator.Model.Teacher;
import at.htl.timetableGenerator.Model.Timetable;
import at.htl.timetableGenerator.constrains.Constraint;
import at.htl.timetableGenerator.constrains.TimeKeyword;

import java.util.Map;
import java.util.Set;

public class CustomConstraint implements Constraint {
	private final String subject;
	private final int hour;
	private final TimeKeyword keyword;
	private final boolean isRequired;

	public CustomConstraint(String subject, int hour, TimeKeyword keyword, boolean isRequired) {
		this.subject = subject;
		this.hour = hour;
		this.keyword = keyword;
		this.isRequired = isRequired;
	}

	@Override
	public boolean isRequired() {
		return isRequired;
	}

	@Override
	public boolean check(Timetable timetable, Lesson lesson, Set<Teacher> teachers,
	                     Map<String, Room> rooms) {
		if (lesson.getSubject().name().equals(subject)) {
			switch (keyword) {
				case BEFORE -> {
					return lesson.getTimeSlot().getHour() >= hour - 1;
				}
				case AFTER -> {
					return lesson.getTimeSlot().getHour() <= hour - 1;
				}
				case ON -> {
					return lesson.getTimeSlot().getHour() != hour - 1;
				}
			}
		} else {
			return true;
		}
		return false;
	}
}
