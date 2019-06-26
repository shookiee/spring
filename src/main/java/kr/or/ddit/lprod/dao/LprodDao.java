package kr.or.ddit.lprod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

@Repository
public class LprodDao implements ILprodDao {

	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	/**
	* Method : lprodList
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : Lprod 전체 리스트 조회
	*/
	@Override
	public List<LprodVO> lprodList() {
		return sqlSession.selectList("lprod.lprodList");
	}

	
	/**
	 * Method : lprodCnt
	 * 작성자 : PC23
	 * 변경이력 :
	 * @return
	 * Method 설명 : Lprod 전체 갯수
	 */
	@Override
	public int lprodCnt() {
		return sqlSession.selectOne("lprod.lprodCnt");
	}

	
	/**
	* Method : lprodPagingList
	* 작성자 : PC23
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : Lprod 페이징 리스트 조회
	*/
	@Override
	public List<LprodVO> lprodPagingList(PageVO pageVo) {
		return sqlSession.selectList("lprod.lprodPagingList", pageVo);
	}

	

}
