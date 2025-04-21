package project1;

public class DistrictNode {
	private District district;
	private DistrictNode next, prev;

	public DistrictNode(District Name) {
		this.district = Name;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public DistrictNode getNext() {
		return next;
	}

	public DistrictNode getPrev() {
		return prev;
	}

	public void setNext(DistrictNode next) {
		this.next = next;
	}

	public void setPrev(DistrictNode prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return "Distrcit: " + district;
	}

}
