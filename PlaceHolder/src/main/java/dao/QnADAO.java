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

import dto.QnADTO;
import dto.QnADTO;


public class QnADAO {
	private static QnADAO instance = null;
	public static QnADAO getInstance() {
		if(instance == null) {
			instance = new QnADAO();
		}
		return instance;
	}
	private QnADAO() {}
	
	private Connection getConnection()throws Exception{
		Context ctx = new InitialContext(); //
		//ctx.lookup("java:comp/env/jdbc/oracle"); //java:comp/env 源뚯��뒗 嫄곗쓽 怨좎젙
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	public int insert(QnADTO dto) throws Exception{
		String sql = "insert into qna values(inquiry_seq.nextval, ?, ?, ?, ?, ?)";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getHotelId());
			pstat.setString(2, dto.getUserId());
			pstat.setString(3, dto.getInquiryStat());
			pstat.setString(4, dto.getInquiryContent());
			pstat.setDate(5, dto.getInquiryCreated());
			return pstat.executeUpdate();

		}
	}

	public List<QnADTO> selectAll() throws Exception{
		String sql = "select * from qna order by inquiry_seq desc";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()){
			List<QnADTO> dto = new ArrayList<>();
			while(rs.next()) {
				int inquiry = rs.getInt("inquiry");
				String hotelId = rs.getString("hotelId");
				String userId = rs.getString("userId");
				String inquiryStat = rs.getString("inquiryStat");
				String inquiryContent = rs.getString("inquiryContent");
				Date inquiryCreated = rs.getDate("inquiryCreated");
				
				dto.add(new QnADTO(inquiry,hotelId,userId,inquiryStat,inquiryContent,inquiryCreated));
			}
			return dto;
		}
	}
	
	
	public QnADTO selectBySeq(int inquiry_seq) throws Exception{
		String sql = "select * from qna where inquiry_seq=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1,inquiry_seq);
			try(ResultSet rs = pstat.executeQuery();){
				QnADTO dto = null;

				if(rs.next()) {
					int inquiry = rs.getInt("inquiry");
					String hotelId = rs.getString("hotelId");
					String userId = rs.getString("userId");
					String inquiryStat = rs.getString("inquiryStat");
					String inquiryContent = rs.getString("inquiryContent");
					Date inquiryCreated = rs.getDate("inquiryCreated");
					
					dto=new QnADTO(inquiry,hotelId,userId,inquiryStat,inquiryContent,inquiryCreated);

				}
				return dto;
			}
		}
	}
	
	public int delete(int inquiry) throws Exception {
		String sql = "delete from qna where inquiry=? and id=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, inquiry);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	public int modify(int inquiry, String inquiryContent) throws Exception{
		String sql = "update qna set inquiryContent=? where inquiry=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, inquiry);
			pstat.setString(2, inquiryContent);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	
	//호텔 아이디를 조건으로 하여 QnA 조회하기
		public List<QnADTO> selectQnAByHotelId(String hotelId)throws Exception{
			String sql = "select * from qna where hotelId = ?";
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setString(1, hotelId);
				try(ResultSet rs = pstat.executeQuery();){
					List<QnADTO> list = new ArrayList<>();
					while(rs.next()) {
						QnADTO dto = new QnADTO();
						dto.setInquiry(rs.getInt("inquiry"));
						dto.setHotelId(rs.getString("hotelId"));
						dto.setUserId(rs.getString("userId"));
						dto.setInquiryStat(rs.getString("inquuiryStat"));
						dto.setInquiryContent(rs.getString("inquiryContent"));
						dto.setInquiryCreated(rs.getDate("inquiryCreated"));
						list.add(dto);
					}return list;
				}
			}
		}
}