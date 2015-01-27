package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import logic.BoardLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pbean.Board;
import vbean.VBoard;

@Controller
public class BoardUpdateController {
	@Autowired
	private BoardLogic boardLogic;

	public void setBoardLogic(BoardLogic boardLogic) {
		this.boardLogic = boardLogic;
	}
	
	private String formViewName = "/updateBoardForm";
	
	@RequestMapping(value="/update.html", method=RequestMethod.GET)
	public ModelAndView form(@RequestParam("num") int num) {
		Board board = this.boardLogic.getBoard(num);
		return new ModelAndView(formViewName, "board", board);
	}
	
	@RequestMapping(value="/update.html", method=RequestMethod.POST)
	public String submit(VBoard vboard, HttpServletRequest req) throws IllegalStateException, IOException {
		//파일 업로드
		MultipartFile file = vboard.getFile();
		
		//파일을 업로드했다면
		if (file.getSize() > 0) {
			//파일이름 설정
			vboard.setFilename(file.getOriginalFilename());  //예)이승철.jpg
			File imageFile = new File(req.getRealPath("/") + "/images",
					vboard.getFilename());
			file.transferTo(imageFile);
		}
		
		this.boardLogic.updArticle(vboard);
		
		return "redirect:/boardList.html";
	}
}






