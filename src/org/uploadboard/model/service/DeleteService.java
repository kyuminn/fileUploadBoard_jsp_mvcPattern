package org.uploadboard.model.service;

import org.uploadboard.model.dao.BoardDao;

public class DeleteService {
	private BoardDao dao ; 
	
	public DeleteService(BoardDao dao) {
		this.dao= dao;
	}
	
	public String getFileName(int num) {
		return dao.selectFileName(num);
	}
	
	public int delete(int num, String password) {
		return dao.delete(num, password);
	}
}
