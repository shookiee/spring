package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;

@Repository
public class ProdDao implements IProdDao {

	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	
	/**
	* Method : prodList
	* 작성자 : PC23
	* 변경이력 :
	* @param prod_lgu
	* @return
	* Method 설명 : Prod 리스트 조회
	*/
	@Override
	public List<ProdVO> prodList(String prod_lgu) {
		return sqlSession.selectList("prod.prodList", prod_lgu);
	}


	/**
	* Method : prodCnt
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : Prod 전체 갯수 
	*/
	@Override
	public int prodCnt() {
		return sqlSession.selectOne("prod.prodCnt");
	}


	/**
	* Method : prodPagingList
	* 작성자 : PC23
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : Prod 페이징 리스트 조회
	*/
	@Override
	public List<ProdVO> prodPagingList(PageVO pageVo) {
		return sqlSession.selectList("prod.prodPagingList", pageVo);
	}

}
