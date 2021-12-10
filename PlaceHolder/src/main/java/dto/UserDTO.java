package dto;

public class UserDTO {
	private String userId;
	private String userName;
	private String userPw;
	private String userNickname;
	private String userEmail;
	private String userBirth;
	private String userPhone;
	private String userPost;
	private String userRoadAddress;
	private String userRoadAddress2;
	
	public UserDTO() {}
	public UserDTO(String userId, String userName, String userPw, String userNickname, String userEmail,
			String userBirth, String userPhone, String userPost, String userRoadAddress, String userRoadAddress2) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPw = userPw;
		this.userNickname = userNickname;
		this.userEmail = userEmail;
		this.userBirth = userBirth;
		this.userPhone = userPhone;
		this.userPost = userPost;
		this.userRoadAddress = userRoadAddress;
		this.userRoadAddress2 = userRoadAddress2;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPost() {
		return userPost;
	}
	public void setUserPost(String userPost) {
		this.userPost = userPost;
	}
	public String getUserRoadAddress() {
		return userRoadAddress;
	}
	public void setUserRoadAddress(String userRoadAddress) {
		this.userRoadAddress = userRoadAddress;
	}
	public String getUserRoadAddress2() {
		return userRoadAddress2;
	}
	public void setUserRoadAddress2(String userRoadAddress2) {
		this.userRoadAddress2 = userRoadAddress2;
	}
	
	
}
	
	
	