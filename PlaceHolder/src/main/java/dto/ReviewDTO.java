package dto;

import java.sql.Date;

public class ReviewDTO {
	
	private int reviewId;
	private String revId;
	private String userId;
	private String hotelId;
	private String reviewContent;
	private int reviewScore;
	private Date reviewCreated;
	
	public ReviewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewDTO(int reviewId, String revId, String userId, String hotelId, String reviewContent,
			int reviewScore, Date reviewCreated) {
		super();
		this.reviewId = reviewId;
		this.revId = revId;
		this.userId = userId;
		this.hotelId = hotelId;
		this.reviewContent = reviewContent;
		this.reviewScore = reviewScore;
		this.reviewCreated = reviewCreated;
	}
	
	public int getReviewId() {
		return reviewId;
	}


	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
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


	public String getReviewContent() {
		return reviewContent;
	}


	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}


	public int getReviewScore() {
		return reviewScore;
	}


	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}


	public Date getReviewCreated() {
		return reviewCreated;
	}


	public void setReviewCreated(Date reviewCreated) {
		this.reviewCreated = reviewCreated;
	}
}