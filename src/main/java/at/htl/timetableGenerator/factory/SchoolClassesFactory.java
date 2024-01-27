package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.SchoolClass;
import at.htl.timetableGenerator.Teacher;
import at.htl.timetableGenerator.WeeklySubject;
import at.htl.timetableGenerator.exceptions.ImportException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SchoolClassesFactory {
	public static @NotNull SchoolClass createFromString(@NotNull String line, @NotNull Set<Teacher> teachers,
	                                                    @NotNull HashMap<String, HashSet<WeeklySubject>> possibleWeeklySubjects) {
		// Splitting the line (csv) and storing it in an array for later access
		String[] field = line.split(";");

		// Throws IllegalArgumentException if the array is longer or shorter then expected
		if (field.length != 2) {
			throw new ImportException("Line is not valid: \"" + line + "\"");
		}

		String name = field[0].strip();
		String teacherName = field[1].strip();
		Teacher formTeacher = null;
		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();

		for (Teacher teacher : teachers) {
			if (teacher.getName().equals(teacherName)) {
				formTeacher = teacher;
			}
		}

		possibleWeeklySubjects.forEach((schoolClass, weeklySubject) -> {
			if (schoolClass.equals(name)) {
				weeklySubjects.addAll(weeklySubject);
			}
		});

		SchoolClass schoolClass = new SchoolClass(name, weeklySubjects);
		if (formTeacher != null) {
			schoolClass.setFormTeacher(formTeacher);
		}

		return schoolClass;
	}

	public static @NotNull Set<SchoolClass> createFromFile(String path, @NotNull Set<Teacher> teachers,
	                                                       @NotNull HashMap<String, HashSet<WeeklySubject>> possibleWeeklySubjects) {
		try (Stream<String> lines = Files.lines(Paths.get(path))) {
			return lines.skip(1)
			            .map(line -> SchoolClassesFactory.createFromString(line, teachers, possibleWeeklySubjects))
			            .collect(Collectors.toSet());
		} catch (IOException e) {
			throw new ImportException("Error reading File!", e);
		}
	}
}
