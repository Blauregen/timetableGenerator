package at.htl.timetableGenerator.constraints.constraints;

import at.htl.timetableGenerator.constraints.Constraint;
import at.htl.timetableGenerator.constraints.TimeKeyword;
import at.htl.timetableGenerator.model.Lesson;
import at.htl.timetableGenerator.model.Room;
import at.htl.timetableGenerator.model.Teacher;
import at.htl.timetableGenerator.model.Timetable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class CustomConstraint implements Constraint {
	private final int hour;
	private final TimeKeyword keyword;
	private final boolean isRequired;
	private final String subject;

	@Contract(pure = true)
	public CustomConstraint(@NotNull String subject, int hour, TimeKeyword keyword, boolean isRequired) {
		this.subject = subject.replaceAll("\\*", ".*");
		this.hour = hour;
		this.keyword = keyword;
		this.isRequired = isRequired;
	}

	@Override
	public boolean isRequired() {
		return isRequired;
	}

	@Override
	public boolean check(Timetable timetable, @NotNull Lesson lesson, Set<Teacher> teachers,
	                     Map<String, Room> rooms) {

		if (lesson.getSubject().name().matches(subject)) {
			switch (keyword) {
				case BEFORE -> {
					return lesson.getTimeSlot().getHour() >= hour;
				}
				case AFTER -> {
					return lesson.getTimeSlot().getHour() <= hour;
				}
				case ON -> {
					return lesson.getTimeSlot().getHour() != hour;
				}
			}
		} else {
			return true;
		}
		return false;
	}
}
