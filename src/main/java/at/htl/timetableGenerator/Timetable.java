package at.htl.timetableGenerator;

import at.htl.timetableGenerator.output.TimetablePrinter;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.HashMap;

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
	private HashMap<TimeSlot, Lesson> timetable;  // The timetable
	private int maxNoOfHoursPerDay;  // The maximum number of hours per day
	private int noOfDayPerWeek;  // The number of days per week

	/**
	 * Constructs a new Timetable with the specified number of days per week, maximum number of hours per day, and
	 * constraints.
	 *
	 * @param noOfDayPerWeek     the number of days per week
	 * @param maxNoOfHoursPerDay the maximum number of hours per day
	 */
	public Timetable(int noOfDayPerWeek, int maxNoOfHoursPerDay) {
		setNoOfDayPerWeek(noOfDayPerWeek);
		setMaxNoOfHoursPerDay(maxNoOfHoursPerDay);
		setTimetable(FREISTUNDE);
	}


	/**
	 * Returns the timetable.
	 *
	 * @return the timetable
	 */
	public HashMap<TimeSlot, Lesson> getTimetable() {
		return timetable;
	}

	/**
	 * Sets the timetable.
	 *
	 * @param timetable the timetable to set
	 */
	public void setTimetable(HashMap<TimeSlot, Lesson> timetable) {
		this.timetable = timetable;
	}

	/**
	 * Sets the timetable with a given course.
	 *
	 * @param fill the course to fill the timetable with
	 */
	public void setTimetable(Course fill) {
		HashMap<TimeSlot, Lesson> lessons = new HashMap<>();
		for (int i = 0; i < noOfDayPerWeek; i++) {
			for (int j = 0; j < maxNoOfHoursPerDay; j++) {
				Lesson lesson = new Lesson(fill, new TimeSlot(DayOfWeek.of(i + 1), j));
				lessons.put(lesson.getTimeSlot(), lesson);
			}
		}

		setTimetable(lessons);
	}

	/**
	 * Sets the lesson at a given time slot.
	 *
	 * @param lesson the lesson to set
	 */
	public void setLesson(Lesson lesson) {
		TimeSlot timeSlot = lesson.getTimeSlot();

		if (timeSlot.getDay().ordinal() > getNoOfDayPerWeek()) {
			throw new IllegalArgumentException("Day is invalid");
		}

		if (timeSlot.getHour() >= maxNoOfHoursPerDay || timeSlot.getHour() < 0) {
			throw new IllegalArgumentException("Time is invalid");
		}

		timetable.put(timeSlot, lesson);
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
		boolean[] toReturn = new boolean[1];

		timetable.forEach((slot, lesson) -> {
			if (lesson.getCourse() == course) {
				toReturn[0] = true;
			}
		});

		return toReturn[0];
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
		return timetable.get(timeSlot);
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

	public Lesson[][] getTimetableAsArray() {
		Lesson[][] lessonsArray = new Lesson[noOfDayPerWeek][maxNoOfHoursPerDay];
		Collection<Lesson> lessons = timetable.values();

		lessons.forEach(
				lesson -> lessonsArray[lesson.getTimeSlot().getDay().ordinal()][lesson.getTimeSlot().getHour()] =
						lesson);

		return lessonsArray;
	}
}
