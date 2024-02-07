package at.htl.timetableGenerator;

import at.htl.timetableGenerator.factory.*;
import at.htl.timetableGenerator.output.ExportData;
import at.htl.timetableGenerator.output.ExportFormat;
import org.apache.commons.cli.*;
import org.ini4j.Ini;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.*;

/**
 * The main class of the application.
 * It creates a timetable, a subject, and a lesson, and then prints the timetable.
 */
public class App {
	public static Random random;

	/**
	 * The main method of the application.
	 *
	 * @param args The command-line arguments
	 */
	public static void main(String[] args) {
		Options options = new Options();
		options.addOption("c", "config", true, "Path to the config file");

		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse(options, args);
			String configFile = cmd.getOptionValue("config");

			Ini ini = new Ini(new File(configFile));
			int noOfDaysPerWeek = Integer.parseInt(ini.get("general", "noOfDaysPerWeek").strip());
			int noOfHoursPerDay = Integer.parseInt(ini.get("general", "noOfHoursPerDay").strip());
			String schoolName = ini.get("general", "schoolName").strip();
			String delimiter = ini.get("general", "delimiter");

			if (delimiter == null) {
				delimiter = ";";
			} else {
				delimiter = delimiter.strip();
			}

			String exportFormatString = ini.get("output", "outputFormat").strip();
			exportFormatString = exportFormatString.substring(1, exportFormatString.length() - 1);

			Set<ExportFormat> exportFormats = new HashSet<>();
			for (String format : exportFormatString.split(",")) {
				if (!format.isBlank()) {
					exportFormats.add(ExportFormat.valueOf(format.strip()));
				}
			}

			String exportDataString = ini.get("output", "outputData").strip();
			exportDataString = exportDataString.substring(1, exportDataString.length() - 1);

			Set<ExportData> exportData = new HashSet<>();
			for (String data : exportDataString.split(",")) {
				if (!data.isBlank()) {
					exportData.add(ExportData.valueOf(data.strip()));
				}
			}

			String outputPath = ini.get("output", "outputPath");

			if (outputPath == null) {
				outputPath = Paths.get(configFile).getParent().toString() +
				             FileSystems.getDefault().getSeparator() + "output" +
				             FileSystems.getDefault().getSeparator();
			} else {
				outputPath = outputPath.strip();
			}

			String constraintString = ini.get("constraints", "constraints").strip();
			constraintString = constraintString.substring(1, constraintString.length() - 1);

			Set<Constraint> constraints = new HashSet<>();
			for (String constraint : constraintString.split(",")) {
				if (!constraint.isBlank()) {
					constraints.add(ConstraintUtils.getConstraintFromString(constraint.strip()));
				}
			}

			String subjectsPath = ini.get("input", "subjects");
			String weeklySubjectsPath = ini.get("input", "weeklySubjects");
			String classesPath = ini.get("input", "classes");
			String teachersPath = ini.get("input", "teachers");
			String roomPath = ini.get("input", "rooms");

			String seedString = ini.get("general", "seed");
			long seed;
			try {
				seed = Long.parseLong(seedString.strip());
			} catch (Exception e) {
				seed = System.currentTimeMillis();
			}

			App.random = new Random(seed);

			Set<Subject> subjects =
					SubjectFactory.createFromFile(getRelativePath(configFile, subjectsPath), delimiter);
			HashMap<String, HashSet<WeeklySubject>> weeklySubjects =
					WeeklySubjectsFactory.createFromFile(getRelativePath(configFile, weeklySubjectsPath),
					                                     subjects, delimiter);
			Set<Teacher> teachers =
					TeacherFactory.createFromFile(getRelativePath(configFile, teachersPath), subjects,
					                              delimiter);
			Set<SchoolClass> schoolClasses =
					SchoolClassesFactory.createFromFile(getRelativePath(configFile, classesPath), teachers,
					                                    weeklySubjects, delimiter);
			Map<String, Room> rooms =
					RoomFactory.createFromFile(getRelativePath(configFile, roomPath), delimiter);

			School school = new School(schoolName, schoolClasses, teachers);
			school.setConstraints(constraints);
			school.setRooms(rooms);
			school.generateTimetables(noOfDaysPerWeek, noOfHoursPerDay);
			school.exportAllTimetables(exportData, exportFormats, outputPath);
			school.getSchoolClasses()
			      .forEach((schoolClass -> System.out.println(schoolClass.getTimetable())));

			System.out.println("Generation seed: " + seed);
		} catch (ParseException | IOException e) {
			throw new IllegalArgumentException("No valid config file passed");
		}
	}

	@NotNull
	private static String getRelativePath(@NotNull String configFile, String classesPath) {
		return Paths.get(configFile).getParent() + FileSystems.getDefault().getSeparator() + classesPath;
	}
}
