package at.htl.timetableGenerator.output;

import at.htl.timetableGenerator.Lesson;
import at.htl.timetableGenerator.Subject;
import at.htl.timetableGenerator.Timetable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;

/**
 * Utility class for printing a timetable in a formatted manner.
 */
public final class TimetablePrinter {

	/**
	 * Private constructor to prevent instantiation of utility class.
	 */
	private TimetablePrinter() {
		throw new AssertionError("Utility class should not be instantiated.");
	}

	/**
	 * Prints a thick separator line in the timetable.
	 *
	 * @param tt          The timetable to print the separator for.
	 * @param columnWidth The width of each column in the timetable.
	 * @param formattedTT The StringBuilder to append the separator to.
	 */
	public static void printThickSeparator(@NotNull Timetable tt, int columnWidth,
	                                       @NotNull StringBuilder formattedTT) {
		for (int i = 0; i < tt.getNoOfDayPerWeek(); i++) {
			formattedTT.append("|".repeat(columnWidth));

			if (i < tt.getNoOfDayPerWeek() - 1) {
				formattedTT.append('|');
			}
		}

		formattedTT.append('\n');
	}

	/**
	 * Calculates the width of the widest column in the timetable.
	 *
	 * @param tt The timetable to calculate the column width for.
	 *
	 * @return The width of the widest column in the timetable.
	 */
	public static int calculateColumnWidth(@NotNull Timetable tt) {
		int columnWidth = 0;

		for (Lesson[] lessons : tt.getTimetableAsArray()) {
			for (Lesson lesson : lessons) {
				columnWidth = Math.max(columnWidth, lesson.getSubject().shortName().length());
			}
		}

		//For extra padding
		return Math.max(columnWidth, 2) + 2;
	}

	/**
	 * Prints a row of subjects in the timetable.
	 *
	 * @param tt          The timetable to print the row for.
	 * @param time        The index of the row to print.
	 * @param columnWidth The width of the widest column in the timetable.
	 * @param formattedTT The StringBuilder to append the row to.
	 */
	public static void printRow(@NotNull Timetable tt, int time, int columnWidth,
	                            @NotNull StringBuilder formattedTT) {
		for (int i = 0; i < tt.getNoOfDayPerWeek(); i++) {
			try {
				Subject currentSubject = tt.getTimetableAsArray()[i][time].getSubject();

				int noOfPaddingSpaces = getPadding(columnWidth, currentSubject.shortName());
				String paddingString = " ".repeat(noOfPaddingSpaces);
				formattedTT.append(paddingString);
				formattedTT.append(
						String.format("%1$-" + (columnWidth - paddingString.length()) + "s",
						              currentSubject));
			} catch (ArrayIndexOutOfBoundsException e) {
				formattedTT.append(" ".repeat(columnWidth));
			}

			if (i < tt.getNoOfDayPerWeek() - 1) {
				formattedTT.append('|');
			}
		}

		formattedTT.append('\n');
	}

	/**
	 * Calculates the number of padding spaces needed to center a string within a given width.
	 *
	 * @param width          The total width to center the string within.
	 * @param stringToCenter The string to center.
	 *
	 * @return The number of padding spaces needed to center the string.
	 */
	@Contract(pure = true)
	public static int getPadding(int width, @NotNull String stringToCenter) {
		return (width - stringToCenter.length()) / 2;
	}

	/**
	 * Prints a separator line in the timetable.
	 *
	 * @param tt          The timetable to print the separator for.
	 * @param columnWidth The width of the widest column in the timetable.
	 * @param formattedTT The StringBuilder to append the separator to.
	 */
	public static void printSeparator(@NotNull Timetable tt, int columnWidth,
	                                  @NotNull StringBuilder formattedTT) {
		for (int i = 0; i < tt.getNoOfDayPerWeek(); i++) {
			formattedTT.append("-".repeat(columnWidth));

			if (i < tt.getNoOfDayPerWeek() - 1) {
				formattedTT.append('|');
			}
		}

		formattedTT.append('\n');
	}

	/**
	 * Prints the header row of the timetable.
	 *
	 * @param tt          The timetable to print the header for.
	 * @param columnWidth The width of the widest column in the timetable.
	 * @param formattedTT The StringBuilder to append the header to.
	 */
	public static void printHeader(@NotNull Timetable tt, int columnWidth,
	                               @NotNull StringBuilder formattedTT) {
		for (int i = 0; i < tt.getNoOfDayPerWeek(); i++) {
			String currentDay = DayOfWeek.values()[i].toString();
			currentDay = currentDay.charAt(0) + currentDay.substring(1, 2).toLowerCase();

			int noOfPaddingSpaces = getPadding(columnWidth, currentDay);
			String paddingString = " ".repeat(noOfPaddingSpaces);
			formattedTT.append(paddingString);
			formattedTT.append(String.format("%1$-" + (columnWidth - paddingString.length()) + "s",
			                                 currentDay));

			if (i < tt.getNoOfDayPerWeek() - 1) {
				formattedTT.append('|');
			}
		}

		formattedTT.append('\n');
	}

	/**
	 * Prints the entire timetable in a formatted manner.
	 *
	 * @param tt The timetable to print.
	 *
	 * @return A string representation of the formatted timetable.
	 */
	public static @NotNull String print(@NotNull Timetable tt) {
		StringBuilder formattedTT = new StringBuilder();

		int columnWidth = calculateColumnWidth(tt);
		printHeader(tt, columnWidth, formattedTT);
		printThickSeparator(tt, columnWidth, formattedTT);

		for (int i = 0; i < tt.getMaxNoOfHoursPerDay(); i++) {
			printRow(tt, i, columnWidth, formattedTT);
			printSeparator(tt, columnWidth, formattedTT);
		}

		return formattedTT.toString();
	}
}
