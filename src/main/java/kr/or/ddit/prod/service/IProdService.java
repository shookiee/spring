package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;

public interface IProdService {
	
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
	Map<String, Object> prodPagingList(PageVO pageVo);
}
