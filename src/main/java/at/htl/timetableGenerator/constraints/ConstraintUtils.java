package at.htl.timetableGenerator.constraints;

import at.htl.timetableGenerator.exceptions.NoSuchConstraintException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class provides utility methods for handling constraints in the timetable generator.
 */
public class ConstraintUtils {
	/**
	 * Default constructor.
	 */
	public ConstraintUtils() {
	}

	/**
	 * This method returns a Constraint object based on the input string.
	 * The input string should match the name of one of the constraint classes.
	 *
	 * @param constraintString the name of the constraint class
	 *
	 * @return a new instance of the constraint class
	 *
	 * @throws IllegalArgumentException if the input string does not match any constraint class
	 *                                  name
	 */
	public static @Nullable Constraint getConstraintFromString(@NotNull String constraintString) {
		try {
			// Use reflection to get the Class object for the given class name
			Class<?> clazz =
					Class.forName("at.htl.timetableGenerator.constraints.constraints." + constraintString);

			// Create a new instance of the class using reflection
			Object instance = clazz.getDeclaredConstructor().newInstance();

			if (instance instanceof Constraint) {
				return (Constraint) instance;
			}
		} catch (Exception e) {
			throw new NoSuchConstraintException("Constraint " + constraintString + " does not exist");
		}

		return null;
	}
}
