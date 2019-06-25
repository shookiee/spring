package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVO;

@Service
public class UserService implements IUserService {

	@Resource(name="userDao")
	private IUserDao userDao;
	
	
	/**
	* Method : userList
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Override
	public List<UserVO> userList() {
		return userDao.userList();
	}

	
	/**
	 * Method : getUser
	 * 작성자 : PC23
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 정보 조회
	 */
	@Override
	public UserVO getUser(String userId) {
		return userDao.getUser(userId);
	}

	
	/**
	 * Method : usersCnt
	 * 작성자 : PC23
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 전체 수 조회
	 */
	@Override
	public int usersCnt() {
		return userDao.usersCnt();
	}

	
	/**
	* Method : insertUser
	* 작성자 : PC23
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(UserVO userVo) {
		return userDao.insertUser(userVo);
	}


	/**
	* Method : deleteUser
	* 작성자 : PC23
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	@Override
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}


	/**
	* Method : updateUser
	* 작성자 : PC23
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 정보 수정
	*/
	@Override
	public int updateUser(UserVO userVo) {
		return userDao.updateUser(userVo);
	}


	/**
	* Method : userPagingList
	* 작성자 : PC23
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	@Override
	public Map<String, Object> userPagingList(PageVO pageVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("userList", userDao.userPagingList(pageVo));
		
		int usersCnt = userDao.usersCnt();

		int paginationSize = (int)Math.ceil((double)usersCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}


	/**
	* Method : userListForPassEncrypt
	* 작성자 : PC23
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 비밀번호 암호화 적용 대상 사용자 전체 조회
	*/
	@Override
	public List<UserVO> userListForPassEncrypt(SqlSession sqlSession) {
		return userDao.userListForPassEncrypt(sqlSession);
	}


	/**
	* Method : updateUserEncryptPass
	* 작성자 : PC23
	* 변경이력 :
	* @param sqlSession
	* @param userVo
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 적용
	*/
	@Override
	public int updateUserEncryptPass(SqlSession sqlSession, UserVO userVo) {
		return userDao.updateUserEncryptPass(sqlSession, userVo);
	}

	
}
