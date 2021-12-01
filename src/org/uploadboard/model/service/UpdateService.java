package org.uploadboard.model.service;

import org.uploadboard.model.dao.BoardDao;
import org.uploadboard.model.vo.BoardVo;

public class UpdateService {
	private BoardDao dao ;
	
	public UpdateService(BoardDao dao) {
		this.dao = dao;
	}
	
	public BoardVo selectOne(int num) {
		return dao.selectOneForUpdate(num);
	}
	
	public boolean isValidPassword(int num, String password) {
		return dao.isValidPassword(num, password);
	}
	public int update(BoardVo vo) {
		return dao.update(vo);
	}
}
