package at.htl.timetableGenerator;

import at.htl.timetableGenerator.output.CSVExporter;
import at.htl.timetableGenerator.output.ExcelExporter;

import java.time.DayOfWeek;
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
		Timetable timetable = new Timetable(5, 10);
		Subject ly = new Subject("Lyric", "LY");
		Set<Subject> subjects = new HashSet<>();
		subjects.add(ly);
		Teacher dietmar = new Teacher("Dietmar von Aist", subjects, new Timetable(5, 10));
		Lesson lesson = new Lesson(ly, new TimeSlot(DayOfWeek.MONDAY, 0));
		lesson.setTeacher(dietmar);
		timetable.setLesson(lesson);

		HashMap<String, Timetable> timetableHashMap = new HashMap<>();
		timetableHashMap.put("3Bhitm", timetable);
		timetableHashMap.put("Dietmar von Aist", dietmar.getTimetable());

		System.out.println(timetable);
		System.out.println(dietmar.getTimetable());
		ExcelExporter.exportToWorkbook(timetableHashMap, "output/excel.xlsx");
		CSVExporter.exportTimetablesToSingleFile(timetableHashMap, "output/all.csv");
		CSVExporter.exportTimetablesToMultipleFiles(timetableHashMap, "output/multiple/");
		CSVExporter.exportTimetableToFile(dietmar.getTimetable(), "output/dietmar.csv", "Dietmar von Aist");
	}
}
