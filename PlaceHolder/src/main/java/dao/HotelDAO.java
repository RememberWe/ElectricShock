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
	//정보 전체 불러오기(리스트에 들어갈 항목들)
	public List<HotelDTO> selectHotel() throws Exception{
		String sql = "selct * from hotel";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			ArrayList<HotelDTO> list = new ArrayList();
			while(rs.next()) {
				HotelDTO dto = new HotelDTO();
				dto.setHotelId(rs.getString("hotelID"));
				dto.setHotelName(rs.getString("hotelName"));
				dto.setHotelPhone(rs.getString("hotelPhone"));
				dto.setHotelRoadAddress(rs.getString("hotelRoadAddress"));
				dto.setHotelLongitude(rs.getString("hotelLongitude"));
				dto.setHotelLatitude(rs.getString("hotelLatitude"));
				list.add(dto);
			}return list;
		}
	}
	//상품 정보 불러오기(호텔 이미지만 뽑아오기)
	public List<String> selectHotelImg()throws Exception{
		String sql = "select hotelImg from imgFile";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			try(ResultSet rs = pstat.executeQuery();){
				ArrayList<String> list = new ArrayList();
				while(rs.next()) {
					list.add(rs.getString("hotelImg"));
				}return list;
			}
		}
	}
	//정렬(이름)
	public List<HotelDTO> searchName(String keyword)throws Exception{
		String sql = "select * from hotel where hotelName like '%?%'";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, keyword);
			try(ResultSet rs = pstat.executeQuery();){
				ArrayList<HotelDTO> list = new ArrayList();
				while(rs.next()) {
					HotelDTO dto = new HotelDTO();
					dto.setHotelId(rs.getString("hotelID"));
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
	//정렬(이름)에 맞는 이미지 불러오기
		public List<String> searchNameHotelImg(String keyword)throws Exception{
			String sql = "select i.hotelImg from hotel h, imgFile i where h.hotelid = i.hotelid and h.hotelname = ?";
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setString(1, keyword );
				try(ResultSet rs = pstat.executeQuery();){
					ArrayList<String> list = new ArrayList();
					while(rs.next()) {
						list.add(rs.getString("hotelImg"));
					}return list;
				}
			}
		}
	//정렬(위치)
	public List<HotelDTO> searchLocation(String keyword)throws Exception{
		String sql = "select * from hotel where hotelRoadAddress like '%?%'";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, keyword);
			try(ResultSet rs = pstat.executeQuery();){
				ArrayList<HotelDTO> list = new ArrayList();
				while(rs.next()) {
					HotelDTO dto = new HotelDTO();
					dto.setHotelId(rs.getString("hotelID"));
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
	//정렬(위치)에 맞는 이미지 불러오기
			public List<String> searchLocationHotelImg(String keyword)throws Exception{
				String sql = "select i.hotelImg from hotel h, imgFile i where h.hotelid = i.hotelid and h.hotelRoadAddress = ?";
				try(Connection con = this.getConnection();
						PreparedStatement pstat = con.prepareStatement(sql);){
					pstat.setString(1, keyword );					
					try(ResultSet rs = pstat.executeQuery();){
						ArrayList<String> list = new ArrayList();
						while(rs.next()) {
							list.add(rs.getString("hotelImg"));
						}return list;
					}
				}
			}
	//상품 정보 불러오기(방이미지만 뽑아오기)
	public List<String> selectRoomImg(String name)throws Exception{
		String sql = "select i.roomImg from Hotel h, imgFile i where h.hotelId = i.hotelId and h.hotelName = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, name);
			try(ResultSet rs = pstat.executeQuery();){
				ArrayList<String> list = new ArrayList();
				while(rs.next()) {
					list.add(rs.getString("roomImg"));
				}return list;
			}
		}

	}
	


}

