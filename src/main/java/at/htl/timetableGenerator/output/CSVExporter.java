package at.htl.timetableGenerator.output;

import at.htl.timetableGenerator.TimeSlot;
import at.htl.timetableGenerator.Timetable;
import at.htl.timetableGenerator.exceptions.ExportException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
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
	 * @param name      the name of the timetable
	 */
	public static void exportTimetableToFile(Timetable timetable, String path, String name) {
		HashMap<String, Timetable> timetables = new HashMap<>();
		timetables.put(name, timetable);
		exportTimetablesToSingleFile(timetables, path);
	}

	/**
	 * Exports multiple timetables to a single CSV file.
	 *
	 * @param timetables the map of timetable names to timetables
	 * @param stringPath the path of the CSV file
	 */
	public static void exportTimetablesToSingleFile(Map<String, Timetable> timetables, String stringPath) {
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
			timetables.forEach((name, timetable) -> {
				try {
					generateCSV(timetable, csvPrinter, name);

					csvPrinter.println();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
		} catch (IOException e) {
			throw new ExportException("Error writing to csv file", e);
		}
	}

	/**
	 * Exports multiple timetables to separate CSV files.
	 *
	 * @param timetables the map of timetable names to timetables
	 * @param directory  the directory to save the CSV files
	 */
	public static void exportTimetablesToMultipleFiles(Map<String, Timetable> timetables, String directory) {
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
				try {
					generateCSV(timetable, csvPrinter, name);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private static void generateCSV(Timetable timetable, CSVPrinter csvPrinter, String name) throws IOException {
		List<String> headers = new LinkedList<>();

		for (int i = 0; i < timetable.getNoOfDayPerWeek(); i++) {
			headers.add(DayOfWeek.of(i + 1).toString().substring(0, 2));
		}

		headers.add("Class");
		headers.add("Teacher");

		csvPrinter.printRecord(headers);

		for (int j = 0; j < timetable.getTimetableAsArray()[0].length; j++) {
			List<String> row = new LinkedList<>();
			for (int i = 0; i < timetable.getTimetableAsArray().length; i++) {
				row.add(timetable.getLesson(new TimeSlot(DayOfWeek.of(i + 1), j)).toString());
			}

			row.add(name);
			csvPrinter.printRecord(row);
		}
	}
}
