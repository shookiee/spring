package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-test.xml")
public class SpringIocJunitTest {
	
	// field 기준 boardService, boardService2 : spring boardService bean (scope=singleton)
	//			boardServiceConstructor : spring boardServiceConstructor bean (scope=singleton)
	// 1. boardSerivce, boardService2 : 같아야 함
	// 2. boardService, boardServiceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton 개념에 따라 다른 객체
	// 3. boardService2, boardServiceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton 개념에 따라 다른 객체
			
	@Resource(name="boardService")
	private IBoardService boardService;

	@Resource(name="boardService")
	private IBoardService boardService2;
		
	@Resource(name="boardServiceConstructor")
	private IBoardService boardServiceConstructor;

	
	// boardDao : spring boardDao(scope=singleton)
	// boardDaoPrototype : spring boardDaoPrototype(scope=prototype)
	// boardDaoPrototype2 : spring boardDaoPrototype(scope=prototype)
	// 1. boardDao boardDaoPrototype : spring bean id가 다르므로 다른 객체
	// 2. boardDaoPrototype, boardDaoPrototype2 : 두 객체는 같은 스프링 빈이지만 
	//											  scope가 prototype이므로 다른 객체이어야 한다.
	@Resource(name="boardDao")
	private IBoardDao boardDao;
	
	@Resource(name="boardDaoPrototype")
	private IBoardDao boardDaoPrototype;

	@Resource(name="boardDaoPrototype")
	private IBoardDao boardDaoPrototype2;
	
	

	
	/**
	* Method : springIocTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너 생성 테스트
	*/
	@Test
	public void springIocTest() {
		/***Given***/
//		// 스프링 컨테이너 생성		// 위의 두줄로 인해 생략 가능
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:kr/or/ddit/ioc/application-ioc-test.xml");
		
		/***When***/
//		IBoardService boardService = (IBoardService)context.getBean("boardService");	// DL
		String msg = boardService.sayHello();
		
		/***Then***/
		assertNotNull(boardService);
		assertEquals("boardDao sayHello", msg);
	
	}
	
	
	@Test
	public void singletonScopeTest() {
		/***Given***/
		// 1. boardSerivce, boardService2 : 같아야 함
		// 2. boardService, boardServiceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton 개념에 따라 다른 객체
		// 3. boardService2, boardServiceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton 개념에 따라 다른 객체

		/***When***/

		/***Then***/
		assertNotNull(boardService);
		assertNotNull(boardService2);
		assertNotNull(boardServiceConstructor);
		assertEquals(boardService, boardService2);
		assertNotEquals(boardService, boardServiceConstructor);
		assertNotEquals(boardService2, boardServiceConstructor);
	}
	
	
	@Test
	public void prototypeScopeTest() {
		/***Given***/
		// 1. boardDao boardDaoPrototype : spring bean id가 다르므로 다른 객체
		// 2. boardDaoPrototype, boardDaoPrototype2 : 두 객체는 같은 스프링 빈이지만 
		//											  scope가 prototype이므로 다른 객체이어야 한다.

		/***When***/

		/***Then***/
		assertNotNull(boardDao);
		assertNotNull(boardDaoPrototype);
		assertNotNull(boardDaoPrototype2);
		
		assertNotEquals(boardDao, boardDaoPrototype);
		assertNotEquals(boardDaoPrototype, boardDaoPrototype2);
		assertNotEquals(boardDao, boardDaoPrototype2);
	}

}
