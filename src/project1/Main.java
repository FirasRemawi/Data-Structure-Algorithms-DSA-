package project1;
/*// Method to print districts, locations, martyr dates, and martyrs
	private void printDistrictsLocationsMartyrs() {
		System.out.println("Districts, Locations, Martyr Dates, and Martyrs:");
		printDistrictsLocationsMartyrs(districtTree.getRoot());
	}

	// Helper method to print districts, locations, martyr dates, and martyrs
	// recursively
	private void printDistrictsLocationsMartyrs(DistrictNode node) {
		if (node != null) {
			System.out.println("District: " + ((District) node.getElement()).getName());
			printLocationsMartyrDates(node.getHead());
			printDistrictsLocationsMartyrs(node.getLeft());
			printDistrictsLocationsMartyrs(node.getRight());
		}
	}

	// Helper method to print locations and martyr dates
	private void printLocationsMartyrDates(LocationTree locationTree) {
		if (locationTree != null) {
			printLocationsMartyrDates(locationTree.getRoot());
		}
	}

	// Helper method to print locations and martyr dates recursively
	private void printLocationsMartyrDates(LocationNode locationNode) {
		if (locationNode != null) {
			System.out.println("\tLocation: " + ((Location) locationNode.getElement()).getName());
			printMartyrDates(locationNode.getHead());
			printLocationsMartyrDates(locationNode.getLeft());
			printLocationsMartyrDates(locationNode.getRight());
		}
	}

	// Helper method to print martyr dates and martyrs
	private void printMartyrDates(DateTree martyrDateTree) {
		if (martyrDateTree != null) {
			printMartyrDates(martyrDateTree.getRoot());
		}
	}

	// Helper method to print martyr dates and martyrs recursively
	private void printMartyrDates(DateNode node) {
		if (node != null) {
			System.out.println("\t\tMartyr Date: " + (node.getDate().getDate()));
			printMartyrs(node.getHead());
			printMartyrDates(node.getLeft());
			printMartyrDates(node.getRight());
		}
	}

	// When printing martyrs:
	private void printMartyrs(LinkedList martyrList) {
		if (martyrList != null && martyrList.getFront() != null) {
			MartyrNode current = martyrList.getFront();
			while (current != null) {
				System.out.println("\t\t\tMartyr: " + ((Martyr) current.getElement()).getName());
				current = current.getNext();
			}
		} else {
			System.out.println("\t\t\tNo martyrs found for this date.");
		}
	}
*/
/*

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	private FileChooser fChooser;
	private File f;
	private Scanner read;
	private Martyr martyr;
	private DoubleLinkedList districtList;
	private LinkedList locations, martyrs;
	private DistrictNode districtsNode;
	private LocationNode locationNode;
	private MartyrNode martyrNode;

	public void start(Stage primaryStage) {
		districtList = new DoubleLinkedList();
		locations = new LinkedList();
		martyrs = new LinkedList();
		District dis = new District("Ramallah");
		dis.getLocations().insertLocationSorted(new LocationNode("Firas"));
		dis.getLocations().insertLocationSorted(new LocationNode("m"));
		dis.getLocations().insertLocationSorted(new LocationNode("wewe"));
		System.out.println(dis.getLocations().getFront().getNext());
		districtsNode = new DistrictNode(dis);
		districtList.insertSorted(districtsNode);
		locations = districtList.getFront().getDistrict().getLocations();
		locations.insertLocationSorted(new LocationNode("firas"));
		locations.insertLocationSorted(new LocationNode("bahaa"));
		locations.insertLocationSorted(new LocationNode("ahmad"));
		LocationNode n = (LocationNode) locations.getFront();
		while (n != null) {
			System.out.println(n.getLocationName());
			n = n.next;
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	private void readFromAFile() {
		fChooser = new FileChooser();
		fChooser.setInitialDirectory(new File("C:\\Users\\ASUS\\eclipse-workspace\\StructureProjects"));
		// Enable the user to choose the file when the program runs
		f = fChooser.showOpenDialog(null);

		if (f != null) {
			try (Scanner read = new Scanner(f)) {
				while (read.hasNextLine()) {
					String line = read.nextLine().trim();
					String[] parts = line.split(",");

					if (isNumeric(parts[2]) && isChar(parts[5])) {
						Byte age = Byte.parseByte(parts[2]);
						char gender = parts[5].charAt(0);
						martyr = new Martyr(parts[0], parts[1], age, parts[3], parts[4], gender);

						// Find or create the district for this martyr
						DistrictNode districtNode = districtList.findDistrictNode(martyr.getDistrict());
						if (districtNode == null) {
							districtNode = new DistrictNode(martyr.getDistrict());
							districtList.insertSorted(districtNode);
						}
						districtNode.getLocations().insertLocationSorted(new LocationNode("Firas"));
						districtNode.getLocations().insertLocationSorted(new LocationNode("Bahaa"));
						districtNode.getLocations().insertLocationSorted(new LocationNode("Mohammad"));

						/*
						 * Location loc = new Location(parts[3].trim()); locations =
						 * districtNode.getLocations(); LocationNode locationNode = (LocationNode)
						 * locations.findNode(loc.getName()); if (locationNode == null) { locationNode =
						 * new LocationNode(martyr.getLocation());
						 * districtNode.getLocations().insertLocationSorted(locationNode); }
						 

						// Insert the martyr into the location's martyrs list
						LinkedList martyrList = locationNode.getMartyr(); // Assuming this returns the list correctly
						MartyrNode martyrNode = new MartyrNode(martyr);
						martyrList.insertMartyrSorted(martyrNode);
					}

				}
				printDistrictsWithLocationsOnly();

			} catch (FileNotFoundException e) {
				System.out.println("File not found: " + f.getAbsolutePath());
				e.printStackTrace();

			}
		}

	}

	private boolean isNumeric(String s) {
		if (s == null) {
			return false;
		}
		try {
			Integer.parseInt(s.trim()); // Use Integer.parseInt to support a larger range of numbers
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isChar(String s) {
		if (s == null) {
			return false;
		}
		String trimmed = s.trim();
		// Expecting only one character
		return trimmed.length() == 1 && Character.isLetter(trimmed.charAt(0));
	}

	public void printDistrictsWithLocationsOnly() {
		DistrictNode currentDistrict = districtList.getFront();
		while (currentDistrict != null) {
			String district = currentDistrict.getName();
			System.out.println("District: " + district);

			// Print locations
			LinkedList locationList = (LinkedList) currentDistrict.getLocations();
			LocationNode currentLocation = (LocationNode) locationList.getFront();
			while (currentLocation != null) {
				System.out.println("\tLocation: " + currentLocation.getLocationName());
				currentLocation = currentLocation.getNext();
			}

			currentDistrict = currentDistrict.getNext();
		}
	}

}
*/

