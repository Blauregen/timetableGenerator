package at.htl.timetableGenerator.model;

import at.htl.timetableGenerator.constraints.Constraint;
import at.htl.timetableGenerator.output.ExportData;
import at.htl.timetableGenerator.output.TimetableExporter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a school.
 * A school is defined by a set of school classes and a set of teachers.
 */
public class School {
	private Long id;
	private static Long idCounter = 0L;
	private final String name;
	private final HashSet<TimetableExporter> exporters = new HashSet<>();
	// school
	private @NotNull Map<String, Room> rooms = new HashMap<>();
	private Set<Constraint> constraints = new HashSet<>();
	private @NotNull Set<SchoolClass> schoolClasses = new HashSet<>();
	// The set of school classes in this school
	private @NotNull Set<Teacher> teachers = new HashSet<>();  // The set of teachers in this

	/**
	 * Constructs a new School with the specified school classes and teachers.
	 *
	 * @param name          the name of the school
	 * @param schoolClasses the set of school classes in this school
	 * @param teachers      the set of teachers in this school
	 */
	public School(String name, @NotNull Set<SchoolClass> schoolClasses, @NotNull Set<Teacher> teachers) {
		this.id = idCounter++;
		this.name = name;
		setSchoolClasses(schoolClasses);
		setTeachers(teachers);
	}

	/**
	 * Constructs a new School with the specified name.
	 *
	 * @param name the name of the school
	 */
	public School(String name) {
		this.name = name;
	}

	public void addExporter(TimetableExporter exporter) {
		exporters.add(exporter);
	}

	/**
	 * Returns the rooms of the school.
	 *
	 * @return the rooms of the school
	 */
	public @NotNull Map<String, Room> getRooms() {
		return rooms;
	}

	/**
	 * Sets the rooms of the school.
	 *
	 * @param rooms the rooms to set
	 */
	public void setRooms(@NotNull Map<String, Room> rooms) {
		this.rooms = rooms;
	}

	/**
	 * Adds a room to the school.
	 *
	 * @param room the room to add
	 */
	public void addRoom(@NotNull Room room) {
		rooms.put(room.getName(), room);
	}

	/**
	 * Returns the room with the specified name.
	 *
	 * @param name the name of the room
	 *
	 * @return the room with the specified name
	 */
	public Room getRoom(String name) {
		return rooms.get(name);
	}

	/**
	 * Returns the name of the school.
	 *
	 * @return the name of the school
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the constraints of the school.
	 *
	 * @return the constraints of the school
	 */
	public Set<Constraint> getConstraints() {
		return constraints;
	}

	/**
	 * Sets the constraints of the school.
	 *
	 * @param constraints the constraints to set
	 */
	public void setConstraints(Set<Constraint> constraints) {
		this.constraints = constraints;
		updateConstraints();
	}

	/**
	 * Returns the school classes of the school.
	 *
	 * @return the school classes of the school
	 */
	public @NotNull Set<SchoolClass> getSchoolClasses() {
		return schoolClasses;
	}

	/**
	 * Sets the school classes in this school.
	 *
	 * @param schoolClasses the set of school classes to set
	 */
	public void setSchoolClasses(@NotNull Set<SchoolClass> schoolClasses) {
		if (schoolClasses.isEmpty()) {
			throw new IllegalArgumentException("schoolClasses was null or empty");
		}

		this.schoolClasses = schoolClasses;
	}

	/**
	 * Returns the teachers of the school.
	 *
	 * @return the teachers of the school
	 */
	public @NotNull Set<Teacher> getTeachers() {
		return teachers;
	}

	/**
	 * Sets the teachers in this school.
	 *
	 * @param teachers the set of teachers to set
	 */
	public void setTeachers(@NotNull Set<Teacher> teachers) {
		if (teachers.isEmpty()) {
			throw new IllegalArgumentException("teachers was null or empty");
		}

		this.teachers = teachers;
	}

