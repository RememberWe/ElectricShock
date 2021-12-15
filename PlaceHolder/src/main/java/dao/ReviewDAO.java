package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.ReviewDTO;

public class ReviewDAO {

	//인스턴스와 커넥션
	public static ReviewDAO instance = null;
	public static ReviewDAO getInstance() {
		if(instance == null) {
			instance = new ReviewDAO();
		} return instance;
	}
	private ReviewDAO() {}
	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle"); 
		// AWS 사용할 때 수정 필요함
		return ds.getConnection();
	}

	// 1. 리뷰 작성하기 기능
	public int writeReview(ReviewDTO dto) throws Exception {

		String sql = "insert into review values(reviewId.nextVal,?,?,?,?,?,default)";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			
			pstat.setString(1, dto.getRevId());
			pstat.setString(2, dto.getUserId());
			pstat.setString(3, dto.getHotelId());
			pstat.setString(4, dto.getReviewContent());
			pstat.setInt(5, dto.getReviewScore());
			

			int result = pstat.executeUpdate();
			return result;
		}
	}

	// 2. 리뷰 조회하기 기능 (내가 쓴 글)
	public List<ReviewDTO> selectMyReview(String loginId) throws Exception {
		String sql = "select * from review where userId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, loginId);

			try(ResultSet rs = pstat.executeQuery();){
				List<ReviewDTO> list = new ArrayList<>();
				while(rs.next()) {
					ReviewDTO dto = new ReviewDTO();
					dto.setReviewId(rs.getInt("reviewId"));
					dto.setRevId(rs.getString("revId"));
					dto.setUserId(rs.getString("userId"));
					dto.setHotelId(rs.getString("hotelId"));
					dto.setReviewContent(rs.getString("reviewContent"));
					dto.setReviewScore(rs.getInt("reviewScore"));
					dto.setReviewCreated(rs.getDate("reviewCreated"));
					list.add(dto);
				}return list;
			}
		}
	}

	// 3. 리뷰 수정하기 기능
	public int modifyReview(ReviewDTO dto)throws Exception {
		String sql = "update review set reviewContent = ?, reviewScore = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getReviewContent());
			pstat.setInt(2, dto.getReviewScore());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}

	// 4. 리뷰 삭제하기 기능
	public int deleteReview(int reviewId)throws Exception {
		String sql = "delete from review where reviewId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, reviewId);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}

	// 5. 호텔 아이디 값에 맞는 review 조회하기
	public List<ReviewDTO> selectReviewByHotelId(String hotelID)throws Exception {
		String sql = "select * from review where  hotelId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, hotelID);
			try(ResultSet rs = pstat.executeQuery();){
				List<ReviewDTO> list = new ArrayList<>();
				while(rs.next()) {
					ReviewDTO dto = new ReviewDTO();
					dto.setReviewId(rs.getInt("reviewId"));
					dto.setRevId(rs.getString("revId"));
					dto.setUserId(rs.getString("userId"));
					dto.setHotelId(rs.getString("hotelId"));
					dto.setReviewContent(rs.getString("reviewContent"));
					dto.setReviewScore(rs.getInt("reviewScore"));
					dto.setReviewCreated(rs.getDate("reviewCreated"));
					list.add(dto);
				}return list;
			}
		}
	}
	//호텔 아이디 값에 맞는 리뷰 조회하는데 범위 값이라는 조건 주기
	public List<ReviewDTO> selectReviewByHotelB(int start, int end, String hotelId)throws Exception{
		String sql = "select * from(select review.*, row_number() over(order by reviewId asc)rn from review) where rn between ? and ? and hotelId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			pstat.setString(3, hotelId);
			try(ResultSet rs = pstat.executeQuery();){
				List<ReviewDTO> list = new ArrayList();
				while(rs.next()) {
					ReviewDTO dto = new ReviewDTO();
					dto.setHotelId(rs.getString("hotelId"));
					dto.setRevId(rs.getString("revId"));
					dto.setUserId(rs.getString("userId"));
					dto.setReviewId(rs.getInt("reviewId"));
					dto.setReviewContent(rs.getString("reviewContent"));
					dto.setReviewCreated(rs.getDate("reveiwCreated"));
					dto.setReviewScore(rs.getInt("reviewScroe"));
					list.add(dto);
				}return list;
			}
		}
	}








}
