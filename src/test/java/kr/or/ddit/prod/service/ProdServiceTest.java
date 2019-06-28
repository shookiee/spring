package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class ProdServiceTest extends LogicTestEnv {
	
	@Resource(name="prodService")
	private IProdService prodService;

	
	/**
	* Method : ProdListTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : Prod 리스트 조회 테스트
	*/
	@Test
	public void ProdListTest() {
		/***Given***/
		String prod_lgu = "P101";

		/***When***/
		List<ProdVO> prodList = prodService.prodList(prod_lgu);
		
		/***Then***/
		assertNotNull(prodList);
		assertTrue(prodList.size() == 6);
	}
	
	/**
	* Method : lprodCntTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : Prod 전체 갯수 테스트
	*/
	@Test
	public void prodCntTest() {
		/***Given***/

		/***When***/
		int prodCnt = prodService.prodCnt();
		
		/***Then***/
		assertEquals(74, prodCnt);
	}
	
	
	/**
	* Method : lprodPagingListTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : Prod 페이징 리스트 조회 테스트
	*/
	@Test
	public void lprodPagingListTest() {
		/*** Given ***/
		PageVO pageVo = new PageVO(1, 10);

		/*** When ***/
		Map<String, Object> resultMap = prodService.prodPagingList(pageVo);
		List<LprodVO> prodList = (List<LprodVO>)resultMap.get("prodList");
		int paginationSize = (Integer)resultMap.get("paginationSize");
		
		/*** Then ***/
		assertNotNull(prodList);
		assertEquals(10, prodList.size());
		assertEquals(8, paginationSize);
		
	}
	
	
	/**
	* Method : getProdTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 특정 Prod 조회 테스트
	*/
	@Test
	public void getProdTest() {
		/***Given***/
		String prod_id = "P101000002";

		/***When***/
		ProdVO prodVo = prodService.getProd(prod_id);
		
		/***Then***/
		assertNotNull(prodVo);
		assertEquals("모니터 삼성전자17인치칼라", prodVo.getProd_name());
	}
}
