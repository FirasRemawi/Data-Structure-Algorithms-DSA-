package Project3;

import java.util.Objects;

public class Martyr implements Comparable<Martyr> {
	private String name;
	private String date;
	private byte age;
	private String location;
	private String district;
	private char gender;

	public Martyr(String name, String date, byte age, String location, String district, char gender) {
		this.name = name;
		this.date = date;
		this.district = district;
		this.age = age;
		this.location = location;
		this.gender = gender;
	}

	public Martyr(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	public String getDistrict() {
		return district;
	}

	public byte getAge() {
		return age;
	}

	public String getLocation() {
		return location;
	}

	public char getGender() {
		return gender;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Martyr martyr = (Martyr) o;
		boolean result = age == martyr.age && gender == martyr.gender && Objects.equals(name, martyr.name)
				&& Objects.equals(date, martyr.date) && Objects.equals(location, martyr.location)
				&& Objects.equals(district, martyr.district);
		return result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, date, age, location, district, gender);
	}

	@Override
	public int compareTo(Martyr other) {
		int last = this.name.compareTo(other.name);
		return last == 0 ? this.date.compareTo(other.date) : last;
	}

	@Override
	public String toString() {
		return "Martyr{" + "name='" + name + '\'' + ", date='" + date + '\'' + ", age=" + age + ", location='"
				+ location + '\'' + ", district='" + district + '\'' + ", gender=" + gender + '}';
	}

}
