package application;

public class Martyr extends Person {

	public Martyr(String name, int age, String eventLocation, String dateOfDeath, char gender) {
		this.name = name;
        this.age = age;
        this.EventLocation = eventLocation;
        this.dateOfDeath = dateOfDeath;
        //this.gender = gender;
	}

	@Override
	public String toString() {
		return "name," + name + ", EventLocation," + EventLocation + ", dateOfDeath," + dateOfDeath + ", age,"
				+ age + ", gender," + gender;
	}

	
}
