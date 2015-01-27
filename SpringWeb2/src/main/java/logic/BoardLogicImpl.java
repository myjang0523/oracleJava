package logic;

import java.util.List;
import java.util.Map;

import dao.BoardDAO;
import pbean.Board;
import pbean.BoardFile;
import vbean.VBoard;

public class BoardLogicImpl implements BoardLogic {

	private BoardDAO boardDAO;
	
	public List<Board> getBoardList() {
		return this.boardDAO.getBoardList();
	}
	public List<Board> getBoardList(Map<String, Object> map) {
		return this.boardDAO.getBoardList(map);
	}
	public Board getBoard(int num) {
		return this.boardDAO.getBoard(num);
	}
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	//게시물 추가
	public void addArticle(VBoard vboard) {
		Board board = new Board(vboard);
//		board.setNum(vboard.getNum());
//		board.setUserid(vboard.getUserid());
//		board.setTitle(vboard.getTitle());
//		board.setContent(vboard.getContent());
//		board.setFilename(vboard.getTitle());
//		board.setRef(vboard.getRef());
//		board.setRe_level(vboard.getRe_level());
//		board.setRe_step(vboard.getRe_step());
//		board.setRegdate(vboard.getRegdate());
		this.boardDAO.addArticle(board);
		
	}
	public void updArticle(VBoard vboard) {
		Board board = new Board(vboard);
		this.boardDAO.updArticle(board);
		
	}
	public void delArticle(int num) {
		this.boardDAO.delArticle(num);
	}
	public void addArticleCount(int num) {
		this.boardDAO.addArticleCount(num);
		
	}
	public int getBoardCnt() {
		return this.boardDAO.getBoardCnt();
	}
	public List<BoardFile> getBoardFile(int boardnum) {
		return this.boardDAO.getBoardFile(boardnum);
	}
	
	
}
