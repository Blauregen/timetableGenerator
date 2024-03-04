package at.htl.timetableGenerator;

import at.htl.timetableGenerator.constraints.ConstraintUtils;
import at.htl.timetableGenerator.constraints.constraints.DoubleHourConstraint;
import at.htl.timetableGenerator.constraints.constraints.NoMoreThanTwoInRowConstraint;
import at.htl.timetableGenerator.constraints.constraints.RoomConstraint;
import at.htl.timetableGenerator.constraints.constraints.TeacherConstraint;
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
		assertEquals(NoMoreThanTwoInRowConstraint.class,
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