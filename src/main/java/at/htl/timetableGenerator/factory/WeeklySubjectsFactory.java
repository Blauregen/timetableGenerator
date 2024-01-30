package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.Subject;
import at.htl.timetableGenerator.WeeklySubject;
import at.htl.timetableGenerator.exceptions.ImportException;
import org.apache.commons.math3.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WeeklySubjectsFactory {
	public static @NotNull Pair<String, WeeklySubject> createFromString(@NotNull String line,
	                                                                    @NotNull Set<Subject> possibleSubjects,
	                                                                    @NotNull String delimiter) {
		// Splitting the line (csv) and storing it in an array for later access
		String[] field = line.split(delimiter);

		// Throws IllegalArgumentException if the array is longer or shorter then expected
		if (field.length != 3) {
			throw new ImportException("Line is not valid: \"" + line + "\"");
		}

		String subjectName = field[0].strip();
		int timesPerWeek = Integer.parseInt(field[1].strip());
		String schoolClass = field[2].strip();
		Subject subject = null;

		for (Subject currentSubject : possibleSubjects) {
			if (currentSubject.name().equals(subjectName)) {
				subject = currentSubject;
			}
		}

		if (subject == null) {
			throw new ImportException("Subject is not valid + \"" + line + "\"");
		}

		return new Pair<>(schoolClass, new WeeklySubject(subject, timesPerWeek));
	}

	public static @NotNull HashMap<String, HashSet<WeeklySubject>> createFromFile(
			@NotNull String path, @NotNull Set<Subject> possibleSubjects,
			@NotNull String delimiter) {
		try {
			HashMap<String, HashSet<WeeklySubject>> weeklySubjects = new HashMap<>();
			List<String> lines = Files.readAllLines(Paths.get(path));

			for (int i = 1; i < lines.size(); i++) {
				Pair<String, WeeklySubject> subject =
						createFromString(lines.get(i), possibleSubjects, delimiter);
				HashSet<WeeklySubject> weeklySubject =
						weeklySubjects.getOrDefault(subject.getKey(), new HashSet<>());
				weeklySubject.add(subject.getValue());
				weeklySubjects.put(subject.getKey(), weeklySubject);
			}

			return weeklySubjects;
		} catch (IOException e) {
			throw new ImportException("Error reading File!", e);
		}
	}
}
