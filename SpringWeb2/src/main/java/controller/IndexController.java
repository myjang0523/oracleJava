package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@RequestMapping("/index.html")
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		//현재시간
		String timestamp =
				new SimpleDateFormat("yyyyMMdd HHmmss")
					.format(Calendar.getInstance().getTime());
		
		//View
		modelAndView.setViewName("index");  //뷰 이름이 index로 하라는 뜻
		//Model
		modelAndView.addObject("now", timestamp);
		return modelAndView;
	}
}




