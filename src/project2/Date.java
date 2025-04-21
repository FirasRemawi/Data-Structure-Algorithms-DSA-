package project2;

public class Date {
	private String date;
	private LinkedList martyrList;

	public Date(String Date) {
		this.date = Date;
		this.martyrList = new LinkedList();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public LinkedList getMartyrList() {
		return martyrList;
	}

	public void setMartyrList(LinkedList martyrList) {
		this.martyrList = martyrList;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Date dte = (Date) obj;
		return date != null ? date.equalsIgnoreCase(dte.getDate()) : dte.getDate() == null;
	}

	@Override
	public String toString() {

		return date;
	}

}
