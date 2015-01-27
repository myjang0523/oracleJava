package dao.mybatis;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import pbean.Board;
import pbean.BoardFile;
import dao.BoardDAO;

@Repository
public class BoardDAOImpl2 
	extends SqlSessionDaoSupport 
	implements BoardDAO {

	public List<Board> getBoardList() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getBoardList");
	}

	public List<Board> getBoardList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getBoardListPaging", map);
	}

	public Board getBoard(int num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getBoard", num);
	}

	public void addArticle(Board board) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertBoard", board);
	}

	public void updArticle(Board board) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateBoard", board);
	}

	public void delArticle(int num) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteBoard", num);
	}

	public void addArticleCount(int num) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateCount", num);
	}

	public int getBoardCnt() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getBoardCount");
	}

	public void addVideo(BoardFile video) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertBoardFile", video);
		
	}

	public List<BoardFile> getBoardFile(int boardnum) {
		return getSqlSession().selectList("getBoardFileList");
	}
	
	
}
