package kr.or.ddit.lprod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

public interface ILprodService {

	/**
	* Method : lprodList
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : Lprod 전체 리스트 조회
	*/
	List<LprodVO> lprodList();
	
	
	/**
	 * Method : lprodCnt
	 * 작성자 : PC23
	 * 변경이력 :
	 * @return
	 * Method 설명 : Lprod 전체 갯수
	 */	
	int lprodCnt();
	
	
	/**
	* Method : lprodPagingList
	* 작성자 : PC23
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : Lprod 페이징 리스트 조회
	*/
	Map<String, Object> lprodPagingList(PageVO pageVo);
}
