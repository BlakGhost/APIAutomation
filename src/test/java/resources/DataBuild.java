package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.DeletePlace;
import pojo.Location;

public class DataBuild{
	public AddPlace addPlacePayload(String name, String address, String language) {
		AddPlace addp= new AddPlace();
		addp.setAccuracy(50);
		addp.setAddress(address);
		addp.setLanguage(language);
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		addp.setLocation(loc);
		addp.setName(name);
		addp.setWebsite("https/adfasfdsf");
		List<String> type= new ArrayList<String>();
		type.add("maths");
		type.add("java");
		addp.setTypes(type);
		return addp;
	}
	public DeletePlace deletePlacePayload(String place_id) {
		DeletePlace delete = new DeletePlace();
		delete.setPlace_id(place_id);
		return delete;
	}
}
