package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.domain.Member;
import com.study.dto.ResponseDto;
import com.study.dto.UserDto;
import com.study.service.MemberRestService;
import com.study.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/rest") //모든 경로에 rest가 붙게됨
public class MemberRestController {
	
	/* 
	 * 기존 일반 @Controller에 @ResponseBody가 붙어있으면 json이 반환됨.
	 *  ex) idCheck
	 *  @RestController에서는 자동적으로 @ResponseBody로 활용됨
	 */
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRestService memberRestService;
	
	//@GetMapping("/rest/test") -> RequestMapping을 통해서 /rest를 붙여줌
	@GetMapping("/test") // localhost/rest/test
	public String test() {
		// System.out.println("test");
		// log를 사용하면 sysout 안해도 됨
		
		log.info("test");// @Slf4j를 사용하면 로그로 출력 가능
		
		return "test";
	}
	
	@GetMapping("/user")
	public String getMember(@RequestParam(value="empNo",defaultValue="111") String empNo) {
		/* 
		 * url에 넘겨주는 값은 주소를 사용할 때 로컬/rest/user/user01 형식으로 사용하고
		 *실제 주소는 /rest?id=user01 형식이었는데
		 * /rest/user처럼 그대로 가져와도 됨
	     */
		
		log.info("emoNo:{}",empNo); //변수 empNo값을 {}안에 넣어줌
		//System.out.printf("empNo:%s"+emoNo);와 같음 - String형
		
		return "ok: "+empNo;
	}
	
	@GetMapping("/user/{id}") // {}안에는 변수명이므로 아무거나 가능. PathVariable과 맞추면됨
	public String getMemberId(@PathVariable("id") String id) {
		// @PathVariable 주소창에서 넘어오는 id에서 가져온 값을 String id에 넣음
		log.info("id:{}",id);
		return "ok:"+id;// 로컬/rest/user/user01이라고 입력하면 ok:user01 출력됨
	}
	
	
	
	//테스트는 webMVC나 포스트맨 등으로 가능
	
	// post로 보내면 http프로토콜 안에 넣어서 보내줌
	// http 프로토콜 안에 넣어줄 수 없기 때문에 test용으로 실행해 볼 수 있음
	/* test용 실행방법 
	 * 1.postman사용
	 * 2. webmvc사용 (mvcrepository에서 검색하여 build.gradle 설정하여 사용)
	 *    SpringDoc OpenAPI Starter WebMVC UI 사용했음.
	 * 	  사용방법: 웹브라우저에서 로컬 url주소 끝에 swagger-ui.html을 붙임
	 *    ex) http://localhost:8080/swagger-ui.html
	 */
	@PostMapping("/userpost") // insert에 post를 사용
	public String saveItem(@RequestBody String item) {
		// item을 body에 실어서 보낼것
		log.info("item:{}",item);
		return "ok:"+item;
	}
	
	
	/*
	 * json으로 아래와 같은 형태로 받고 싶을 때 
	 * dto클래스를 만들어 객체에 담에 return
	  {
	  		"id" : "user01",
	  		"name" : "홍길동"
	  }
	 */
	
	/*
	 * 아래에서 다른거 작성할거라 주석
	@PostMapping("/userdto")
	public UserDto saveUserDto(@RequestBody UserDto userDto) {
		Member m = memberRestService.saveUserDto(userDto);
		// saveUserDto한 결과가 Member로 반환된것
		// 입력하지 않은 값들은 null인 상태
		
		// m을 userDto로 바꿔줘야 반환값으로 넘겨줄 수 있음
		UserDto reDto = new UserDto(m.getId(),m.getName());
		// id, name이 들어오는 생성자를 만든 이유는 여기서 사용하기 위함
		
		// 생성자를 사용하지 않으면 아래처럼 넣어야함
		// reDto.setId(m.getId());
		// reDto.setName(m.getName());
		
		return reDto;
	}
	*/
	
	/*
	**
	Member m = memberRestService.saveUserDto(userDto);에서는
	객체를 생성하면서 m에 String형의 id, name을 저장해서 반환했음.
	return reDto;대신
	return new UserDto(m.getId(),m.getName());도 가능한것
	이때는 생성자의 인자(String, String)인 상태 (id, name)
	
	생성자의 인자를 String이 아닌 Member m 을 사용한 경우
	그런데 userDto에서 생성자를 String형태가 아니라
	Member객체를 활용하는 것으로 수정했을 때는
	return new UserDto(m);이 되는데
	m 안에는 memberRestService.saveUserDto(userDto)가 들어있으므로
	
	아래처럼 한 줄로 사용할 수 있음
	return new UserDto(memberRestService.saveUserDto(userDto));
	
	이렇게 생성자를 어떤걸 사용하느냐에 따라서 코드가 더 효율적으로 바뀜
	*/
	
	/* 
	 * 만드는 순서 설명
	 * DTO생성 -> 컨트롤러구성->service이름작성(memberRestService)
	 * ->실행할것이름작성(saveUserDto)하고 saveUserDto작성
	 * reDto줄 작성, 리턴값 작성하고 실행(루트/swagger-ui)
	 * POST : userDto에서 {"id": "user03","name": "유저3"}작성
	 * Excute누르면 실행되고 DB에 저장됨
	 */
	
	// @PostMapping("/usetdto") 같은 경로를 사용해서 위를 주석걸고 아래를 엶
	@PostMapping("/userdto")
	public ResponseDto saveUserDto(@RequestBody UserDto userDto) {
		Member m = memberRestService.saveUserDto(userDto);
		ResponseDto responseDto = new ResponseDto();

		if(m.getId() != null) {
			responseDto.setMessage("ok");
			return responseDto;
		}
		responseDto.setMessage("fail");
		return responseDto;
	}
	
	// Post주소를 타면 insert, Get을 타면 select인걸 직관적으로 알 수 있음
	// 경로에 {}로 지정해주면 PathVariable을 사용하고 아니면 RequestParam사용
	@GetMapping("/userdto")
	public UserDto getUserDto(@RequestParam("id") String id) {
		return memberRestService.getUserById(id); // 깃허브 이거 맞겠지
	}
	
	@GetMapping("/userdto/{id}")
	public UserDto getUserPath(@PathVariable("id") String id) {
		return memberRestService.getUserById(id);
		// 루트/rest/userdto/user03 형식으로 입력하면 user03의 정보를 볼 수 있음
	}
	
	// 아래 두개는 전체 조회 - 단, 출력 값이 다름
	@GetMapping("/userall")
	public List<Member> getUserAll(){
		return memberRestService.getUserAll();
	}
	
	@GetMapping("/userdtoall")
	public List<UserDto> getUserDtoAll(){
		return memberRestService.getUserDtoAll();
	}
	
}	
