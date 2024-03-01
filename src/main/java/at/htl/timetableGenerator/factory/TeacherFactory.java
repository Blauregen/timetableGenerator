package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.Model.Subject;
import at.htl.timetableGenerator.Model.Teacher;
import at.htl.timetableGenerator.exceptions.ImportException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class provides factory methods for creating Teacher objects.
 * It includes methods for creating a Teacher from a string and from a file.
 */
public class TeacherFactory {
	private static final int VALID_TEACHER_ARRAY_LENGTH = 4;

	public TeacherFactory() {
	}

	/**
	 * Creates a Teacher object from a string.
	 * The string is expected to be a line from a CSV file, with fields separated by the specified
	 * delimiter.
	 * The method also requires a set of possible subjects for the teacher.
	 *
	 * @param line             the line from the CSV file.
	 * @param possibleSubjects the set of possible subjects for the teacher.
	 * @param delimiter        the delimiter used to separate fields in the line.
	 *
	 * @return a Teacher object created from the line.
	 *
	 * @throws ImportException if the line is not valid.
	 */
	public static @NotNull Teacher createFromString(@NotNull String line,
	                                                @NotNull Set<Subject> possibleSubjects,
	                                                @NotNull String delimiter) {
		// Splitting the line (csv) and storing it in an array for later access
		String[] field = line.split(delimiter);

		// Throws IllegalArgumentException if the array is longer or shorter then expected
		if (field.length != VALID_TEACHER_ARRAY_LENGTH) {
			throw new ImportException("Line is not valid: \"" + line + "\"");
		}

		String name = field[0].strip();
		int hoursPerDay = Integer.parseInt(field[1].strip());
		int daysPerWeek = Integer.parseInt(field[2].strip());
		String[] subjectsString = field[3].strip().split(",");
		HashSet<Subject> subjects = new HashSet<>();

		for (String currentSubjectName : subjectsString) {
			for (Subject currentSubject : possibleSubjects) {
				if (currentSubject.name().equals(currentSubjectName.strip())) {
					subjects.add(currentSubject);
				}
			}
		}

		return new Teacher(name, subjects, hoursPerDay, daysPerWeek);
	}

	/**
	 * Creates a set of Teacher objects from a file.
	 * The file is expected to be a CSV file, with each line representing a Teacher.
	 * The method also requires a set of possible subjects for the teachers.
	 *
	 * @param path             the path to the CSV file.
	 * @param possibleSubjects the set of possible subjects for the teachers.
	 * @param delimiter        the delimiter used to separate fields in the lines of the file.
	 *
	 * @return a set of Teacher objects created from the file.
	 *
	 * @throws ImportException if there is an error reading the file.
	 */
	public static @NotNull Set<Teacher> createFromFile(@NotNull String path,
	                                                   @NotNull Set<Subject> possibleSubjects,
	                                                   @NotNull String delimiter) {
		try (Stream<String> lines = Files.lines(Paths.get(path))) {
			return lines.skip(1)
			            .map(line -> TeacherFactory.createFromString(line, possibleSubjects, delimiter))
			            .collect(Collectors.toSet());
		} catch (IOException e) {
			throw new ImportException("Error reading File!", e);
		}
	}
}
