package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import logic.BoardLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import vbean.VBoard;
import vbean.VideoFileItem;

@Controller
public class BoardInsertController {
   @Autowired
   private BoardLogic boardLogic;

   public void setBoardLogic(BoardLogic boardLogic) {
      this.boardLogic = boardLogic;
   }
   
   private String formViewName = "/insertBoardForm";
   
   @RequestMapping(value="/insert.html", method=RequestMethod.GET)
   public String form(Model model) {
      VBoard board = new VBoard();
      for (int i=0; i<2; i++) {
         board.getVideoList().add(new VideoFileItem());
      }
      model.addAttribute("boardForm", board);
      return formViewName;
   }
   
   @RequestMapping(value="/insert.html", method=RequestMethod.POST)
   public String submit(
		   @Valid @ModelAttribute("boardForm")VBoard vboard,
		   HttpServletRequest req,
		   BindingResult result,
		   Model model)
				   throws IllegalStateException, IOException {
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
      
    //멀티파일 업로드
      for (VideoFileItem item : vboard.getVideoList()) {
         MultipartFile videoFile = item.getFile();
         //파일이 비었을 때 처리
         if (videoFile.isEmpty()) {
            continue;
         }
         String contentType = videoFile.getContentType();
         if ( !contentType.equals("video/mp4")) {
            result.rejectValue("videoList", 
                     "videoList.invalidFile",null, "올바른 비디오 형식이 아닙니다" );
            model.addAttribute("boardForm", vboard);
            return formViewName;
         }
         
         String filename = item.getName() + ".mp4";
         File uploadFile = new File("d:/videoTest/",filename);
         videoFile.transferTo(uploadFile);
      }
      
	  if(result.hasErrors()){
		  //바인딩 에러가 발생시에는 별도로 폼을 모델에 담을 필요 없음
		  return formViewName;
	  }
      
      this.boardLogic.addArticle(vboard);
      
      return "redirect:/boardList.html";
   }
}





