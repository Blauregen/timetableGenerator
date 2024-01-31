package at.htl.timetableGenerator;

import at.htl.timetableGenerator.constrains.DoubleHourConstraint;
import at.htl.timetableGenerator.constrains.NoMoreThanThreeInRowConstraint;
import at.htl.timetableGenerator.constrains.RoomConstraint;
import at.htl.timetableGenerator.constrains.TeacherConstraint;
import org.jetbrains.annotations.NotNull;

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
	public static @NotNull Constraint getConstraintFromString(@NotNull String constraintString) {
		return switch (constraintString) {
			case "DoubleHourConstraint" -> new DoubleHourConstraint();
			case "NoMoreThanThreeInRowConstraint" -> new NoMoreThanThreeInRowConstraint();
			case "TeacherConstraint" -> new TeacherConstraint();
			case "RoomConstraint" -> new RoomConstraint();
			default -> throw new IllegalArgumentException(
					"No constraint with given name: " + constraintString);
		};
	}
}
