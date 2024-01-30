package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.Subject;
import at.htl.timetableGenerator.exceptions.ImportException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class provides factory methods for creating Subject objects.
 * It includes methods for creating a Subject from a string and from a file.
 */
public class SubjectFactory {
	public SubjectFactory() {
	}

	private static final int VALID_SUBJECT_ARRAY_LENGTH = 2;

	/**
	 * Creates a Subject object from a string.
	 * The string is expected to be a line from a CSV file, with fields separated by the specified
	 * delimiter.
	 *
	 * @param line      the line from the CSV file.
	 * @param delimiter the delimiter used to separate fields in the line.
	 *
	 * @return a Subject object created from the line.
	 *
	 * @throws ImportException if the line is not valid.
	 */
	public static @NotNull Subject createFromString(@NotNull String line,
	                                                @NotNull String delimiter) {
		// Splitting the line (csv) and storing it in an array for later access
		String[] field = line.split(delimiter);

		// Throws IllegalArgumentException if the array is longer or shorter then expected
		if (field.length != VALID_SUBJECT_ARRAY_LENGTH) {
			throw new ImportException("Line is not valid: \"" + line + "\"");
		}

		String name = field[0].strip(); // Name of subject is expected to be here
		String shortName = field[1].strip(); // Shortened name of subject is expected to be here

		return new Subject(name, shortName);
	}

	/**
	 * Creates a set of Subject objects from a file.
	 * The file is expected to be a CSV file, with each line representing a Subject.
	 *
	 * @param path      the path to the CSV file.
	 * @param delimiter the delimiter used to separate fields in the lines of the file.
	 *
	 * @return a set of Subject objects created from the file.
	 *
	 * @throws ImportException if there is an error reading the file.
	 */
	public static @NotNull Set<Subject> createFromFile(@NotNull String path,
	                                                   @NotNull String delimiter) {
		try (Stream<String> lines = Files.lines(Paths.get(path))) {
			return lines.skip(1).map((String line) -> createFromString(line, delimiter))
			            .collect(Collectors.toSet());
		} catch (IOException e) {
			throw new ImportException("Error reading File!", e);
		}
	}
}
