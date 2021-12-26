package dto;

import java.sql.Date;

public class ReservationDTO {
	private String revId; // 예약코드
	private String userId; // 유저아이디
	private String hotelId; // 호텔 아이디
	private String hotelName; // 호텔이름
	private String hotelRoadAddress; // 호텔 주소
	private String hotelPhone; // 호텔 전화번호
	private Date checkIn; // 체크인 날짜
	private Date checkOut; //체크아웃 날짜
	private Date revDay; // 예약한 날짜
	private String revRoomType; // 예약한 방 타입
	private int revQuantity; // 예약한 방 개수
	private String revRoomInfo; // 예약한 방 상세
	private String revStat; // 예약 상태
	private String revPrice; // 예약 금액

	
	
	public ReservationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ReservationDTO(String revId, String userId, String hotelId, String hotelName, String hotelRoadAddress,
			String hotelPhone, Date checkIn, Date checkOut, Date revDay, String revRoomType, int revQuantity,
			String revRoomInfo, String revStat, String revPrice) {
		super();
		this.revId = revId;
		this.userId = userId;
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.hotelRoadAddress = hotelRoadAddress;
		this.hotelPhone = hotelPhone;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.revDay = revDay;
		this.revRoomType = revRoomType;
		this.revQuantity = revQuantity;
		this.revRoomInfo = revRoomInfo;
		this.revStat = revStat;
		this.revPrice = revPrice;
	}



	public String getHotelRoadAddress() {
		return hotelRoadAddress;
	}

	public void setHotelRoadAddress(String hotelRoadAddress) {
		this.hotelRoadAddress = hotelRoadAddress;
	}

	public String getRevRoomType() {
		return revRoomType;
	}


	public void setRevRoomType(String revRoomType) {
		this.revRoomType = revRoomType;
	}


	public int getRevQuantity() {
		return revQuantity;
	}


	public void setRevQuantity(int revQuantity) {
		this.revQuantity = revQuantity;
	}


	public String getRevRoomInfo() {
		return revRoomInfo;
	}


	public void setRevRoomInfo(String revRoomInfo) {
		this.revRoomInfo = revRoomInfo;
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


	public String getRevId() {
		return revId;
	}


	public void setRevId(String revId) {
		this.revId = revId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getHotelId() {
		return hotelId;
	}


	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}


	public Date getCheckIn() {
		return checkIn;
	}


	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}


	public Date getCheckOut() {
		return checkOut;
	}


	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}


	public Date getRevDay() {
		return revDay;
	}


	public void setRevDay(Date revDay) {
		this.revDay = revDay;
	}


	public String getRevStat() {
		return revStat;
	}


	public void setRevStat(String revStat) {
		this.revStat = revStat;
	}


	public String getRevPrice() {
		return revPrice;
	}


	public void setRevPrice(String revPrice) {
		this.revPrice = revPrice;
	}
}