package at.htl.timetableGenerator.constraints.constraints;

import at.htl.timetableGenerator.constraints.Constraint;
import at.htl.timetableGenerator.constraints.DurationKeyword;
import at.htl.timetableGenerator.model.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CustomDurationConstraint implements Constraint {
	private final int length;
	private final DurationKeyword keyword;
	private final boolean isRequired;
	private String subject;

	public CustomDurationConstraint(String subject, int duration, DurationKeyword keyword,
	                                boolean isRequired) {
		this.subject = subject;
		this.length = duration;
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
		subject = subject.replaceAll("\\*", ".*");

		if (lesson.getSubject().name().matches(subject)) {
			if (Objects.requireNonNull(keyword) == DurationKeyword.LONGER) {
				TimeSlot timeSlot = lesson.getTimeSlot();
				Subject subject = lesson.getSubject();

				if (timeSlot.getHour() < this.length) {
					return true;
				}

				boolean toReturn = false;

				TimeSlot currentSlot = lesson.getTimeSlot();
				for (int i = 0; i < length; i++) {

					if (timetable.getLesson(currentSlot.prevHour()).getSubject() != subject) {
						return false;
					} else {
						currentSlot = currentSlot.prevHour();
					}
				}

				return toReturn;
			}
			return false;
		} else {
			return true;
		}
	}
}
