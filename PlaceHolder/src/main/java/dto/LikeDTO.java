package dto;

public class LikeDTO {
	private int likeId;
	private String hotelId;
	private String loginId;
	private boolean listLike;
	public LikeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikeDTO(int likeId, String hotelId, String loginId, boolean listLike) {
		super();
		this.likeId = likeId;
		this.hotelId = hotelId;
		this.loginId = loginId;
		this.listLike = listLike;
	}
	public LikeDTO(String hotelId) {
		super();
		this.hotelId = hotelId;
	}
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public boolean isListLike() {
		return listLike;
	}
	public void setListLike(boolean listLike) {
		this.listLike = listLike;
	}
	
	
}
