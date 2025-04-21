package application;

public abstract class Person {
	 String name, EventLocation, dateOfDeath;
	 int age;
	 String gender;

	public Person(String name, int age, String eventLocation,String dateString, String gender) {
		super();
		this.name = name;
		this.EventLocation = eventLocation;
		this.age = age;
		this.dateOfDeath = dateString;
		this.gender = gender;
	}

	public Person() {
		this("Firas", 20, "Ramallah","3/3/2024" ,"M");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEventLocation() {
		return EventLocation;
	}

	public void setEventLocation(String eventLocation) {
		EventLocation = eventLocation;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	@Override
	public String toString() {
		return "Martyr name," + name + ", EventLocation," + EventLocation + ", age," + age + ", gender," + gender;
	}

}
