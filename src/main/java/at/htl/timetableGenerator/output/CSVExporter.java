package at.htl.timetableGenerator.output;

import at.htl.timetableGenerator.Model.Timetable;
import at.htl.timetableGenerator.exceptions.ExportException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class provides methods to export timetables to CSV files.
 * It can export multiple timetables to a single file or each timetable to a separate file.
 */
public class CSVExporter {
	/**
	 * Exports a single timetable to a CSV file.
	 *
	 * @param timetable the timetable to export
	 * @param path      the path of the CSV file
	 */
	public static void exportTimetableToFile(Timetable timetable, @NotNull String path) {
		HashMap<String, Timetable> timetables = new HashMap<>();
		timetables.put("Timetable", timetable);
		exportTimetablesToSingleFile(timetables, path);
	}

	/**
	 * Exports multiple timetables to a single CSV file.
	 *
	 * @param timetables the map of timetable names to timetables
	 * @param stringPath the path of the CSV file
	 *
	 * @throws ExportException if there is an error exporting.
	 */
	public static void exportTimetablesToSingleFile(@NotNull Map<String, Timetable> timetables,
	                                                @NotNull String stringPath) {
		Path path = Paths.get(stringPath);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path.getParent());
			} catch (IOException e) {
				throw new ExportException("Error Creating directory for csv file", e);
			}
		}

		try (Writer writer = new FileWriter(stringPath);
		     CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			printHeaders(csvPrinter);

			timetables.forEach((name, timetable) -> generateCSV(timetable, csvPrinter));
		} catch (IOException | ExportException e) {
			throw new ExportException("Error writing to csv file", e);
		}
	}

	/**
	 * Prints the headers for the CSV file.
	 * The headers are "day", "hour", "subject", "teacher", and "class".
	 *
	 * @param csvPrinter The CSVPrinter to use for printing the headers
	 *
	 * @throws ExportException if there is an error exporting.
	 **/
	private static void printHeaders(@NotNull CSVPrinter csvPrinter) {
		List<String> headers = new LinkedList<>();

		headers.add("day");
		headers.add("hour");
		headers.add("subject");
		headers.add("teacher");
		headers.add("class");
		headers.add("room");

		try {
			csvPrinter.printRecord(headers);
		} catch (IOException e) {
			throw new ExportException("Error writing headers to csv", e);
		}
	}

	/**
	 * Exports multiple timetables to separate CSV files.
	 *
	 * @param timetables the map of timetable names to timetables
	 * @param directory  the directory to save the CSV files
	 *
	 * @throws ExportException if there is an error exporting.
	 */
	public static void exportTimetablesToMultipleFiles(@NotNull Map<String, Timetable> timetables,
	                                                   @NotNull String directory) {
		Path path = Paths.get(directory);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				throw new ExportException("Error Creating directory for csv file", e);
			}
		}

		timetables.forEach((name, timetable) -> {
			try (Writer writer = new FileWriter(directory + "/" + name + ".csv");
			     CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

				printHeaders(csvPrinter);
				generateCSV(timetable, csvPrinter);
			} catch (ExportException | IOException e) {
				throw new ExportException("Error writing to csv file", e);
			}
		});
	}

	/**
	 * Generates a CSV representation of the given timetable.
	 * The CSV contains a row for each time slot in the timetable.
	 * Each row contains the day of the week, the hour of the day, the subject, the teacher, and
	 * the
	 * class.
	 *
	 * @param timetable  The timetable to generate the CSV for
	 * @param csvPrinter The CSVPrinter to use for generating the CSV
	 *
	 * @throws ExportException if there is an error exporting.
	 */
	private static void generateCSV(@NotNull Timetable timetable, @NotNull CSVPrinter csvPrinter) {
		timetable.getTimetable().forEach((slot, lesson) -> {
			try {
				if (lesson.getSubject() == Timetable.FREISTUNDE) {
					return;
				}

				LinkedList<String> row = new LinkedList<>();
				row.add(slot.getDay().toString());
				row.add(String.valueOf(slot.getHour()));
				row.add(lesson.getSubject().toString());

				if (lesson.getTeacher() != null) {
					row.add(lesson.getTeacher().toString());
				} else {
					row.add("");
				}

				if (lesson.getSchoolClass() != null) {
					row.add(lesson.getSchoolClass().toString());
				} else {
					row.add("");
				}

				if (lesson.getRoom() != null) {
					row.add(lesson.getRoom().toString());
				} else {
					row.add("");
				}

				csvPrinter.printRecord(row);
			} catch (IOException e) {
				throw new ExportException("Error writing data to csv file", e);
			}
		});
	}
}
