package project1;


public class Location {
    private String name;

    public Location(String name) {
        this.name = name;
    }

    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return "Location name," + name ;
	}
}
