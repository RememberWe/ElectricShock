package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.RoomDTO;

public class RoomDAO {
	//인스턴스와 커넥션
	public static RoomDAO instance = null;
	public static RoomDAO getInstance() {
		if(instance == null) {
			instance = new RoomDAO();
		}return instance;
	}
	private RoomDAO() {}
	private Connection getConnection()throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	//호텔 아이디 값에 맞는 룸 정보 조회하기
	public List<RoomDTO> selectRoomById(String hotelid) throws Exception{
		String sql = "select * from room where hotelId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, hotelid);
			try(ResultSet rs = pstat.executeQuery();){
				List<RoomDTO> list = new ArrayList<>();
				while(rs.next()) {
					RoomDTO dto = new RoomDTO();
					dto.setHotelId(rs.getString("hotelId"));
					dto.setRoomType(rs.getString("roomType"));
					dto.setQuantity(rs.getString("quantity"));
					dto.setRoomPrice(rs.getString("roomPrice"));
					dto.setAddPrice(rs.getString("addPrice"));
					dto.setRoomInfo(rs.getString("roomInfo"));
					list.add(dto);					
				}return list;
			}
		}
	}
	//호텔 아이디값으로 삭제하기
	public int deleteRoom(String hotelId)throws Exception{
		String sql = "delete from room where hotelId= ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, hotelId);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	// ***** 현우 추가 : 예약용, 호텔 id, roomType에 따라서 결과 조회하기
	   public RoomDTO showRoomInfo(String hotelid, String roomType) throws Exception{
	      String sql = "select * from room where hotelId = ? and roomType = ?";
	      
	      RoomDTO dto = new RoomDTO();
	      
	      try(Connection con = this.getConnection();
	            PreparedStatement pstat = con.prepareStatement(sql);){
	         pstat.setString(1, hotelid);
	         pstat.setString(2, roomType);

	         try(ResultSet rs = pstat.executeQuery();){
	            rs.next();
	            
	            dto.setHotelId(rs.getString("hotelId"));
	            dto.setRoomType(rs.getString("roomType"));
	            dto.setQuantity(rs.getString("quantity"));
	            dto.setRoomPrice(rs.getString("roomPrice"));
	            dto.setAddPrice(rs.getString("addPrice"));
	            dto.setRoomInfo(rs.getString("roomInfo"));

	         }
	      }
	      return dto;
	   }
	
}
