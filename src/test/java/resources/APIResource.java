package resources;

public enum APIResource {
	
	getPlaceAPI("/maps/api/place/get/json"),
	addPlaceAPI("/maps/api/place/add/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	String resource;
	APIResource(String resource) {
		this.resource=resource;
	}
	public String getResource() {
		return resource;
	}
	
}
