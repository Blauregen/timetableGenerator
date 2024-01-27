package at.htl.timetableGenerator;
public class SubjectFactory {
	private static final int VALID_RECORD_ARRAY_LENGTH = 2;

	public Subject createFromString(String line){
		// Splitting the line (csv) and soring it in an array for later access
		String[] field = line.split(";");

		// Throws IllegalArgumentException if the array is longer or shorter then expected
		if(field.length != VALID_RECORD_ARRAY_LENGTH) {
			throw new IllegalArgumentException();
		}

		String name = field[0].strip(); // Name of subject is expected to be here
		String shortName = field[1].strip(); // Shortened name of subject is expected to be here

		return new Subject(name, shortName);
	}
}
