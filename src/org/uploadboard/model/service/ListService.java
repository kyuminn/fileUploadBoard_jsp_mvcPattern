package org.uploadboard.model.service;

import java.util.List;

import org.uploadboard.model.dao.BoardDao;
import org.uploadboard.model.vo.BoardVo;
import org.uploadboard.model.vo.Paging;

public class ListService {
	private BoardDao dao;
	public ListService(BoardDao dao) {
		this.dao= dao;
	}
	public List<BoardVo> list(Paging page){
		return dao.list(page);
	}
	
	public int getArticleCount() {
		return dao.getArticleCount();
	}
}
