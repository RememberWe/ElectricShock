package dto;

public class HotelDTO {
	//호텔
	private String hotelId;
	private String hotelName;
	private String hotelPhone;
	private String hotelRoadAddress;
	private String hotelLongitude;
	private String hotelLatitude;
	
	public HotelDTO() {}
	public HotelDTO(String hotelId, String hotelName, String hotelPhone, String hotelRoadAddress, String hotelLongitude,
			String hotelLatitude) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.hotelPhone = hotelPhone;
		this.hotelRoadAddress = hotelRoadAddress;
		this.hotelLongitude = hotelLongitude;
		this.hotelLatitude = hotelLatitude;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelPhone() {
		return hotelPhone;
	}
	public void setHotelPhone(String hotelPhone) {
		this.hotelPhone = hotelPhone;
	}
	public String getHotelRoadAddress() {
		return hotelRoadAddress;
	}
	public void setHotelRoadAddress(String hotelRoadAddress) {
		this.hotelRoadAddress = hotelRoadAddress;
	}
	public String getHotelLongitude() {
		return hotelLongitude;
	}
	public void setHotelLongitude(String hotelLongitude) {
		this.hotelLongitude = hotelLongitude;
	}
	public String getHotelLatitude() {
		return hotelLatitude;
	}
	public void setHotelLatitude(String hotelLatitude) {
		this.hotelLatitude = hotelLatitude;
	}
	
	
	
}