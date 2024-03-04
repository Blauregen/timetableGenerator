package at.htl.timetableGenerator.constraints;

import at.htl.timetableGenerator.exceptions.ImportException;
import at.htl.timetableGenerator.factory.CustomConstraintFactory;
import at.htl.timetableGenerator.factory.MultipleConstraintFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ConstraintParser {
	private final String path;

	public ConstraintParser(String path) {
		this.path = path;
	}

	public Set<Constraint> parse() {
		try (Stream<String> lines = Files.lines(Paths.get(path))) {
			HashSet<Constraint> constraints = new HashSet<>();

			lines.forEach((line) -> {
				for (TimeKeyword value : TimeKeyword.values()) {
					Pattern pattern = Pattern.compile("\\b" + value + "\\b");
					Matcher matcher = pattern.matcher(line.toUpperCase());

					if (matcher.find()) {
						constraints.add(CustomConstraintFactory.createFromString(line));
						return;
					}
				}

				for (DurationKeyword value : DurationKeyword.values()) {
					Pattern pattern = Pattern.compile("\\b" + value + "\\b");
					Matcher matcher = pattern.matcher(line.toUpperCase());

					if (matcher.find()) {
						constraints.add(MultipleConstraintFactory.createFromString(line));
						return;
					}
				}
			});


			return constraints;
		} catch (IOException e) {
			throw new ImportException("Error reading File!", e);
		}
	}
}
