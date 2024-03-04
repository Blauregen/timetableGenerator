package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.exceptions.ImportException;
import at.htl.timetableGenerator.model.Subject;
import at.htl.timetableGenerator.model.WeeklySubject;
import org.apache.commons.math3.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class provides factory methods for creating WeeklySubject objects and mapping them to their
 * respective school classes.
 * It includes methods for creating a WeeklySubject from a string and from a file.
 */
public class WeeklySubjectsFactory {
	public WeeklySubjectsFactory() {
	}

	/**
	 * Creates a Pair object from a string, where the key is the name of the school class and the
	 * value is a WeeklySubject object.
	 * The string is expected to be a line from a CSV file, with fields separated by the specified
	 * delimiter.
	 * The method also requires a set of possible subjects for the WeeklySubject.
	 *
	 * @param line             the line from the CSV file.
	 * @param possibleSubjects the set of possible subjects for the WeeklySubject.
	 * @param delimiter        the delimiter used to separate fields in the line.
	 *
	 * @return a Pair object created from the line, where the key is the name of the school class
	 * and the value is a WeeklySubject object.
	 *
	 * @throws ImportException if the line is not valid or the subject is not valid.
	 */
	public static @NotNull Pair<String, WeeklySubject> createFromString(@NotNull String line,
	                                                                    @NotNull Set<Subject> possibleSubjects,
	                                                                    @NotNull String delimiter) {
		// Splitting the line (csv) and storing it in an array for later access
		String[] field = line.split(delimiter);

		// Throws IllegalArgumentException if the array is longer or shorter then expected
		if (field.length != 3) {
			throw new ImportException("Line is not valid: \"" + line + "\"");
		}

		String subjectName = field[0].strip();
		int timesPerWeek = Integer.parseInt(field[1].strip());
		String schoolClass = field[2].strip();
		Subject subject = null;

		for (Subject currentSubject : possibleSubjects) {
			if (currentSubject.name().equals(subjectName)) {
				subject = currentSubject;
			}
		}

		if (subject == null) {
			throw new ImportException("Subject is not valid + \"" + line + "\"");
		}

		return new Pair<>(schoolClass, new WeeklySubject(subject, timesPerWeek));
	}

	/**
	 * Creates a map of WeeklySubject objects from a file, where the keys are the names of the
	 * school classes and the values are sets of WeeklySubject objects.
	 * The file is expected to be a CSV file, with each line representing a WeeklySubject.
	 * The method also requires a set of possible subjects for the WeeklySubjects.
	 *
	 * @param path             the path to the CSV file.
	 * @param possibleSubjects the set of possible subjects for the WeeklySubjects.
	 * @param delimiter        the delimiter used to separate fields in the lines of the file.
	 *
	 * @return a map of WeeklySubject objects created from the file, where the keys are the
	 * names of
	 * the school classes and the values are sets of WeeklySubject objects.
	 *
	 * @throws ImportException if there is an error reading the file.
	 */
	public static @NotNull HashMap<String, HashSet<WeeklySubject>> createFromFile(
			@NotNull String path, @NotNull Set<Subject> possibleSubjects,
			@NotNull String delimiter) {
		try {
			HashMap<String, HashSet<WeeklySubject>> weeklySubjects = new HashMap<>();
			List<String> lines = Files.readAllLines(Paths.get(path));

			for (int i = 1; i < lines.size(); i++) {
				Pair<String, WeeklySubject> subject =
						createFromString(lines.get(i), possibleSubjects, delimiter);
				HashSet<WeeklySubject> weeklySubject =
						weeklySubjects.getOrDefault(subject.getKey(), new HashSet<>());
				weeklySubject.add(subject.getValue());
				weeklySubjects.put(subject.getKey(), weeklySubject);
			}

			return weeklySubjects;
		} catch (IOException e) {
			throw new ImportException("Error reading File!", e);
		}
	}
}
