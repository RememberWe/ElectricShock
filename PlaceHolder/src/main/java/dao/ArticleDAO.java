package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.ArticleDTO;
import statics.Statics;

public class ArticleDAO {
	//인스턴스와 커넥션
	private static ArticleDAO instance = null;
	public static ArticleDAO getInstance() {
		if(instance == null) {
			instance = new ArticleDAO();
		}return instance;
	}

	private ArticleDAO() {}

	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	//게시글 조회(전체 조회, 범위)

	public List<ArticleDTO> selectArticleB(int start, int end) throws Exception{
		String sql = "select * from (select article.*, row_number() over(order by postId desc)rn from article) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			try(ResultSet rs = pstat.executeQuery();){
				List<ArticleDTO> list= new ArrayList<>();
				while(rs.next()) {
					ArticleDTO dto = new ArticleDTO();
					dto.setPostId(rs.getInt("postId"));
					dto.setUserID(rs.getString("userId"));
					dto.setPostTitle(rs.getString("postTitle"));
					dto.setPostContent(rs.getString("postContent"));
					dto.setPostCreated(rs.getDate("postCreated"));
					dto.setPostModified(rs.getDate("postModified"));
					list.add(dto);
				}return list;
			}
		}
	}
	
	//내가 쓴 글 보기(유저 아이디로 검색, 범위)
	public List<ArticleDTO> selectUserArticleB(int start, int end, String keyword) throws Exception{
		String sql = "select * from (select article.*, row_number() over(order by postId desc)rn from article where userId = ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			pstat.setString(3, keyword);
			try(ResultSet rs = pstat.executeQuery();){
				List<ArticleDTO> list= new ArrayList<>();
				while(rs.next()) {
					ArticleDTO dto = new ArticleDTO();
					dto.setPostId(rs.getInt("postId"));
					dto.setUserID(rs.getString("userId"));
					dto.setPostTitle(rs.getString("postTitle"));
					dto.setPostContent(rs.getString("postContent"));
					dto.setPostCreated(rs.getDate("postCreated"));
					dto.setPostModified(rs.getDate("postModified"));
					list.add(dto);
				}return list;
			}
		}
	}

	//글 보기(제목로 검색, 범위)
		public List<ArticleDTO> selectTitleArticleB(int start, int end, String keyword) throws Exception{
			String sql = "select * from (select article.*, row_number() over(order by postId desc)rn from article where postTitle like ?) where rn between ? and ?";
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setInt(1, start);
				pstat.setInt(2, end);
				pstat.setString(3, "%"+keyword+"%");
				try(ResultSet rs = pstat.executeQuery();){
					List<ArticleDTO> list= new ArrayList<>();
					while(rs.next()) {
						ArticleDTO dto = new ArticleDTO();
						dto.setPostId(rs.getInt("postId"));
						dto.setUserID(rs.getString("userId"));
						dto.setPostTitle(rs.getString("postTitle"));
						dto.setPostContent(rs.getString("postContent"));
						dto.setPostCreated(rs.getDate("postCreated"));
						dto.setPostModified(rs.getDate("postModified"));
						list.add(dto);
					}return list;
				}
			}
		}
		
	//게시글 작성
	public int insertArticle(ArticleDTO dto)throws Exception{
		String sql = "insert into article values(postId.nextval,?,?,?,default,default)";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getUserID());
			pstat.setString(2, dto.getPostTitle());
			pstat.setString(3, dto.getPostContent());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}

	//게시글 수정
	public int modifyArticle(int postId, String postTitle, String postContents) throws Exception{
		String sql = "update articel set postId = ?, postTitle = ?, postContents = ?, postModified = sysdate where postId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, postTitle);
			pstat.setString(2, postContents);
			pstat.setInt(3, postId);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}

	//게시글 삭제
	public int deleteArticle(int postId) throws Exception{
		String sql = "delete from article where postId = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, postId);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}

	//페이징(게시판)
	//전체 레코드 개수
	public int getRecordCount() throws Exception{
		String sql = "select count(*) from article";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			rs.next();
			return rs.getInt(1);
		}
	}
	
	//전체 페이지 개수
	public int getPageTotalCount() throws Exception{
		int recordTotalCount = this.getPageTotalCount();
		int pageTotalCount = 0;
		if(recordTotalCount % Statics.RECORD_COUNT_PER_PAGE == 0) {
			pageTotalCount = recordTotalCount / Statics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount / Statics.RECORD_COUNT_PER_PAGE + 1;
		}return pageTotalCount;
	}
	
	//내비게이션
	public String getPageNavi(int currentPage) throws Exception{
		int recordTotalCount = this.getPageTotalCount();
		int pageTotalCount = 0;
		if(recordTotalCount % Statics.RECORD_COUNT_PER_PAGE == 0) {
			pageTotalCount = recordTotalCount / Statics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount / Statics.RECORD_COUNT_PER_PAGE + 1;
		}
		
		int startNavi = (currentPage-1) / Statics.NAVI_COUNT_PER_PAGE * Statics.NAVI_COUNT_PER_PAGE + 1;
		int endNavi = startNavi + Statics.NAVI_COUNT_PER_PAGE - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {needPrev = false;}
		if(endNavi == pageTotalCount) {needNext = false;}
		
		String pageNavi = "";
		if(needPrev) {pageNavi += "<a href='/list.article?cpage="+(startNavi-1)+"'><</a> ";}
		for(int i = startNavi;i <= endNavi;i++) {
			pageNavi+="<a href='/list.board?cpage="+i+"'>" + i+"</a> ";
		}
		if(needNext) {pageNavi += "<a href='/list.article?cpage="+(endNavi+1)+"'>></a>";}
		return pageNavi;
	}
	
	
	//페이징(유저)
		//전체 레코드 개수
		public int getRecordCountU(String userId) throws Exception{
			String sql = "select count(*) from article where userId = ?";
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setString(1, userId);
				try(ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);
			}
		}
		}
		
		//전체 페이지 개수
		public int getPageTotalCountU(String userId) throws Exception{
			int recordTotalCount = this.getPageTotalCountU(userId);
			int pageTotalCount = 0;
			if(recordTotalCount % Statics.RECORD_COUNT_PER_PAGE == 0) {
				pageTotalCount = recordTotalCount / Statics.RECORD_COUNT_PER_PAGE;
			}else {
				pageTotalCount = recordTotalCount / Statics.RECORD_COUNT_PER_PAGE + 1;
			}return pageTotalCount;
		}
	
		//내비게이션
		public String getPageNaviU(int currentPage, String userId) throws Exception{
			int recordTotalCount = this.getPageTotalCountU(userId);
			int pageTotalCount = 0;
			if(recordTotalCount % Statics.RECORD_COUNT_PER_PAGE == 0) {
				pageTotalCount = recordTotalCount / Statics.RECORD_COUNT_PER_PAGE;
			}else {
				pageTotalCount = recordTotalCount / Statics.RECORD_COUNT_PER_PAGE + 1;
			}
			
			int startNavi = (currentPage-1) / Statics.NAVI_COUNT_PER_PAGE * Statics.NAVI_COUNT_PER_PAGE + 1;
			int endNavi = startNavi + Statics.NAVI_COUNT_PER_PAGE - 1;
			
			if(endNavi > pageTotalCount) {
				endNavi = pageTotalCount;
			}
			boolean needPrev = true;
			boolean needNext = true;
			
			if(startNavi == 1) {needPrev = false;}
			if(endNavi == pageTotalCount) {needNext = false;}
			
			String pageNavi = "";
			if(needPrev) {pageNavi += "<a href='/list.article?cpage="+(startNavi-1)+"'><</a> ";}
			for(int i = startNavi;i <= endNavi;i++) {
				pageNavi+="<a href='/list.board?cpage="+i+"'>" + i+"</a> ";
			}
			if(needNext) {pageNavi += "<a href='/list.article?cpage="+(endNavi+1)+"'>></a>";}
			return pageNavi;
		}
		
		
		//페이징(키워드)
			//전체 레코드 개수
				public int getRecordCountK(String keyword) throws Exception{
					String sql = "select count(*) from article where postTitle like ?";
					try(Connection con = this.getConnection();
							PreparedStatement pstat = con.prepareStatement(sql);){
						pstat.setString(1, "%"+keyword+"%");
						try(ResultSet rs = pstat.executeQuery();){
						rs.next();
						return rs.getInt(1);
					}
				}
				}
				
				//전체 페이지 개수
				public int getPageTotalCountK(String keyword) throws Exception{
					int recordTotalCount = this.getPageTotalCountK(keyword);
					int pageTotalCount = 0;
					if(recordTotalCount % Statics.RECORD_COUNT_PER_PAGE == 0) {
						pageTotalCount = recordTotalCount / Statics.RECORD_COUNT_PER_PAGE;
					}else {
						pageTotalCount = recordTotalCount / Statics.RECORD_COUNT_PER_PAGE + 1;
					}return pageTotalCount;
				}
				
				//내비게이션
				public String getPageNaviK(int currentPage, String keyword) throws Exception{
					int recordTotalCount = this.getPageTotalCountK(keyword);
					int pageTotalCount = 0;
					if(recordTotalCount % Statics.RECORD_COUNT_PER_PAGE == 0) {
						pageTotalCount = recordTotalCount / Statics.RECORD_COUNT_PER_PAGE;
					}else {
						pageTotalCount = recordTotalCount / Statics.RECORD_COUNT_PER_PAGE + 1;
					}
					
					int startNavi = (currentPage-1) / Statics.NAVI_COUNT_PER_PAGE * Statics.NAVI_COUNT_PER_PAGE + 1;
					int endNavi = startNavi + Statics.NAVI_COUNT_PER_PAGE - 1;
					
					if(endNavi > pageTotalCount) {
						endNavi = pageTotalCount;
					}
					boolean needPrev = true;
					boolean needNext = true;
					
					if(startNavi == 1) {needPrev = false;}
					if(endNavi == pageTotalCount) {needNext = false;}
					
					String pageNavi = "";
					if(needPrev) {pageNavi += "<a href='/list.article?cpage="+(startNavi-1)+"'><</a> ";}
					for(int i = startNavi;i <= endNavi;i++) {
						pageNavi+="<a href='/list.board?cpage="+i+"'>" + i+"</a> ";
					}
					if(needNext) {pageNavi += "<a href='/list.article?cpage="+(endNavi+1)+"'>></a>";}
					return pageNavi;
				}

}
