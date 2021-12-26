package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.LikeDTO;

public class LikeDAO {
	private static LikeDAO instance = null;
	public static LikeDAO getInstance() {
		if (instance == null) {
			instance = new LikeDAO();
		}
		return instance;
	}
	private LikeDAO() {}
	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	// 디테일페이지에서 좋아요 되어있는지 확인.
	public boolean likeCheck(String loginId, String hotelId) throws Exception{
		String sql = "select likeid from likey where userid = ? and hotelid = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setString(1, loginId);
			pstat.setString(2, hotelId);
			try(ResultSet rs = pstat.executeQuery()){
				return rs.next();
			}
		}
	}
	
	// 리스트에서 좋아요 체크
//	public ? listLikeCheck(String loginId, String hotelId) throws Exception{
//		String sql = "select likeid from likey where userid = ? and hotelid = ?";
//		try(Connection con = this.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql)){
//			pstat.setString(1, loginId);
//			pstat.setString(2, hotelId);
//			try(ResultSet rs = pstat.executeQuery()){
//				List<LikeDTO> dto = new ArrayList<>();
//				for (int i = 0; i < 10; i++) {
//					
//				}
//			}
//		}
//	}
	
	// 로그인한 아이디로 호텔에 좋아요값이 없을때 추가하기.
	public int likeAdd(String loginId, String hotelId) throws Exception{
		String sql = "insert into likey values(default, ?, ?)";
		System.out.println("addDAO에 들어오는 유저id 호텔id = " + loginId + " : " + hotelId);
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setString(1, hotelId);
			pstat.setString(2, loginId);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	// 로그인한 아이디로 좋아요값이 있을때 그걸 빼버리긔
	public int likeDel(String loginId, String hotelId) throws Exception{
		String sql = "delete from likey where userid = ? and hotelid = ?";
		System.out.println("delDAO에 들어오는 유저id 호텔id = " + loginId + " : " + hotelId);
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setString(1, loginId);
			pstat.setString(2, hotelId);
			System.out.println("like row삭제");
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	// 찜목록 기능
	public List<LikeDTO> likeList(String loginId) throws Exception {
		String sql = "select hotelid from likey where userid = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setString(1, loginId);
			try(ResultSet rs = pstat.executeQuery()){
				List<LikeDTO> list = new ArrayList<>();
				while(rs.next()) {
					LikeDTO dto = new LikeDTO();
					dto.setHotelId(rs.getString("hotelid"));
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	
}
