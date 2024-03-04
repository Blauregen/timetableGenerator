package at.htl.timetableGenerator.constraints;

import at.htl.timetableGenerator.constraints.constraints.CustomConstraint;
import at.htl.timetableGenerator.factory.ConstraintFactory;

import java.util.Set;

public class ConstraintParser {
	private final String path;

	public ConstraintParser(String path) {
		this.path = path;
	}

	public Set<CustomConstraint> parse() {
		return ConstraintFactory.createFromFile(path);
	}
}
