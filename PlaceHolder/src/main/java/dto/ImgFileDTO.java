package dto;

public class ImgFileDTO {
	//이미지
	private String hotelImg;
	private String roomImg;
	
	public ImgFileDTO() {}
	public ImgFileDTO(String hotelImg, String roomImg) {
		super();
		this.hotelImg = hotelImg;
		this.roomImg = roomImg;
	}
	public String getHotelImg() {
		return hotelImg;
	}
	public void setHotelImg(String hotelImg) {
		this.hotelImg = hotelImg;
	}
	public String getRoomImg() {
		return roomImg;
	}
	public void setRoomImg(String roomImg) {
		this.roomImg = roomImg;
	}
	
}
