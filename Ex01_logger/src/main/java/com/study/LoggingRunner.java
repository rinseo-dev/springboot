package com.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class LoggingRunner implements ApplicationRunner{
	Logger logger = LoggerFactory.getLogger(LoggingRunner.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/*
		 LoggingRunner에 ApplicationRunner을 implements해서 run()을 사용할 수 있게 
		 */
		logger.trace("Trace 레벨 로그");
		logger.debug("Debug 레벨 로그");
		logger.info("Info 레벨 로그"); 
		logger.warn("Warn 레벨 로그"); 
		logger.error("Error 레벨 로그"); 
		// application.properties에서 루트 수준 Trace, Debug, Error등 각각 설정해놓고 실행
		
		
	}
}
