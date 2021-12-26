package dto;

import java.sql.Date;

public class PaymentDTO {
	private String payId;
	private String revId;
	private String userId;
	private String hotelId;
	private String payWay;
	private String payStat;
	private String revPrice;
	private Date checkIn;
	private Date checkOut;
	
	public PaymentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentDTO(String payId, String revId, String userId, String hotelId, String payWay, String payStat,
			String revPrice, Date checkIn, Date checkOut) {
		super();
		this.payId = payId;
		this.revId = revId;
		this.userId = userId;
		this.hotelId = hotelId;
		this.payWay = payWay;
		this.payStat = payStat;
		this.revPrice = revPrice;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
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

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getPayStat() {
		return payStat;
	}

	public void setPayStat(String payStat) {
		this.payStat = payStat;
	}

	public String getRevPrice() {
		return revPrice;
	}

	public void setRevPrice(String revPrice) {
		this.revPrice = revPrice;
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
		
	
	
	
}
