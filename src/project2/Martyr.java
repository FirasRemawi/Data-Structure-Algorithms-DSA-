package project2;

public class Martyr {
	private String name, district, location, date;
	private byte age;
	private char gender;

	public Martyr() {

	}

	public Martyr(String name, String date, byte age, String location, String district, char gender) {
		this.name = name;
		this.date = date;
		this.district = district;
		this.age = age;
		this.location = location;
		this.gender = gender;

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Martyr " + (name != null ? "name," + name + ", " : "")
				+ (district != null ? "district," + district + ", " : "")
				+ (location != null ? "location," + location + ", " : "") + (date != null ? "date," + date + ", " : "")
				+ "age," + age ;
	}
	
}
