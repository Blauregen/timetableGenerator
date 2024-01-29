package at.htl.timetableGenerator;

import at.htl.timetableGenerator.constrains.DoubleHourConstraint;
import at.htl.timetableGenerator.constrains.NoMoreThanThreeInRowConstraint;
import at.htl.timetableGenerator.constrains.TeacherConstraint;
import org.jetbrains.annotations.NotNull;

public final class ConstraintUtils {
	public static Constraint getConstraintFromString(@NotNull String constraintString) {
		return switch (constraintString) {
			case "DoubleHourConstraint" -> new DoubleHourConstraint();
			case "NoMoreThanThreeInRowConstraint" -> new NoMoreThanThreeInRowConstraint();
			case "TeacherConstraint" -> new TeacherConstraint();
			default -> throw new IllegalArgumentException("No constraint with given name: " + constraintString);
		};

	}
}
