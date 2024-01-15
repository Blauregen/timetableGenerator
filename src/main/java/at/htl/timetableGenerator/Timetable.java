package at.htl.timetableGenerator;

import at.htl.timetableGenerator.constrains.DoubleHourConstraint;
import at.htl.timetableGenerator.constrains.TeacherConstraint;
import at.htl.timetableGenerator.output.TimetablePrinter;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents a timetable.
 * A timetable is defined by a number of days per week, a maximum number of hours per day, and a set of constraints.
 * It provides methods to get and set the timetable, check if a course is in the timetable, check if a double hour spot
 * is available for a course, and get a lesson at a given time slot.
 */
public class Timetable {
	public static final Course FREISTUNDE = new Course("Freistunde", "");
	public static final int MIN_NUMBER_OF_HOURS_PER_DAY = 1;
	public static final int MIN_NUMBER_OF_DAYS_PER_WEEK = 1;
	private Lesson[][] timetable;  // The timetable
	private int maxNoOfHoursPerDay;  // The maximum number of hours per day
	private int noOfDayPerWeek;  // The number of days per week
	private Set<Constraint> constraints = new HashSet<>();  // The set of constraints for this timetable

	/**
	 * Constructs a new Timetable with the specified number of days per week, maximum number of hours per day, and
	 * constraints.
	 *
	 * @param noOfDayPerWeek     the number of days per week
	 * @param maxNoOfHoursPerDay the maximum number of hours per day
	 * @param constraints        the set of constraints for this timetable
	 */
	public Timetable(int noOfDayPerWeek, int maxNoOfHoursPerDay, @NotNull Set<Constraint> constraints) {
		setNoOfDayPerWeek(noOfDayPerWeek);
		setMaxNoOfHoursPerDay(maxNoOfHoursPerDay);
		this.constraints = constraints.stream().filter((o) -> !(o instanceof DoubleHourConstraint ||
		                                                        o instanceof TeacherConstraint))
		                              .collect(Collectors.toSet());

		setTimetable(FREISTUNDE);
	}

	/**
	 * Constructs a new Timetable with the specified number of days per week and maximum number of hours per day.
	 *
	 * @param noOfDayPerWeek     the number of days per week
	 * @param maxNoOfHoursPerDay the maximum number of hours per day
	 */
	public Timetable(int noOfDayPerWeek, int maxNoOfHoursPerDay) {
		this(noOfDayPerWeek, maxNoOfHoursPerDay, new HashSet<>());
	}

	/**
	 * Returns the timetable.
	 *
	 * @return the timetable
	 */
	public Lesson[][] getTimetable() {
		return timetable;
	}

	/**
	 * Sets the timetable.
	 *
	 * @param timetable the timetable to set
	 */
	public void setTimetable(@NotNull Lesson[] @NotNull [] timetable) {
		if (timetable.length != noOfDayPerWeek) {
			throw new IllegalArgumentException("Too few/many days in the timetable");
		}

		this.timetable = timetable;
	}

	/**
	 * Sets the timetable with a given course.
	 *
	 * @param fill the course to fill the timetable with
	 */
	public void setTimetable(Course fill) {
		Lesson[][] lessons = new Lesson[this.noOfDayPerWeek][this.maxNoOfHoursPerDay];
		for (int i = 0; i < lessons.length; i++) {
			for (int j = 0; j < maxNoOfHoursPerDay; j++) {
				lessons[i][j] = new Lesson(fill, new TimeSlot(DayOfWeek.of(i + 1), j));
			}
		}

		setTimetable(lessons);
	}

	/**
	 * Sets the lesson at a given time slot.
	 *
	 * @param timeSlot the time slot to set the lesson at
	 * @param lesson   the lesson to set
	 */
	public void setLesson(@NotNull TimeSlot timeSlot, Course lesson) {
		if (timeSlot.day().ordinal() > getNoOfDayPerWeek()) {
			throw new IllegalArgumentException("Day is invalid");
		}

		if (timeSlot.hour() >= maxNoOfHoursPerDay || timeSlot.hour() < 0) {
			throw new IllegalArgumentException("Time is invalid");
		}

		timetable[timeSlot.day().ordinal()][timeSlot.hour()] =
				new Lesson(lesson, new TimeSlot(timeSlot.day(), timeSlot.hour()));
	}

