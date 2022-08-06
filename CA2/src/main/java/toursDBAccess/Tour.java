package toursDBAccess;

public class Tour {
	private int tourID;
	private String tourName;
	private String tourDescription;
	private String tourDetailed;
	private float tourCost;
	private int tourSlots;
	private int tourType;
	private String tourImg;
	private String date;
	public int slotsFilled;
	public String tourDate;
	
	public String getTourDate() {
		return tourDate;
	}
	public void setTourDate(String tourDate) {
		this.tourDate = tourDate;
	}
	public int getSlotsFilled() {
		return slotsFilled;
	}
	public void setSlotsFilled(int slotsFilled) {
		this.slotsFilled = slotsFilled;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTourImg() {
		return tourImg;
	}
	public void setTourImg(String tourImg) {
		this.tourImg = tourImg;
	}
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
		return tourDetailed;
	}
	public void setTourDeatiled(String tourDetailed) {
		this.tourDetailed = tourDetailed;
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
