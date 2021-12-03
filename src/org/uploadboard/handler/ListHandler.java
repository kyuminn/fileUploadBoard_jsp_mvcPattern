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
import org.uploadboard.model.vo.Paging;

public class ListHandler implements CommandHandler{
	private BoardDao dao = BoardDao.getInstance();
	private ListService listService = new ListService(dao);

	@Override
	public String handlerAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNum = 0;
		
		if (request.getParameter("pageNum")==null) {
			pageNum = 1;
		}else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		Paging page = new Paging();
		page.getPageBlock(pageNum); // 시작 페이지 번호 , 끝 페이지 번호  설정
		page.pagedArticleList(pageNum); // 현재 페이지의 startRowNum 과 lastRowNum 설정
		
		List<BoardVo> ls  = listService.list(page);
		if (ls.isEmpty()) {
			request.setAttribute("ls", null);
		}else {
			request.setAttribute("ls", ls);
			request.setAttribute("page", page);
		}
		
		if (page.getStartPageNum() > page.getPageBlock()) {
			request.setAttribute("firstBlock", true); // 이전 버튼 활성화
		}
		if (page.getLastPageNum() < page.getTotalPageCount()) {
			request.setAttribute("lastBlock", true); // 다음 버튼 활성화
		}
		
		return "/WEB-INF/views/list.jsp";
	}

}
