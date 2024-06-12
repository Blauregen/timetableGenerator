package at.htl.timetableGenerator.springs;

import at.htl.timetableGenerator.model.Lesson;
import at.htl.timetableGenerator.model.TimeSlot;
import at.htl.timetableGenerator.model.Timetable;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PotentialCalculator {
	public static int calculateTimetablePotential(@NotNull Timetable timetable) {
		List<Spring> springs = new ArrayList<>();
		List<Map.Entry<TimeSlot, Lesson>> sortedTT = timetable.getSortedTimetable();

		DayOfWeek currentDay = null;
		int lastHour = -1;

		for (Map.Entry<TimeSlot, Lesson> timetableEntry : sortedTT) {
			TimeSlot currentSlot = timetableEntry.getKey();
			if (currentDay == null || currentDay != timetableEntry.getKey().getDay()) {
				currentDay = timetableEntry.getKey().getDay();
				lastHour = -1;
			}

			if (lastHour != currentSlot.getHour() - 1) {
				springs.add(new Spring(lastHour, currentDay, currentSlot.getHour(), currentDay, 100));
			}

			lastHour = currentSlot.getHour();
		}

		springs.forEach(System.out::println);
		return springs.stream().mapToInt(Spring::strength).sum();
	}
}
