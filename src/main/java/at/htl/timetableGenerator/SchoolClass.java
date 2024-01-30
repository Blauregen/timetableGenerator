package at.htl.timetableGenerator;

import at.htl.timetableGenerator.constrains.NonePlacedBeforeConstraint;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static at.htl.timetableGenerator.Timetable.FREISTUNDE;

/**
 * This class represents a school class in a school.
 * A school class is defined by a name, a set of constraints, and a list of weekly courses.
 */
public class SchoolClass {
	private final String name;  // The name of the school class
	private final Set<Constraint> constraints = new HashSet<>();
	// The set of constraints for this school class
	private HashSet<WeeklySubject> weeklySubjects;
	// The list of weekly courses for this school class
	private Timetable timetable;
	private Teacher formTeacher;

	/**
	 * Constructs a new SchoolClass with the specified name and weekly courses.
	 *
	 * @param name           the name of the school class
	 * @param weeklySubjects the list of weekly courses for this school class
	 */
	public SchoolClass(String name, @NotNull HashSet<WeeklySubject> weeklySubjects) {
		this.name = name;
		constraints.add(new NonePlacedBeforeConstraint());
		setWeeklySubjects(weeklySubjects);
	}

	/**
	 * Returns the form teacher of the school class.
	 *
	 * @return the form teacher of the school class
	 */
	public Teacher getFormTeacher() {
		return formTeacher;
	}

	/**
	 * Sets the form teacher of the school class.
	 *
	 * @param formTeacher the form teacher to set
	 */
	public void setFormTeacher(Teacher formTeacher) {
		this.formTeacher = formTeacher;
	}

	/**
	 * Adds a constraint to this school class.
	 *
	 * @param constraint the constraint to add
	 */
	public void addConstraint(Constraint constraint) {
		constraints.add(constraint);
	}

	/**
	 * Removes a constraint from this school class.
	 *
	 * @param constraint the constraint to remove
	 */
	public void removeConstraint(Constraint constraint) {
		constraints.remove(constraint);
	}

	/**
	 * Generates a timetable for this school class.
	 *
	 * @param daysPerWeek    the number of days per week
	 * @param maxHoursPerDay the maximum number of hours per day
	 * @param teachers       the set of teachers
	 * @param rooms          the set of rooms
	 *
	 * @return the generated timetable
	 */
	public Timetable generateTimetable(int daysPerWeek, int maxHoursPerDay, Set<Teacher> teachers,
	                                   Map<String, Room> rooms) {
		this.timetable = new Timetable(daysPerWeek, maxHoursPerDay, constraints);

		for (WeeklySubject weeklySubject : weeklySubjects) {
			for (int i = 0; i < weeklySubject.getNoPerWeek(); i++) {
				Lesson bestAvailableLesson =
						getBestAvailableLesson(timetable, weeklySubject.getSubject(), teachers,
								rooms);
				this.setLesson(bestAvailableLesson);
			}
		}

		return this.timetable;
	}

	/**
	 * Returns the constraints of the school class.
	 *
	 * @return the constraints of the school class
	 */
	public @NotNull Set<Constraint> getConstraints() {
		return constraints;
	}

	/**
	 * Returns the timetable of the school class.
	 *
	 * @return the timetable of the school class
	 */
	public Timetable getTimetable() {
		return timetable;
	}

	/**
	 * Sets the timetable of the school class.
	 *
	 * @param timetable the timetable to set
	 */
	public void setTimetable(Timetable timetable) {
		this.timetable = timetable;
	}

	/**
	 * Returns the best available time slot for a given course.
	 *
	 * @param timetable the timetable
	 * @param toAdd     the course to add
	 * @param teachers  the set of teachers
	 * @param rooms     the set of rooms
	 *
	 * @return the best available time slot
	 */
	private @NotNull Lesson getBestAvailableLesson(@NotNull Timetable timetable, Subject toAdd,
	                                               Set<Teacher> teachers,
	                                               Map<String, Room> rooms) {
		for (int i = 0; i < timetable.getMaxNoOfHoursPerDay(); i++) {
			for (int j = 0; j < timetable.getNoOfDayPerWeek(); j++) {
				TimeSlot currentSlot = new TimeSlot(DayOfWeek.of(j + 1), i);
				Lesson lesson = new Lesson(toAdd, currentSlot);

				if (checkConstraints(timetable, lesson, teachers, rooms)) {
					updateConstraints(timetable, lesson, teachers, rooms);
					return lesson;
				}
			}
		}

		throw new IllegalArgumentException("Not enough time");
	}

	/**
	 * Updates the constraints for a given lesson.
	 *
	 * @param timetable the timetable
	 * @param lesson    the lesson
	 * @param teachers  the set of teachers
	 * @param rooms     the set of rooms
	 */
	private void updateConstraints(Timetable timetable, Lesson lesson, Set<Teacher> teachers,
	                               Map<String, Room> rooms) {
		for (Constraint constraint : constraints) {
			constraint.updateOnSuccess(timetable, lesson, teachers, rooms);
		}
	}

	/**
	 * Checks the constraints for a given lesson.
	 *
	 * @param timetable the timetable
	 * @param lesson    the lesson
	 * @param teachers  the set of teachers
	 * @param rooms     the set of rooms
	 *
	 * @return true if the constraints are met, false otherwise
	 */
	private boolean checkConstraints(Timetable timetable, Lesson lesson, Set<Teacher> teachers,
	                                 Map<String, Room> rooms) {
		for (Constraint constraint : constraints) {
			if (!constraint.check(timetable, lesson, teachers, rooms)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the name of this school class.
	 *
	 * @return the name of this school class
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the list of weekly courses for this school class.
	 *
	 * @return the set of weekly courses for this school class
	 */
	public HashSet<WeeklySubject> getWeeklySubjects() {
		return weeklySubjects;
	}

	/**
	 * Sets the list of weekly courses for this school class.
	 *
	 * @param weeklySubjects the list of weekly courses to set
	 */
	public void setWeeklySubjects(@NotNull HashSet<WeeklySubject> weeklySubjects) {
		if (weeklySubjects.isEmpty()) {
			throw new IllegalArgumentException("weeklySubjects was null or empty");
		}

		this.weeklySubjects = weeklySubjects;
	}

	/**
	 * Returns the lesson at the specified time slot.
	 *
	 * @param slot the time slot
	 *
	 * @return the lesson at the specified time slot
	 */
	public @NotNull Lesson getLesson(@NotNull TimeSlot slot) {
		if (timetable != null) {
			return timetable.getLesson(slot);
		}

		return new Lesson(FREISTUNDE, slot);
	}

	/**
	 * Sets the lesson at the specified time slot.
	 *
	 * @param lesson the lesson to set
	 */
	public void setLesson(@NotNull Lesson lesson) {
		if (timetable != null) {
			timetable.setLesson(lesson);

			if (lesson.getSchoolClass() != this) {
				lesson.setSchoolClass(this);
			}
		}
	}

	/**
	 * Returns a string representation of the school class.
	 *
	 * @return a string representation of the school class
	 */
	@Override
	public String toString() {
		return name;
	}
}
