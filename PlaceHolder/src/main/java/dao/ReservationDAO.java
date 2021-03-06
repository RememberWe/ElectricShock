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

import dto.ReservationDTO;

public class ReservationDAO {

	// ReservationDAO instance
	public static ReservationDAO instance = null;

	public static ReservationDAO getInstance() {
		if (instance == null) {
			instance = new ReservationDAO();
		}
		return instance;
	}

	// JNDI 설정
	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle"); // AWS 사용할 때 수정 필요함
		return ds.getConnection();
	}

	// 0. 예약(신규, 수정)이 가능한지 확인하는 기능
	public boolean isReservationAllowed(ReservationDTO dto) throws Exception {

		String sql = "select count(*) from reservation where hotelId = ? and revRoomType = ? and (checkIn = ? and checkOut <= ?)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getHotelId());
			pstat.setString(2, dto.getRevRoomType());
			pstat.setDate(3, dto.getCheckIn());
			pstat.setDate(4, dto.getCheckOut());

			try (ResultSet rs = pstat.executeQuery()) {
				if (dto.getRevQuantity() <= 5 - rs.getRow()) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	// 1. 확정된 예약 정보를 받아 DB에 저장하는 기능
	public int confirmReservation(ReservationDTO dto) throws Exception {

		String sql = "insert into reservation values(revId_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			//				pstat.setString(1, dto.getRevId());
			pstat.setString(1, dto.getUserId());
			pstat.setString(2, dto.getHotelId());
			pstat.setString(3, dto.getHotelName());
			pstat.setString(4, dto.getHotelPhone());
			pstat.setString(5, dto.getHotelRoadAddress());
			pstat.setDate(6, dto.getCheckIn());
			pstat.setDate(7, dto.getCheckOut());
			pstat.setDate(8, dto.getRevDay());
			pstat.setString(9, dto.getRevRoomType());
			pstat.setInt(10, dto.getRevQuantity());
			pstat.setString(11, dto.getRevRoomInfo());
			pstat.setString(12, dto.getRevStat());
			pstat.setString(13, dto.getRevPrice());

			int result = pstat.executeUpdate();

			return result;
		}
	}

	// 2. User가 요청했을 때 확정된 예약 정보를 불러오는 기능
	public List<ReservationDTO> viewCurrentReservation(String loginId) throws Exception {

		// 불러올 때 checkIn 날짜가 sysdate 까지의 경우만 조회
		String sql = "select * from reservation where userId = ? order by checkIn desc";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, loginId);
			try (ResultSet rs = pstat.executeQuery();) {

				// 확정된 예약이 1건 이상일 수도 있음을 고려 -> 경고 메시지 필요할 듯??????

				List<ReservationDTO> list = new ArrayList<>();

				while (rs.next()) {

					String revId = rs.getString("revId");
					String userId = rs.getString("userId");
					String hotelId = rs.getString("hotelId");
					String hotelName = rs.getString("hotelName");
					String hotelPhone = rs.getString("hotelPhone");
					String hotelRoadAddress = rs.getString("hotelRoadAddress");
					Date checkIn = rs.getDate("checkIn");
					Date checkOut = rs.getDate("checkOut");
					Date revDay = rs.getDate("revDay");
					String revRoomType = rs.getString("revRoomType");
					int revQuantity = rs.getInt("revQuantity");
					String revRoomInfo = rs.getString("revRoomInfo");
					String revStat = rs.getString("revStat");
					String revPrice = rs.getString("revPrice");
					ReservationDTO reservDto = new ReservationDTO(revId, userId, hotelId, hotelName, hotelPhone,
							hotelRoadAddress, checkIn, checkOut, revDay, revRoomType, revQuantity, revRoomInfo, revStat,
							revPrice);

					list.add(reservDto);
				}
				return list;
			}
		}
	}

	// 3. 예약 수정하기 기능(Hotel Controller와 통신) **** 현우 수정
	public int modifyReservation(ReservationDTO dto) throws Exception {

		String sql = "update reservation set checkIn = ?, checkOut = ?, revRoomType = ?, revQuantity = ?, revPrice = ?, revStat =? where revId = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setDate(1, dto.getCheckIn());
			pstat.setDate(2, dto.getCheckOut());
			pstat.setString(3, dto.getRevRoomType());
			pstat.setInt(4, dto.getRevQuantity());
			pstat.setString(5, dto.getRevPrice());
			pstat.setString(6, dto.getRevStat());
			pstat.setString(7, dto.getRevId());

			int result = pstat.executeUpdate();

			return result;
		}
	}
	// 4. 예약 삭제하기 기능(Hotel Controller와 통신)

	public int cancelReservation(String revId) throws Exception {

		String sql = "delete from reservation where revId = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, revId);
			int result = pstat.executeUpdate();
			return result;
		}
	}

	// 6. User 리뷰 남기기 기능- 1 (글쓰기 게시판으로 이동해서 남김) -> 데이터 리뷰 테이블로 전송
	// 어떤 예약건에 대한 리뷰를 남길 수 있는지 봐야 함
	public List<ReservationDTO> selectAllUserHistory(String loginId) throws Exception {

		// 불러올 때 checkout 날짜가 sysdate 이전인 경우만 조회
		String sql = "select * from reservation where id = ? and checkOut <= sysdate";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, loginId);
			try (ResultSet rs = pstat.executeQuery();) {

				List<ReservationDTO> list = new ArrayList<>();

				while (rs.next()) {
					String revId = rs.getString("revId");
					String userId = rs.getString("userId");
					String hotelId = rs.getString("hotelId");
					String hotelName = rs.getString("hotelName");
					String hotelPhone = rs.getString("hotelPhone");
					String hotelRoadAddress = rs.getString("hotelRoadAddress");
					Date checkIn = rs.getDate("checkIn");
					Date checkOut = rs.getDate("checkOut");
					Date revDay = rs.getDate("revDay");
					String revRoomType = rs.getString("revRoomType");
					int revQuantity = rs.getInt("revQuantity");
					String revRoomInfo = rs.getString("revRoomInfo");
					String revStat = rs.getString("revStat");
					String revPrice = rs.getString("revPrice");
					ReservationDTO reservDto = new ReservationDTO(revId, userId, hotelId, hotelName, hotelPhone,
							hotelRoadAddress, checkIn, checkOut, revDay, revRoomType, revQuantity, revRoomInfo, revStat,
							revPrice);

					list.add(reservDto);
				}
				return list;
			}
		}
	}
	public List<ReservationDTO> payInformation(String reservationId)throws Exception{
		String sql = "select * from reservation where revId = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, reservationId);
			try (ResultSet rs = pstat.executeQuery();) {

				List<ReservationDTO> list = new ArrayList<>();

				rs.next();

				String revId = rs.getString("revId");
				String userId = rs.getString("userId");
				String hotelId = rs.getString("hotelId");
				String hotelName = rs.getString("hotelName");
				String hotelPhone = rs.getString("hotelPhone");
				String hotelRoadAddress = rs.getString("hotelRoadAddress");
				Date checkIn = rs.getDate("checkIn");
				Date checkOut = rs.getDate("checkOut");
				Date revDay = rs.getDate("revDay");
				String revRoomType = rs.getString("revRoomType");
				int revQuantity = rs.getInt("revQuantity");
				String revRoomInfo = rs.getString("revRoomInfo");
				String revStat = rs.getString("revStat");
				String revPrice = rs.getString("revPrice");

				ReservationDTO reservDto = new ReservationDTO(revId, userId, hotelId, hotelName, hotelPhone,
						hotelRoadAddress, checkIn, checkOut, revDay, revRoomType, revQuantity, revRoomInfo, revStat,
						revPrice);

				list.add(reservDto);
				return list;
			}
		}
	}
	// 현우 추가 : 예약 상태 바꿔주는 함수
	public int completeBooking(String reservationId) throws Exception{
		String sql = "update reservation set revStat = ? where revId =?";

		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, "확정");
			pstat.setString(2, reservationId);

			int result = pstat.executeUpdate();
			System.out.println(result);
			return result;
		}
	}
	// 현우 추가 : 현재까지 예약 건수 조회해주는 함수
	public int countVisit(String loginId) throws Exception {

		String sql = "select count(*) from reservation where userId = ? and checkOut <= sysdate";
		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, loginId);
			try(ResultSet rs = pstat.executeQuery()){
				rs.next();
				int count = rs.getInt(1);
				return count;		
			}
		}
	}
}