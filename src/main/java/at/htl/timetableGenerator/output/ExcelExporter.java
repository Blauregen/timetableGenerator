package at.htl.timetableGenerator.output;

import at.htl.timetableGenerator.exceptions.ExportException;
import at.htl.timetableGenerator.model.Lesson;
import at.htl.timetableGenerator.model.Teacher;
import at.htl.timetableGenerator.model.TimeSlot;
import at.htl.timetableGenerator.model.Timetable;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

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
public class ExcelExporter implements TimetableExporter {

	/**
	 * Exports multiple timetables to an Excel workbook.
	 * Each timetable is exported to a separate sheet in the workbook.
	 * The name of the sheet is the name of the timetable.
	 * The first row of each sheet contains the days of the week.
	 * Each subsequent row represents a time slot and contains the short name of the subject
	 * scheduled at that time
	 * slot.
	 *
	 * @param timetables the map of timetable names to timetables
	 * @param stringPath the path of the Excel workbook
	 *
	 * @throws ExportException if an error occurs during the export process
	 */
	public void export(@NotNull Map<String, Timetable> timetables,
	                                    @NotNull String stringPath) {
		Path path = Paths.get(stringPath);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path.getParent());
			} catch (IOException e) {
				throw new ExportException("Error Creating directory for excel workbook file", e);
			}
		}

		try (Workbook workbook = new XSSFWorkbook()) {
			CellStyle cs = workbook.createCellStyle();
			cs.setWrapText(true);
			cs.setAlignment(HorizontalAlignment.CENTER);
			cs.setVerticalAlignment(VerticalAlignment.CENTER);

			timetables.forEach((name, timetable) -> {
				try (FileOutputStream fileOut = new FileOutputStream(stringPath)) {
					Sheet sheet = workbook.createSheet(name);

					Row row = sheet.createRow(0);
					for (int i = 0; i < timetable.getNoOfDayPerWeek(); i++) {
						Cell cell = row.createCell(i);
						cell.setCellValue(DayOfWeek.of(i + 1).toString().substring(0, 2));
						cell.setCellStyle(cs);

					}

					for (int j = 0; j < timetable.getTimetableAsArray()[0].length; j++) {
						row = sheet.createRow(j + 1);

						for (int i = 0; i < timetable.getTimetableAsArray().length; i++) {
							TimeSlot slot = new TimeSlot(DayOfWeek.of(i + 1), j);
							Lesson lesson = timetable.getLesson(slot);
							Teacher teacher = lesson.getTeacher();

							Cell cell = row.createCell(i);
							String output = lesson.getSubject().toString();

							if (teacher != null) {
								output += System.lineSeparator() + teacher;
							}

							if (lesson.getRoom() != null) {
								output += System.lineSeparator() + lesson.getRoom();
							}

							cell.setCellValue(output);

							cell.setCellStyle(cs);
							sheet.autoSizeColumn(i);
						}

						row.setHeight((short) -1);
					}


					workbook.write(fileOut);
				} catch (IOException e) {
					throw new ExportException("Error creating excel sheet", e);
				}
			});
		} //TODO: remove as soon as apache fixes their shit
		catch (NoSuchMethodError ignored) {
		} catch (IOException e) {
			throw new ExportException("Error creating excel file", e);
		}
	}
}
