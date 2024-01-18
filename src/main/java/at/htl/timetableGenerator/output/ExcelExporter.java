package at.htl.timetableGenerator.output;

import at.htl.timetableGenerator.TimeSlot;
import at.htl.timetableGenerator.Timetable;
import at.htl.timetableGenerator.exceptions.ExportException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.util.Map;

/**
 * This class provides a method to export timetables to an Excel workbook.
 */
public class ExcelExporter {

	/**
	 * Exports multiple timetables to an Excel workbook.
	 * Each timetable is exported to a separate sheet in the workbook.
	 * The name of the sheet is the name of the timetable.
	 * The first row of each sheet contains the days of the week.
	 * Each subsequent row represents a time slot and contains the short name of the course scheduled at that time
	 * slot.
	 *
	 * @param timetables the map of timetable names to timetables
	 * @param stringPath the path of the Excel workbook
	 *
	 * @throws ExportException if an error occurs during the export process
	 */
	public static void exportToWorkbook(Map<String, Timetable> timetables, String stringPath) {
		Path path = Paths.get(stringPath);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path.getParent());
			} catch (IOException e) {
				throw new ExportException("Error Creating directory for csv file", e);
			}
		}

		try (Workbook workbook = new XSSFWorkbook()) {
			timetables.forEach((name, timetable) -> {
				Sheet sheet = workbook.createSheet(name);

				Row row = sheet.createRow(0);
				for (int i = 0; i < timetable.getNoOfDayPerWeek(); i++) {
					row.createCell(i).setCellValue(DayOfWeek.of(i + 1).toString().substring(0, 2));
				}

				for (int j = 0; j < timetable.getTimetableAsArray()[0].length; j++) {
					row = sheet.createRow(j + 1);

					for (int i = 0; i < timetable.getTimetableAsArray().length; i++) {
						row.createCell(i).setCellValue(
								timetable.getLesson(new TimeSlot(DayOfWeek.of(i + 1), j)).getCourse().shortName());
					}
				}

				try (FileOutputStream fileOut = new FileOutputStream(stringPath)) {
					workbook.write(fileOut);
				} catch (IOException e) {
					throw new ExportException("Error creating excel sheet", e);
				}
			});
		} catch (IOException e) {
			throw new ExportException("Error creating excel file", e);
		}
	}
}
