package dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class QnADTO {
	private int inquiry;
	private String hotelId;
	private String userId;
	private String inquiryStat;
	private String inquiryContent;
	private Date inquiryCreated;
	
	public int getInquiry() {
		return inquiry;
	}
	public void setInquiry(int inquiry) {
		this.inquiry = inquiry;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInquiryStat() {
		return inquiryStat;
	}
	public void setInquiryStat(String inquiryStat) {
		this.inquiryStat = inquiryStat;
	}
	public String getInquiryContent() {
		return inquiryContent;
	}
	public void setInquiryContent(String inquiryContent) {
		this.inquiryContent = inquiryContent;
	}
	public Date getInquiryCreated() {
		return inquiryCreated;
	}
	public void setInquiryCreated(Date inquiryCreated) {
		this.inquiryCreated = inquiryCreated;
	}
	
	public QnADTO(int inquiry, String hotelId, String userId, String inquiryStat, String inquiryContent,
			Date inquiryCreated) {
		super();
		this.inquiry = inquiry;
		this.hotelId = hotelId;
		this.userId = userId;
		this.inquiryStat = inquiryStat;
		this.inquiryContent = inquiryContent;
		this.inquiryCreated = inquiryCreated;
	}
	
	public QnADTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFormedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		return sdf.format(this.inquiryCreated.getTime());
	}
	public String getDetailDate() {
		long current_time = System.currentTimeMillis();	//�쁽�옱�쓽 Timestamp
		long write_time = this.inquiryCreated.getTime();	//湲��씠 �옉�꽦�맂 �떆�젏�쓽 Timestamp
		
		long time_gap = current_time - write_time;
		if(time_gap < 60000) {
			return "1遺� �씠�궡";
		}else if(time_gap < 300000) {
			return "5遺� �씠�궡";
		}else if(time_gap < 3600000) {
			return "1�떆媛� �씠�궡";
		}else if(time_gap < 86400000) {
			return "�삤�뒛";
		}else {
			return getFormedDate();
		}
		
	}
}
