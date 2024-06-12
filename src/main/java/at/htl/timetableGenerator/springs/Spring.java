package at.htl.timetableGenerator.springs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;

public record Spring(int srcHour, DayOfWeek srcDay, int destHour, DayOfWeek destDay, int strength) {
	@Contract(pure = true)
	@Override
	public @NotNull String toString() {
		return "Spring{" +
		       "srcHour=" + srcHour +
		       ", srcDay=" + srcDay +
		       ", destHour=" + destHour +
		       ", destDay=" + destDay +
		       ", strength=" + strength +
		       '}';
	}
}
