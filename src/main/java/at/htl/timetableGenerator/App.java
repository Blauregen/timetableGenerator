package at.htl.timetableGenerator;

import at.htl.timetableGenerator.constrains.DoubleHourConstraint;
import at.htl.timetableGenerator.constrains.NoMoreThanThreeInRowConstraint;
import at.htl.timetableGenerator.constrains.TeacherConstraint;
import at.htl.timetableGenerator.output.CSVExporter;
import at.htl.timetableGenerator.output.ExportData;
import at.htl.timetableGenerator.output.ExportFormat;
import org.apache.commons.cli.*;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

			Set<ExportFormat> exportFormats = new HashSet<>();
			for (String format : exportFormatString.split(",")) {
				exportFormats.add(ExportFormat.valueOf(format.strip()));
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
				constraints.add(ConstraintUtils.getConstraintFromString(constraint.strip()));
			}

			String subjectsPath = ini.get("input", "subjects");
			String weeklySubjectsPath = ini.get("input", "weeklySubjects");
			String classesPath = ini.get("input", "classes");
			String teachersPath = ini.get("input", "teachers");

			System.out.println(noOfDaysPerWeek);
			System.out.println(noOfHoursPerDay);
			System.out.println(schoolName);
			System.out.println(delimiter);

			exportFormats.forEach(System.out::println);
			System.out.println(outputPath);
			exportData.forEach(System.out::println);
			constraints.forEach(System.out::println);

			System.out.println(subjectsPath);
			System.out.println(weeklySubjectsPath);
			System.out.println(classesPath);
			System.out.println(teachersPath);
		} catch (ParseException | IOException e) {
			throw new IllegalArgumentException("No valid config file passed");
		}

		Subject math = new Subject("MATH", "AM");
		Subject ITP = new Subject("ITP", "ITP");
		Subject German = new Subject("German", "D");

		WeeklySubjects maths = new WeeklySubjects(math, 3);
		WeeklySubjects itps = new WeeklySubjects(ITP, 5);
		WeeklySubjects Germans = new WeeklySubjects(German, 3);

		HashSet<Subject> mathSubject = new HashSet<>();
		mathSubject.add(math);

		HashSet<Subject> itpSubject = new HashSet<>();
		itpSubject.add(ITP);

		HashSet<Subject> germanSubject = new HashSet<>();
		germanSubject.add(German);

		HashSet<WeeklySubjects> weeklySubjectsBhitm = new HashSet<>();
		weeklySubjectsBhitm.add(maths);
		weeklySubjectsBhitm.add(itps);

		HashSet<WeeklySubjects> weeklySubjectsAhitm = new HashSet<>();
		weeklySubjectsAhitm.add(Germans);
		weeklySubjectsAhitm.add(itps);

		Teacher kerschi = new Teacher("Kerschi", mathSubject, new Timetable(5, 5));
		Teacher aberger = new Teacher("Aberger", itpSubject, new Timetable(5, 5));
		Teacher luger = new Teacher("Luger", germanSubject, new Timetable(5, 5));

		HashSet<Teacher> teachers = new HashSet<>();
		teachers.add(kerschi);
		teachers.add(aberger);
		teachers.add(luger);

		SchoolClass bhitm3 = new SchoolClass("3bhitm", weeklySubjectsBhitm);
		bhitm3.addConstraint(new DoubleHourConstraint());
		bhitm3.addConstraint(new NoMoreThanThreeInRowConstraint());
		bhitm3.addConstraint(new TeacherConstraint());
		bhitm3.generateTimetable(5, 5, teachers);

		SchoolClass ahitm3 = new SchoolClass("3ahitm", weeklySubjectsAhitm);
		ahitm3.addConstraint(new DoubleHourConstraint());
		ahitm3.addConstraint(new NoMoreThanThreeInRowConstraint());
		ahitm3.addConstraint(new TeacherConstraint());
		ahitm3.generateTimetable(5, 5, teachers);

		HashMap<String, Timetable> timetables = new HashMap<>();
		timetables.put("3bhitm", bhitm3.getTimetable());
		timetables.put("3ahitm", ahitm3.getTimetable());
		timetables.put("Kerschi", kerschi.getTimetable());
		timetables.put("Aberger", aberger.getTimetable());
		timetables.put("Luger", luger.getTimetable());

		CSVExporter.exportTimetablesToMultipleFiles(timetables, "./output/");

		System.out.println(bhitm3.getTimetable());
		System.out.println(ahitm3.getTimetable());
	}
}
