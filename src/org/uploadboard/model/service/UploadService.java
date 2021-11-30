package org.uploadboard.model.service;

import org.uploadboard.model.dao.BoardDao;
import org.uploadboard.model.vo.BoardVo;

public class UploadService {
	
	private BoardDao dao;
	public UploadService(BoardDao dao ) {
		this.dao= dao;
	}
	
	public boolean upload(BoardVo vo) {
		return dao.upload(vo);
	}
	
}
