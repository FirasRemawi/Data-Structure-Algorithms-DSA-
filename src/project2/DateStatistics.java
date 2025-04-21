package project2;

import javafx.scene.control.TextArea;

public class DateStatistics {
	public Date earliestDate;
	public Date latestDate;
	public Date maxMartyrsDate;
	public int maxMartyrsCount;

	// Calculate statistics for a specific location
	public static DateStatistics calculateForLocation(LocationNode locationNode) {
		if (locationNode == null || locationNode.getHead() == null) {
			return new DateStatistics(); // return empty statistics if no data
		}

		DateTree dateTree = locationNode.getHead();
		return calculateDateMartyrsRecursively(dateTree.getRoot(), new DateStatistics());
	}

	private static DateStatistics calculateDateMartyrsRecursively(DateNode node, DateStatistics currentStats) {
		if (node == null) {
			return currentStats; // base case for recursion
		}

		// Update current stats with node data
		if (currentStats.earliestDate == null
				|| node.getDate().getDate().compareTo(currentStats.earliestDate.getDate()) < 0) {
			currentStats.earliestDate = node.getDate();
		}
		if (currentStats.latestDate == null
				|| node.getDate().getDate().compareTo(currentStats.latestDate.getDate()) > 0) {
			currentStats.latestDate = node.getDate();
		}
		if (node.getMartyrs().size() > currentStats.maxMartyrsCount) {
			currentStats.maxMartyrsCount = node.getMartyrs().size();
			currentStats.maxMartyrsDate = node.getDate();
		}

		// Recursively calculate stats for left and right subtrees
		currentStats = calculateDateMartyrsRecursively(node.getLeft(), currentStats);
		currentStats = calculateDateMartyrsRecursively(node.getRight(), currentStats);

		return currentStats;
	}

	public void display(TextArea ta) {
		ta.appendText("\nEarliest Date: " + (earliestDate != null ? earliestDate.toString() : "None"));
		ta.appendText("\nLatest Date: " + (latestDate != null ? latestDate.toString() : "None"));
		ta.appendText("\nMax Martyrs on a Date: " + maxMartyrsCount + " on "
				+ (maxMartyrsDate != null ? maxMartyrsDate.toString() : "None"));
	}
}
