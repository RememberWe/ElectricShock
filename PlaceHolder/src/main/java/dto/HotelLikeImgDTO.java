package dto;

public class HotelLikeImgDTO {
	private String hotelId;
	private String hotelName;
	private String hotelInfo;
	private String hotelPhone;
	private String hotelRoadAddress;
	private String hotelLongitude;
	private String hotelLatitude;
	private String hotelScore;
	private String hotelDetail;
	private String hotelImg;
	public HotelLikeImgDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HotelLikeImgDTO(String hotelId, String hotelName, String hotelInfo, String hotelPhone,
			String hotelRoadAddress, String hotelLongitude, String hotelLatitude, String hotelScore, String hotelDetail,
			String hotelImg) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.hotelInfo = hotelInfo;
		this.hotelPhone = hotelPhone;
		this.hotelRoadAddress = hotelRoadAddress;
		this.hotelLongitude = hotelLongitude;
		this.hotelLatitude = hotelLatitude;
		this.hotelScore = hotelScore;
		this.hotelDetail = hotelDetail;
		this.hotelImg = hotelImg;
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
	public String getHotelInfo() {
		return hotelInfo;
	}
	public void setHotelInfo(String hotelInfo) {
		this.hotelInfo = hotelInfo;
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
	public String getHotelScore() {
		return hotelScore;
	}
	public void setHotelScore(String hotelScore) {
		this.hotelScore = hotelScore;
	}
	public String getHotelDetail() {
		return hotelDetail;
	}
	public void setHotelDetail(String hotelDetail) {
		this.hotelDetail = hotelDetail;
	}
	public String getHotelImg() {
		return hotelImg;
	}
	public void setHotelImg(String hotelImg) {
		this.hotelImg = hotelImg;
	}
}
