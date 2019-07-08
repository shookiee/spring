package kr.or.ddit.board.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.IBoardDao;

@Transactional		// <aop:config>
@Service
public class BoardService implements IBoardService {
	
	@Resource(name="boardDao")
	private IBoardDao boardDao; 	// property or field
	
	private String name;
	
	public BoardService() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public BoardService(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public IBoardDao getBoardDao() {
		return boardDao;
	}

	public void setBoardDao(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public String sayHello() {
		return boardDao.sayHello();
	}
	
	
}
