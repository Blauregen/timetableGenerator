package at.htl.timetableGenerator.backend;

import at.htl.timetableGenerator.constraints.DurationKeyword;
import at.htl.timetableGenerator.constraints.TimeKeyword;
import at.htl.timetableGenerator.constraints.constraints.CustomConstraint;
import at.htl.timetableGenerator.model.*;
import at.htl.timetableGenerator.output.ExportData;
import at.htl.timetableGenerator.output.ExportFormat;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Path("/api")
public class TimetableResource {
	public class MultipleStringsReturnObject {
		public String[] answer;
//==========================ERROR CODES==============================
//Information [100 - 199]		==		Success [200 - 299]
//---------------------------------------------------------------------
//100 - Continue				==		200 - Ok
//101 - Switching Protocols		==		201 - Created
//102 - Processing				==		202 - Accepted
//103 - Early Hints				==		204 - No Content
//								==		206 - Partial Content
//======================================================================
//Redirect [300 - 399]			==		Client Error [400 - 499]
//----------------------------------------------------------------------
//300 - Multiple Choices		==		400 - Bad Request
//301 - Moved Permanantly		==		401 - Unauthorized
//304 - Not Modified			==		403 - Forbidden
//307 - Temporary Redirect		==		404 - Not Found
//308 - Permanant Redirect		==		409 - Conflict
//======================================================================
//Server Error [500 - 599]
//----------------------------------------------------------------------
//500 - Internal Server Error
//501 - Not Implemented
//502 - Bad Gateway
//503 - Service Unavailable
//504 - Gateway timeout
//=======================================================================
		public int errorCode = 500;
	}



	@GET
	@Path("/getAllExportFormats")
	@Produces(MediaType.APPLICATION_JSON)
	public MultipleStringsReturnObject exportFormats(){
		MultipleStringsReturnObject result = new MultipleStringsReturnObject();

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
	public MultipleStringsReturnObject exportData(){
		MultipleStringsReturnObject result = new MultipleStringsReturnObject();

		ExportData[] exportData = ExportData.values();
		List<String> exportsAsStrings = new LinkedList<>();

		for (ExportData currentExportData : exportData) {
			exportsAsStrings.add(currentExportData.toString());
		}

		result.answer = exportsAsStrings.toArray(new String[0]);
		result.errorCode = 200;
		return result;
	}

//	@GET
//	@Path("/getAllConstraints")
//	@Produces(MediaType.APPLICATION_JSON)
//	public multipleAnswersReturnObject constraints(){
//		multipleAnswersReturnObject result = new multipleAnswersReturnObject();
//
//		Set<Class<?>> constraints = ConstraintUtils.getAllConstraints();
//		List<String> constraintsAsString = new LinkedList<>();
//
//		for (Class<?> currentConstraint : constraints) {
//			constraintsAsString.add(currentConstraint.toString());
//		}
//
//		result.answer = constraintsAsString.toArray(new String[0]);
//		return result;
//	}
	@GET
	@Path("/getAllTimeKeywords")
	@Produces(MediaType.APPLICATION_JSON)
	public MultipleStringsReturnObject timeKeywords(){
		MultipleStringsReturnObject result = new MultipleStringsReturnObject();

		TimeKeyword[] timeKeywords = TimeKeyword.values();
		List<String> timeKeywordsAsString = new LinkedList<>();

		for (TimeKeyword currentExportData : timeKeywords) {
			timeKeywordsAsString.add(currentExportData.toString());
		}

		result.answer = timeKeywordsAsString.toArray(new String[0]);
		result.errorCode = 200;
		return result;
	}

	@GET
	@Path("/getAllDurationKeywords")
	@Produces(MediaType.APPLICATION_JSON)
	public MultipleStringsReturnObject durationKeywords(){
		MultipleStringsReturnObject result = new MultipleStringsReturnObject();

		DurationKeyword[] timeKeywords = DurationKeyword.values();
		List<String> durationKeywordsAsString = new LinkedList<>();

		for (DurationKeyword currentExportData : timeKeywords) {
			durationKeywordsAsString.add(currentExportData.toString());
		}

		result.answer = durationKeywordsAsString.toArray(new String[0]);
		result.errorCode = 200;
		return result;
	}

	@POST
	@Path("/createCustomConstraintFromString")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(CustomConstraint constraint){
		return Response.ok().build();
	}

	@GET
	@Path("/getAllSchools")
	@Produces(MediaType.APPLICATION_JSON)
	public List<School> schools(){
		List<School> schools = new LinkedList<>();
		schools.add(getSampleSchool("HTLLeonding"));
		schools.add(getSampleSchool("HTL Goethestraße / HTL Paul Hahn"));
		schools.add(getSampleSchool("Hildegard von Bingen´s Übungslazarett"));
		return schools;
	}

	@GET
	@Path("/getAllRooms")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Room> rooms(){
		List<Room> rooms = new LinkedList<>();
		rooms.add(new Room("123-A"));
		rooms.add(new Room("123-B"));
		rooms.add(new Room("E24"));
		rooms.add(new Room("U11"));
		rooms.add(new Room("112"));
		rooms.add(new Room("143"));
		rooms.add(new Room("206"));
		rooms.add(new Room("208"));
		rooms.add(new Room("U07"));
		rooms.add(new Room("101"));
		rooms.add(new Room("E46"));
		rooms.add(new Room("116"));

		return rooms;
	}

	private static School getSampleSchool(String name){
		Subject subjectE = new Subject("Englisch", "E", 2);
		Subject subjectAM = new Subject("Angewandte Mathematik", "AM", 3);

		WeeklySubject weeklySubject1 = new WeeklySubject(subjectE, 2);
		WeeklySubject weeklySubject2 = new WeeklySubject(subjectAM, 1);

		HashSet<WeeklySubject> weeklySubjects = new HashSet<>();
		HashSet<WeeklySubject> weeklySubjects2 = new HashSet<>();

		weeklySubjects.add(weeklySubject1);
		weeklySubjects.add(weeklySubject2);
		weeklySubjects2.add(weeklySubject2);

		SchoolClass schoolClass = new SchoolClass("5AHIF", weeklySubjects);
		SchoolClass schoolClass2 = new SchoolClass("5AHIF", weeklySubjects2);

		HashSet<SchoolClass> schoolClasses = new HashSet<>();

		schoolClasses.add(schoolClass);
		schoolClasses.add(schoolClass2);

		HashSet<Subject> wellischSubjects = new HashSet<>();
		wellischSubjects.add(subjectE);
		Teacher wellisch = new Teacher("Wellisch", wellischSubjects, 5, 5);

		HashSet<Subject> kerschnerSubjects = new HashSet<>();
		kerschnerSubjects.add(subjectAM);
		Teacher kerschner = new Teacher("Kerschner", kerschnerSubjects, 7, 3);

		HashSet<Teacher> teachers = new HashSet<>();
		teachers.add(wellisch);
		teachers.add(kerschner);

		return new School(name, schoolClasses, teachers);
	}



}