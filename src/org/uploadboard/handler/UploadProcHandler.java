package org.uploadboard.handler;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uploadboard.model.dao.BoardDao;
import org.uploadboard.model.service.UploadService;
import org.uploadboard.model.vo.BoardVo;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UploadProcHandler implements CommandHandler{
	
	private BoardDao dao = BoardDao.getInstance();
	private UploadService uploadService = new UploadService(dao);

	@Override
	public String handlerAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int uploadFileSizeLimit = 5 * 1024 *1024;
		String encType= "UTF-8";
		String uploadFilePath = "C:/upload";
		
		// 해당 디렉터리가 없으면 디렉터리 생성
		File dir = new File(uploadFilePath);
		if (!dir.exists()) {
			try {
				dir.mkdir();
				System.out.println("C:/upload 생성 완료!");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("C:/upload 폴더 확인");
		}
		
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(request,uploadFilePath,uploadFileSizeLimit,encType, new DefaultFileRenamePolicy());
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("파일 업로드 경로 :"+ uploadFilePath);
		// form 의 encType이 multipart/form-data 인 경우 request 객체로 파라미터를 가져올 수 없다.
		// 대신 MultipartRequest 객체로 가져올 수 있음!
		String uploader =multi.getParameter("uploader");
		String fileName = multi.getFilesystemName("uploadFile"); // 파일 이름의 경우 getFilesystemName method로 가져옴
		String description = multi.getParameter("description");
		String password = multi.getParameter("password");
		BoardVo article = new BoardVo();
		
		article.setUploader(uploader);
		article.setFilename(fileName);
		article.setPassword(password);
		article.setDescription(description);
		article.setRegdate(new Timestamp(System.currentTimeMillis()));
		
		boolean flag=uploadService.upload(article);
		request.setAttribute("flag", flag);
		
		return "/WEB-INF/views/uploadProc.jsp";
	}

}
