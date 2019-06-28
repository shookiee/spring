package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserControllerTest extends ControllerTestEnv{

	/**
	* Method : userListTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 사용자 전체 리스트 테스트
	 * @throws Exception 
	*/
	@Test
	public void userListTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/list")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("user/userList", mav.getViewName());
		assertEquals(106,  ((List<UserVO>) mav.getModelMap().get("userList")).size());
	}

	
	/**
	* Method : userPagingListTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 사용자 페이징 리스트 테스트
	 * @throws Exception 
	*/
	@Test
	public void userPagingListTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/pagingList")
																					.param("page", "2")
																					.param("pageSize", "10"))
																					.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVO> userList = (List<UserVO>)mav.getModelMap().get("userList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVO pageVo = (PageVO) mav.getModelMap().get("pageVo");
				
		/***Then***/
		// viewName
		// 속성 userList, paginationSize, pageVo
		assertEquals("tiles.userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
		assertEquals(2, pageVo.getPage());
		assertEquals(10, pageVo.getPageSize());
	}
	
	
	/**
	* Method : userPagingListWithoutParameterTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 사용자 페이징 리스트 테스트(page, pageSize 파라미터 없이 호출)
	 * @throws Exception 
	*/
	@Test
	public void userPagingListWithoutParameterTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/pagingList")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVO> userList = (List<UserVO>)mav.getModelMap().get("userList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVO pageVo = (PageVO) mav.getModelMap().get("pageVo");
				
		/***Then***/
		// viewName
		// 속성 userList, paginationSize, pageVo
		assertEquals("tiles.userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
		
//		assertEquals(new PageVO(1, 10), pageVo);		// pageVo equals, hashCode 메서드를 구현해서 객체간 값 비교
		assertEquals(1, pageVo.getPage());
		assertEquals(10, pageVo.getPageSize());
		
	}
	
	
	/**
	* Method : userTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 사용자 상세조회 테스트
	 * @throws Exception 
	*/
	@Test
	public void userTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO userVo = (UserVO) mav.getModelMap().get("userVo");
		
		/***Then***/
		assertEquals("user/user", viewName);
		assertEquals("브라운", userVo.getName());
	}
	
	
	/**
	* Method : userFormTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 사용자 입력화면 요청
	 * @throws Exception 
	*/
	@Test
	public void userFormTest() throws Exception {
		mockMvc.perform(get("/user/form")).andExpect(view().name("user/userForm"));
	}
	
	
	/**
	* Method : userFormPostSuccessTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트(success 시나리오)
	 * @throws Exception 
	*/
	@Test
	public void userFormPostSuccessTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/user/form").file(file)
																					.param("userId", "userTest123")
																					.param("name", "대덕인")
																					.param("alias", "대덕인")
																					.param("addr1", "대전시 중구")
																					.param("addr2", "영민빌딩")
																					.param("zipcd", "34940")
																					.param("birth", "2019-05-31")
																					.param("pass", "userTest1234"))
																					.andExpect(view().name("redirect:/user/pagingList"));

	}
	
	
	/**
	* Method : userFormPostFailTest
	* 작성자 : PC23
	* 변경이력 : 사용자 등록 테스트(fail 시나리오)
	* @throws Exception
	* Method 설명 :
	*/
	@Test
	public void userFormPostFailTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("/user/profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/user/form").file(file)
																					.param("userId", "brown")	// 이미 존재하는 아이디
																					.param("name", "대덕인")
																					.param("alias", "대덕인")
																					.param("addr1", "대전시 중구")
																					.param("addr2", "영민빌딩")
																					.param("zipcd", "34940")
																					.param("birth", "2019-05-31")
																					.param("pass", "userTest1234"))
																					.andExpect(view().name("user/userForm"));

	}
	
	
	/**
	* Method : profileTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 사용자 사진 응답 테스트
	 * @throws Exception 
	*/
	@Test
	public void profileTest() throws Exception  {
		mockMvc.perform(get("/user/profile").param("userId", "brown")).andExpect(status().isOk());
	}
	
	
	/**
	* Method : userModifyGetTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 사용자 수정 화면 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void userModifyGetTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/modify").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO userVo = (UserVO) mav.getModelMap().get("userVo");
		
		/***Then***/
		assertEquals("user/userModify", viewName);
		assertEquals("브라운", userVo.getName());
	}
	
	
	/**
	* Method : userModifyPostTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 사용자 정보 수정 테스트
	 * @throws Exception 
	*/
	@Test
	public void userModifyPostTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));

		/***When***/
		mockMvc.perform(fileUpload("/user/modify").file(file)
				.param("userId", "user14")
				.param("name", "대덕인")
				.param("alias", "대덕인")
				.param("addr1", "대전시 중구")
				.param("addr2", "영민빌딩")
				.param("zipcd", "34940")
				.param("birth", "2019-05-31")
				.param("pass", "userTest1234"))
				.andExpect(view().name("redirect:/user/user"));

		/***Then***/
		
	}
}
