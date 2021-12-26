package dto;

public class HotelImgDTO {
	//이미지
	private String hotelId;
	private String hotelImg;
	
	
	public HotelImgDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HotelImgDTO(String hotelImg) {
		super();
		this.hotelImg = hotelImg;
	}
	
	public HotelImgDTO(String hotelId, String hotelImg) {
		super();
		this.hotelId = hotelId;
		this.hotelImg = hotelImg;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelImg() {
		return hotelImg;
	}
	public void setHotelImg(String hotelImg) {
		this.hotelImg = hotelImg;
	}
	
	
	
	
}
