package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HotelDAO;
import dao.LikeDAO;
import dto.HotelFullDTO;
import dto.HotelLikeImgDTO;
import dto.LikeDTO;


@WebServlet("*.like")
public class LikeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=UTF-8");
		String cmd = request.getServletPath();
		System.out.println(cmd);
		
		LikeDAO ldao = LikeDAO.getInstance();
		HttpSession session = request.getSession();
		HotelDAO hdao = HotelDAO.getInstance();
		try {
			
			// ROW 이용 Like연산
			if (cmd.equals("/detailClick.like")) {
				String loginId = request.getParameter("loginId");
				String hotelId = request.getParameter("hotelId");
				// 해당 아이디랑 호텔 id받아오기.
				System.out.println(loginId);
				System.out.println(hotelId);
				
				boolean DetailLikeCheck = ldao.likeCheck(loginId, hotelId);
				System.out.println("하트 체크 : " + DetailLikeCheck);
				if(!DetailLikeCheck) { // 하트를 눌렀는데 false면 현재 페이지에서는 투명이고 사용자는 하트를 처음 누르는것이니 add
					ldao.likeAdd(loginId, hotelId);
					response.getWriter().append("add");
				} else if (DetailLikeCheck) {	// 해당 사용자가 하트를 눌렀는데 이미 누른적이 있는 사용자라면 빨간색 하트를 다시 검정으로 바꿔버려야됨.
					ldao.likeDel(loginId, hotelId);
					response.getWriter().append("del");
				}

				
			} else if (cmd.equals("/likeList.like")) { // 찜목록 뽑아오기
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
	            List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
	            request.setAttribute("hotelListS", hotelListS);
				
				String loginId = request.getParameter("loginId");
				List<LikeDTO> list = ldao.likeList(loginId);
				List<HotelFullDTO> hotelList = new ArrayList<>();
				
				for(LikeDTO dto : list) {
					hotelList.add(hdao.getLikeHotelOne(dto.getHotelId()));
				}
				
				System.out.println("로그인된 사용자의 ID : " + loginId);
				List<LikeDTO> dto = new ArrayList<>();
	            for (HotelFullDTO d : hotelList) {
	            	String hotelId = d.getHotelId();
	            	boolean likeCheck = ldao.likeCheck(loginId, hotelId);
	            	dto.add(new LikeDTO(0, hotelId, loginId, likeCheck));
	            }
	            request.setAttribute("likeDto", dto);
				
				
				request.setAttribute("hotelList", hotelList);
				request.getRequestDispatcher("/views/hotel/hotelList.jsp").forward(request, response);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
