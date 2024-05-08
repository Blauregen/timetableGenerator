package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.exceptions.ImportException;
import at.htl.timetableGenerator.model.SchoolClass;
import at.htl.timetableGenerator.model.Teacher;
import at.htl.timetableGenerator.model.WeeklySubject;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class provides factory methods for creating SchoolClass objects.
 * It includes methods for creating a SchoolClass from a string and from a file.
 */
public class SchoolClassesFactory {

	public SchoolClassesFactory() {
	}

	/**
	 * Creates a SchoolClass object from a string.
	 * The string is expected to be a line from a CSV file, with fields separated by the specified
	 * delimiter.
	 * The method also requires a set of teachers and a map of possible weekly subjects for the
	 * school class.
	 *
	 * @param line                   the line from the CSV file.
	 * @param teachers               the set of teachers.
	 * @param possibleWeeklySubjects the map of possible weekly subjects for the school class.
	 * @param delimiter              the delimiter used to separate fields in the line.
	 *
	 * @return a SchoolClass object created from the line.
	 *
	 * @throws ImportException if the line is not valid.
	 */
	public static @NotNull SchoolClass createFromString(@NotNull String line, @NotNull Set<Teacher> teachers,
	                                                    @NotNull
	                                                    HashMap<String, HashSet<WeeklySubject>> possibleWeeklySubjects,
	                                                    @NotNull String delimiter) {
		// Splitting the line (csv) and storing it in an array for later access
		String[] field = line.split(delimiter);

		// Throws IllegalArgumentException if the array is longer or shorter then expected
		if (field.length != 2) {
			throw new ImportException("Line is not valid: \"" + line + "\"");
		}

		String name = field[0].strip();
		String teacherName = field[1].strip();
		Teacher formTeacher = null;
		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();

		for (Teacher teacher : teachers) {
			if (teacher.getLastName().equals(teacherName)) {
				formTeacher = teacher;
			}
		}

		possibleWeeklySubjects.forEach((schoolClass, weeklySubject) -> {
			if (schoolClass.equals(name)) {
				weeklySubjects.addAll(weeklySubject);
			}
		});
		SchoolClass schoolClass;

		try {
			schoolClass = new SchoolClass(name, weeklySubjects);
		} catch (IllegalArgumentException e) {
			throw new ImportException(e.getMessage(), e);
		}

		if (formTeacher != null) {
			schoolClass.setFormTeacher(formTeacher);
		}

		return schoolClass;
	}

	/**
	 * Creates a set of SchoolClass objects from a file.
	 * The file is expected to be a CSV file, with each line representing a SchoolClass.
	 * The method also requires a set of teachers and a map of possible weekly subjects for the
	 * school classes.
	 *
	 * @param path                   the path to the CSV file.
	 * @param teachers               the set of teachers.
	 * @param possibleWeeklySubjects the map of possible weekly subjects for the school classes.
	 * @param delimiter              the delimiter used to separate fields in the lines of the
	 *                               file.
	 *
	 * @return a set of SchoolClass objects created from the file.
	 *
	 * @throws ImportException if there is an error reading the file.
	 */
	public static @NotNull Set<SchoolClass> createFromFile(@NotNull String path,
	                                                       @NotNull Set<Teacher> teachers, @NotNull
	                                                       HashMap<String, HashSet<WeeklySubject>> possibleWeeklySubjects,
	                                                       @NotNull String delimiter) {
		try (Stream<String> lines = Files.lines(Paths.get(path))) {
			return lines.skip(1).map(line -> SchoolClassesFactory.createFromString(line, teachers,
			                                                                       possibleWeeklySubjects,
			                                                                       delimiter))
			            .collect(Collectors.toSet());
		} catch (IOException e) {
			throw new ImportException("Error reading File!", e);
		}
	}
}
