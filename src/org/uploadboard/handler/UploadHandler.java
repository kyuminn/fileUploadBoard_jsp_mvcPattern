package org.uploadboard.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uploadboard.model.dao.BoardDao;
import org.uploadboard.model.service.UploadService;

public class UploadHandler implements CommandHandler {


	@Override
	public String handlerAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/WEB-INF/views/uploadForm.jsp";
	}

}
