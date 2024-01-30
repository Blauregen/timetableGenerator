package at.htl.timetableGenerator;

import at.htl.timetableGenerator.constrains.DoubleHourConstraint;
import at.htl.timetableGenerator.constrains.NoMoreThanThreeInRowConstraint;
import at.htl.timetableGenerator.constrains.RoomConstraint;
import at.htl.timetableGenerator.constrains.TeacherConstraint;
import org.jetbrains.annotations.NotNull;

public class ConstraintUtils {
	public ConstraintUtils() {
	}

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
