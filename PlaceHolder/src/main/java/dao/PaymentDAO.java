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

import dto.PaymentDTO;
import dto.ReservationDTO;



public class PaymentDAO {
	private static PaymentDAO instance = null;
	public static PaymentDAO getInstance() {
		if(instance == null) {
			instance = new PaymentDAO();
		}
		return instance;
	}
	private PaymentDAO() {}
	
	private Connection getConnection()throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
//	public PaymentDTO selectBySeq(String payIds ) throws Exception{
//		String sql = "select * from Payment where payId=?";
//		try(Connection con = this.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setString(1,payIds);
//			try(ResultSet rs = pstat.executeQuery();){
//				PaymentDTO dto = null;
//
//				if(rs.next()) {
//					String payId = rs.getString("payId");
//					String revId = rs.getString("revID");
//					String userId = rs.getString("userId");
//					String hotelId = rs.getString("hotelId");
//					String payWay = rs.getString("payWay");
//					String payStat = rs.getString("payStat");
//					String revPrice = rs.getString("revPrice");
//					Date checkIn = rs.getDate("checkIn");
//					Date checkOut = rs.getDate("checkOut");
//					
//					dto=new PaymentDTO(payId,revId,userId,hotelId,payWay,payStat,revPrice,checkIn,checkOut);
//
//				}
//				return dto;
//			}
//		}
//	}
//	public List<PaymentDTO> viewCurrentReservation(String loginId) throws Exception {
//
//		// 불러올 때 checkIn 날짜가 sysdate 까지의 경우만 조회
//		String sql = "select * from reservation where id = ? order by checkIn desc";
//
//		try(Connection con = this.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setString(1, loginId);
//			try(ResultSet rs = pstat.executeQuery();){ 
//
//				// 확정된 예약이 1건 이상일 수도 있음을 고려 -> 경고 메시지 필요할 듯??????
//
//				List<PaymentDTO> list = new ArrayList<>();
//
//				while(rs.next()) {
//					String payId = rs.getString("payId");
//					String revId = rs.getString("revID");
//					String userId = rs.getString("userId");
//					String hotelId = rs.getString("hotelId");
//					String payWay = rs.getString("payWay");
//					String payStat = rs.getString("payStat");
//					String revPrice = rs.getString("revPrice");
//					Date checkIn = rs.getDate("checkIn");
//					Date checkOut = rs.getDate("checkOut");
//					PaymentDTO paymentDto = new PaymentDTO(payId,revId,userId,hotelId,payWay,payStat,revPrice,checkIn,checkOut);
//
//					list.add(paymentDto);
//				}
//				return list;
//			}
//		}
//	}
}