	/**
	 * Returns the maximum number of hours per day.
	 *
	 * @return the maximum number of hours per day
	 */
	public int getMaxNoOfHoursPerDay() {
		return maxNoOfHoursPerDay;
	}

	/**
	 * Sets the maximum number of hours per day.
	 *
	 * @param maxNoOfHoursPerDay the maximum number of hours per day to set
	 */
	public void setMaxNoOfHoursPerDay(int maxNoOfHoursPerDay) {
		if (maxNoOfHoursPerDay < MIN_NUMBER_OF_HOURS_PER_DAY) {
			throw new IllegalArgumentException(
					"Max number of hours per day can't be smaller than " + MIN_NUMBER_OF_HOURS_PER_DAY + "!");
		}

		this.maxNoOfHoursPerDay = maxNoOfHoursPerDay;
	}

	/**
	 * Returns the number of days per week.
	 *
	 * @return the number of days per week
	 */
	public int getNoOfDayPerWeek() {
		return noOfDayPerWeek;
	}

	/**
	 * Sets the number of days per week.
	 *
	 * @param noOfDayPerWeek the number of days per week to set
	 */
	public void setNoOfDayPerWeek(int noOfDayPerWeek) {
		if (noOfDayPerWeek < MIN_NUMBER_OF_DAYS_PER_WEEK || noOfDayPerWeek > 7) {
			throw new IllegalArgumentException(
					"Number of days per week can't be smaller than " + MIN_NUMBER_OF_DAYS_PER_WEEK +
					" or bigger than 7!");
		}

		this.noOfDayPerWeek = noOfDayPerWeek;
	}

	/**
	 * Checks if the timetable contains a specific course.
	 *
	 * @param course The course to check for.
	 *
	 * @return true if the timetable contains the course, false otherwise.
	 */
	public boolean contains(Course course) {
		for (Lesson[] lessons : timetable) {
			for (Lesson lesson : lessons) {
				if (lesson.getCourse() == course) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks if the given time slot and course meet all constraints.
	 *
	 * @param timeSlot The time slot to check.
	 * @param course   The course to check.
	 *
	 * @return true if all constraints are met, false otherwise.
	 */
	private boolean checkConstraints(TimeSlot timeSlot, Course course) {
		for (Constraint constraint : constraints) {
			if (!constraint.check(this, timeSlot, course, new HashSet<>())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks if there is an available double hour spot for a given course.
	 * A double hour spot is defined as a free time slot that is preceded by the given course.
	 *
	 * @param course The course to check for.
	 *
	 * @return true if there is an available double hour spot, false otherwise.
	 */
	public boolean hasAvailableDoubleHourSpot(Course course) {
		for (int i = 0; i < timetable.length; i++) {
			for (int j = 1; j < timetable.length; j++) {
				TimeSlot currentSlot = new TimeSlot(DayOfWeek.of(i + 1), j);

				if (getLesson(currentSlot.prevHour()).getCourse() == course && checkConstraints(currentSlot, course)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Returns the lesson at a given time slot.
	 *
	 * @param timeSlot The time slot to get the lesson for.
	 *
	 * @return The lesson at the given time slot.
	 *
	 * @throws IllegalArgumentException If the day or time of the time slot is invalid.
	 */
	public Lesson getLesson(@NotNull TimeSlot timeSlot) {
		try {
			return timetable[timeSlot.day().ordinal()][timeSlot.hour()];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Tried to get Lesson with invalid day or time", e);
		}
	}

	/**
	 * Returns a string representation of this timetable.
	 *
	 * @return a string representation of this timetable
	 */
	@Override
	public String toString() {
		return TimetablePrinter.print(this);
	}
}
