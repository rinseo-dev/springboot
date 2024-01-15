package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.dto.Board;
import com.study.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping("/")
	public String root() throws Exception {
		return "redirect:list"; // /WEB-INF/views/+list+.jsp
	}
	
	
/*
 	요청 처리 후 응답페이지로 결과를 담는 방법
 	1. Model
 	   - 뷰로 전달하고자 하는 데이터를 맵형식(key=value)로 담을 수 있는 객체
 	   - Model은 RequestScope이다.(다른곳에선 못받음..?)
 	     단, setAttribute()가 아닌 addAttribute()메소드를 이용함
 	   
 	2. ModelAndView - view정보도 담아갈 수 있음
	   - Model영역 - 뷰로 전달하고자 하는 데이터를 맵형식(key=value)로 담을 수 있는 객체
	     View 영역 - 응답뷰에 대한 정보를 담을 수 있는 공간
	     
	     ex)
	     public String list(ModelAndView mv) {
		 	List<Board> rlist = boardService.list();
		 	mv.addObject("boardList",rlist);
		 	mv.setViewName("/list");
		 	return mv; // mv에 키,값,view까지 담겨있어서 return을 mv로 해도 됨
 		 }
*/
	@RequestMapping("/list")
	public String list(Model model) {
		List<Board> list = boardService.list();
		
		// list()로 가져오고 List<Board>에 담아서 사용?
		model.addAttribute("list",boardService.list());
		model.addAttribute("totalRecord",boardService.totalRecord());
		// list와 전체 글개수를 Model에 담아서 list로 감

		return "list"; // 값을 갱신해서 갖고 가야 하므로 redirect
	}
	
	@GetMapping("/writeForm")
	public String writeForm() {
		return "writeForm";
	}
	
	
/*
  파라미터(요청시 전달값)를 받는 방법
  1. HttpServletRequest를 이용하여 전달받기 (기존 jsp/servlet때의 전달방식)
  2. @RequestParam annotation이용 방법 - @RequestParam(속성)
	- 속성
	  value = uri에서 바인딩하게 될 값
	  required(true|false) : 필수적으로 값이 전달되는지 안받아도 되는지
	    	   true일 때 값이 안들어오면 error
	  defaultValue : 값이 없을 때 기본값으로 사용할 값
	  	   
	- @RequestParam("전달된 name(key)") 변수명 : defaultValue를 안넣을 때는 value 생략 가능

	-사용법
	 @RequestParam("value="key"[, defaultValue="", required=true]) 자료형 변수명
	 @RequestParam("key") // value값만 넣을 땐 value도 생략 가능.

  3. 커맨드 객체 방식
     dto클래스 담는 방식
     요청시 전달값의 키(name)를 dto클래스에 담고자 하는 필드명을 작성
     
     스프링 컨테이너가 해당 객체의 기본 생성자를 생성 후 setter메소드를 찾아서 전달된 값을 해당 필드에 내부적으로 담아주는 원리
     ex) public String write(Board board) <- 기본 생성자가 필요함. dto에서 기본생성자 선언해야됨 or @Data로 생성
     
     - 반드시 기본생성자가 필요함 (dto에서 생성)
     - setter메소드가 필요함
     - 키이름이 dto의 필드명과 같아야함
  */
	@PostMapping("write")
	public String write(Board board) {
		// Board로 3개 값을 한번에 받음. request로 받으면 3번받아야됨.
		// dto의 필드와 값이 같으면 바로 연결돼서 받을 수 있음
		
		/* 값 잘 넘어오나 테스트
		 * System.out.println("제목:"+board.getTitle());
		 * System.out.println("작성자:"+board.getWriter());
		 * System.out.println("내용:"+board.getContent());
		 */
		boardService.insertBoard(board);
		return "redirect:/list";
		// redirect로 이동하면 처리한 결과를 가지고 list를 재호출->입력정보가 갱신이 됨
	}
	
	@GetMapping("/detail")
	public String detailBoard(HttpServletRequest request, Model model) {
		//String no = request.getParameter("no");
		// "no"에 들어간 값을 request.getParameter로 가져와서 no에 넣어 사용할 수 있음
		
		System.out.println("no"+request.getParameter("no")); // "no"가져와지는지 출력
		
		/*
		변수로 가져와서 이렇게 작성해도 됨
		Board board = boardService.detailBoard(request.getParameter("no"));
		model.addAttribute("detailBoard", board);
		아래는 한 줄로 줄임
		*/
		model.addAttribute("detailBoard", boardService.detailBoard(request.getParameter("no")));
		return "detailForm"; // detailForm.jsp로 가야하므로
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(value="no", defaultValue="1") String bno) {
		// bno의 자료형은 service를 통해서 impl, dao까지 String no로 지정해놨음.
		
		boardService.deleteBoard(bno); // ReqeustParam으로 받은 bno 사용
		return "redirect:/list"; // 재호출해서 삭제한건 안보이게
	}
}
