package org.uploadboard.handler;

import java.io.IOException;
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
	@Override
	public String handlerAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<BoardVo> ls = listService.list();
		request.setAttribute("ls", ls);
		return "/WEB-INF/views/list.jsp";
	}

}
