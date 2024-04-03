package at.htl.timetableGenerator.backend;

import at.htl.timetableGenerator.output.ExportData;
import at.htl.timetableGenerator.output.ExportFormat;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.LinkedList;
import java.util.List;

@Path("/api")
public class TestResource {
	public class multipleAnswersReturnObject {
		public String[] answer;
	}


	@GET
	@Path("/exportFormats")
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
	@Path("/exportData")
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
}