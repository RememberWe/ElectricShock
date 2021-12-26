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

import dto.ArticleDTO;
import dto.QnADTO;
import dto.QnADTO;

public class QnADAO {
	private static QnADAO instance = null;

	public static QnADAO getInstance() {
		if (instance == null) {
			instance = new QnADAO();
		}
		return instance;
	}

	private QnADAO() {
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext(); //
		// ctx.lookup("java:comp/env/jdbc/oracle"); //java:comp/env 源뚯��뒗 嫄곗쓽 怨좎젙
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	// 1. Q & A insert 
	public int insert(QnADTO dto) throws Exception {
		String sql = "insert into qna values(inquiry_seq.nextval, ?, ?, '답변 대기', ?, default)";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getHotelId());
			pstat.setString(2, dto.getUserId());
			pstat.setString(3, dto.getInquiryContent());

			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}

	// 2. hotelId로 검색할 수 있게 수정
	public List<QnADTO> selectAll(String hotelId) throws Exception {
		String sql = "select * from qna where hotelId=?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, hotelId);

			try (ResultSet rs = pstat.executeQuery()) {
				List<QnADTO> dto = new ArrayList<>();
				while (rs.next()) {
					int inquiry = rs.getInt("inquiry");
					String hotelIds = rs.getString("hotelId");
					String userId = rs.getString("userId");
					String inquiryStat = rs.getString("inquiryStat");
					String inquiryContent = rs.getString("inquiryContent");
					Date inquiryCreated = rs.getDate("inquiryCreated");

					dto.add(new QnADTO(inquiry, hotelIds, userId, inquiryStat, inquiryContent, inquiryCreated));
				}
				return dto;
			}
		}
	}

	public QnADTO selectBySeq(int inquiry_seq) throws Exception {
		String sql = "select * from qna where inquiry=?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, inquiry_seq);
			try (ResultSet rs = pstat.executeQuery();) {
				QnADTO dto = null;

				if (rs.next()) {
					int inquiry = rs.getInt("inquiry");
					String hotelId = rs.getString("hotelId");
					String userId = rs.getString("userId");
					String inquiryStat = rs.getString("inquiryStat");
					String inquiryContent = rs.getString("inquiryContent");
					Date inquiryCreated = rs.getDate("inquiryCreated");

					dto = new QnADTO(inquiry, hotelId, userId, inquiryStat, inquiryContent, inquiryCreated);

				}
				return dto;
			}
		}
	}
	// 2. Q & A 삭제
	public int delete(int inquiry) throws Exception {
		String sql = "delete from qna where inquiry=?";
		try (Connection con = this.getConnection(); 
			PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, inquiry);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	// 2. Q & A 수정
	public int modify(QnADTO dto) throws Exception {
		String sql = "update qna set inquiryContent=? where inquiry=?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getInquiryContent());
			pstat.setInt(2, dto.getInquiry());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}

	// 호텔 아이디를 조건으로 하여 QnA 조회하기
	public List<QnADTO> selectQnAByHotelId(String hotelId) throws Exception {
		String sql = "select * from qna where hotelId = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, hotelId);
			try (ResultSet rs = pstat.executeQuery();) {
				List<QnADTO> list = new ArrayList<>();
				while (rs.next()) {
					QnADTO dto = new QnADTO();
					dto.setInquiry(rs.getInt("inquiry"));
					dto.setHotelId(rs.getString("hotelId"));
					dto.setUserId(rs.getString("userId"));
					dto.setInquiryStat(rs.getString("inquiryStat"));
					dto.setInquiryContent(rs.getString("inquiryContent"));
					dto.setInquiryCreated(rs.getDate("inquiryCreated"));
					list.add(dto);
				}
				return list;
			}
		}
	}

	// QNA 전체 조회하기(소현)
	public List<QnADTO> qnaList() throws Exception {
		String sql = "select * from qna order by inquiry desc";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			List<QnADTO> list = new ArrayList<>();
			while (rs.next()) {
				QnADTO dto = new QnADTO();
				dto.setInquiry(rs.getInt("inquiry"));
				dto.setHotelId(rs.getString("hotelId"));
				dto.setUserId(rs.getString("userId"));
				dto.setInquiryStat(rs.getString("inquiryStat"));
				dto.setInquiryContent(rs.getString("inquiryContent"));
				dto.setInquiryCreated(rs.getDate("inquiryCreated"));
				list.add(dto);
			}
			return list;
		}
	}
	
	// 유저명으로 검색(소현)
	public List<QnADTO> selectQnA(String keyword) throws Exception {
		String sql = "select * from qna where userId = ? order by inquiry desc";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, keyword);
			try (ResultSet rs = pstat.executeQuery();) {
				List<QnADTO> list = new ArrayList<>();
				while (rs.next()) {
					QnADTO dto = new QnADTO();
					dto.setInquiry(rs.getInt("inquiry"));
					dto.setHotelId(rs.getString("hotelId"));
					dto.setUserId(rs.getString("userId"));
					dto.setInquiryStat(rs.getString("inquiryStat"));
					dto.setInquiryContent(rs.getString("inquiryContent"));
					dto.setInquiryCreated(rs.getDate("inquiryCreated"));
					list.add(dto);
				}
				return list;
			}
		}
	}
}