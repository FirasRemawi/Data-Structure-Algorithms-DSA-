package project;

public class Manger {
    private DoubleLinkedList districtList;

    public Manger(DoubleLinkedList districtList) {
        this.districtList = districtList;
    }

    /**
     * Updates the name of an existing district and keeps the list sorted.
     * 
     * @param oldName The current name of the district.
     * @param newName The new name for the district.
     * @return true if the district was found and updated, false otherwise.
     */
    public boolean updateDistrictName(String oldName, String newName) {
        DoubleNode current = districtList.getFront();
        while (current != null) {
            District district = current.getElement();
            if (district.getName().equalsIgnoreCase(oldName)) {
                // Step 1: Remove the district node from the list to avoid sorting issues.
                districtList.remove(district);
                
                // Step 2: Update the district's name.
                district.setName(newName);
                
                // Step 3: Re-add the district to the list to maintain sorted order.
                districtList.addSorted(district);
                
                return true; // Update successful
            }
            current = current.getNext();
        }
        return false; // District not found
    }
}
