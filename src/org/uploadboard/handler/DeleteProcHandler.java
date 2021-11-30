package org.uploadboard.handler;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uploadboard.model.dao.BoardDao;
import org.uploadboard.model.service.DeleteService;

public class DeleteProcHandler implements CommandHandler {

	private BoardDao dao = BoardDao.getInstance();
	private DeleteService deleteService = new DeleteService(dao);
	
	@Override
	public String handlerAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String password = request.getParameter("password");
		int result = deleteService.delete(num, password);
		
		// 입력한 비밀번호가 일치하면 , 업로드된 파일도 함꼐 삭제함.
		String fileName = request.getParameter("fileName");
		File file = null;
		if (result ==1) {
			file =new File("/upload/"+fileName);
			if (file.exists()) {
				file.delete();
			}else {
				System.out.println("파일이 존재하지 않습니다.");
			}
		}
		request.setAttribute("result",result);
		
		return "/WEB-INF/views/deleteProc.jsp";
	}

}
