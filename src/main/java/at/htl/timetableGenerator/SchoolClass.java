package at.htl.timetableGenerator;

import at.htl.timetableGenerator.constrains.NonePlacedBeforeConstraint;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a school class in a school.
 * A school class is defined by a name, a set of constraints, and a list of weekly courses.
 */
public class SchoolClass {
	private final String name;  // The name of the school class
	private final Set<Constraint> constraints = new HashSet<>();  // The set of constraints for this school class
	private HashSet<WeeklySubjects> weeklySubjects;  // The list of weekly courses for this school class
	private Timetable timetable;
	private Teacher formTeacher;

	/**
	 * Constructs a new SchoolClass with the specified name and weekly courses.
	 *
	 * @param name           the name of the school class
	 * @param weeklySubjects the list of weekly courses for this school class
	 */
	public SchoolClass(String name, HashSet<WeeklySubjects> weeklySubjects) {
		this.name = name;
		constraints.add(new NonePlacedBeforeConstraint());
		setWeeklySubjects(weeklySubjects);
	}

	public Teacher getFormTeacher() {
		return formTeacher;
	}

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
		constraints.add(constraint);
	}

	/**
	 * Generates a timetable for this school class.
	 *
	 * @param daysPerWeek    the number of days per week
	 * @param maxHoursPerDay the maximum number of hours per day
	 * @param teachers       the set of teachers
	 *
	 * @return the generated timetable
	 */
	public Timetable generateTimetable(int daysPerWeek, int maxHoursPerDay, Set<Teacher> teachers) {
		this.timetable = new Timetable(daysPerWeek, maxHoursPerDay, constraints);

		for (WeeklySubjects weeklySubject : weeklySubjects) {
			for (int i = 0; i < weeklySubject.getNoPerWeek(); i++) {
				TimeSlot bestAvailableSlot = getBestAvailableSlot(timetable, weeklySubject.getSubject(), teachers);
				this.setLesson(new Lesson(weeklySubject.getSubject(), bestAvailableSlot));
			}
		}

		return this.timetable;
	}

	public Set<Constraint> getConstraints() {
		return constraints;
	}

	public Timetable getTimetable() {
		return timetable;
	}

	public void setTimetable(Timetable timetable) {
		this.timetable = timetable;
	}

	/**
	 * Returns the best available time slot for a given course.
	 *
	 * @param timetable the timetable
	 * @param toAdd     the course to add
	 * @param teachers  the set of teachers
	 *
	 * @return the best available time slot
	 */
	private @NotNull TimeSlot getBestAvailableSlot(@NotNull Timetable timetable, Subject toAdd,
	                                               Set<Teacher> teachers) {
		for (int i = 0; i < timetable.getMaxNoOfHoursPerDay(); i++) {
			for (int j = 0; j < timetable.getNoOfDayPerWeek(); j++) {
				TimeSlot currentSlot = new TimeSlot(DayOfWeek.of(j + 1), i);

				if (checkConstraints(timetable, currentSlot, toAdd, teachers)) {
					updateConstraints(timetable, currentSlot, toAdd, teachers);
					return currentSlot;
				}
			}
		}

		throw new IllegalArgumentException("Not enough time");
	}

	/**
	 * Updates the constraints for a given time slot and course.
	 *
	 * @param timetable the timetable
	 * @param timeSlot  the time slot
	 * @param subject   the course
	 * @param teachers  the set of teachers
	 */
	private void updateConstraints(Timetable timetable, TimeSlot timeSlot, Subject subject, Set<Teacher> teachers) {
		for (Constraint constraint : constraints) {
			constraint.updateOnSuccess(timetable, timeSlot, subject, teachers);
		}
	}

	/**
	 * Checks the constraints for a given time slot and course.
	 *
	 * @param timetable the timetable
	 * @param timeSlot  the time slot
	 * @param course    the course
	 * @param teachers  the set of teachers
	 *
	 * @return true if the constraints are met, false otherwise
	 */
	private boolean checkConstraints(Timetable timetable, TimeSlot timeSlot, Subject course, Set<Teacher> teachers) {
		for (Constraint constraint : constraints) {
			if (!constraint.check(timetable, timeSlot, course, teachers)) {
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
	public HashSet<WeeklySubjects> getWeeklySubjects() {
		return weeklySubjects;
	}

	/**
	 * Sets the list of weekly courses for this school class.
	 *
	 * @param weeklySubjects the list of weekly courses to set
	 */
	public void setWeeklySubjects(HashSet<WeeklySubjects> weeklySubjects) {
		if (weeklySubjects == null || weeklySubjects.isEmpty()) {
			throw new IllegalArgumentException("weeklySubjects was null or empty");
		}

		this.weeklySubjects = weeklySubjects;
	}

	public Lesson getLesson(TimeSlot slot) {
		if (timetable != null) {
			return timetable.getLesson(slot);
		}

		return null;
	}

	public void setLesson(Lesson lesson) {
		if (timetable != null) {
			timetable.setLesson(lesson);

			if (lesson.getSchoolClass() != this) {
				lesson.setSchoolClass(this);
			}
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
