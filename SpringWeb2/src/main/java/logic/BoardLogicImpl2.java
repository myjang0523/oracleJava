package logic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.BoardDAO;
import pbean.Board;
import pbean.BoardFile;
import vbean.VBoard;
import vbean.VideoFileItem;

@Service
public class BoardLogicImpl2 
	implements BoardLogic{
	@Autowired
	private BoardDAO boardDAO;
	
	public List<Board> getBoardList() {
		// TODO Auto-generated method stub
		return this.boardDAO.getBoardList();
	}

	public List<Board> getBoardList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.boardDAO.getBoardList(map);
	}

	public Board getBoard(int num) {
		// TODO Auto-generated method stub
		return this.boardDAO.getBoard(num);
	}
	
	@Transactional
	public void addArticle(VBoard vboard) {
		// TODO Auto-generated method stub
		Board board = new Board(vboard);
		this.boardDAO.addArticle(board);
		for(VideoFileItem item : vboard.getVideoList())
		{
			if(!item.getName().equals("") &&
					!item.getFile().isEmpty()){
				String filename = item.getName() + ".mp4";
				int boardnum = board.getNum();
				BoardFile video = new BoardFile();
				video.setFilename(filename);
				video.setBoardnum(boardnum);
				this.boardDAO.addVideo(video);
			}
		}
	}

	@Transactional
	public void updArticle(VBoard vboard) {
		// TODO Auto-generated method stub
		Board board = new Board(vboard);
		this.boardDAO.updArticle(board);
	}
	
	@Transactional
	public void delArticle(int num) {
		// TODO Auto-generated method stub
		this.boardDAO.delArticle(num);
	}

	public void addArticleCount(int num) {
		// TODO Auto-generated method stub
		this.boardDAO.addArticleCount(num);
	}

	public int getBoardCnt() {
		// TODO Auto-generated method stub
		return this.boardDAO.getBoardCnt();
	}

	public List<BoardFile> getBoardFile(int boardnum) {
		
		return this.boardDAO.getBoardFile(boardnum);
	}

}
