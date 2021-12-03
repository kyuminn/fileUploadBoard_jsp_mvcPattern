package org.uploadboard.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainHandler implements CommandHandler {

	@Override
	public String handlerAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// views (jsp 파일) 에 사용자에게 보여줄 정보 있으면
		// request.setAttribute 이용
		return "/WEB-INF/views/main.jsp";
	}

}
