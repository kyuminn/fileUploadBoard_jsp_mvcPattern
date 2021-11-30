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

public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		String contextConfigFile = this.getInitParameter("properties");
		Properties pr = new Properties();
		FileInputStream fis = null;
		try {
			String path = this.getServletContext().getRealPath(contextConfigFile);
			fis = new FileInputStream(path);
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
		Iterator keyIt = pr.keySet().iterator();
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
		CommandHandler handler = null;
		String viewPage= null;
		if (requestURI.indexOf(req.getContextPath())==0) {
			handler = commandHandlerMap.get(command);
			viewPage = handler.handlerAction(req, resp);
		}
		req.getRequestDispatcher(viewPage).forward(req, resp);
	}
}
