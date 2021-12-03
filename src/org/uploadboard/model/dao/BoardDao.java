package org.uploadboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.uploadboard.model.common.ConnectionUtil;
import org.uploadboard.model.vo.BoardVo;
import org.uploadboard.model.vo.Paging;

// DB과 연결된 부분 
public class BoardDao {
	private static BoardDao instance = null;
	// 다른 클래스에서 직접 DAO객체 생성할 수 없도록 private 한 생성자 만들기 
	private BoardDao() {
		
	}
	
	// Singleton pattern
	public static BoardDao getInstance() {
		if (instance== null) {
			synchronized(BoardDao.class) {
				instance= new BoardDao();
			}
		}
		return instance;
	}
	
	// 여기서부터 ConnectionUtil.getConnection 사용 
	
	// 파일 올리기
	public boolean upload(BoardVo vo) {
		int result = -1;
		String sql = "insert into uploadboard values (upload_seq.nextval,?,?,?,?,?,?)";
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, vo.getDescription());
			pstmt.setString(2, vo.getFilename());
			pstmt.setString(3, vo.getUploader());
			pstmt.setTimestamp(4, vo.getRegdate());
			pstmt.setInt(5, 0);
			pstmt.setString(6, vo.getPassword());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result==1? true:false;
	}
	
	// 리스트
	public List<BoardVo> list(Paging page){
		String sql="select * from "
				+ "(select rownum rnum,num,description,filename,uploader,"
				+ "regdate,readcount,password from "
				+ "(select * from uploadboard order by num desc)) "
				+ "where rnum>=? and rnum<=?";
		List<BoardVo> ls = new ArrayList<BoardVo>();
		ResultSet rs = null;
		try(Connection conn= ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, page.getStartRowNum());
			pstmt.setInt(2, page.getLastRowNum());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					BoardVo vo = new BoardVo();
					vo.setNum(rs.getInt("num"));
					vo.setDescription(rs.getString("description"));
					vo.setFilename(rs.getString("filename"));
					vo.setUploader(rs.getString("uploader"));
					vo.setRegdate(rs.getTimestamp("regdate"));
					vo.setReadcount(rs.getInt("readcount"));
					vo.setPassword(rs.getString("password"));
					ls.add(vo);
				}while(rs.next());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs !=null) rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return ls;
	}
	
	// 한 개의 게시글 가져오기 (readcount +1 )
	public BoardVo selectOne(int num) {
		String sql="";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		try {
			sql = "update uploadboard set readcount = readcount+1 where num=?";
			conn = ConnectionUtil.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeQuery();
			pstmt.close();
			sql = "select * from uploadboard where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new BoardVo();
				vo.setNum(rs.getInt("num"));
				vo.setDescription(rs.getString("description"));
				vo.setFilename(rs.getString("filename"));
				vo.setUploader(rs.getString("uploader"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setReadcount(rs.getInt("readcount"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt !=null) pstmt.close();
				if (conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	// 게시글 삭제
	public int delete(int num, String password) {
		String sql ="";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1 ; // num 에 해당하는 password 없음
		String dbPassword = "";
		try {
			conn = ConnectionUtil.getConnection();
			sql  ="select password from uploadboard where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPassword = rs.getString("password");
				if (dbPassword.equals(password)) {
					sql = "delete from uploadboard where num=?";
					pstmt.close();
					pstmt= conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					result = pstmt.executeUpdate(); // result =1 (비밀번호 일치)
				}else {
					result = 0 ; // (비밀번호 불일치)
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if ( rs != null) rs.close();
				if ( pstmt!= null) pstmt.close();
				if ( conn !=null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	}
	// 게시글 삭제 시, 삭제할 게시글에 올린 파일 이름 가져오기
	public String selectFileName(int num) {
		String fileName ="";
		String sql= "select filename from uploadboard where num=?";
		ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fileName = rs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if ( rs != null) rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}
	
	// 게시글 수정 시 , 조회수를 올리지 않고 하나의 게시글만 가져오기
	public BoardVo selectOneForUpdate(int num) {
		String sql="select * from uploadboard where num=?";
		ResultSet rs = null;
		BoardVo vo = null;
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new BoardVo();
				vo.setNum(rs.getInt("num"));
				vo.setDescription(rs.getString("description"));
				vo.setFilename(rs.getString("filename"));
				vo.setUploader(rs.getString("uploader"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setReadcount(rs.getInt("readcount"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	// 게시글 수정
	public int update(BoardVo vo) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1 ; 
		try {
			conn = ConnectionUtil.getConnection();
			sql = "update uploadboard set description=?,filename=?,regdate=? where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getDescription());
			pstmt.setString(2, vo.getFilename());
			pstmt.setTimestamp(3, vo.getRegdate()); // 수정한 시간으로 바뀜
			pstmt.setInt(4, vo.getNum());
			result = pstmt.executeUpdate(); // result = 1 이면 수정완료
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt !=null) pstmt.close();
				if (conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 글의 개수를 가져오는 메서드
	public int getArticleCount() {
		String sql="select count(*) from uploadboard";
		int count = 0;
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){
			if (rs.next()) {
				count = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	// 비밀번호 검증용 메서드
	public boolean isValidPassword(int num, String password) {
		int result = -1;
		String sql="select password from uploadboard where num=?";
		ResultSet rs = null;
		String dbPassword ="";
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPassword = rs.getString(1);
				if (password.equals(dbPassword)) {
					result = 1;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result==1?true:false;
	}
}
