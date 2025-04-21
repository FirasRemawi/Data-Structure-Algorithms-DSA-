package Project3;

public class HashMap {
	private int tableSize;
	private int currentSize;
	private HashEntry[] table;

	public HashMap(int size) {
		table = new HashEntry[size];
		tableSize = size;
		currentSize = 0;
	}

	public void makeItEmpty() {
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
		currentSize = 0;
	}

	private int hOfK(String key) {
		int hashValue = 0;
		for (int i = 0; i < key.length(); i++) {
			hashValue = (hashValue + (key.charAt(i) * (int) Math.pow(32, i))) % tableSize;
		}
		return Math.abs(hashValue);
	}

	private int fOfK(String key, int i) {
		return Math.abs((hOfK(key) + i * i) % tableSize);
	}

	public AVL insert(String date) {
		int hashIndex = hOfK(date);
		int probeDistance = 0;

		while (probeDistance < tableSize) {
			int currentIndex = (hashIndex + probeDistance * probeDistance) % tableSize;
			if (table[currentIndex] == null || table[currentIndex].getStatus() != 'F') {
				AVL tree = new AVL();
				table[currentIndex] = new HashEntry(date, tree, 'F'); // Initialize the new HashEntry with a fresh
																		// AVL tree.
				currentSize++;
				if ((double) currentSize / tableSize >= 0.5) {
					rehash();
				}
				return tree;
			} else if (table[currentIndex].getDate().equals(date)) {
				return table[currentIndex].getAvlTree(); // Date exists, return the existing AVL tree.
			}
			probeDistance++;
		}

		return null; // In case the table is full and no spot was found (this should be handled
						// ideally by resizing or error handling)
	}

	public HashEntry find(String date) {
		int hashIndex = hOfK(date);
		int i = 0;

		while (i < tableSize && table[hashIndex] != null) {
			if (table[hashIndex].getDate().equals(date) && table[hashIndex].getStatus() == 'F') {
				return table[hashIndex];
			}
			i++;
			hashIndex = fOfK(date, i);
		}

		return null; // Return null if the date is not found
	}

	public boolean removeDate(String date) {
		HashEntry entry = find(date);
		if (entry == null)
			return false;
		entry.setStatus('D');
		currentSize--;
		return true;
	}

	public boolean updateMartyr(Martyr martyr, Martyr newMartyr) {
		int entryIndex = getDateIndex(martyr.getDate());
		if (entryIndex == -1) {
			return false; // Martyr's original date entry not found
		}

		AVL tree = table[entryIndex].getAvlTree();
		if (tree == null) {
			return false; // No AVL tree found for the original date
		}

		// Remove the martyr once from the current tree before making any updates
		tree.remove(martyr);

		// Update all necessary fields
		if (!martyr.getName().equalsIgnoreCase(newMartyr.getName())) {
			martyr.setName(newMartyr.getName());
		}
		if (!martyr.getDistrict().equalsIgnoreCase(newMartyr.getDistrict())) {
			martyr.setDistrict(newMartyr.getDistrict());
		}
		if (newMartyr.getLocation() != null) {
			martyr.setLocation(newMartyr.getLocation());
		}

		// Handle date change
		if (!martyr.getDate().equalsIgnoreCase(newMartyr.getDate())) {
			martyr.setDate(newMartyr.getDate()); // Update the date if it has changed
			HashEntry newEntry = find(newMartyr.getDate());
			AVL newTree = (newEntry != null) ? newEntry.getAvlTree() : null;

			if (newTree == null) { // If no tree exists for the new date, create one
				newTree = insert(newMartyr.getDate());
			}

			// Now insert the updated martyr into the appropriate AVL tree
			newTree.insert(martyr);
		} else {
			// If the date hasn't changed, re-insert into the original tree
			tree.insert(martyr);
		}

		return true;
	}

	public boolean updateDate(String oldDate, String newDate) {
		int hashIndex = getDateIndex(oldDate);
		if (hashIndex == -1) { // Old date isn't found
			return false;
		}

		HashEntry entry = table[hashIndex];
		AVL tree = entry.getAvlTree();
		entry.setStatus('D');

		// Traverse the tree and update the date for each martyr
		traverseOverTreeAndChangeDate(tree.getRoot(), newDate); 

		// Update the date in the HashEntry
		insert(newDate);
		HashEntry newEntry = find(newDate);
		newEntry.setAvlTree(tree);

		return true;
	}

	// Recursive method to traverse and update martyrs' dates
	private void traverseOverTreeAndChangeDate(AVLNode node, String newDate) {
		if (node != null) {
			traverseOverTreeAndChangeDate(node.getLeft(), newDate); // Traverse left subtree
			node.getMartyr().setDate(newDate); // Update the date for the current martyr
			traverseOverTreeAndChangeDate(node.getRight(), newDate); // Traverse right subtree
		}
	}

	public boolean findDate(String date) {
		int hashIndex = hOfK(date);
		int i = 0;

		while (i < tableSize && table[hashIndex] != null) {
			if (table[hashIndex].getDate().equals(date) && table[hashIndex].getStatus() == 'F') {
				return true;
			}
			i++;
			hashIndex = fOfK(date, i);
		}

		return false;
	}

