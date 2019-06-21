package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

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

}
