package com.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex01LoggerApplication {

	/*
	 * 로그 활용
	 * - 애플리케이션의 흐흠을 모니터링 한다
	 * - 프로그램 코드의 예기치 못한 오류를 포착하는데 도움이 된다
	 * - 스프링부트 스타터 패키지에는 "별도의 구성없이" 로깅을 쓸 수 있는 로그백이 있다
	 * 
	 * - 구현
	 *  1) Logger클래스 활용
	 *  2) LoggerFactory 클래스
	 *  3) LoggerFactory.getLogger()
	 *  4) 로깅 수준 설정
	 *  	- OFF
	 *  	- ERROR : 치명적인 에러 메시지 출력
	 *  	- WARN : 향후 에러의 원인이 될 수 있는 경고성 메시지 출력(운영단계)
	 *  	- INFO : 상태 변경과 같은 정보 전달 목적의 메시지(기본값)
	 *  	- DEBUG : 디버킹을 위해 출력하는 메시지 출력(개발단계)
	 *  	- TRACE : 디버길 레벨 보다 좀 더 상세한 메시지 출력
	 */
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Ex01LoggerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(Ex01LoggerApplication.class, args);
		
		System.out.println("테스트");
	}

}
