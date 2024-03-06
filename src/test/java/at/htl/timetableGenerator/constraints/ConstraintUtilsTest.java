package at.htl.timetableGenerator.constraints;

import at.htl.timetableGenerator.constraints.constraints.DoubleHourConstraint;
import at.htl.timetableGenerator.exceptions.NoSuchConstraintException;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ConstraintUtilsTest {
	@Test
	void testConstructor() {
		assertDoesNotThrow(ConstraintUtils::new);
	}

	@Test
	void getConstraintFromString() {
		assertEquals(DoubleHourConstraint.class,
		             Objects.requireNonNull(ConstraintUtils.getConstraintFromString("DoubleHourConstraint"))
		                    .getClass());
		assertThrows(NoSuchConstraintException.class,
		             () -> ConstraintUtils.getConstraintFromString("No Constraint"),
		             "No constraint with given name: No Constraint");
	}
}