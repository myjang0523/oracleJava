package dao.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import pbean.Board;
import pbean.BoardFile;
import utils.MyBatisManager;
import dao.BoardDAO;

public class BoardDAOImpl implements BoardDAO {

	public List<Board> getBoardList() {
		List<Board> list = null;
		SqlSession sqlSession = MyBatisManager.getSqlSession().openSession();
		
		try {
			list = sqlSession.selectList("getBoardList");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			sqlSession.close();
		}
		
		return list;
	}
	
	public List<Board> getBoardList(Map<String, Object>map) {
		List<Board> list = null;
		SqlSession sqlSession = MyBatisManager.getSqlSession().openSession();
		
		try {
			list = sqlSession.selectList("getBoardListPaging", map);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			sqlSession.close();
		}
		
		return list;
	}

	public Board getBoard(int num) {
		Board board = null;
		SqlSession sqlSession = MyBatisManager.getSqlSession().openSession();
		try {
			board = sqlSession.selectOne("getBoard", num);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			sqlSession.close();
		}
		return board;
	}
	
	public int getBoardCnt() {
		int cnt = 0;
		SqlSession sqlSession = MyBatisManager.getSqlSession().openSession();
		try {
			cnt = sqlSession.selectOne("getBoardCount");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			sqlSession.close();
		}
		return cnt;
	}

	public void addArticle(Board board) {
		SqlSession sqlSession = MyBatisManager.getSqlSession().openSession();
		try {
			sqlSession.insert("insertBoard", board);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
	}

	public void updArticle(Board board) {
		SqlSession sqlSession = MyBatisManager.getSqlSession().openSession();
		try {
			sqlSession.insert("updateBoard", board);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
	}

	public void delArticle(int num) {
		SqlSession sqlSession = MyBatisManager.getSqlSession().openSession();
		try {
			sqlSession.insert("deleteBoard", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
	}

	public void addArticleCount(int num) {
		SqlSession sqlSession = MyBatisManager.getSqlSession().openSession();
		try {
			sqlSession.insert("updateCount", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
	}

	public void addVideo(pbean.BoardFile video) {
		SqlSession sqlSession = MyBatisManager.getSqlSession().openSession();
		try {
			sqlSession.insert("insertBoardFile", video);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
	}

	public List<BoardFile> getBoardFile(int boardnum) {
		List<BoardFile> list = null;
		SqlSession sqlSession = MyBatisManager.getSqlSession().openSession();
		
		try {
			list = sqlSession.selectList("getBoardFileList");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			sqlSession.close();
		}
		
		return list;
	}

	

}


