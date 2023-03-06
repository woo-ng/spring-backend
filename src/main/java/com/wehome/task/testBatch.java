package com.wehome.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class testBatch {
	
	@Scheduled(cron = "0 * * * * *")
	public void testMethod() throws Exception{
		
		log.warn("배치 실행 테스트...............");
		log.warn("==========================================");
	}

}
