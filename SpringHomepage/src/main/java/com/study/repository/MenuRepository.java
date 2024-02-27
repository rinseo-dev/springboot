package com.study.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.domain.Menu;
import com.study.domain.Taste;
import com.study.domain.Type;

public interface MenuRepository extends JpaRepository<Menu, Long>{
	List<Menu> findByType(Type type);

	List<Menu> findByTypeAndTaste(Type type, Taste taste);

}