	public int martyrsTotal(String date) {
		int hashIndex = getDateIndex(date);
		if (hashIndex == -1) { // date isn't found
			return 0;
		}

		HashEntry entry = table[hashIndex];
		AVL tree = entry.getAvlTree();

		int total = traverseOverTree(tree.getRoot());
		return total;
	}

	private int traverseOverTree(AVLNode node) {
		if (node != null) {
			return 1 + traverseOverTree(node.getLeft()) + traverseOverTree(node.getRight()); // Traverse left + right
		}
		return 0;
	}

	public int getDateIndex(String date) {
		int hashIndex = hOfK(date);
		int i = 0;

		while (i < tableSize && table[hashIndex] != null) {
			if (table[hashIndex].getDate().equals(date) && table[hashIndex].getStatus() == 'F') {
				return hashIndex;
			}
			i++;
			hashIndex = fOfK(date, i);
		}
		return -1; // Return -1 if the date is not found
	}

	public double martyrsAverage(String date) {
		int hashIndex = getDateIndex(date);
		if (hashIndex == -1) { // date isn't found
			return 0;
		}

		HashEntry entry = table[hashIndex];
		AVL tree = entry.getAvlTree();

		double total = traverseOverTreeForSumAge(tree.getRoot());
		int count = traverseOverTreeForTotal(tree.getRoot());
		return count == 0 ? 0 : total / count;
	}

	private int traverseOverTreeForSumAge(AVLNode node) {
		if (node == null) {
			return 0;
		}
		return node.getMartyr().getAge() + traverseOverTreeForSumAge(node.getLeft())
				+ traverseOverTreeForSumAge(node.getRight());
	}

	private int traverseOverTreeForTotal(AVLNode node) {
		if (node == null) {
			return 0;
		}
		return 1 + traverseOverTreeForTotal(node.getLeft()) + traverseOverTreeForTotal(node.getRight());
	}

	public int martyrsMale(String date) {
		int hashIndex = getDateIndex(date);
		if (hashIndex == -1) { // date isn't found
			return 0;
		}

		HashEntry entry = table[hashIndex];
		AVL tree = entry.getAvlTree();

		int totalMales = traverseOverTreeMale(tree.getRoot());
		return totalMales;
	}

	private int traverseOverTreeMale(AVLNode node) {
		if (node == null) {
			return 0;
		}
		if (node.getMartyr().getGender() == 'M')
			return 1 + traverseOverTreeMale(node.getLeft()) + traverseOverTreeMale(node.getRight());
		return traverseOverTreeMale(node.getLeft()) + traverseOverTreeMale(node.getRight());
	}

	public District districtWithMaxMartyrs(HashEntry entry) {
		Stack districtStack = treeToStack(entry);
		return (District) districtStack.max(districtStack);
	}

	private void rehash() {
		// Calculate the new size of the hash table, ideally next prime number to reduce
		// collision
		int newSize = nextPrime(tableSize * 2);
		HashEntry[] oldTable = table; // Save the old table
		table = new HashEntry[newSize];
		tableSize = newSize;
		currentSize = 0; // Reset current size as it will be recalculated

		// Reinsert entries from the old table to the new table
		for (HashEntry oldEntry : oldTable) {
			if (oldEntry != null && oldEntry.getStatus() == 'F') {
				// Reinsert using direct insertion to avoid rehash being triggered
				reinsert(oldEntry.getDate(), oldEntry.getAvlTree());
			}
		}
	}

	private void reinsert(String date, AVL avlTree) {
		int hashIndex = hOfK(date);
		int probeDistance = 0;
		while (probeDistance < tableSize) {
			int currentIndex = (hashIndex + probeDistance * probeDistance) % tableSize;
			if (table[currentIndex] == null || table[currentIndex].getStatus() != 'F') {
				table[currentIndex] = new HashEntry(date, avlTree, 'F');
				currentSize++;
				break; // Exit after successful insertion
			}
			probeDistance++;
		}
	}

	private int nextPrime(int n) {
		while (!isPrime(n)) {
			n++;
		}
		return n;
	}

	private boolean isPrime(int n) {
		if (n <= 1)
			return false;
		if (n <= 3)
			return true;
		if (n % 2 == 0 || n % 3 == 0)
			return false;
		for (int i = 5; i * i <= n; i += 6) {
			if (n % i == 0 || n % (i + 2) == 0)
				return false;
		}
		return true;
	}

	public void printList() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) {
				continue;
			} else {
				if (table[i].getStatus() == 'F')
					System.out.println(/* "Index " + i + ": " + */ table[i]);
			}
		}
	}

	public Stack treeToStack(HashEntry entry) {
		Stack districtStack = new Stack();
		AVLNode root = entry.getAvlTree().getRoot();
		inOrderTraversal(root, districtStack);
		return districtStack;
	}

	private void inOrderTraversal(AVLNode node, Stack districtStack) {
		if (node == null) {
			return;
		}

		inOrderTraversal(node.getLeft(), districtStack);

		Martyr martyr = node.getMartyr();
		String districtName = martyr.getDistrict();
		District district = (District) districtStack.find(districtName);

		if (district == null) {
			district = new District(districtName);
			districtStack.push(district);
		} else {
			district.incrementMartyrCount();
		}

		district.addOrUpdateLocation(martyr.getLocation());

		inOrderTraversal(node.getRight(), districtStack);
	}

	public int getTableSize() {
		return tableSize;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public HashEntry[] getTable() {
		return table;
	}

}
