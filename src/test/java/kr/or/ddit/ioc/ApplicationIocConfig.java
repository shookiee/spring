package kr.or.ddit.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;

@Configuration
public class ApplicationIocConfig {

//	<bean id="boardDao" class="kr.or.ddit.board.dao.BoardDaoImpl" />
	@Bean
	public IBoardDao boardDao() {	// 메서드 이름이 name이 됨
		return new BoardDao();
	}
	
	
/*	<bean id="boardService"
			class="kr.or.ddit.board.service.BoardServiceImpl" scope="singleton">
			<!-- boardService의 field, property -->
			<!-- setter injection -->
			<property name="boardDao" ref="boardDao" />
	</bean>*/
	@Bean
	public BoardService boardService () {
		BoardService boardService = new BoardService();
		boardService.setName("brown");
		boardService.setBoardDao(boardDao());
		return boardService;
	}
	
}
