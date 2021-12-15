package dto;

import java.sql.Date;

public class ArticleDTO {
	private int postId;
	private String userID;
	private String postTitle;
	private String postContent;
	private Date postCreated;
	private Date postModified;
	
	public ArticleDTO() {}
	public ArticleDTO(int postId, String userID, String postTitle, String postContent, Date postCreated,
			Date postModified) {
		super();
		this.postId = postId;
		this.userID = userID;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postCreated = postCreated;
		this.postModified = postModified;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public Date getPostCreated() {
		return postCreated;
	}
	public void setPostCreated(Date postCreated) {
		this.postCreated = postCreated;
	}
	public Date getPostModified() {
		return postModified;
	}
	public void setPostModified(Date postModified) {
		this.postModified = postModified;
	}
	
	
}
