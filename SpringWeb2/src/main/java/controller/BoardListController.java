package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.BoardLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pbean.Board;
import aop.Authrorize;
import aop.AuthrorizeAdmin;

@Controller
//boardListController가 있으면 그걸로 등록하고 없으면 자동으로 등록된다.
public class BoardListController {
	@Autowired
	private BoardLogic boardLogic;
	
	public void setBoardLogic(BoardLogic boardLogic) {
		this.boardLogic = boardLogic;
	}
	
	//페이지 사이즈(한 페이지당 글의 갯수)
	private int pageSize;
	//페이지 블럭 사이즈(페이지 블럭의 크기)
	private int pageBlockSize;
	

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public void setPageBlockSize(int pageBlockSize) {
		this.pageBlockSize = pageBlockSize;
	}


	@RequestMapping("/boardList.html")
	@Authrorize
	@AuthrorizeAdmin
	public ModelAndView 
		handleRequest(@RequestParam(value="pageNum", required=false)
									String pageNum
				) throws Exception {
		if (pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		int count = this.boardLogic.getBoardCnt();
		int totalPage = (count-1) / pageSize + 1;
		int prevPage =( (currentPage-1) / pageBlockSize) * pageBlockSize;
		int nextPage = prevPage + (pageBlockSize + 1);
		nextPage = (nextPage > totalPage)?totalPage+1:nextPage;
		
		ModelAndView modelAndView = new ModelAndView();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", startRow);
		map.put("end", endRow);
		
		List<Board> list = this.boardLogic.getBoardList(map);
		
		modelAndView.setViewName("boardList");
		modelAndView.addObject("list", list);
		modelAndView.addObject("count", count);
		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("totalPage", totalPage);
		modelAndView.addObject("prevPage", prevPage);
		modelAndView.addObject("nextPage", nextPage);
		modelAndView.addObject("pageSize", pageSize);
		
		return modelAndView;
	}
}




