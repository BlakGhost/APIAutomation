package stepdefinitions;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		if(StepDefinition.place_id==null) {
		StepDefinition sd = new StepDefinition();
		sd.add_place_payload_with("Akshay", "krishnaopuri", "Spanish");
		sd.user_call_the_add_place_api_with_http_post_method("addPlaceAPI", "post");
		sd.the_api_call_is_success_with_status_code(200);
		StepDefinition.place_id = sd.getJsonValue(sd.finalres,"place_id");
		}
	}
	@After
	public  void afterScenario() {
		System.out.println("After Scenario is executed");
	}

}
