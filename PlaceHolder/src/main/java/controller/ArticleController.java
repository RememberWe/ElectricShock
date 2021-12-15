package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticleDAO;
import dto.ArticleDTO;
import statics.Statics;

@WebServlet("*.article")
public class ArticleController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//기본세팅
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=UTF-8");
		String requestURI = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String cmd = requestURI.substring(ctxPath.length());
		System.out.println(cmd); //경로 잘 들어오나 확인용
		
		//미리세팅
		ArticleDAO adao = ArticleDAO.getInstance();
		String userId = (String)request.getSession().getAttribute("loginId");
		
		try {
			//1.게시글 작성 창으로 이동
			if(cmd.equals("/writeForm.article")) {
				//jsp에 따라 수정
				response.sendRedirect("/view/article/write.jsp");
			
			//2.게시글 작성 버튼(작성 insert)
			}else if(cmd.equals("/write.article")) {
				String postTitle = request.getParameter("postTitle");
				String postContent = request.getParameter("postContent");
				adao.insertArticle(new ArticleDTO(0,userId,postTitle,postContent,null,null));
				//jsp에 따라 수정
				response.sendRedirect("/views/article/list.jsp");
				
			//3.게시글 수정 버튼(수정 update)
			}else if(cmd.equals("/modify.article")) {
				int postId = Integer.parseInt(request.getParameter("postId"));
				String postTitle = request.getParameter("postTitle");
				String postContent = request.getParameter("postContent");
				adao.modifyArticle(postId, postTitle, postContent);
				//jsp 나오면 변경
				response.sendRedirect("/views/article/writeFrom.jsp");
				
			//4.게시글 삭제 버튼(삭제 delete)
			}else if(cmd.equals("/delete.article")) {
				int postId = Integer.parseInt(request.getParameter("postId"));
				adao.deleteArticle(postId);
				//jsp에 따라 수정
				response.sendRedirect("/views/article/list.jsp");
				
			//5.게시글 조회(전체 글)
			}else if(cmd.equals("/list.article")) {
				String cpage = request.getParameter("cpage");
				if(cpage == null) {cpage = "1";}
				
				int currentPage = Integer.parseInt(cpage);
				int pageTotalCount = adao.getPageTotalCount();
				
				if(currentPage < 1) {currentPage = 1;}
				if(currentPage > pageTotalCount) {currentPage = pageTotalCount;}
				
				int start = currentPage * Statics.RECORD_COUNT_PER_PAGE - (Statics.RECORD_COUNT_PER_PAGE-1);
				int end = currentPage * Statics.RECORD_COUNT_PER_PAGE;
				
				List<ArticleDTO> list = adao.selectArticleB(start, end);
				String navi = adao.getPageNavi(currentPage);
				request.setAttribute("list", list);
				request.setAttribute("navi", navi);
				request.getRequestDispatcher("/views/articles/list.jsp").forward(request, response);
				
			//6.게시글 조회(유저 글)
			}else if(cmd.equals("/listUser.article")) {
				String cpage = request.getParameter("cpage");
				if(cpage == null) {cpage = "1";}
				
				int currentPage = Integer.parseInt(cpage);
				int pageTotalCount = adao.getPageTotalCountU(userId);
				
				if(currentPage < 1) {currentPage = 1;}
				if(currentPage > pageTotalCount) {currentPage = pageTotalCount;}
				
				int start = currentPage * Statics.RECORD_COUNT_PER_PAGE - (Statics.RECORD_COUNT_PER_PAGE-1);
				int end = currentPage * Statics.RECORD_COUNT_PER_PAGE;
				
				List<ArticleDTO> list = adao.selectUserArticleB(start, end, userId);
				String navi = adao.getPageNaviU(currentPage, userId);
				request.setAttribute("list", list);
				request.setAttribute("navi", navi);
				request.getRequestDispatcher("/views/articles/myArticle.jsp").forward(request, response);
			
			//7.게시글 검색(유저 아이디/제목)
			}else if(cmd.equals("/search.article")) {
				//검색옵션과 키워드 받아오기
				String option = request.getParameter("option");
				String keyword = request.getParameter("keyword");
				
				//검색옵션에 따라 분기점
				if(option.equals("작성자")) {
					String cpage = request.getParameter("cpage");
					if(cpage == null) {cpage = "1";}
					
					int currentPage = Integer.parseInt(cpage);
					int pageTotalCount = adao.getPageTotalCountU(keyword);
					
					if(currentPage < 1) {currentPage = 1;}
					if(currentPage > pageTotalCount) {currentPage = pageTotalCount;}
					
					int start = currentPage * Statics.RECORD_COUNT_PER_PAGE - (Statics.RECORD_COUNT_PER_PAGE-1);
					int end = currentPage * Statics.RECORD_COUNT_PER_PAGE;
					
					List<ArticleDTO> list = adao.selectUserArticleB(start, end, keyword);
					String navi = adao.getPageNaviU(currentPage, keyword);
					request.setAttribute("list", list);
					request.setAttribute("navi", navi);
					request.getRequestDispatcher("/views/articles/myArticle.jsp").forward(request, response);
					
				}else if(option.equals("제목")) {
					String cpage = request.getParameter("cpage");
					if(cpage == null) {cpage = "1";}
					
					int currentPage = Integer.parseInt(cpage);
					int pageTotalCount = adao.getPageTotalCountK(keyword);
					
					if(currentPage < 1) {currentPage = 1;}
					if(currentPage > pageTotalCount) {currentPage = pageTotalCount;}
					
					int start = currentPage * Statics.RECORD_COUNT_PER_PAGE - (Statics.RECORD_COUNT_PER_PAGE-1);
					int end = currentPage * Statics.RECORD_COUNT_PER_PAGE;
					
					List<ArticleDTO> list = adao.selectTitleArticleB(start, end, keyword);
					String navi = adao.getPageNaviK(currentPage, keyword);
					request.setAttribute("list", list);
					request.setAttribute("navi", navi);
					request.getRequestDispatcher("/views/articles/myArticle.jsp").forward(request, response);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
