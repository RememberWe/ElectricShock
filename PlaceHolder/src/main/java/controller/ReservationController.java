package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.HotelDAO;
import dao.ImgFileDAO;
import dao.ReservationDAO;
import dao.RoomDAO;
import dao.UserDAO;
import dto.HotelDTO;
import dto.HotelImgDTO;
import dto.HotelLikeImgDTO;
import dto.ReservationDTO;
import dto.RoomDTO;
import tool.DateChangerToSql;
import tool.PriceCharger;

@WebServlet("*.book")
public class ReservationController extends HttpServlet {

	// doGet Method
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Encoding utf-8로 설정하기
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=UTF-8");

		// 경로값 받아오기
		String cmd = request.getServletPath();

		// Import ReservationDAO
		ReservationDAO reservDao = ReservationDAO.getInstance();

		// Import UserDAO
		UserDAO userDao = UserDAO.getInstance();

		// Import RoomDAO
		RoomDAO roomDao = RoomDAO.getInstance();

		// Import HotelDAO
		HotelDAO hotelDao = HotelDAO.getInstance();

		// Import imgFileDAO
		ImgFileDAO idao = ImgFileDAO.getInstance();

		HotelDAO hdao = HotelDAO.getInstance();

		// Import Gson
		Gson g = new Gson();

		// Session 값으로 떠 다니는 ID 값 받아오기
		String loginId = (String) request.getSession().getAttribute("loginId");

