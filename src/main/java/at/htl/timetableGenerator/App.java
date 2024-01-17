package at.htl.timetableGenerator;

import java.time.DayOfWeek;

public class App {
	public static void main(String[] args) {
		Timetable timetable = new Timetable(5, 10);
		Course am = new Course("Mathe", "AM");
		timetable.setLesson(new TimeSlot(DayOfWeek.MONDAY, 0), am);

		System.out.println(timetable);
	}
}
