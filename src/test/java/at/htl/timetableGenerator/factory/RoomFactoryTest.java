package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.exceptions.ImportException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoomFactoryTest {
	@Test
	void testCreateFromFile() {
		RoomFactory roomFactory =
				new RoomFactory(); //Needed, so jacoco recognizes the default constructor as tested

		assertDoesNotThrow(() -> {
			RoomFactory.createFromFile("src/test/testFiles/correctRoomFactory.csv", ";");
		});

		assertThrows(ImportException.class,
		             () -> RoomFactory.createFromFile("src/test/testFiles/incorrectRoomFactory.csv",
		                                              ";"));

		assertThrows(ImportException.class, () -> RoomFactory.createFromFile(
				"non/existing/path/but/pls/never_add/folders/called/like/this/or/everything" +
				"/will/break.csv", ";"));
	}
}