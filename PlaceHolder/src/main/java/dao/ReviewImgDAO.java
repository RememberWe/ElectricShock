package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.HotelDTO;

public class ReviewImgDAO {
	//인스턴스와 커넥션
	public static ReviewImgDAO instance = null;
	public static ReviewImgDAO getInstance() {
		if(instance == null) {
			instance = new ReviewImgDAO();
		}return instance;
	}
	private ReviewImgDAO() {}
	private Connection getConnection()throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	//룸 컨트롤러에서 쓰일 것
	//호텔아이디 값에 맞는 리뷰 이미지 뽑아오기
	public List<String> selectReviewImgByHotelId(String hotelId)throws Exception{
		String sql = "select i.reviewImage from review r inner join reivewImg i on r.reveiwId = i.reviewId where r.hotelId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, hotelId);
			try(ResultSet rs = pstat.executeQuery();){
				List<String> list = new ArrayList<>();
				while(rs.next()) {
					list.add(rs.getString("reviewImage"));
				}return list;
			}
		}
	}
	//호텔아이디 값에 맞는 리뷰 이미지 뽑아오는데 범위 내의 값만 가져오기
	public List<String> selectReviewImgByHotelIdB(int start, int end, String hotelId)throws Exception{
		String sql = "select * from(select review.*, row_number() over(order by reviewId asc)rn from reviewImg) where rn between ? and ? and hotelId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			pstat.setString(3, hotelId);
			try(ResultSet rs = pstat.executeQuery();){
				List<String> list = new ArrayList();
				while(rs.next()) {
					list.add(rs.getString("reviewImage"));
				}return list;
				}
			}
	}
	
	//리뷰 컨트롤러 에서 쓸 것
	//유저아이디 값에 맞는 리뷰 이미지 뽑아오기
	public List<String> selectMyReviewImg(String userId)throws Exception{
		String sql = "select * from reviewImg where userID = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, userId);
			try(ResultSet rs = pstat.executeQuery();){
				List<String> list = new ArrayList();
				while(rs.next()) {
					list.add(rs.getString("reviewImage"));
				}return list;
			}
		}
	}
}
