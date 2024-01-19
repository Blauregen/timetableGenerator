package at.htl.timetableGenerator;

import java.util.HashMap;
import java.util.Set;

/**
 * This class represents a school.
 * A school is defined by a set of school classes and a set of teachers.
 */
public class School {
	private Set<SchoolClass> schoolClasses;  // The set of school classes in this school
	private Set<Teacher> teachers;  // The set of teachers in this school


	/**
	 * Constructs a new School with the specified school classes and teachers.
	 *
	 * @param schoolClasses the set of school classes in this school
	 * @param teachers      the set of teachers in this school
	 */
	public School(Set<SchoolClass> schoolClasses, Set<Teacher> teachers) {
		setSchoolClasses(schoolClasses);
		setTeachers(teachers);
	}

	/**
	 * Sets the teachers in this school.
	 *
	 * @param teachers the set of teachers to set
	 */
	public void setTeachers(Set<Teacher> teachers) {
		if (teachers == null || teachers.isEmpty()) {
			throw new IllegalArgumentException("teachers was null or empty");
		}

		this.teachers = teachers;
	}

	/**
	 * Sets the school classes in this school.
	 *
	 * @param schoolClasses the set of school classes to set
	 */
	public void setSchoolClasses(Set<SchoolClass> schoolClasses) {
		if (schoolClasses == null || schoolClasses.isEmpty()) {
			throw new IllegalArgumentException("schoolClasses was null or empty");
		}

		this.schoolClasses = schoolClasses;
	}

	/**
	 * Generates timetables for all school classes in this school.
	 *
	 * @param daysPerWeek    the number of days per week
	 * @param maxHoursPerDay the maximum number of hours per day
	 *
	 * @return a map of school class names to their respective timetables
	 */
	public HashMap<String, Timetable> generateTimetables(int daysPerWeek, int maxHoursPerDay) {
		HashMap<String, Timetable> timetables = new HashMap<>();
		for (Teacher teacher : teachers) {
			teacher.setOccupiedLessons(new Timetable(daysPerWeek, maxHoursPerDay));
		}

		for (SchoolClass schoolClass : schoolClasses) {
			Timetable timetable = schoolClass.generateTimetable(daysPerWeek, maxHoursPerDay, teachers);
			timetables.put(schoolClass.getName(), timetable);
		}

		return timetables;
	}

	/**
	 * Adds a constraint to all school classes in this school.
	 *
	 * @param constraint the constraint to add
	 */
	public void addConstraint(Constraint constraint) {
		for (SchoolClass schoolClass : schoolClasses) {
			schoolClass.addConstraint(constraint);
		}
	}

	/**
	 * Removes a constraint from all school classes in this school.
	 *
	 * @param constraint the constraint to remove
	 */
	public void removeConstraint(Constraint constraint) {
		for (SchoolClass schoolClass : schoolClasses) {
			schoolClass.removeConstraint(constraint);
		}
	}
}
