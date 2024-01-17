package at.htl.timetableGenerator;

import at.htl.timetableGenerator.exceptions.IncompatibleCourseException;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Lesson lesson = (Lesson) o;
		return Objects.equals(course, lesson.course) && Objects.equals(timeSlot, lesson.timeSlot);
	}

	@Override
	public int hashCode() {
		return Objects.hash(course, timeSlot);
	}
}