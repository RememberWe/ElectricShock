package dto;

public class RoomImgDTO {
	private String hotelId;
	private String roomImg;
	
	public RoomImgDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RoomImgDTO (String roomImg) {
		super();
		this.roomImg = roomImg;
	}

	public RoomImgDTO(String hotelId, String roomImg) {
		super();
		this.hotelId = hotelId;
		this.roomImg = roomImg;
	}
	
	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomImg() {
		return roomImg;
	}

	public void setRoomImg(String roomImg) {
		this.roomImg = roomImg;
	}

}
