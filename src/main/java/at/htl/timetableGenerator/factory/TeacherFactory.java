package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.Subject;
import at.htl.timetableGenerator.Teacher;
import at.htl.timetableGenerator.exceptions.ImportException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TeacherFactory {
	private static final int VALID_TEACHER_ARRAY_LENGTH = 4;

	@Contract("_, _ -> new")
	public static @NotNull Teacher createFromString(@NotNull String line, @NotNull Set<Subject> possibleSubjects) {
		// Splitting the line (csv) and storing it in an array for later access
		String[] field = line.split(";");

		// Throws IllegalArgumentException if the array is longer or shorter then expected
		if (field.length != VALID_TEACHER_ARRAY_LENGTH) {
			throw new ImportException("Line is not valid: \"" + line + "\"");
		}

		String name = field[0].strip();
		int hoursPerDay = Integer.parseInt(field[1].strip());
		int daysPerWeek = Integer.parseInt(field[2].strip());
		String[] subjectsString = field[3].strip().split(",");
		HashSet<Subject> subjects = new HashSet<>();

		for (String currentSubjectName : subjectsString) {
			for (Subject currentSubject : possibleSubjects) {
				if (currentSubject.name().equals(currentSubjectName)) {
					subjects.add(currentSubject);
				}
			}
		}

		return new Teacher(name, subjects, hoursPerDay, daysPerWeek);
	}

	public static @NotNull Set<Teacher> createFromFile(String path, Set<Subject> possibleSubjects) {
		try (Stream<String> lines = Files.lines(Paths.get(path))) {
			return lines.skip(1).map(line -> TeacherFactory.createFromString(line, possibleSubjects))
			            .collect(Collectors.toSet());
		} catch (IOException e) {
			throw new ImportException("Error reading File!", e);
		}
	}
}
