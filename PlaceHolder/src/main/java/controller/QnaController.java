package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.HotelDAO;
import dao.QnADAO;
import dto.ArticleDTO;
import dto.HotelLikeImgDTO;
import dto.QnADTO;
import tool.DateChangerToSql;

@WebServlet("*.qna")
public class QnaController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Import Qnqdao
		QnADAO qdao = QnADAO.getInstance();
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=UTF-8");
		HotelDAO hdao = HotelDAO.getInstance();
		// 경로값 받아오기
		String cmd = request.getServletPath();

		// Import SimpleDateFormat
		SimpleDateFormat sdf = new SimpleDateFormat();

		// userId 세션 값으로 받아두고 활용
		String loginId = (String) request.getSession().getAttribute("loginId");
		String hotelId = request.getParameter("hotelId");

		try {
			// 1.Q&A 작성 창으로 이동
			if (cmd.equals("/writeForm.qna")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
				List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
				request.setAttribute("hotelListS", hotelListS);

				// jsp에 따라 수정
				response.sendRedirect("/views/inquiry/inquiryWrite.jsp");

			} // 2. 고객센터 게시판에서 Q&A 작성 버튼(작성 insert)
			else if(cmd.equals("/write.qna")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
				List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
				request.setAttribute("hotelListS", hotelListS);

				// loginId, 예약코드, 유저아이디, 호텔 아이디, qna내용, qna 작성날짜 받아오기
				String userId = loginId; // session 값
				String inquiryContent = request.getParameter("inquiryContent");

				// QnADTO 객체에 파라미터 값 담기

				System.out.println(userId +":"+ inquiryContent +":"+ hotelId);

				qdao.insert(new QnADTO(0, null, userId, "답변 대기", inquiryContent, null));

				// jsp에 따라 수정
				response.sendRedirect("/inquiryList.qna");
			}

			// 2-2. hotelDetail에서 Q&A 작성 버튼(작성 insert)
			else if (cmd.equals("/writeQuick.qna")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
				List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
				request.setAttribute("hotelListS", hotelListS);

				// loginId, 예약코드, 유저아이디, 호텔 아이디, qna내용, qna 작성날짜 받아오기
				String userId = loginId; // session 값
				String inquiryContent = request.getParameter("inquiryContent");

				// QnADTO 객체에 파라미터 값 담기
				System.out.println(userId +":"+ inquiryContent +":"+ hotelId);

				qdao.insert(new QnADTO(0, null, userId, "답변 대기", inquiryContent, null));

				response.sendRedirect("/views/hotel/hotelDetail.jsp");
				// jsp에 따라 수정

				// 3.Q&A 수정 버튼(수정 update)
			} else if (cmd.equals("/modify.qna")) {
				// 빠른예약용 모든호텔 정보 보내주기
				List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
				request.setAttribute("hotelListS", hotelListS);

				String userId = loginId; // session 값
				int inquiry = Integer.parseInt(request.getParameter("inquiry"));
				//String hotelId = ;
				String inquiryStat = "답변 대기";
				String inquiryContent = request.getParameter("inquiryContent");
				System.out.println(inquiry);
				qdao.modify(new QnADTO(inquiry, null, userId, inquiryStat, inquiryContent, null));
				// jsp 나오면 변경
				response.sendRedirect("/detail.qna?inquiry=" + inquiry);

				// 4.Q&A 삭제 버튼(삭제 delete)
			} else if (cmd.equals("/delete.qna")) {
				int inquiry = Integer.parseInt(request.getParameter("inquiry"));
				System.out.println("inquiry : " + inquiry);
				System.out.println(inquiry);
				qdao.delete(inquiry);

				// jsp에 따라 수정
				response.sendRedirect("/inquiryList.qna");

				// 5.Q&A 조회(전체 글)
			} else if (cmd.equals("/inquiryList.qna")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
				List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
				request.setAttribute("hotelListS", hotelListS);

				int start = 1;
				int end = start + 9;
//				List<ArticleDTO> list = qdao.selectArticleB(start, end);
				List<QnADTO> list = qdao.qnaList();
				request.setAttribute("list", list);
				request.getRequestDispatcher("/views/inquiry/inquiryList.jsp").forward(request, response);
			}
//				// 5-1. Q&A 더보기
//			} else if (cmd.equals("/listPlus.qna")) {
//				// 더보기 버튼의 value값을 받아옴
//				System.out.println("정상출력");
//				int start = Integer.parseInt(request.getParameter("btn"));
//				int end = start + 9;
//				if (end > qdao.getHotelCount()) {
//					end = qdao.getHotelCount();
//				}
//				List<ArticleDTO> articleList = qdao.selectArticleB(start, end);
//				Gson g = new Gson();
//				String result = g.toJson(articleList);
//				response.getWriter().append(result);

				// 6.Q&A 조회(유저 글)
			 else if (cmd.equals("/listUser.qna")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
				List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
				request.setAttribute("hotelListS", hotelListS);

				// 현우 추가
				List<HotelLikeImgDTO> hotelListAll = hdao.selectHotel();
				request.setAttribute("hotelListAll", hotelListAll); // 현우추가
				
				List<QnADTO> list = qdao.selectQnA(loginId);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/views/member/mypage.jsp").forward(request, response);

				// 7.Q&A 검색(유저 아이디/제목)
			} else if (cmd.equals("/search.qna")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
				List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
				request.setAttribute("hotelListS", hotelListS);

				// 검색옵션과 키워드 받아오기
				String option = request.getParameter("option");
				String keyword = request.getParameter("Keyword");
				System.out.println(option + " : " + keyword);
				// 검색옵션에 따라 분기점
				if (option.equals("유저ID")) {
					List<QnADTO> list = qdao.selectQnA(keyword);
					request.setAttribute("list", list);
					request.getRequestDispatcher("/views/inquiry/inquiryList.jsp").forward(request, response);
				} 
				else if (option.equals("호텔 아이디")) {
					List<QnADTO> list = qdao.selectQnAByHotelId(keyword);
					request.setAttribute("list", list);
					request.getRequestDispatcher("/views/inquiry/articleList.jsp").forward(request, response);
				}
			}
				// 8. Q&A 상세보기
			 else if (cmd.equals("/detail.qna")) {
				// 빠른예약 기능 모든 호텔 정보 넣어주기 진규추가
				List<HotelLikeImgDTO> hotelListS = hdao.selectHotel();
				request.setAttribute("hotelListS", hotelListS);

				int inquiry = Integer.parseInt(request.getParameter("inquiry"));
				QnADTO inquiryList = qdao.selectBySeq(inquiry);
				request.setAttribute("inquiryList", inquiryList);
				request.getRequestDispatcher("/views/inquiry/inquiryDetail.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}