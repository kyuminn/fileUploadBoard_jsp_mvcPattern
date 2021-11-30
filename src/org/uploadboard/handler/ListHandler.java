package org.uploadboard.handler;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uploadboard.model.dao.BoardDao;
import org.uploadboard.model.service.ListService;
import org.uploadboard.model.vo.BoardVo;

public class ListHandler implements CommandHandler{
	private BoardDao dao = BoardDao.getInstance();
	private ListService listService = new ListService(dao);
//	List<BoardVo> ls = listService.list();
//	request.setAttribute("ls", ls);
	@Override
	public String handlerAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNum= request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 3; // 한 페이지 당 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		
		// 페이지의 시작글 번호
		int startRow = (currentPage-1)*pageSize+1;
		// 페이지의 마지막 글 번호
		int endRow = currentPage * pageSize;
		// 전체 글 개수
		int count = listService.getArticleCount();
		List<BoardVo> ls = null;
		if (count > 0) {
			ls = listService.list(startRow, endRow);
		}else {
			ls = Collections.emptyList();
		}
		// number 사용 안함
		
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("ls", ls);
		
		

		return "/WEB-INF/views/list.jsp";
	}

}
