package com.study.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.domain.Menu;
import com.study.domain.Taste;
import com.study.domain.Type;
import com.study.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuRestController {
	
	@Autowired
	MenuService menuService;
	
	@GetMapping
	public List<Menu> menuAllList() {
		return menuService.menuAllList();
	}
	
	@GetMapping("/type/{type}")
	public List<Menu> menuType(@PathVariable("type") Type type) {
		return menuService.menuType(type);
	}
	
	@GetMapping("/type/{type}/taste/{taste}")
	public List<Menu> menuTypeTaste(@PathVariable("type") Type type,
									@PathVariable("taste") Taste taste)
	{
		return menuService.menuTypeTaste(type, taste);
	}
	/*
	@GetMapping("/{id}")
	public Menu findById(@PathVariable("id") Long id) {
		return menuService.findById(id);
		// 사용자가 db에 있는 id를 넣었을 때는 정상조회
		// 사용자가 db에 없는 id를 넣었을 때 오류500(서버측오류) 
	}
	*/
	/*
	 * 지금까지 상태코드를 지정하기 위해 HttpServletResponse의 setStatus()와 sendError()를 사용
	   문제점은 에러 시, JSON이 아닌 HTML결과를 응답함.
	   정상, 비정상 모두 JSON응답을 위해 ResponseEntity사용.
	   
	 - Restful 하게 만들 때는 요구한 자원이 없을때 404오류 반환(클라이언트측 오류<id가 없는것을 넣었기 때문>)
	   그래서 null과 null이 아닐 때를 나누어 처리가 필요
	   
	 * ResponseEntity : 결과 데이터와 HTTP상태코드와 오류코드까지 직접 제어할 수 있는 클래스
	 					(HttpRequest에 대한 응답 데이터가 포함되어 있음)
	 	- status : 응답에 대한 status코드
	 	- header : header값 (요청/응답)에 대한 요구사항
	 	- body : 메시지 body에 작성될 내용(요구사항의 내용)
	 	
	 * ResponseEntity 사용법
	   - status와 body를 이용
	     ResponseEntity.status(상태코드).body(객체)
	     
	     + 상태코드 HttpStatus에 정의된 값 이용
	     
	     + 상태코드 OK와 body를 한번에 사용
	       ResponseEntity.ok(menu)
	       
	     + body가 없을 때, build() 사용
	       ResponseEntity.status(HttpStatus.NOT_FOUND).build() <- 결과값이 없다는 것. error아님
	       
	     + body가 없고, status대신 사용하는 메서드
	       noContent() : 204
	       badRequest() : 400 <- 오류
	       notFound() : 404	     
	 */
	
	// id에 해당하는걸 클릭했을 때 발생
	@GetMapping("/{id}")
	public ResponseEntity<Menu> findById(@PathVariable("id") Long id) {
		Optional<Menu> menu = menuService.findById(id);
		
		if(menu.isPresent()) {
			// return ResponseEntity.ok().body(menu.get());  // ok는 200번
			return ResponseEntity.ok(menu.get());
		} else {
			return ResponseEntity.notFound().build();		// 404처리
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> insertMenu(@RequestBody Menu menu) {
		Menu reMenu = menuService.insertMenu(menu);
		return ResponseEntity.created(URI.create("/menu/" + reMenu.getId())).build();	// http상태코드 201
		//  /menu/{id} 형태의 url를 가지게 된다는듯
	}
	
	@PutMapping()
	public ResponseEntity<?> updateMenu(@RequestBody Menu menu){
							// http에 header에 담겨서 넘어옴
		Menu reMenu = menuService.updateMenu(menu);
		return ResponseEntity.ok(reMenu); // http상태코드 : 200
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMenu(@PathVariable("id") Long id){
		menuService.deleteMenu(id);
		return ResponseEntity.noContent().build(); // http상태코드 204(no content)실행은 됐는데 되돌려줄 값이 없음
		
	}
}
