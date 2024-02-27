package com.study.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.domain.Menu;
import com.study.domain.Taste;
import com.study.domain.Type;
import com.study.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	MenuRepository menuRepository;

	public List<Menu> menuAllList() {		
		return menuRepository.findAll();
	}

	public List<Menu> menuType(Type type) {
		return menuRepository.findByType(type);
	}

	public List<Menu> menuTypeTaste(Type type, Taste taste) {
		return menuRepository.findByTypeAndTaste(type, taste);
	}
	/*
	public Menu findById(Long id) {
		return menuRepository.findById(id).get();
	}
	*/

	public Optional<Menu> findById(Long id) {
		return menuRepository.findById(id);	
	}

	public Menu insertMenu(Menu menu) {
		return menuRepository.save(menu);
	}

	public Menu updateMenu(Menu menu) { // 여기 menu는 사용자가 넣은 값
		// menuRepository.findById(menu.getId()); 이렇게 하면 return값이 optional이므로 .get()해줌
		
		// 여기 Menu는 기존 저장값
		Menu reMenu = menuRepository.findById(menu.getId()).get();
		reMenu.setName(menu.getName()); // 사용자가 넣은 값을 가져와서 setName으로 넣어줌
		reMenu.setPrice(menu.getPrice());
		reMenu.setRestaurant(menu.getRestaurant());
		reMenu.setType(menu.getType());
		reMenu.setTaste(menu.getTaste());
		
		// return menuRepository.save(menu);
		// id값은 변하지 않고 기존에 있는 그대로 유지할 것이므로 save()에는 reMenu를 입력
		return menuRepository.save(reMenu);
		/*
		 * update를 할 때는 영속성 안에 넣어줘야해서 save(menu)로 하면 그냥 insert임
		 * 영속성 안에는 해당 id에 대한 값이 꼭 들어가있어야함.
		 * findById를 찾아서 영속성 안에 넣어줘야함.
		 */
	}
	
	public void deleteMenu(Long id) {
		menuRepository.deleteById(id);
	}
}