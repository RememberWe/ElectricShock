package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.RoomDTO;

public class RoomDataCreator {

	public static void main(String[] args) throws Exception {
		try {
			// sql developer 연결 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@3.38.78.110:1521:xe";
			String username = "kh";
			String password = "kh";

			// 호텔 id 리스트를 담을 ArrayList 생성
			List<String> hotelIdList = new ArrayList<>();

			// hotel table에서 hotelId 불러오기
			String sql = "select * from hotel";
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement pstat = con.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			{
				while (rs.next()) {
					String hotelId = rs.getString("hotelID");
					hotelIdList.add(hotelId);
				}

				// 각 방을 담을 ArrayList 각각 생성
				List<RoomDTO> standardRoom = new ArrayList<>();
				List<RoomDTO> doubleRoom = new ArrayList<>();
				List<RoomDTO> deluxRoom = new ArrayList<>();
				List<RoomDTO> suiteRoom = new ArrayList<>();
				List<RoomDTO> executiveRoom = new ArrayList<>();

				// 확인용 코드
				System.out.println("호텔 id 값을 받아오는 중....");

				// 스탠다드
				for (int i = 0; i < hotelIdList.size(); i++) {
					System.out.println("스탠다드 상세 정보 입력 중.....");

					String sqlOne = "insert into room values(?,?,?,?,?,?)";
					PreparedStatement pstatOne = con.prepareStatement(sqlOne);

					pstatOne.setString(1, hotelIdList.get(i));
					pstatOne.setString(2, "스탠다드룸");
					pstatOne.setString(3, "5");
					pstatOne.setString(4, "150000");
					pstatOne.setString(5, "30000");
					pstatOne.setString(6, "이 스탠다드룸은 방음 시설, 전기 주전자, 주방용품을 갖추고 있습니다.");

					int result = pstatOne.executeUpdate();

				}
				// 더블룸
				for (int i = 0; i < hotelIdList.size(); i++) {
					System.out.println("더블룸 상세 정보 입력 중.....");

					String sqlOne = "insert into room values(?,?,?,?,?,?)";
					PreparedStatement pstatOne = con.prepareStatement(sqlOne);

					pstatOne.setString(1, hotelIdList.get(i));
					pstatOne.setString(2, "더블룸");
					pstatOne.setString(3, "5");
					pstatOne.setString(4, "200000");
					pstatOne.setString(5, "30000");
					pstatOne.setString(6, "이 더블룸은 에어컨, DVD 플레이어, 전용 출입구를 갖추고 있습니다.");

					int result = pstatOne.executeUpdate();

				}
				// 디럭스룸
				for (int i = 0; i < hotelIdList.size(); i++) {
					System.out.println("디럭스룸 상세 정보 입력 중.....");

					String sqlOne = "insert into room values(?,?,?,?,?,?)";
					PreparedStatement pstatOne = con.prepareStatement(sqlOne);

					pstatOne.setString(1, hotelIdList.get(i));
					pstatOne.setString(2, "디럭스룸");
					pstatOne.setString(3, "5");
					pstatOne.setString(4, "250000");
					pstatOne.setString(5, "30000");
					pstatOne.setString(6,
							"이 객실은 에어컨, DVD 플레이어, 전용 출입구를 갖추고 있고, 객실에는 추가 요금으로 이용 가능한 엑스트라 베드를 비치할 수 있는 공간이 있습니다.");

					int result = pstatOne.executeUpdate();

				}
				// 스위트룸
				for (int i = 0; i < hotelIdList.size(); i++) {
					System.out.println("스위트룸 상세 정보 입력 중.....");

					String sqlOne = "insert into room values(?,?,?,?,?,?)";
					PreparedStatement pstatOne = con.prepareStatement(sqlOne);

					pstatOne.setString(1, hotelIdList.get(i));
					pstatOne.setString(2, "스위트룸");
					pstatOne.setString(3, "5");
					pstatOne.setString(4, "300000");
					pstatOne.setString(5, "30000");
					pstatOne.setString(6,
							"이 객실은 라운지 공간 및 욕조가 놓인 대리석 욕실을 갖추고 있습니다.실내 욕실에는 별도의 욕조 및 샤워 시설과 전자식 비데가 구비되어 있습니다.");

					int result = pstatOne.executeUpdate();

				}

				// 이그제큐티브룸
				for (int i = 0; i < hotelIdList.size(); i++) {
					System.out.println("이그제큐티브룸 상세 정보 입력 중.....");

					String sqlOne = "insert into room values(?,?,?,?,?,?)";
					PreparedStatement pstatOne = con.prepareStatement(sqlOne);

					pstatOne.setString(1, hotelIdList.get(i));
					pstatOne.setString(2, "이그제큐티브룸");
					pstatOne.setString(3, "5");
					pstatOne.setString(4, "350000");
					pstatOne.setString(5, "30000");
					pstatOne.setString(6,
							"이 객실은 천장부터 바닥까지 내려오는 대형 창문과 황금빛의 참나무 가구로 꾸며져 있습니다.실내 욕실에는 별도의 욕조 및 샤워 시설과 전자식 비데가 구비되어 있습니다.");

					int result = pstatOne.executeUpdate();

				}

				// 최종 입력 확인
				System.out.println("총" + hotelIdList.size() + "개의 호텔 이미지 입력");
			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}