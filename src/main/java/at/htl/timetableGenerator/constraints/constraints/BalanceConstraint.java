package at.htl.timetableGenerator.constraints.constraints;

import at.htl.timetableGenerator.constraints.Constraint;
import at.htl.timetableGenerator.model.Lesson;
import at.htl.timetableGenerator.model.Room;
import at.htl.timetableGenerator.model.Teacher;
import at.htl.timetableGenerator.model.Timetable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class BalanceConstraint implements Constraint {
	@Override
	public boolean isRequired() {
		return true;
	}

	@Override
	public boolean check(@NotNull Timetable timetable, Lesson lesson, Set<Teacher> teachers,
	                     Map<String, Room> rooms) {
		int maxScorePerDay =
				(int) Math.ceil((double) timetable.getMaxTotalScore() / timetable.getNoOfDayPerWeek());

		if (lesson.getSubject().score() > maxScorePerDay) {
			return true;
		}

		timetable.setLesson(lesson);

		if (timetable.getScoreForDay(lesson.getTimeSlot().getDay()) > maxScorePerDay) {
			timetable.setLesson(new Lesson(Timetable.FREISTUNDE, lesson.getTimeSlot()));
			return false;
		} else {
			timetable.setLesson(new Lesson(Timetable.FREISTUNDE, lesson.getTimeSlot()));
		}

		return true;
	}
}
