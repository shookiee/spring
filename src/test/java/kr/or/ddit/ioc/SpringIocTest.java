package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.board.service.IBoardService;

public class SpringIocTest {
	private static final Logger logger = LoggerFactory.getLogger(SpringIocTest.class);
	
	/**
	* Method : springIocTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너 생성 테스트
	*/
	@Test
	public void springIocTest() {
		
		/***Given***/
		// 스프링 컨테이너 생성
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:kr/or/ddit/ioc/application-ioc-test.xml");
		
		/***When***/
		IBoardService boardService = (IBoardService)context.getBean("boardService");
		String msg = boardService.sayHello();
		
		/***Then***/
		assertNotNull(boardService);
		assertEquals("boardDao sayHello", msg);
		logger.debug("msg: {}", msg);
	
	}

}
