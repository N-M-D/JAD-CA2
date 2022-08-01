package toursDBAccess;

public class Tour {
	private int tourID;
	private String tourName;
	private String tourDescription;
	private String tourDeatiled;
	private float tourCost;
	private int tourSlots;
	private int tourType;
	
	public int getTourID() {
		return tourID;
	}
	public void setTourID(int tourID) {
		this.tourID = tourID;
	}
	public String getTourName() {
		return tourName;
	}
	public void setTourName(String tourName) {
		this.tourName = tourName;
	}
	public String getTourDescription() {
		return tourDescription;
	}
	public void setTourDescription(String tourDescription) {
		this.tourDescription = tourDescription;
	}
	public String getTourDeatiled() {
		return tourDeatiled;
	}
	public void setTourDeatiled(String tourDeatiled) {
		this.tourDeatiled = tourDeatiled;
	}
	public float getTourCost() {
		return tourCost;
	}
	public void setTourCost(float tourCost) {
		this.tourCost = tourCost;
	}
	public int getTourSlots() {
		return tourSlots;
	}
	public void setTourSlots(int tourSlots) {
		this.tourSlots = tourSlots;
	}
	public int getTourType() {
		return tourType;
	}
	public void setTourType(int tourType) {
		this.tourType = tourType;
	}
	
}
