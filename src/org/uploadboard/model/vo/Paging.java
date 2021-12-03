package org.uploadboard.model.vo;

import org.uploadboard.model.dao.BoardDao;

public class Paging {

	private int pageSize = 3; // 페이지에 들어가는 글 수 (startRowNum ~ lastRowNum)
	private int pageBlock = 3; // 페이지 블록 수 (1,2,3// 4,5,6///)
	private int startRowNum = 0; // 한 페이지의 시작 번호 
	private int lastRowNum = 0;  // 한 페이지의 끝 번호 
	private int startPageNum = 0; // 예) 1, 4 page...
	private int lastPageNum=0; // 예 ) 3,6 page ...
	private int totalPageCount =0; // 전체 페이지 수 
	private int back = 0;  // 이젠 페이지로 
	private int forward = 0; // 다음 페이지로 
	private int totalRowCount = 0; // 전체 글 수 
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public int getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	public int getLastRowNum() {
		return lastRowNum;
	}
	public void setLastRowNum(int lastRowNum) {
		this.lastRowNum = lastRowNum;
	}
	public int getStartPageNum() {
		return startPageNum;
	}
	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}
	public int getLastPageNum() {
		return lastPageNum;
	}
	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getBack() {
		return back;
	}
	public void setBack(int back) {
		this.back = back;
	}
	public int getForward() {
		return forward;
	}
	public void setForward(int forward) {
		this.forward = forward;
	}
	
	public int getTotalRowCount() {
		return totalRowCount;
	}
	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}
	// 특정 페이지의 게시글 시작번호, 끝번호 설정하는 메서드
	public void pagedArticleList(int currentPage) {
		startRowNum = (currentPage-1) * pageSize +1 ;
		lastRowNum = startRowNum + pageSize -1;
	}
	
	// 오버라이딩!
	// 전체 페이지 수 , 시작 페이지 , 마지막 페이지, 이전으로, 다음으로 값 설정하는 메서드
	public void getPageBlock(int currentPage) {
		BoardDao dao = BoardDao.getInstance();
		totalRowCount = dao.getArticleCount();
		int imsi = totalRowCount % pageSize ==0 ? 0 :1;
		totalPageCount = totalRowCount/pageSize + imsi;
		
		startPageNum = ((currentPage-1) / pageBlock) * pageBlock +1;  // 1,4
		lastPageNum = startPageNum + pageBlock -1 ; // 3, 6
		
		if (lastPageNum >=totalPageCount) {
			lastPageNum = totalPageCount ;
		}
		
		back = startPageNum - 1; // 이전으로 (이전 페이지 블록의 마지막 페이지로)
		forward = startPageNum +pageBlock; // 다음으로 (다음 페이지 블록의 첫번째 페이지로)
	}
	
	
}
