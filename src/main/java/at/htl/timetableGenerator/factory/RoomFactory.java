package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.exceptions.ImportException;
import at.htl.timetableGenerator.model.Room;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides factory methods for creating Room objects.
 * It includes methods for creating a Room from a string and from a file.
 */
public class RoomFactory {
	public RoomFactory() {
	}

	/**
	 * Creates a Room object from a string.
	 * The string is expected to be a line from a CSV file, with fields separated by the specified
	 * delimiter.
	 *
	 * @param line      the line from the CSV file.
	 * @param delimiter the delimiter used to separate fields in the line.
	 *
	 * @return a Room object created from the line.
	 *
	 * @throws ImportException if the line is not valid.
	 */
	public static @NotNull Room createFromString(@NotNull String line, @NotNull String delimiter) {
		// Splitting the line (csv) and storing it in an array for later access
		String[] field = line.split(delimiter);

		// Throws IllegalArgumentException if the array is longer or shorter then expected
		if (field.length != 1) {
			throw new ImportException("Line is not valid: \"" + line + "\"");
		}

		String name = field[0].strip(); // Name of subject is expected to be here

		return new Room(name);
	}

	/**
	 * Creates a map of Room objects from a file.
	 * The file is expected to be a CSV file, with each line representing a Room.
	 * The map's keys are the names of the rooms, and the values are the Room objects.
	 *
	 * @param path      the path to the CSV file.
	 * @param delimiter the delimiter used to separate fields in the lines of the file.
	 *
	 * @return a map of Room objects created from the file.
	 *
	 * @throws ImportException if there is an error reading the file.
	 */
	public static @NotNull Map<String, Room> createFromFile(@NotNull String path,
	                                                        @NotNull String delimiter) {
		try {
			HashMap<String, Room> rooms = new HashMap<>();
			List<String> lines = Files.readAllLines(Paths.get(path));

			for (int i = 1; i < lines.size(); i++) {
				Room room = createFromString(lines.get(i), delimiter);
				rooms.put(room.getName(), room);
			}

			return rooms;
		} catch (IOException e) {
			throw new ImportException("Error reading File!", e);
		}
	}
}
