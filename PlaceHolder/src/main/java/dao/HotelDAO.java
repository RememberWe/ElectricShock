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

public class HotelDAO {
	//인스턴스와 커넥션
	public static HotelDAO instance = null;
	public static HotelDAO getInstance() {
		if(instance == null) {
			instance = new HotelDAO();
		}return instance;
	}
	private HotelDAO() {}
	private Connection getConnection()throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	//호텔 컨트롤러에서 쓰일 것
	//호텔 테이블에서 정보 모두 불러오기
	public List<HotelDTO> selectHotel() throws Exception{
		String sql = "select * from hotel";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			List<HotelDTO> list = new ArrayList();
			while(rs.next()) {
				HotelDTO dto = new HotelDTO();
				dto.setHotelId(rs.getString("hotelID"));
				dto.setHotelInfo(rs.getString("hotelInfo"));
				dto.setHotelName(rs.getString("hotelName"));
				dto.setHotelPhone(rs.getString("hotelPhone"));
				dto.setHotelRoadAddress(rs.getString("hotelRoadAddress"));
				dto.setHotelLongitude(rs.getString("hotelLongitude"));
				dto.setHotelLatitude(rs.getString("hotelLatitude"));
				list.add(dto);
			}return list;
		}
	}

	//호텔 테이블에서 범위 내의 정보 불러오기
	public List<HotelDTO> selectHotelB(int start, int end) throws Exception{
		String sql = "select * from(select hotel.*, row_number() over(order by hotelId asc)rn from hotel) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			try(ResultSet rs = pstat.executeQuery();){
				List<HotelDTO> list = new ArrayList();
				while(rs.next()) {
					HotelDTO dto = new HotelDTO();
					dto.setHotelId(rs.getString("hotelID"));
					dto.setHotelInfo(rs.getString("hotelInfo"));
					dto.setHotelName(rs.getString("hotelName"));
					dto.setHotelPhone(rs.getString("hotelPhone"));
					dto.setHotelRoadAddress(rs.getString("hotelRoadAddress"));
					dto.setHotelLongitude(rs.getString("hotelLongitude"));
					dto.setHotelLatitude(rs.getString("hotelLatitude"));
					list.add(dto);
				}return list;
			}
		}
	}

	//호텔 테이블에서 이름 키워드를 포함한 정보 값을 불러오기
	public List<HotelDTO> searchHotelName(String keyword)throws Exception{
		String sql = "select * from hotel where hotelName like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+keyword+"%");
			try(ResultSet rs = pstat.executeQuery();){
				List<HotelDTO> list = new ArrayList();
				while(rs.next()) {
					HotelDTO dto = new HotelDTO();
					dto.setHotelId(rs.getString("hotelID"));
					dto.setHotelInfo(rs.getString("hotelInfo"));
					dto.setHotelName(rs.getString("hotelName"));
					dto.setHotelPhone(rs.getString("hotelPhone"));
					dto.setHotelRoadAddress(rs.getString("hotelRoadAddress"));
					dto.setHotelLongitude(rs.getString("hotelLongitude"));
					dto.setHotelLatitude(rs.getString("hotelLatitude"));
					list.add(dto);
				}return list;
			}
		}
	}

	//호텔 테이블에서 키워드(이름)을 포함한 범위 내의 값을 불러오기
	public List<HotelDTO> searchHotelNameB(int start, int end, String keyword)throws Exception{
		String sql = "select * from (select hotel.*, row_number() over(order by hotelId asc)rn from hotel where name like ?) where rn between ? and ?'";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			pstat.setString(3, "%"+keyword+"%");
			try(ResultSet rs = pstat.executeQuery();){
				List<HotelDTO> list = new ArrayList();
				while(rs.next()) {
					HotelDTO dto = new HotelDTO();
					dto.setHotelId(rs.getString("hotelID"));
					dto.setHotelInfo(rs.getString("hotelInfo"));
					dto.setHotelName(rs.getString("hotelName"));
					dto.setHotelPhone(rs.getString("hotelPhone"));
					dto.setHotelRoadAddress(rs.getString("hotelRoadAddress"));
					dto.setHotelLongitude(rs.getString("hotelLongitude"));
					dto.setHotelLatitude(rs.getString("hotelLatitude"));
					list.add(dto);
				}return list;
			}
		}
	}

	//호텔 테이블에서 키워드(위치)를 포함한 값을 불러오기
	public List<HotelDTO> searchHotelSite(String keyword)throws Exception{
		String sql = "select * from hotel where hotelRoadAddress like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+keyword+"%");
			try(ResultSet rs = pstat.executeQuery();){
				List<HotelDTO> list = new ArrayList();
				while(rs.next()) {
					HotelDTO dto = new HotelDTO();
					dto.setHotelId(rs.getString("hotelID"));
					dto.setHotelInfo(rs.getString("hotelInfo"));
					dto.setHotelName(rs.getString("hotelName"));
					dto.setHotelPhone(rs.getString("hotelPhone"));
					dto.setHotelRoadAddress(rs.getString("hotelRoadAddress"));
					dto.setHotelLongitude(rs.getString("hotelLongitude"));
					dto.setHotelLatitude(rs.getString("hotelLatitude"));
					list.add(dto);
				}return list;
			}
		}
	}
	//호텔테이블에서 모든 것을 삭제
	public int deleteHotel(String hotelId)throws Exception{
		String sql = "delete from hotel where hotelId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, hotelId);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	//RoomController 에서 쓰일 것
	//아이디값에 맞는 호텔 정보 조회
	public HotelDTO selectHotelById(String id)throws Exception{
		String sql = "select * from hotel where hotelId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					HotelDTO dto = new HotelDTO();
					dto.setHotelId(rs.getString("hotelID"));
					dto.setHotelName(rs.getString("hotelName"));
					dto.setHotelPhone(rs.getString("hotelPhone"));
					dto.setHotelRoadAddress(rs.getString("hotelRoadAddress"));
					dto.setHotelLongitude(rs.getString("hotelLongitude"));
					dto.setHotelLatitude(rs.getString("hotelLatitude"));
					return dto;
				}return null;
			}
		}
	}

	//페이징
	//전체 개수 가져오기
	public int getHotelCount() throws Exception{
		String sql = "select count(*) from hotel";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			ResultSet rs = pstat.executeQuery();
			rs.next();
			return rs.getInt(1);
		}
	} 

}

