package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.HotelImgDTO;
import dto.ReservationDTO;
import dto.RoomImgDTO;

public class ImgFileDAO {
	//인스턴스와 커넥션
	public static ImgFileDAO instance = null;
	public static ImgFileDAO getInstance() {
		if(instance == null) {
			instance = new ImgFileDAO();
		}return instance;
	}
	private ImgFileDAO() {}
	private Connection getConnection()throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	//호텔컨트롤러에서 쓰일 것
	//호텔 이미지 뽑아오는데 범위 내의 값만 가져오기

	// 진규 수정 String리스트를 호텔이미지 DTO로 변경
	public List<HotelImgDTO> selectHotelImgB(int start, int end)throws Exception{
		String sql = "select * from(select hotelimg.*, row_number() over(order by hotelId asc)rn from hotelimg) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			try(ResultSet rs = pstat.executeQuery();){
				List<HotelImgDTO> list = new ArrayList<>();
				while(rs.next()) {
					HotelImgDTO dto = new HotelImgDTO(rs.getString("hotelImg"));
					list.add(dto);
				}return list;
			}
		}
	}
	//호텔 이미지 불러오기(안쓸거같음)
	public List<String> selectHotelImg()throws Exception{
		String sql = "select hotelImg from imgFile";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			try(ResultSet rs = pstat.executeQuery();){
				List<String> list = new ArrayList();
				while(rs.next()) {
					list.add(rs.getString("hotelImg"));
				}return list;
			}
		}
	}

	//검색(이름)에 맞는 호텔이미지 불러오기
	public List<HotelImgDTO> searchHotelNameImg(String keyword)throws Exception{
		String sql = "select i.hotelImg from hotel h, hotelimg i where h.hotelid = i.hotelid and h.hotelname like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+keyword+"%" );
			try(ResultSet rs = pstat.executeQuery();){
				List<HotelImgDTO> list = new ArrayList<>();
				while(rs.next()) {
					HotelImgDTO dto = new HotelImgDTO(rs.getString("hotelImg"));
					list.add(dto);
				}return list;
			}
		}
	}

	//검색(이름)에 맞는 호텔이미지 불러오는데 범위 값
	public List<HotelImgDTO> searchHotelNameImgB(int start, int end, String keyword)throws Exception{
		String sql = "select i.hotelImg from (select hotelimg.*, row_number() over(order by hotelId asc)rn from hotel where h.hotelname like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			pstat.setString(3, "%"+keyword+"%" );
			try(ResultSet rs = pstat.executeQuery();){
				List<HotelImgDTO> list = new ArrayList<>();
				while(rs.next()) {
					HotelImgDTO dto = new HotelImgDTO(rs.getString("hotelImg"));
					list.add(dto);
				}return list;
			}
		}
	}

	//키워드(위치)에 맞는 호텔이미지 불러오기
	public List<String> searchHotelSiteImg(String keyword)throws Exception{
		String sql = "select i.hotelImg from hotel h, imgFile i where h.hotelid = i.hotelid and h.hotelRoadAddress like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+keyword+"%" );					
			try(ResultSet rs = pstat.executeQuery();){
				List<String> list = new ArrayList();
				while(rs.next()) {
					list.add(rs.getString("hotelImg"));
				}return list;
			}
		}
	}

	//검색(위치)에 맞는 호텔이미지 불러오는데 범위 값
	public List<String> searchHotelSiteImgB(int start, int end, String keyword)throws Exception{
		String sql = "select i.hotelImg from (select imgFile.*, row_number() over(order by hotelId asc)rn from imgFile where i.roadAddress like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			pstat.setString(3, "%"+keyword+"%" );
			try(ResultSet rs = pstat.executeQuery();){
				ArrayList<String> list = new ArrayList();
				while(rs.next()) {
					list.add(rs.getString("hotelImg"));
				}return list;
			}
		}
	}

	//룸컨트롤러에서 쓰일 것
	//호텔 아이디 값에 맞는 호텔 이미지
	// 진규 수정 img -> hotelimg로
	public String selectHotelImgById(String hotelId)throws Exception{
		String sql = "select hotelImg from hotelimg where hotelId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, hotelId );					
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					String result = rs.getString("hotelImg");
					return result;
				}return null;
			}
		}
	}

	//호텔 아이디 값에 맞는 방 이미지
	// 진규 수정 String -> RoomImgDTO
	public List<RoomImgDTO> selectRoomImgById(String id)throws Exception{
		String sql = "select roomImg from roomimg where hotelId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery();){
				List<RoomImgDTO> list = new ArrayList<>();
				while(rs.next()) {
					RoomImgDTO dto = new RoomImgDTO(rs.getString("roomImg"));
					list.add(dto);
				}return list;
			}
		}
	}

	//호텔 이미지 값을 받아서 삭제
	public int deleteImg(String hotelId)throws Exception{
		String sql = "delete from ImgFile where hotelId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, hotelId);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}		
	}
	// 현우 추가 : 예약 목록에서 호텔 이미지 파일 받아오기
	public List<HotelImgDTO> imgList(List<ReservationDTO> list) throws Exception{

		String sql = "select * from hotelImg where hotelId = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			List<HotelImgDTO> iList = new ArrayList<>();

			for(int i = 0; i < list.size(); i++) {
				pstat.setString(1, list.get(i).getHotelId());
				try(ResultSet rs = pstat.executeQuery();){
					while(rs.next()) {
						HotelImgDTO idto = new HotelImgDTO(rs.getString("hotelImg"));
						iList.add(idto);
					}
				}
			}
			return iList;
		}
	}
}