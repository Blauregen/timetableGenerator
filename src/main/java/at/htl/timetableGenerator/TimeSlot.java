package at.htl.timetableGenerator;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.util.Objects;

/**
 * This class represents a time slot in a timetable.
 * A time slot is defined by a day of the week and an hour of the day.
 */
public final class TimeSlot {
	private DayOfWeek day;  // The day of the week for this time slot
	private int hour;  // The hour of the day for this time slot

	/**
	 * Constructs a new TimeSlot with the specified day and hour.
	 *
	 * @param day  the day of the week
	 * @param hour the hour of the day
	 */
	public TimeSlot(DayOfWeek day, int hour) {
		this.day = day;
		this.hour = hour;
	}

	/**
	 * Sets the day of the week for this time slot.
	 *
	 * @param day the day of the week
	 */
	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	/**
	 * Sets the hour of the day for this time slot.
	 *
	 * @param hour the hour of the day
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/**
	 * Returns the day of the week for this time slot.
	 *
	 * @return the day of the week
	 */
	public DayOfWeek day() {
		return day;
	}

	/**
	 * Returns the hour of the day for this time slot.
	 *
	 * @return the hour of the day
	 */
	public int hour() {
		return hour;
	}

	/**
	 * Checks if this time slot is equal to the specified object.
	 * The result is true if and only if the argument is not null and is a TimeSlot object that has the same day and
	 * hour as this object.
	 *
	 * @param obj the object to compare this TimeSlot against
	 *
	 * @return true if the given object represents a TimeSlot equivalent to this time slot, false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		var that = (TimeSlot) obj;
		return this.day.ordinal() == that.day.ordinal() && this.hour == that.hour;
	}

	/**
	 * Returns a hash code value for this time slot.
	 *
	 * @return a hash code value for this time slot
	 */
	@Override
	public int hashCode() {
		return Objects.hash(day, hour);
	}

	/**
	 * Returns a string representation of this time slot.
	 *
	 * @return a string representation of this time slot
	 */
	@Contract(pure = true)
	@Override
	public @NotNull String toString() {
		return String.format("%.2s %d", day, hour);
	}

	/**
	 * Returns a new TimeSlot that represents the next day at the same hour.
	 *
	 * @return a new TimeSlot that represents the next day at the same hour
	 */
	@Contract(" -> new")
	public @NotNull TimeSlot nextDay() {
		return new TimeSlot(this.day.plus(1), this.hour);
	}

	/**
	 * Returns a new TimeSlot that represents the previous day at the same hour.
	 *
	 * @return a new TimeSlot that represents the previous day at the same hour
	 */
	@Contract(" -> new")
	public @NotNull TimeSlot prevDay() {
		return new TimeSlot(this.day.minus(1), this.hour);
	}

	/**
	 * Returns a new TimeSlot that represents the same day at the next hour.
	 *
	 * @return a new TimeSlot that represents the same day at the next hour
	 */
	@Contract(value = " -> new", pure = true)
	public @NotNull TimeSlot nextHour() {
		return new TimeSlot(this.day, this.hour + 1);
	}

	/**
	 * Returns a new TimeSlot that represents the same day at the previous hour.
	 * Throws an IllegalArgumentException if the hour is 0.
	 *
	 * @return a new TimeSlot that represents the same day at the previous hour
	 *
	 * @throws IllegalArgumentException if the hour is 0
	 */
	@Contract(" -> new")
	public @NotNull TimeSlot prevHour() {
		if (hour == 0) {
			throw new IllegalArgumentException("Trying to get timeslot for negative time");
		}
		return new TimeSlot(this.day, this.hour - 1);
	}
}