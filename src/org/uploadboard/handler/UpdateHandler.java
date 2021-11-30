package org.uploadboard.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uploadboard.model.dao.BoardDao;
import org.uploadboard.model.service.UpdateService;
import org.uploadboard.model.vo.BoardVo;

public class UpdateHandler implements CommandHandler{
	
	private BoardDao dao = BoardDao.getInstance();
	private UpdateService updateService = new UpdateService(dao);
	
	@Override
	public String handlerAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardVo vo = updateService.selectOne(num);
		request.setAttribute("vo", vo);
		return "/WEB-INF/views/updateForm.jsp";
	}

}
