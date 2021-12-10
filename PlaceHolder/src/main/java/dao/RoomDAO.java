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
	//제품 정보 불러오기(이미지, 호텔, 상세정보)
	public List<HotelDTO> selectGoods(String id) throws Exception{
		String sql = "select * from hotel where hotelId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery();){
				ArrayList<HotelDTO> list = new ArrayList();
				while(rs.next()) {
					HotelDTO dto = new HotelDTO();
				}
			}return null;
		}
	}
}
