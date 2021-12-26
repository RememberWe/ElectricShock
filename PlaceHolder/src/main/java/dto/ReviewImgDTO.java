package dto;

public class ReviewImgDTO {
	private int reviewId;
	private String hotelId;
	private String userId;
	private String reviewImage;
	
	public ReviewImgDTO() {}
	public ReviewImgDTO(int reviewId, String hotelId, String userId, String reviewImage) {
		super();
		this.reviewId = reviewId;
		this.hotelId = hotelId;
		this.userId = userId;
		this.reviewImage = reviewImage;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
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
	public String getReviewImage() {
		return reviewImage;
	}
	public void setReviewImage(String reviewImage) {
		this.reviewImage = reviewImage;
	}	
}
