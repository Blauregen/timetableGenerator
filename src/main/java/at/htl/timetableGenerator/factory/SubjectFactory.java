package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.Subject;
import at.htl.timetableGenerator.exceptions.ImportException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubjectFactory {
	private static final int VALID_SUBJECT_ARRAY_LENGTH = 2;

	@Contract("_ -> new")
	public static @NotNull Subject createFromString(@NotNull String line) {
		// Splitting the line (csv) and storing it in an array for later access
		String[] field = line.split(";");

		// Throws IllegalArgumentException if the array is longer or shorter then expected
		if (field.length != VALID_SUBJECT_ARRAY_LENGTH) {
			throw new IllegalArgumentException();
		}

		String name = field[0].strip(); // Name of subject is expected to be here
		String shortName = field[1].strip(); // Shortened name of subject is expected to be here

		return new Subject(name, shortName);
	}

	public static @NotNull Set<Subject> createFromFile(String path) {
		try (Stream<String> lines = Files.lines(Paths.get(path))) {
			return lines.skip(1).map(SubjectFactory::createFromString).collect(Collectors.toSet());
		} catch (IOException e) {
			throw new ImportException("Error reading File!", e);
		}
	}
}
