package org.uploadboard.model.service;

import org.uploadboard.model.dao.BoardDao;
import org.uploadboard.model.vo.BoardVo;

public class ContentService {
	private BoardDao dao;
	
	public ContentService(BoardDao dao) {
		this.dao= dao;
	}
	
	public BoardVo selectOne(int num) {
		return dao.selectOne(num);
	}
}
