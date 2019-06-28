package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;

public interface IProdDao {

	/**
	* Method : prodList
	* 작성자 : PC23
	* 변경이력 :
	* @param prod_lgu
	* @return
	* Method 설명 : Prod 리스트 조회
	*/
	List<ProdVO> prodList(String prod_lgu);
	
	
	/**
	* Method : prodCnt
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : Prod 전체 갯수
	*/
	int prodCnt();
	
	
	/**
	* Method : prodPagingList
	* 작성자 : PC23
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : Prod 페이징 리스트 조회
	*/
	List<ProdVO> prodPagingList(PageVO pageVo);
	
	
	/**
	* Method : getProd
	* 작성자 : PC23
	* 변경이력 :
	* @param prod_id
	* @return
	* Method 설명 : 특정 Prod 정보 조회
	*/
	ProdVO getProd(String prod_id);
}
