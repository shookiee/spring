package kr.or.ddit.board.dao;

import org.springframework.stereotype.Repository;

// spring bean 이름 : 인스턴스 생성규칙 --> 클래스명에서 첫글자를 소문자로
@Repository
public class BoardDao implements IBoardDao {

	@Override
	public String sayHello() {
		return "boardDao sayHello";
	}

	

}
