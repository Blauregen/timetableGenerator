package at.htl.timetableGenerator;

import at.htl.timetableGenerator.output.ExportData;
import at.htl.timetableGenerator.output.ExportFormat;
import org.apache.commons.cli.*;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * The main class of the application.
 * It creates a timetable, a subject, and a lesson, and then prints the timetable.
 */
public class App {
	/**
	 * The main method of the application.
	 * It creates a timetable with 5 days and 10 periods per day, a subject named "Mathe" with the abbreviation "AM",
	 * and a lesson with the subject and a time slot for the first period on Monday.
	 * Then it sets the lesson in the timetable and prints the timetable.
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
			String delimiter = ini.get("general", "delimiter").strip();

			String exportFormatString = ini.get("output", "outputFormat").strip();
			exportFormatString = exportFormatString.substring(1, exportFormatString.length() - 1);

			Set<ExportFormat> exportFormat = new HashSet<>();
			for (String format : exportFormatString.split(",")) {
				exportFormat.add(ExportFormat.valueOf(format.strip()));
			}

			String exportDataString = ini.get("output", "outputData").strip();
			exportDataString = exportDataString.substring(1, exportDataString.length() - 1);

			Set<ExportData> exportData = new HashSet<>();
			for (String format : exportDataString.split(",")) {
				exportData.add(ExportData.valueOf(format.strip()));
			}

			String outputPath = ini.get("output", "outputPath").strip();

			String constraintString = ini.get("constraints", "constraints").strip();
			constraintString = constraintString.substring(1, constraintString.length() - 1);

			Set<Constraint> constraints = new HashSet<>();
			for (String constraint : constraintString.split(",")) {
				constraints.add(ConstraintUtils.getConstraintFromString(constraintString));
			}

			String subjectsPath = ini.get("input", "subjects");
			String weeklySubjectsPath = ini.get("input", "weeklySubjectsPath");
			String classesPath = ini.get("input", "classes");
			String teachersPath = ini.get("input", "teachers");
		} catch (ParseException | IOException e) {
			throw new IllegalArgumentException("No valid config file passed");
		}
	}
}
