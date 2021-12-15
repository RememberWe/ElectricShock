package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QnADAO;
import dto.QnADTO;



@WebServlet("*.qna")
public class QnaController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnADAO dao = QnADAO.getInstance();
		request.setCharacterEncoding("utf8");

		String uri = request.getRequestURI();
		System.out.println("�궗�슜�옄媛� �썝�븯�뒗 二쇱냼 : " + uri);

		String ctxPath = request.getContextPath();
		System.out.println("�봽濡쒖젥�듃 紐� : " + ctxPath);

		String cmd = uri.substring(ctxPath.length());
		System.out.println("�궗�슜�옄媛� �썝�븯�뒗 湲곕뒫 : " + cmd);
		
		try {
			if(cmd.equals("/list.qna")) {
				System.out.println("여기옴ㅎ");
				List<QnADTO> dto = dao.selectAll();
				request.setAttribute("qna_List", dto); 
				response.sendRedirect("/qna.jsp");
				response.sendRedirect("/qnaList.jsp");
				
			}else if(cmd.equals("/write.qna")) {
				
				HttpSession session = request.getSession();
				String hotelId = (String)request.getSession().getAttribute("hotelId");
				String userId = request.getParameter("userId");
				String inquiryStat = request.getParameter("inquiryStat");
				String inquiryContent = request.getParameter("inquiryContent");
				Date inquiryCreated = Date.valueOf(request.getParameter("inquiryCreated"));
				int inquiry = Integer.parseInt(request.getParameter("inquiry"));
				
				System.out.println(hotelId);
				System.out.println(userId);
				System.out.println(inquiryStat);

				int result = dao.insert(new QnADTO(inquiry,hotelId,userId,inquiryStat,inquiryContent,inquiryCreated));
				request.getRequestDispatcher("/list.qna").forward(request, response);
				if(result > 0) {
					System.out.println("�옉�꽦 �셿猷�");
					response.sendRedirect("/qnaList.qna"); // .board濡� �븞蹂대궡怨� �럹�씠吏�濡� 蹂대궡�룄 湲��씠 �삱�씪�삤�뒗吏� �솗�씤�빐�빞�맖 = �솗�씤寃곌낵 .jsp濡� 諛붾줈蹂대궡硫� �븞�삱�씪�샂
				}
			}else if(cmd.equals("/delete.qna")) {
				int inquiry_seq = Integer.parseInt(request.getParameter("inquiry_seq"));
				int result = dao.delete(inquiry_seq);
				response.sendRedirect("/list.qna");

			}else if(cmd.equals("/modify.qna")) {
				int inquiry_seq = Integer.parseInt(request.getParameter("inquiry_seq"));
				String inquiryContent = request.getParameter("inquiryContent");
				
				int result = dao.modify(inquiry_seq,inquiryContent);
				
				response.sendRedirect("/list.qna?inquiry_seq="+inquiry_seq);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
