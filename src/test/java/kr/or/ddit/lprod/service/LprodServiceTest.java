package kr.or.ddit.lprod.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class LprodServiceTest extends LogicTestEnv{
	
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	
	/**
	* Method : lprodListTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : Lprod 전체 리스트 조회 테스트
	*/
	@Test
	public void lprodListTest() {
		/***Given***/

		/***When***/
		List<LprodVO> lprodList = lprodService.lprodList();
		
		/***Then***/
		assertNotNull(lprodList);
		assertTrue(lprodList.size() == 17);
	}
	
	
	/**
	* Method : lprodCntTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : Lprod 전체 갯수 테스트
	*/
	@Test
	public void lprodCntTest() {
		/***Given***/

		/***When***/
		int lprodCnt = lprodService.lprodCnt();
		
		/***Then***/
		assertEquals(17, lprodCnt);
	}
	
	
	/**
	* Method : lprodPagingListTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : Lprod 페이징 리스트 조회 테스트
	*/
	@Test
	public void lprodPagingListTest() {
		/*** Given ***/
		PageVO pageVo = new PageVO(1, 10);

		/*** When ***/
		Map<String, Object> resultMap = lprodService.lprodPagingList(pageVo);
		List<LprodVO> lprodList = (List<LprodVO>)resultMap.get("lprodList");
		int paginationSize = (Integer)resultMap.get("paginationSize");
		
		/*** Then ***/
		assertNotNull(lprodList);
		assertEquals(10, lprodList.size());
		assertEquals(2, paginationSize);
	}


}
