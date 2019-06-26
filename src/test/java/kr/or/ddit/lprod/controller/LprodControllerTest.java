package kr.or.ddit.lprod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.ControllerTestEnv;

public class LprodControllerTest extends ControllerTestEnv {

	private static final Logger logger = LoggerFactory.getLogger(LprodControllerTest.class);
	
	/**
	* Method : lprodPagingListTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : Lprod 페이징 리스트 테스트
	 * @throws Exception 
	*/
	@Test
	public void lprodPagingListTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/pagingList")
																					.param("page", "2")
																					.param("pageSize", "10"))
																					.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<LprodVO> lprodList = (List<LprodVO>) mav.getModelMap().get("lprodList");
		int paginationSize = (int) mav.getModelMap().get("paginationSize");
		PageVO pageVo = (PageVO) mav.getModelMap().get("pageVo");
		
		/***Then***/
		assertEquals("lprod/lprodPagingList", viewName);
		assertEquals(7, lprodList.size());
		assertEquals(2, paginationSize);
		assertEquals(2, pageVo.getPage());
		assertEquals(10, pageVo.getPageSize());
	}

}
