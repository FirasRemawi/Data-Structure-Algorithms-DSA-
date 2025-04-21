package Project3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private HashMap hash = new HashMap(11); // Initial size of 11

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		readFile();
		// Remaining initializations and setups...

		HashEntry entry = hash.find("1/6/2009");
//		if (entry != null && entry.getAvlTree() != null) {
//			Martyr found = entry.getAvlTree().findByName("Rab'ah Iyad Faiz a-Dayah");
//			if (found != null) {
//				System.out.println("Before: " + found.toString());
//			} else {
//				System.out.println("Martyr not found.");
//			}
//		} else {
//			System.out.println("Date entry or AVL tree not found for the date 1/6/2009.");
//		}
//
//		// Ensure the martyr to be updated exists
//		Martyr martyrToUpdate = entry.getAvlTree().findByName("Firas Mohammad Rajeh Remawi");
//		if (martyrToUpdate != null) {
//			System.out.println("Before: " + martyrToUpdate);
//			hash.updateMartyr(martyrToUpdate, new Martyr("Firas", "99/6/2009", (byte) 0, "f", "f", 'f'));
//			System.out.println("After: " + martyrToUpdate);
//			System.out.println(entry.getAvlTree().toString());
//			HashEntry newDate = hash.find("99/6/2009");
//			if (newDate != null) {
//				System.out.println(newDate);
//				System.out.println(newDate.getAvlTree().toString());
//			} else {
//				System.out.println("No entry found for the new date 99/6/2009.");
//			}
//		} else {
//			System.out.println("Martyr Firas not found for updates.");
//		}
		System.out.println(entry.getAvlTree().getHeight() + " " + entry.getAvlTree().getSize());
		sortAndPrintMartyrs(hash);
	}

	public void sortAndPrintMartyrs(HashMap hash) {
		MaxHeap heap = new MaxHeap(1000); // Assuming a max size, adjust as necessary

		// Iterate over each hash entry
		for (int i = 0; i < hash.getTableSize(); i++) {
			HashEntry entry = hash.getTable()[i];
			if (entry != null && entry.getStatus() == 'F') {
				collectMartyrs(heap, entry.getAvlTree().getRoot());
			}
		}

		heap.buildMaxHeap();

		// Print sorted martyrs
		while (!heap.isEmpty()) {
			Martyr m = heap.extractMax();
			System.out.println("Martyr: " + m.getName() + ", Age: " + m.getAge());
		}
	}

	private void collectMartyrs(MaxHeap heap, AVLNode node) {
		if (node != null) {
			heap.insert(node.getMartyr());
			collectMartyrs(heap, node.getLeft());
			collectMartyrs(heap, node.getRight());
		}
	}

	private void readFile() {
		File file = new File("sample.csv");
		try (Scanner read = new Scanner(file)) {
			read.nextLine(); // Skip the first line; header
			while (read.hasNextLine()) {
				String line = read.nextLine();
				String[] values = line.split(",");
				if (values.length < 6) { // Ensure that all required fields are present
					continue;
				}
				byte age = values[2].isEmpty() ? (byte) -1 : Byte.parseByte(values[2]);
				AVL tree = hash.insert(values[1]); // Insert the date and get the AVL tree (either new or existing)
				if (tree != null) {
					tree.insert(new Martyr(values[0], values[1], age, values[3], values[4], values[5].charAt(0)));
				} else {
					System.out.println("Error: Unable to insert date or retrieve AVL tree for date " + values[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//
//	private boolean insertDate(String date) {
//		if (hash.insertDate(date))
//			return true;
//		return false;
//	}

	public boolean updateDate(String oldDate, String newDate) {
		if (hash.updateDate(oldDate, newDate)) {
			return true;
		}
		return false;
	}

	private boolean removeDate(String date) {
		if (hash.removeDate(date))
			return true;
		return false;
	}

	private void navigateMartyrs(String date) {
		int entryIndex = hash.getDateIndex(date);
		if (entryIndex == -1)
			return;

	}

	private void martyrSummary() {
//		int totalMartyrs = totalMartyr();
	}
}
