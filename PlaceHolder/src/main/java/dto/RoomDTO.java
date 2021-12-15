package dto;

public class RoomDTO {
	//상세정보
	private String hotelId;
	private String roomType;
	private String quantity;
	private String roomPrice;
	private String addPrice;
	private String roomInfo;
	
	public RoomDTO() {}
	
	public RoomDTO(String hotelId, String roomType, String quantity, String roomPrice, String addPrice,
			String roomInfo) {
		super();
		this.hotelId = hotelId;
		this.roomType = roomType;
		this.quantity = quantity;
		this.roomPrice = roomPrice;
		this.addPrice = addPrice;
		this.roomInfo = roomInfo;
	}

	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(String roomPrice) {
		this.roomPrice = roomPrice;
	}
	public String getAddPrice() {
		return addPrice;
	}
	public void setAddPrice(String addPrice) {
		this.addPrice = addPrice;
	}
	public String getRoomInfo() {
		return roomInfo;
	}
	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}
	
	
}
