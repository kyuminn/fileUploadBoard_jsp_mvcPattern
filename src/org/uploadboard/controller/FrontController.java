package org.uploadboard.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uploadboard.handler.CommandHandler;

// HttpServlet을 상속함으로써 FrontController가 servlet임을 선언
public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	//명령어와 명령어 처리 핸들러 클래스 정보를 담아놓는 Map
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	
	// 서블릿이 동작할 때 가장 먼저 수행되는 init Method
	// properties 파일 읽어들여서 commandHandlerMap에 저장
	@Override
	public void init() throws ServletException {
		String contextConfigFile = this.getInitParameter("properties");
		Properties pr = new Properties();
		FileInputStream fis = null;
		try {
			String path = this.getServletContext().getRealPath(contextConfigFile);
			fis = new FileInputStream(path); // 빨대꽂기
			pr.load(fis);
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (fis!=null) fis.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		Iterator<Object> keyIt = pr.keySet().iterator();
		while(keyIt.hasNext()) {
			String command = (String)keyIt.next();
			String handlerClassName = pr.getProperty(command);
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				commandHandlerMap.put(command, (CommandHandler)handlerClass.newInstance());
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(IllegalAccessException e) {
				e.printStackTrace();
			}catch(InstantiationException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI= req.getRequestURI().toString();
		String command = requestURI.substring(req.getContextPath().length());
		
		// 메인 페이지 요청했을때 로그용 예시 
		System.out.println(requestURI); // /org.uploadboard/main.do
		System.out.println(command); // /main.do
		System.out.println(req.getContextPath()); // /org.uploadboard
		
		CommandHandler handler = null;
		String viewPage= null;
		if (requestURI.indexOf(req.getContextPath())==0) {
			handler = commandHandlerMap.get(command);
			viewPage = handler.handlerAction(req, resp);
		}
		req.getRequestDispatcher(viewPage).forward(req, resp);
	}
}
