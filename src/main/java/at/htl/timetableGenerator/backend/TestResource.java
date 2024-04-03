package at.htl.timetableGenerator.backend;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test")
public class TestResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Tester test() {
		Tester tester = new Tester();
		tester.blub = "Test";
		tester.meep = 1;
		return tester;
	}

	public static class Tester {
		public String blub;
		public int meep;
	}
}
