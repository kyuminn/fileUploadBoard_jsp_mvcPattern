package org.uploadboard.handler;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uploadboard.model.dao.BoardDao;
import org.uploadboard.model.service.UpdateService;
import org.uploadboard.model.vo.BoardVo;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class UpdateProcHandler implements CommandHandler{
	private BoardDao dao = BoardDao.getInstance();
	private UpdateService updateservice = new UpdateService(dao);
	
	@Override
	public String handlerAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		int uploadFileSizeLimit = 5 * 1024 *1024;
		String uploadFilePath = "C:/upload";
		File dir = new File(uploadFilePath);
		
		// 수정하기 전에 입력한 비밀번호 검증먼저
		// 1. MultipartParser 객체를 이용하여 parameter 값 먼저 가져오기
			MultipartParser mp = new MultipartParser(request,uploadFileSizeLimit);
			mp.setEncoding("UTF-8");
			Part part;
			String password = "";
			String description = "";
			String fileName = "";
			String uploadedFileName="";
			int result = -1;
			
			while((part = mp.readNextPart())!=null) {
				String name = new String(part.getName());
				if (part.isParam()) {
					ParamPart paramPart = (ParamPart) part ;
					if (name.equals("password")) {
						password = paramPart.getStringValue();
					}else if (name.equals("description")) {
						description = paramPart.getStringValue();
					}else if (name.equals("uploadedFile")) {
						uploadedFileName = paramPart.getStringValue();
					}
					String value = new String(paramPart.getStringValue());
					System.out.println(name+":"+value);
				}else if (part.isFile()) {
					if (updateservice.isValidPassword(num, password)) { // 비밀번호 검증 후 맞으면 파일 업로드
						result = 1 ;
						FilePart filePart =(FilePart)part;
						fileName = filePart.getFileName();
						System.out.println(fileName);
						if (fileName !=null) {
							fileName = new String(filePart.getFileName());
							System.out.println(fileName);
							long size = filePart.writeTo(dir);

						}
					}else {
						result = 0; // 비밀번호 일치하지 않음
					}
				}
			}

			System.out.println(updateservice.isValidPassword(num, password)); // log 용
			if(updateservice.isValidPassword(num, password)) {
				BoardVo vo = new BoardVo();
				vo.setRegdate(new Timestamp(System.currentTimeMillis()));
				vo.setFilename(fileName);
				vo.setDescription(description);
				vo.setNum(num);
				vo.setPassword(password);
				updateservice.update(vo);
				
				//result = updateservice.update(vo);
				
				// 비밀번호가 일치할 때만 파일 수정 후 삭제
				if (result == 1) {
					// 기존 파일 삭제
					File file = new File("/upload/"+ uploadedFileName);
					if(file.exists()) {
						file.delete();
					}else {
						System.out.println("파일이 존재하지 않습니다");
					}
				}
			}

		request.setAttribute("result", result);
		
		return "/WEB-INF/views/updateProc.jsp";
	}

}
