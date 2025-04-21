package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class Driver extends Application {
	private DistrictTree districtTree;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		districtTree = new DistrictTree(); // Initialize the main data structure
		districtTree.insert(new District("Firas"));
		districtTree.insert(new District("al-Quds"));
		districtTree.insert(new District("HAhahahah"));
		districtTree.insert(new District("Haneen"));
		DistrictNode districtNode = districtTree.find(new District("Haneen"));

		System.out.println(districtTree.contains(new District("Haneen")));
	}

	public void setupGUI(Stage primaryStage) {
		// Set the title for the window
		primaryStage.setTitle("Martyrs Management System");

		// Create the root node for a tree view or any other GUI component
		TreeItem<String> rootItem = new TreeItem<>("Districts");
		TreeView<String> treeView = new TreeView<>(rootItem);
		primaryStage.setScene(new Scene(treeView, 800, 600)); // Set scene size

		// Show the GUI
		primaryStage.show();
	}

	public void testDistrictTree() {
		DistrictTree tree = new DistrictTree();
		tree.insert(new District("al-Quds"));
		tree.insert(new District("Bethlehem"));
		DistrictNode found = tree.find(new District("al-Quds"));
		if (found != null) {
			System.out.println("District found: " + found.getDistrictName().getName());
		} else {
			System.out.println("District not found: al-Quds");
		}
	}

	// Method to read from a file
	private void readFile1() {
		File f = new File("sample_2.csv");
		try (Scanner read = new Scanner(f)) {
			// Skip the first line
			read.nextLine();
			while (read.hasNextLine()) {
				String s = read.nextLine();
				// Split the line by ","
				String[] line = s.split(",");
				try {
					// If the format of the line was wrong
					if (line.length != 6)
						throw new IndexOutOfBoundsException("Incorrect inputs check the format!\n");
					// The first entry represents the name
					String name = line[0];
					if (isNumeric(name)) {
						throw new InputMismatchException(name + ": name must only be String not a number");
					}

					// The second entry represents the event
					String date = line[1];
					byte age;

					// The third entry represents the age
					if (line[2].isEmpty()) {
						age = -1;

					} else {
						// Checks if the age was a digit or not
						if (isNumeric(line[2]))
							age = Byte.parseByte(line[2]);
						else {
							age = -1;
						}
					}
					// The forth entry represents the location
					String location = line[3];
					if (isNumeric(location)) {
						throw new InputMismatchException(name + ": location must only be String not a number");
					}

					// The fifth entry represents the district
					String district = line[4];
					if (isNumeric(district)) {
						throw new InputMismatchException(name + ": district must only be String not a number");
					}
					char gender = '?';

					// The sixth entry represents the gender
					if (line[5].equals("NA") || line[5].isEmpty())
						gender = 'N';
					else
						gender = line[5].charAt(0);

					if (gender != 'm' && gender != 'M' && gender != 'F' && gender != 'f' && gender != 'N')
						throw new InputMismatchException(name + ": Gender must only be M/m/F/f\n");

					// Insert district
					District dis = new District(district);
					DistrictNode districtNode = districtTree.find(dis);
					// district or Null
					if (districtNode == null) {
						districtNode = new DistrictNode(dis);
						districtTree.insert(dis);
						districtNode.setHead(dis.getLocationTree());
					}

					// Get the reference to the locationTree of the district
					LocationTree locationTree = districtNode.getHead();
					if (locationTree == null) {
						locationTree = new LocationTree();
						districtNode.setHead(locationTree);
					}

					// Insert location
					Location loc = new Location(location);
					LocationNode locationNode = locationTree.find(loc);
					if (locationNode == null) {
						locationNode = new LocationNode(loc);
						locationTree.insert(loc);
						System.out.println(loc.getName());
						locationNode.setHead(loc.getDateTree()); // Explicitly set a new DateTree if not already set
					}

					// Now, access the DateTree
					DateTree martyrDateTree = locationNode.getHead();
					if (martyrDateTree == null) {
						martyrDateTree = new DateTree();
						locationNode.setHead(martyrDateTree);
					}

					// Insert martyr date
					Date martDate = new Date(date);
					DateNode martDateNode = martyrDateTree.find(martDate);
					if (martDateNode == null) {
						martDateNode = new DateNode(martDate);
						martyrDateTree.insert(martDate);
						martDateNode.setHead(martDate.getMartyrList());
					}
//							MartyrLinkedList martyrList = martDate.getMartyrList();
					LinkedList martyrList = martDateNode.getHead();
//							System.out.println(martDateNode.getHead().equals(martyrList));
					if (martyrList == null) {
						martyrList = new LinkedList(); // Create a new MartyrLinkedList
						martDateNode.setHead(martyrList); // Assign it to the BSTMartyrDateNode
					}

					// Insert martyr
					Martyr martyr = new Martyr(name, date, age, location, district, gender);

					MartyrNode martyrNode = new MartyrNode(martyr);
					martyrList.addMartyrSorted((Martyr) martyrNode.getElement());

				}
				// Catch blocks to handle exceptions
				catch (InputMismatchException e1) {
					e1.printStackTrace();
				} catch (IndexOutOfBoundsException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printDistrictsLocationsMartyrs();
	}

	// Method to print districts, locations, martyr dates, and martyrs
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

	private boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isValidDate(String date) {
		return date.matches("\\d{1}/\\d{1}/\\d{4}") || date.matches("\\d{1}/\\d{2}/\\d{4}")
				|| date.matches("\\d{2}/\\d{1}/\\d{4}") || date.matches("\\d{2}/\\d{2}/\\d{4}");
	}
}
