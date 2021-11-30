package org.uploadboard.handler;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uploadboard.model.dao.BoardDao;
import org.uploadboard.model.service.UpdateService;
import org.uploadboard.model.vo.BoardVo;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdateProcHandler implements CommandHandler{
	private BoardDao dao = BoardDao.getInstance();
	private UpdateService updateservice = new UpdateService(dao);
	
	@Override
	public String handlerAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		int uploadFileSizeLimit = 5 * 1024 *1024;
		String encType= "UTF-8";
		String uploadFilePath = "C:/upload";
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(request,uploadFilePath,uploadFileSizeLimit,encType, new DefaultFileRenamePolicy());
		}catch(Exception e) {
			e.printStackTrace();
		}
		String password = multi.getParameter("password");
		String description = multi.getParameter("description");
		String fileName = multi.getFilesystemName("updateFile");
		String uploadedFileName = multi.getParameter("uploadedFile");
		
		// 기존 파일 삭제
		File file = new File("/upload/"+ uploadedFileName);
		if(file.exists()) {
			file.delete();
		}else {
			System.out.println("파일이 존재하지 않습니다");
		}
		
		BoardVo vo = new BoardVo();
		vo.setRegdate(new Timestamp(System.currentTimeMillis()));
		vo.setFilename(fileName);
		vo.setDescription(description);
		vo.setNum(num);
		vo.setPassword(password);
		
		int result = updateservice.update(vo);
		
		request.setAttribute("result", result);
		
		return "/WEB-INF/views/updateProc.jsp";
	}

}
