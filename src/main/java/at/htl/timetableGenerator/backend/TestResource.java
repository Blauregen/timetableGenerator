package at.htl.timetableGenerator.backend;

import at.htl.timetableGenerator.constraints.ConstraintUtils;
import at.htl.timetableGenerator.output.ExportData;
import at.htl.timetableGenerator.output.ExportFormat;
import io.vertx.codegen.TypeParamInfo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Path("/api")
public class TestResource {
	public class multipleAnswersReturnObject {
		public String[] answer;
	}


	@GET
	@Path("/getAllExportFormats")
	@Produces(MediaType.APPLICATION_JSON)
	public multipleAnswersReturnObject exportFormats(){
		multipleAnswersReturnObject result = new multipleAnswersReturnObject();

		ExportFormat[] formats = ExportFormat.values();
		List<String> exportsAsStrings = new LinkedList<>();

		for (ExportFormat currentExportFormat : formats) {
			exportsAsStrings.add(currentExportFormat.toString());
		}

		result.answer = exportsAsStrings.toArray(new String[0]);
		return result;
	}

	@GET
	@Path("/getAllExportData")
	@Produces(MediaType.APPLICATION_JSON)
	public multipleAnswersReturnObject exportData(){
		multipleAnswersReturnObject result = new multipleAnswersReturnObject();

		ExportData[] exportData = ExportData.values();
		List<String> exportsAsStrings = new LinkedList<>();

		for (ExportData currentExportData : exportData) {
			exportsAsStrings.add(currentExportData.toString());
		}

		result.answer = exportsAsStrings.toArray(new String[0]);
		return result;
	}

	@GET
	@Path("/getAllConstraints")
	@Produces(MediaType.APPLICATION_JSON)
	public multipleAnswersReturnObject constraints(){
		multipleAnswersReturnObject result = new multipleAnswersReturnObject();

		Set<Class<?>> constraints = ConstraintUtils.getAllConstraints();
		List<String> constraintsAsString = new LinkedList<>();

		for (Class<?> currentConstraint : constraints) {
			constraintsAsString.add(currentConstraint.toString());
		}

		result.answer = constraintsAsString.toArray(new String[0]);
		return result;
	}
}