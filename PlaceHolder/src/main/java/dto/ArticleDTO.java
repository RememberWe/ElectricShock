package dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ArticleDTO {
	private int postId;
	private String userId;
	private String postTitle;
	private String postContent;
	private Date postCreated;
	private Date postModified;
	
	
	public ArticleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArticleDTO(int postId, String userId, String postTitle, String postContent, Date postCreated,
			Date postModified) {
		super();
		this.postId = postId;
		this.userId = userId;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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