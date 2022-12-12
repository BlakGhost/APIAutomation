package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	static RequestSpecification req;
	static PrintStream log;
	
	public RequestSpecification requestSpec() throws IOException {
		if(req==null) {
		log = new PrintStream(new File("logging.txt"));}
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType("application/json").addQueryParam("key", "qaclick123").build();
		return req;
	}
	
	public ResponseSpecification responseSpec() {
		return new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").build();
		
	}
	public String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("C:\\Users\\NEETU\\Documents\\AkshayProject\\Cucumber\\src\\test\\java\\resources\\global.properties");
		prop.load(file);
		return prop.getProperty(key);
		
	}
	public String getJsonValue(Response res,String key) {
		JsonPath js = new JsonPath(res.asString());
		return js.get(key).toString();
	}
}
