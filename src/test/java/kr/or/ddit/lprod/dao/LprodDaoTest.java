package kr.or.ddit.lprod.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class LprodDaoTest extends LogicTestEnv {

	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	
	
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
		List<LprodVO> lprodList = lprodDao.lprodList();
		
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
		int lprodCnt = lprodDao.lprodCnt();
		
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
		/***Given***/
		PageVO pageVo = new PageVO(1, 10);

		/***When***/
		List<LprodVO> lprodList = lprodDao.lprodPagingList(pageVo);

		/***Then***/
		assertNotNull(lprodList);
		assertEquals(10, lprodList.size());
	}

}
