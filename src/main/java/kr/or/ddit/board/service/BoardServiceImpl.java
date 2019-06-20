package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.IBoardDao;

public class BoardServiceImpl implements IBoardService {

	private IBoardDao boardDao; 	// property or field

	public BoardServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardServiceImpl(IBoardDao boardDao) {
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
