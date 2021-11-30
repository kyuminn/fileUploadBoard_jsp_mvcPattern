package org.uploadboard.handler;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uploadboard.model.dao.BoardDao;
import org.uploadboard.model.service.ContentService;
import org.uploadboard.model.vo.BoardVo;

public class ContentHandler implements CommandHandler{
	private BoardDao dao = BoardDao.getInstance();
	private ContentService contentService = new ContentService(dao);
	
	
	@Override
	public String handlerAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardVo vo = contentService.selectOne(num);
		request.setAttribute("vo", vo);
//		String savePath ="/WEB-INF/upload";
//		String uploadFilePath = request.getSession().getServletContext().getRealPath(savePath);
		String uploadFilePath = "C:/upload";
		File uploadDir = null;
		
		try {
			uploadDir = new File(uploadFilePath);
			String[] fileNames = uploadDir.list();
			request.setAttribute("fileNames", fileNames);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "/WEB-INF/views/content.jsp";
	}

}
