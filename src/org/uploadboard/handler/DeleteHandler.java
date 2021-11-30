package org.uploadboard.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uploadboard.model.dao.BoardDao;
import org.uploadboard.model.service.DeleteService;



public class DeleteHandler implements CommandHandler {
	
	private BoardDao dao = BoardDao.getInstance();
	private DeleteService deleteService = new DeleteService(dao);
	
	@Override
	public String handlerAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String fileName = deleteService.getFileName(num);
		request.setAttribute("num", num);
		request.setAttribute("fileName", fileName);
		return "/WEB-INF/views/delete.jsp";
	}

}
