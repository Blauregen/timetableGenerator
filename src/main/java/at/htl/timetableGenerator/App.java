package at.htl.timetableGenerator;

import java.time.DayOfWeek;

public class App {
	public static void main(String[] args) {
		Timetable timetable = new Timetable(5, 10);
		Course am = new Course("Mathe", "AM");
		timetable.setLesson(new Lesson(am, new TimeSlot(DayOfWeek.MONDAY, 0)));

		System.out.println(timetable);
	}
}
