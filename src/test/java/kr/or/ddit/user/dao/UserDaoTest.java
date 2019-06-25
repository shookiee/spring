package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;
public class UserDaoTest extends LogicTestEnv {

	@Resource(name="userDao")
	private IUserDao userDao;
	
	
	/**
	* Method : userListTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 사용자 전체 리스트 조회 테스트
	*/
	@Test
	public void userListTest() {
		/***Given***/

		/***When***/
		List<UserVO> userList = userDao.userList();
		
		/***Then***/
		assertNotNull(userList);
		assertTrue(userList.size() >= 100);
	}

	
	/**
	 * Method : getUserTest
	 * 작성자 : PC23
	 * 변경이력 :
	 * Method 설명 : 사용자 정보 조회 테스트
	 */
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";
		
		/***When***/
		UserVO userVo = userDao.getUser(userId);
		
		/***Then***/
		assertEquals("브라운", userVo.getName());
		assertEquals("곰", userVo.getAlias());
	}
	
	
	/**
	* Method : usersCntTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 사용자 전체 수 조회 테스트
	*/
	@Test
	public void usersCntTest() {
		/***Given***/

		/***When***/
		int usersCnt = userDao.usersCnt();
		
		/***Then***/
		assertEquals(106, usersCnt);
	}
	
	
	/**
	 * Method : insertUserTest 
	 * 작성자 : PC23 
	 * 변경이력 : 
	 * Method 설명 : 사용자 등록 테스트
	 */
	@Test
	public void insertUserTest() {
		/*** Given ***/
		// 사용자 정보를 담고 있는 vo 객체 준비
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVO userVo = null;
		try {
			userVo = new UserVO("tes", "shookie913", "tes", "shookie1234",
					"대전광역시 중구 중앙로 76", "영민빌딩 2층 204호", "34940",
					sdf.parse("2019-05-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/*** When ***/
		// userDao.insertUser()
		int insertCnt = userDao.insertUser(userVo);

		/*** Then ***/
		// insertCnt(1)
		assertEquals(1, insertCnt);
		userDao.deleteUser(userVo.getUserId());
	}
	
	
	/**
	* Method : updateUserTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 사용자 정보 수정 테스트
	*/
	@Test
	public void updateUserTest() {
		/***Given***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVO userVo = null;
		
		try {
			userVo = new UserVO("수정쓰", "user13", "수정별명", "1234123", "대전광역시 중구 중앙로 76", "영민빌딩 2층 204호", "34940", sdf.parse("2019-05-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/***When***/
		int updateCnt = userDao.updateUser(userVo);
				
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	
	/**
	* Method : userPagingListTest
	* 작성자 : PC23
	* 변경이력 :
	* Method 설명 : 사용자 페이징 리스트 조회 테스트
	*/
	@Test
	public void userPagingListTest() {
		/***Given***/
		PageVO pageVo = new PageVO(1,  10);

		/***When***/
		List<UserVO> userList = userDao.userPagingList(pageVo);
		
		/***Then***/
		assertNotNull(userList);
		assertEquals(10, userList.size());
	}
}
