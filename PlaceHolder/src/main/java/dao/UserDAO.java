package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.UserDTO;

public class UserDAO {
	// 인스턴스와 커넥션
	public static UserDAO instance = null;

	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	private UserDAO() {
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	// 아이디 중복확인
	public boolean idCheck(String id) throws Exception {
		String sql = "select * from userInfo where userId = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, id);
			try (ResultSet rs = pstat.executeQuery();) {
				return rs.next();
			}
		}
	}

	// 회원가입(oracle에 회원 삽입)
	public int insert(UserDTO dto) throws Exception {
		String sql = "insert into userInfo values(?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getUserId());
			pstat.setString(2, dto.getUserName());
			pstat.setString(3, dto.getUserPw());
			pstat.setString(4, dto.getUserNickname());
			pstat.setString(5, dto.getUserEmail());
			pstat.setString(6, dto.getUserBirth());
			pstat.setString(7, dto.getUserPhone());
			pstat.setString(8, dto.getUserPost());
			pstat.setString(9, dto.getUserRoadAddress());
			pstat.setString(10, dto.getUserRoadAddress2());
			int result = pstat.executeUpdate();
			con.commit();
			return result;

		}
	}

	// 로그인(oracle에 저장된 회원정보와 일치하는지 확인)
	public boolean login(String id, String pw) throws Exception {
		String sql = "select * from userInfo where userID = ? and userPw = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, id);
			pstat.setString(2, pw);
			try (ResultSet rs = pstat.executeQuery();) {
				return rs.next();
			}
		}
	}

	// 회원탈퇴(oracle에 저장된 회원정보를 삭제)
	public int delete(String id) throws Exception {
		String sql = "delete from userInfo where userId = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, id);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}

	// 회원정보 수정(oracle에 저장된 회원정보를 수정)
	public int update(UserDTO dto) throws Exception {
		String sql = "update userInfo set userName = ?, userPw = ?, userNickname = ?, userEmail = ?, userBirth = ?, userPhone = ?, userPost = ?, userRoadAddress = ?, userRoadAddress2 = ? where userId = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getUserName());
			pstat.setString(2, dto.getUserPw());
			pstat.setString(3, dto.getUserNickname());
			pstat.setString(4, dto.getUserEmail());
			pstat.setString(5, dto.getUserBirth());
			pstat.setString(6, dto.getUserPhone());
			pstat.setString(7, dto.getUserPost());
			pstat.setString(8, dto.getUserRoadAddress());
			pstat.setString(9, dto.getUserRoadAddress2());
			pstat.setString(10, dto.getUserId());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}

	// 회원정보 불러오기(oracle에 저장된 회원의 정보를 불러오기)
	public UserDTO info(String id) throws Exception {
		String sql = "select * from userInfo where userID = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, id);
			try (ResultSet rs = pstat.executeQuery();) {
				UserDTO dto = new UserDTO();
				if (rs.next()) {
					dto.setUserId(rs.getString("userId"));
					dto.setUserName(rs.getString("userName"));
					dto.setUserPw(rs.getString("userPw"));
					dto.setUserNickname(rs.getString("userNickname"));
					dto.setUserEmail(rs.getString("userEmail"));
					dto.setUserBirth(rs.getString("userBirth"));
					dto.setUserPhone(rs.getString("userPhone"));
					dto.setUserPost(rs.getString("userPost"));
					dto.setUserRoadAddress(rs.getString("userRoadAddress"));
					dto.setUserRoadAddress2(rs.getString("userRoadAddress2"));

					return dto;
				}
			}
			return null;
		}
	}

	// 검색한 회원 리스트 불러오기(소현)
	public List<UserDTO> searchList(String id) throws Exception {
		String sql = "select * from userInfo where userId like ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, "%" + id + "%");
			try (ResultSet rs = pstat.executeQuery();) {
				List<UserDTO> list = new ArrayList<>();
				while (rs.next()) {
					UserDTO dto = new UserDTO();
					dto.setUserId(rs.getString("userId"));
					dto.setUserName(rs.getString("userName"));
					dto.setUserPw(rs.getString("userPw"));
					dto.setUserNickname(rs.getString("userNickname"));
					dto.setUserEmail(rs.getString("userEmail"));
					dto.setUserBirth(rs.getString("userBirth"));
					dto.setUserPhone(rs.getString("userPhone"));
					dto.setUserPost(rs.getString("userPost"));
					dto.setUserRoadAddress(rs.getString("userRoadAddress"));
					dto.setUserRoadAddress2(rs.getString("userRoadAddress2"));
					list.add(dto);
				}
				return list;
			}
		}
	}

	// 회원 리스트 불러오기(소현)
	public List<UserDTO> userList() throws Exception {
		String sql = "select * from userInfo";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			List<UserDTO> list = new ArrayList<>();
			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setUserId(rs.getString("userId"));
				dto.setUserName(rs.getString("userName"));
				dto.setUserPw(rs.getString("userPw"));
				dto.setUserNickname(rs.getString("userNickname"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserBirth(rs.getString("userBirth"));
				dto.setUserPhone(rs.getString("userPhone"));
				dto.setUserPost(rs.getString("userPost"));
				dto.setUserRoadAddress(rs.getString("userRoadAddress"));
				dto.setUserRoadAddress2(rs.getString("userRoadAddress2"));
				list.add(dto);
			}
			return list;
		}
	}
}
