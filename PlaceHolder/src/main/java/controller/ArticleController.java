package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.ArticleDAO;
import dao.HotelDAO;
import dto.ArticleDTO;
import dto.HotelLikeImgDTO;

@WebServlet("*.article")
public class ArticleController extends HttpServlet {
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
		ArticleDAO adao = ArticleDAO.getInstance();
		HotelDAO hdao = HotelDAO.getInstance();
		String userId = (String) request.getSession().getAttribute("loginId");

		try {

			// 1.게시글 작성 창으로 이동
			if (cmd.equals("/writeForm.article")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
	            List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
	            request.setAttribute("hotelListS", hotelListS);
				
				// jsp에 따라 수정
				response.sendRedirect("/views/article/write.jsp");

				// 2.게시글 작성 버튼(작성 insert)
			} else if (cmd.equals("/write.article")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
	            List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
	            request.setAttribute("hotelListS", hotelListS);
				
				String postTitle = request.getParameter("postTitle");
				String postContent = request.getParameter("postContent");
				System.out.println(postTitle + " : " + userId + " : " + postContent);
				adao.insertArticle(new ArticleDTO(0, userId, postTitle, postContent, null, null));
				// jsp에 따라 수정
				response.sendRedirect("/articleList.article");

				// 3.게시글 수정 버튼(수정 update)
			} else if (cmd.equals("/modify.article")) {
				// 빠른예약용 모든호텔 정보 보내주기
				List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
	            request.setAttribute("hotelListS", hotelListS);
	            
				int postId = Integer.parseInt(request.getParameter("postId"));
				String postTitle = request.getParameter("postTitle");
				String postContent = request.getParameter("postContent");
				adao.modifyArticle(postId, postTitle, postContent);
				// jsp 나오면 변경
				response.sendRedirect("/detail.article?postId=" + postId);

				// 4.게시글 삭제 버튼(삭제 delete)
			} else if (cmd.equals("/delete.article")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
	            List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
	            request.setAttribute("hotelListS", hotelListS);
	            
				int postId = Integer.parseInt(request.getParameter("postId"));
				System.out.println(postId);
				adao.deleteArticle(postId);
				// jsp에 따라 수정
				response.sendRedirect("/articleList.article");

				// 5.게시글 조회(전체 글)
			} else if (cmd.equals("/articleList.article")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
	            List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
	            request.setAttribute("hotelListS", hotelListS);
				
				int start = 1;
				int end = start + 9;
				List<ArticleDTO> list = adao.selectArticleB(start, end);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/views/article/articleList.jsp").forward(request, response);

				// 5-1. 게시글 더보기
			} else if (cmd.equals("/listPlus.article")) {
				// 더보기 버튼의 value값을 받아옴
				System.out.println("정상출력");
				int start = Integer.parseInt(request.getParameter("btn"));
				int end = start + 9;
				if (end > adao.getHotelCount()) {
					end = adao.getHotelCount();
				}
				List<ArticleDTO> articleList = adao.selectArticleB(start, end);
				Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String result = g.toJson(articleList);
				response.getWriter().append(result);

				// 6.게시글 조회(유저 글)
			} else if (cmd.equals("/listUser.article")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
	            List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
	            request.setAttribute("hotelListS", hotelListS);
				
				List<ArticleDTO> list = adao.selectUserArticle(userId);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/views/article/myArticle.jsp").forward(request, response);

				// 7.게시글 검색(유저 아이디/제목)
			} else if (cmd.equals("/search.article")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
	            List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
	            request.setAttribute("hotelListS", hotelListS);
				
				// 검색옵션과 키워드 받아오기
				String option = request.getParameter("option");
				String keyword = request.getParameter("Keyword");
				System.out.println(option + " : " + keyword);
				// 검색옵션에 따라 분기점
				if (option.equals("유저ID")) {
					List<ArticleDTO> list = adao.selectUserArticle(keyword);
					request.setAttribute("list", list);
					request.getRequestDispatcher("/views/article/articleList.jsp").forward(request, response);

				} else if (option.equals("제목")) {
					List<ArticleDTO> list = adao.selectTitleArticle(keyword);
					request.setAttribute("list", list);
					request.getRequestDispatcher("/views/article/articleList.jsp").forward(request, response);
				}
			}

			// 7. ***** 현우 추가 : 마이페이지에서 유저 게시글 검색(메서드 수정)
			else if (cmd.equals("/viewMyArticle.article")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
	            List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
	            request.setAttribute("hotelListS", hotelListS);
				
	         // 현우 추가
				List<HotelLikeImgDTO> hotelListAll = hdao.selectHotel();
				request.setAttribute("hotelListAll", hotelListAll); // 현우추가
	            
				List<ArticleDTO> articleList = adao.selectUserArticle(userId);
				request.setAttribute("articleList", articleList);
				request.getRequestDispatcher("/views/member/mypage.jsp").forward(request, response);

				// 8. 게시글 상세보기
			} else if (cmd.equals("/detail.article")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
	            List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
	            request.setAttribute("hotelListS", hotelListS);
				
				int postId = Integer.parseInt(request.getParameter("postId"));
				ArticleDTO articleList = adao.selectBySeq(postId);
				request.setAttribute("articleList", articleList);
				request.getRequestDispatcher("/views/article/detail.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