	/**
	 * Generates timetables for all school classes in this school.
	 *
	 * @param daysPerWeek    the number of days per week
	 * @param maxHoursPerDay the maximum number of hours per day
	 *
	 * @return a map of school class names to their respective timetables
	 */
	public @NotNull HashMap<String, Timetable> generateTimetables(int daysPerWeek, int maxHoursPerDay,
	                                                              int attempts) {
		HashMap<String, Timetable> timetables = new HashMap<>();

		int counter = 0;
		while (timetables.isEmpty() && counter < attempts) {
			try {
				counter++;
				System.out.println("Attempt " + (counter));
				for (Teacher teacher : teachers) {
					teacher.setOccupiedLessons(new Timetable(daysPerWeek, maxHoursPerDay,
					                                         Integer.MAX_VALUE));
				}

				for (SchoolClass schoolClass : schoolClasses) {
					Timetable timetable =
							schoolClass.generateTimetable(daysPerWeek, maxHoursPerDay, teachers, rooms);
					timetables.put(schoolClass.getName(), timetable);
				}
			} catch (Exception ignored) {
				timetables.forEach((_, timetable) -> timetable.setTimetable(new HashMap<>()));
				teachers.forEach(teacher -> teacher.setOccupiedLessons(
						new Timetable(daysPerWeek, maxHoursPerDay, Integer.MAX_VALUE)));
				rooms.forEach((_, room) -> room.getTimetable().setTimetable(new HashMap<>()));
				timetables = new HashMap<>();
			}
		}

		if (timetables.isEmpty()) {
			throw new IllegalArgumentException("Not Enough Time");
		}

		return timetables;
	}

	/**
	 * Adds a constraint to all school classes in this school.
	 *
	 * @param constraint the constraint to add
	 */
	public void addConstraint(Constraint constraint) {
		constraints.add(constraint);

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
		constraints.remove(constraint);

		for (SchoolClass schoolClass : schoolClasses) {
			schoolClass.removeConstraint(constraint);
		}
	}

	/**
	 * Exports all timetables of the school to the specified directory in the specified formats.
	 *
	 * @param exportData the data to export
	 * @param directory  the directory to export to
	 */
	public void exportAllTimetables(@NotNull Set<ExportData> exportData, String directory) {
		HashMap<String, Timetable> timetables = new HashMap<>();

		if (exportData.contains(ExportData.CLASSES)) {
			schoolClasses.forEach(
					(schoolClass -> timetables.put(schoolClass.getName(), schoolClass.getTimetable())));
		}

		if (exportData.contains(ExportData.TEACHERS)) {
			teachers.forEach(teacher -> timetables.put(teacher.getName(), teacher.getTimetable()));
		}

		if (exportData.contains(ExportData.ROOMS)) {
			rooms.values().forEach(room -> timetables.put(room.getName(), room.getTimetable()));
		}

		exporters.forEach(exporter -> exporter.export(timetables, directory));
	}

	/**
	 * Adds a school class to the school.
	 *
	 * @param schoolClass the school class to add
	 */
	public void addSchoolClass(SchoolClass schoolClass) {
		schoolClasses.add(schoolClass);
		updateConstraints();
	}

	/**
	 * Removes a school class from the school.
	 *
	 * @param schoolClass the school class to remove
	 */
	public void removeSchoolClass(SchoolClass schoolClass) {
		schoolClasses.remove(schoolClass);
	}

	/**
	 * Updates the constraints of the school.
	 */
	public void updateConstraints() {
		for (Constraint constraint : constraints) {
			addConstraint(constraint);
		}
	}

	/**
	 * Adds a teacher to the school.
	 *
	 * @param teacher the teacher to add
	 */
	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
	}

	/**
	 * Removes a teacher from the school.
	 *
	 * @param teacher the teacher to remove
	 */
	public void removeTeacher(Teacher teacher) {
		teachers.remove(teacher);
	}
}
