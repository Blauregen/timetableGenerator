package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.Room;
import at.htl.timetableGenerator.exceptions.ImportException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomFactory {
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