		// 명령어에 따라 예약 업무 처리
		try {

			// Reservation Controller 기능 (RoomController와 통신한다)

			// 1. 상품 상세페이지에서 확정된 예약 정보를 받아 DB에 저장하는 기능
			if(cmd.equals("/confirm.book")) {

				// 호텔 상세페이지에서 직접 받아오는 정보
				System.out.println(request.getParameter("hotelId") + " : " 
						+ request.getParameter("checkIn") + " : " + request.getParameter("checkOut") 
						+ " : " + request.getParameter("revRoomType") + " : " + request.getParameter("revQuantity")
						+ " : " + loginId);

				String hotelId = request.getParameter("hotelId");
				Date checkIn = DateChangerToSql.changeSqlDate(request.getParameter("checkIn"));
				Date checkOut = DateChangerToSql.changeSqlDate(request.getParameter("checkOut"));

				String revRoomType = request.getParameter("revRoomType");
				int revQuantity = Integer.parseInt(request.getParameter("revQuantity"));

				String revId = "auto";
				String userId = loginId;

				// Hotel DB에서 호텔 정보 받아오기
				HotelDTO hDto = hotelDao.selectHotelById(hotelId); // hotelId
				String hotelName = hDto.getHotelName();
				String hotelRoadAddress = hDto.getHotelRoadAddress();
				String hotelPhone = hDto.getHotelPhone();

				// Room DB에서 방 정보 받아오기
				RoomDTO selectedRoomInfo = roomDao.showRoomInfo(hotelId, revRoomType);
				String revRoomInfo = roomDao.showRoomInfo(hotelId, revRoomType).getRoomInfo();
				String revStat = "대기";
				String addPrice = request.getParameter("addPrice");

				// 기본 방 가격 + 추가인원 * 추가인원 당 가격 * 숙박일수
				String revPrice = PriceCharger.charger(selectedRoomInfo.getRoomPrice(), revQuantity , addPrice, checkIn, checkOut);

				// 예약일 : SYSDATE
				Date revDay = DateChangerToSql.changeCurrentTime(System.currentTimeMillis());

				// ** 해당 옵션의 방이 예약 가능한지 확인해야 함.c
				// 호텔 측에 문의했을 때 예약이 가능한 경우
				// 조건1. : quantity >= revQuantity;
				// 조건2. : checkIn > sysdate;
				// 조건3. : checkIn < checkOut; -> view에서 걸러준다.

				// 예약 DB 전체를 스캔하면서 다음 조건이 맞는지 확인한 후에 풀어낸다.
				ReservationDTO reservDto = new ReservationDTO(revId, userId, hotelId, hotelName, hotelRoadAddress, hotelPhone, checkIn, checkOut, revDay, revRoomType, revQuantity, revRoomInfo, revStat, revPrice);

				System.out.println(
						"확인용 코드 : " + revId + " : " + userId + " : " + hotelId + " : " + hotelName + " : " + hotelRoadAddress + " : " + hotelPhone + " : " + checkIn + " : " + checkOut 
						+ " : " + revDay + " : " + revRoomType + " : " + revQuantity + " : " + revRoomInfo + " : " + revStat + " : " + revPrice 
						);

				boolean condition = reservDao.isReservationAllowed(reservDto);

				if(condition) {
					// Reservation table에 예약 내역 저장
					int result = reservDao.confirmReservation(reservDto);

					// 예약 확인할 수 있게 마이페이지로 이동 (협의 필요함)
					response.sendRedirect("/mypage.home");
					// 예약이 불가능한 경우
				}

				// 2. User가 요청했을 때 확정된 예약 정보를 불러오는 기능	
			}else if(cmd.equals("/viewReservationList.book")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
	            List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
	            request.setAttribute("hotelListS", hotelListS);
	            
				// Session 값 활용해서 User의 예약 현황 조회하기
				List<ReservationDTO> reservDto = reservDao.viewCurrentReservation(loginId); // session 값 활용하기

				// ReservationDTO 객체를 request에 담기
				//				request.setAttribute("reservDto",reservDto);

				// 마이페이지로 request를 전송
				String result = g.toJson(reservDto);
				response.getWriter().append(result);

				List<HotelImgDTO> hotelImgList = idao.imgList(reservDto);

				// 현우 추가
				List<HotelLikeImgDTO> hotelListAll = hdao.selectHotel();
				request.setAttribute("hotelListAll", hotelListAll); // 현우추가

				request.setAttribute("reserveList", reservDto);
				request.setAttribute("hotelImgList", hotelImgList);
				request.getRequestDispatcher("/views/member/mypage.jsp").forward(request, response);

				// 3. 예약 수정하기 기능(Hotel Controller와 통신)	
			}else if(cmd.equals("/modifyReservation.book")) {

				// 수정할 예약 내용 데이터 받아오기(어디서, 어떤 방식으로?)
				System.out.println("예약 수정 넘어오는 것 : " + request.getParameter("revId") + " : " + request.getParameter("checkIn") + " : " + request.getParameter("checkOut"));
				String revId = request.getParameter("revId");
				String userId = loginId;
				Date checkIn = DateChangerToSql.changeSqlDate(request.getParameter("checkIn"));
				Date checkOut = DateChangerToSql.changeSqlDate(request.getParameter("checkOut"));
				Date revDay = DateChangerToSql.changeCurrentTime(System.currentTimeMillis());				

				String hotelId = request.getParameter("hotelId");
				String hotelName = "none";
				String hotelRoadAddress = "none";
				String hotelPhone = "none";

				String revRoomType = request.getParameter("revRoomType");
				int revQuantity = Integer.parseInt(request.getParameter("revQuantity"));
				String revRoomInfo = request.getParameter("revRoomInfo");
				String revStat = "대기";

				String addPrice = request.getParameter("addPrice");

				System.out.println(checkIn + " : " + checkOut + " : " + revRoomType);

				RoomDTO selectedRoomInfo = roomDao.showRoomInfo(hotelId, revRoomType);

				String revPrice = PriceCharger.charger(selectedRoomInfo.getRoomPrice(), revQuantity, addPrice, checkIn, checkOut);

				// Reservation DB의 데이터 수정
				ReservationDTO reservDto = new ReservationDTO(revId, userId, hotelId, hotelName, hotelRoadAddress, hotelPhone, checkIn, checkOut, revDay, revRoomType, revQuantity, revRoomInfo, revStat, revPrice);

				// 수정 가능한지 확인
				boolean condition = reservDao.isReservationAllowed(reservDto);

				if(condition) {
					// Reservation table에 예약 내역 저장
					int result = reservDao.modifyReservation(reservDto);

					// 수정내역 확인할 수 있게 예약목록으로 이동 (협의 필요함)
					response.sendRedirect("/viewReservationList.book");
				} else {
					request.setAttribute("false", condition);
					request.getRequestDispatcher("/viewReservationList.book").forward(request, response);
				}
			}

			// 4. 예약 삭제하기 기능(Hotel Controller와 통신)	
			else if(cmd.equals("/deleteReservation.book")) {

				// 예약코드 받아오기
				String revId = request.getParameter("revId"); // revId(예약코드)
				// Reservation DB에서 예약코드에 해당하는 예약 삭제하기
				int result = reservDao.cancelReservation(revId);

				// Hotel Controller로 Room 데이터보내 Room DB에서 예약 삭제하기
				request.setAttribute("result", result);
				request.getRequestDispatcher("/viewReservationList.book").forward(request, response);
			}
			// 12.21 현우 수정 (5번 메서드는 삭제)
			else if(cmd.equals("/paid.book")) {
				String revId = request.getParameter("revId");
				System.out.println("예약 확정할 번호 : " + revId);
				int result = reservDao.completeBooking(revId);
				response.sendRedirect("/viewReservationList.book");
			}

		}catch(Exception e) { // error 발생 시 처리
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	// doPost Method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}