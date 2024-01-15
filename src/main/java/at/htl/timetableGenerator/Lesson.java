package at.htl.timetableGenerator;

public class Lesson {
	private Course course;
	private TimeSlot timeSlot;

	public Lesson(Course course, TimeSlot timeSlot) {
		this.course = course;
		this.timeSlot = timeSlot;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	@Override
	public String toString() {
		return String.format("%s: %s", timeSlot, course);
	}
}
