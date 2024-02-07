package at.htl.timetableGenerator;

import at.htl.timetableGenerator.constrains.DoubleHourConstraint;
import at.htl.timetableGenerator.constrains.NoMoreThanThreeInRowConstraint;
import at.htl.timetableGenerator.constrains.RoomConstraint;
import at.htl.timetableGenerator.constrains.TeacherConstraint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstraintUtilsTest {
	@Test
	void testConstructor() {
		assertDoesNotThrow(ConstraintUtils::new);
	}

	@Test
	void getConstraintFromString() {
		assertEquals(DoubleHourConstraint.class,
		             ConstraintUtils.getConstraintFromString("DoubleHourConstraint").getClass());
		assertEquals(NoMoreThanThreeInRowConstraint.class,
		             ConstraintUtils.getConstraintFromString("NoMoreThanThreeInRowConstraint")
		                            .getClass());
		assertEquals(TeacherConstraint.class,
		             ConstraintUtils.getConstraintFromString("TeacherConstraint").getClass());
		assertEquals(RoomConstraint.class,
		             ConstraintUtils.getConstraintFromString("RoomConstraint").getClass());

		assertThrows(IllegalArgumentException.class,
		             () -> ConstraintUtils.getConstraintFromString("No Constraint"),
		             "No constraint with given name: No Constraint");
	}
}