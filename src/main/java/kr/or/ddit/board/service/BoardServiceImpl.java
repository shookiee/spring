package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.IBoardDao;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao boardDao;

	
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
