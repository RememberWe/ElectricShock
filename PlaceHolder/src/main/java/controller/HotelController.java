package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HotelDAO;
import dto.HotelDTO;
import dto.ImgFileDTO;

@WebServlet("*.hotel")
public class HotelController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//기본세팅
		request.setCharacterEncoding("utf8");
		String requestURI = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String cmd = requestURI.substring(ctxPath.length());
		System.out.println(cmd); //경로 잘 들어오나 확인용

		//미리 세팅
		HotelDAO dao = HotelDAO.getInstance();
		
		try {
			//상품 리스트로 이동(메인에서 호텔을 눌렀을 때)
			if(cmd.equals("/list.hotel")) {
				//호텔 정보와 이미지경로값 받아오기
//				List<HotelDTO> hotelList = dao.selectHotel();
//				List<String> hotelImgList = dao.selectHotelImg();
//				request.setAttribute("hotelList", hotelList);
//				request.setAttribute("hotelImgList", hotelImgList);
				request.getRequestDispatcher("/views/hotel/hotelList.jsp").forward(request, response);
			
			//정렬(이름)
			}else if(cmd.equals("/listOrderByName.hotel")) {
				String keyword = request.getParameter("Keyword");
				List<HotelDTO> hotelListName = dao.searchName(keyword);
				List<String> hotelImgListName = dao.searchNameHotelImg(keyword);
				request.setAttribute("hotelListName", hotelListName);
				request.setAttribute("hotelImgListName", hotelImgListName);
				request.getRequestDispatcher("/hotel/list.hotel").forward(request, response);
			
			//정렬(위치)
			}else if(cmd.equals("/listOrderByLocation.hotel")) {
				String keyword = request.getParameter("Keyword");
				List<HotelDTO> hotelListLocation = dao.searchName(keyword);
				List<String> hotelImgListLocation = dao.searchNameHotelImg(keyword);
				request.setAttribute("hotelListLocation", hotelListLocation);
				request.setAttribute("hotelImgListLocation", hotelImgListLocation);
				request.getRequestDispatcher("/hotel/list.hotel").forward(request, response);
			}
//			//상품 페이지(예약과 QnA, 리뷰 등을 볼 수 있는 페이지)
//			}else if(cmd.equals("/goods.hotel")) {
//				//호텔-룸-이미지정보
//				String name = request.getParameter("name");
//				//dao.selectByName(name);
//			//상품 페이지 탭(상품설명_방이미지 뽑아오기)-ajax
//			}else if(cmd.equals("/desc.hotel")) {
//				//이하동문
//				String name = request.getParameter("name");
//				dao.selectRoomImg(name);
//				
//			//상품 페이지 탭(리뷰)-ajax
//			}else if(cmd.equals("/review.hotel")) {
//				
//			//상품 페이지 탭(QnA)-ajax
//			}else if(cmd.equals("/qna.hotel")) {
//				
//			//상품 페이지 탭(정보)-ajax	
//			}else if(cmd.equals("/info.hotel")) {
//				String name = request.getParameter("name");
//				dao.selectByName(name);
//			//상품 페이지 예약
//			}else if(cmd.equals("/reser.hotel")) {
//				
//			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
