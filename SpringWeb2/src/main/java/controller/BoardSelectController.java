package controller;

import logic.BoardLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pbean.Board;
import pbean.BoardFile;

@Controller
public class BoardSelectController {
	@Autowired
	private BoardLogic boardLogic;

	public void setBoardLogic(BoardLogic boardLogic) {
		this.boardLogic = boardLogic;
	}
	
	@RequestMapping("/detail.html")
	public ModelAndView process(@RequestParam("num") int num) {
		Board board = this.boardLogic.getBoard(num);
		BoardFile boardfile = (BoardFile) this.boardLogic.getBoardFile(num);
		
		this.boardLogic.addArticleCount(num);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("boardSelect");
		modelAndView.addObject("board", board);
		
		return modelAndView;
	}
	
	

}






