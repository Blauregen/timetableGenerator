package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.constraints.DurationKeyword;
import at.htl.timetableGenerator.constraints.constraints.CustomMultipleConstraint;
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
public class MultipleConstraintFactory {
	public MultipleConstraintFactory() {
	}

	/**
	 * Creates a Constraint from a string.
	 * The string is expected to be a line from a Constraint config file
	 *
	 * @param line the line from the Config file.
	 *
	 * @return a Constraint created from the line.
	 *
	 * @throws ImportException if the line is not valid.
	 */
	public static @NotNull CustomMultipleConstraint createFromString(@NotNull String line) {
		String[] field = line.split("\"");

		if (field.length != 3) {
			throw new IllegalArgumentException("Most likely unclosed String literals in constraint config");
		}

		String subject = field[1];

		String[] limits = field[2].substring(1).split(" ");
		if (limits.length != 2) {
			throw new IllegalArgumentException("Syntax error in time specification");
		}

		DurationKeyword keyword = DurationKeyword.valueOf(limits[0].toUpperCase());
		int duration = Integer.parseInt(limits[1]);

		String[] requires = field[0].substring(0, field[0].length() - 1).split(" ");
		if (requires.length != 2) {
			throw new IllegalArgumentException("Syntax error in requirement specification");
		}

		boolean isRequired;
		if (requires[0].equals("require")) {
			isRequired = true;
		} else if (requires[0].equals("prefer")) {
			isRequired = false;
		} else {
			throw new IllegalArgumentException("Invalid requirement specifier");
		}

		if (!requires[1].equals("no")) {
			throw new IllegalArgumentException("Missing \"no\" after requirement specifier");
		}

		return new CustomMultipleConstraint(subject, duration, keyword, isRequired);
	}

	/**
	 * Creates a set of Constraints from a file.
	 * The file is expected to be a CustomConstraint file, with each line representing a Constraint.
	 *
	 * @param path the path to the config file.
	 *
	 * @return a set of Constraints created from the file.
	 *
	 * @throws ImportException if there is an error reading the file.
	 */
	public static @NotNull Set<CustomMultipleConstraint> createFromFile(@NotNull String path) {
		try (Stream<String> lines = Files.lines(Paths.get(path))) {
			return lines.map(MultipleConstraintFactory::createFromString).collect(Collectors.toSet());
		} catch (IOException e) {
			throw new ImportException("Error reading File!", e);
		}
	}
}
