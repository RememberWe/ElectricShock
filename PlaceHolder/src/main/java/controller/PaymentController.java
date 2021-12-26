package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HotelDAO;
import dao.PaymentDAO;
import dao.ReservationDAO;
import dao.RoomDAO;
import dao.UserDAO;
import dto.ReservationDTO;

@WebServlet("*.pay")
public class PaymentController extends HttpServlet {

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

		// Impoty PaymentDAO
		PaymentDAO paymentDao = PaymentDAO.getInstance();

		// Session 값으로 떠 다니는 ID 값 받아오기
		String loginId = (String) request.getSession().getAttribute("loginId");

		try {
		if(cmd.equals("/reservation.pay")) {
				
				String revId = request.getParameter("revId");
				System.out.println("예약번호 받기 : " + revId);
				
				List<ReservationDTO> list = reservDao.payInformation(revId);
				
				request.setAttribute("paymentInfo",list);
				// 마이페이지로 request를 전송
				request.getRequestDispatcher("/views/hotel/payment.jsp").forward(request, response);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
