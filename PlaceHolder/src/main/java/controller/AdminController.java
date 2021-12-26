package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticleDAO;
import dao.HotelDAO;
import dao.QnADAO;
import dao.UserDAO;
import dto.ArticleDTO;
import dto.HotelDTO;
import dto.HotelLikeImgDTO;
import dto.QnADTO;
import dto.UserDTO;

@WebServlet("*.admin")
public class AdminController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 기본세팅
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=UTF-8");
		String requestURI = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String cmd = requestURI.substring(ctxPath.length());
		System.out.println(cmd); // 경로 잘 들어오나 확인용

		// 미리세팅
		UserDAO udao = UserDAO.getInstance();
		HotelDAO hdao = HotelDAO.getInstance();
		QnADAO qdao = QnADAO.getInstance();
		ArticleDAO adao = ArticleDAO.getInstance();

		try {
			// 1.유저관리(유저 전체 조회)
			if (cmd.equals("/user.admin")) {
				List<UserDTO> userList = udao.userList();
				request.setAttribute("userList", userList);
				request.getRequestDispatcher("/views/admin/userList.jsp").forward(request, response);

				// 1-1.유저관리페이지에서 검색(유저아이디로 검색)
			} else if (cmd.equals("/userSearch.admin")) {
				String keyword = request.getParameter("Keyword");
				List<UserDTO> userList = udao.searchList(keyword);
				request.setAttribute("userList", userList);
				request.getRequestDispatcher("/views/admin/userList.jsp").forward(request, response);

				// 1-2.유저관리페이지에서 삭제(유저아이디로 삭제)
			} else if (cmd.equals("/userDelete.admin")) {
				String userId = request.getParameter("userId");
				udao.delete(userId);
				response.sendRedirect("/user.admin");

				// 2.호텔 관리(호텔 전체 조회)
			} else if (cmd.equals("/hotel.admin")) {
				List<HotelLikeImgDTO> hotelList = hdao.selectHotel();
				request.setAttribute("hotelList", hotelList);
				request.getRequestDispatcher("/views/admin/hotelList.jsp").forward(request, response);

				// 2-1.호텔관리페이지에서 검색(호텔 이름으로 검색)
			} else if (cmd.equals("/hotelSearch.admin")) {
				String keyword = request.getParameter("Keyword");
				List<HotelLikeImgDTO> hotelList = hdao.searchHotelName(keyword);
				request.setAttribute("hotelList", hotelList);
				request.getRequestDispatcher("/views/admin/hotelList.jsp").forward(request, response);

				// 2-2.큐앤에이관리페이지에서 삭제(유저아이디로 삭제)
	         } else if (cmd.equals("/qnaDelete.admin")) {
	            int inquiry = Integer.parseInt(request.getParameter("inquiry"));
	            qdao.delete(inquiry);
	            response.sendRedirect("/qna.admin");

				// 3.큐앤에이 관리(큐앤에이 전체 조회)
			} else if (cmd.equals("/qna.admin")) {
				List<QnADTO> qnaList = qdao.qnaList();
				request.setAttribute("qnaList", qnaList);
				request.getRequestDispatcher("/views/admin/qnaList.jsp").forward(request, response);

				// 3-1.큐앤에이관리페이지에서 검색(유저아이디로 검색)
			} else if (cmd.equals("/qnaSearch.admin")) {
				String keyword = request.getParameter("Keyword");
				System.out.println(keyword);
				List<QnADTO> qnaList = qdao.selectQnA(keyword);
				request.setAttribute("qnaList", qnaList);
				request.getRequestDispatcher("/views/admin/qnaList.jsp").forward(request, response);

				// 2-2.큐앤에이관리페이지에서 삭제(유저아이디로 삭제)
			} else if (cmd.equals("/qnaDelete.admin")) {
				String userId = request.getParameter("userId");
				hdao.deleteHotel(userId);
				response.sendRedirect("/qna.admin");

				// 4.아티클 관리(아티클 전체 조회)
			} else if (cmd.equals("/article.admin")) {
				List<ArticleDTO> articleList = adao.selectArticle();
				request.setAttribute("articleList", articleList);
				request.getRequestDispatcher("views/admin/articleList.jsp").forward(request, response);

				// 4-1.아티클관리페이지에서 검색(아티클 제목으로/유저로)
			} else if (cmd.equals("/articleSearch.admin")) {
				String option = request.getParameter("option");
				String keyword = request.getParameter("Keyword");
				System.out.println(option + " : " + keyword);
				if (option.equals("유저ID")) {
					List<ArticleDTO> articleList = adao.selectUserArticle(keyword);
					request.setAttribute("articleList", articleList);
					request.getRequestDispatcher("/views/admin/articleList.jsp").forward(request, response);

				} else if (option.equals("제목")) {
					List<ArticleDTO> articleList = adao.selectTitleArticle(keyword);
					request.setAttribute("articleList", articleList);
					request.getRequestDispatcher("/views/admin/articleList.jsp").forward(request, response);
				}

				// 4-2.아티클 삭제
			} else if (cmd.equals("/articleDelete.admin")) {
				int postId = Integer.parseInt(request.getParameter("postId"));
				adao.deleteArticle(postId);
				response.sendRedirect("/article.admin");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
