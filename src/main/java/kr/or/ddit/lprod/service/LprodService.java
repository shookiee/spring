package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

@Service
public class LprodService implements ILprodService {
	
	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	
	
	/**
	* Method : lprodList
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : Lprod 전체 리스트 조회
	*/
	@Override
	public List<LprodVO> lprodList() {
		return lprodDao.lprodList();
	}
	
	
	/**
	 * Method : lprodCnt
	 * 작성자 : PC23
	 * 변경이력 :
	 * @return
	 * Method 설명 : Lprod 전체 갯수
	 */	
	@Override
	public int lprodCnt() {
		return lprodDao.lprodCnt();
	}

	
	/**
	* Method : lprodPagingList
	* 작성자 : PC23
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : Lprod 페이징 리스트 조회
	*/
	@Override
	public Map<String, Object> lprodPagingList(PageVO pageVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("lprodList", lprodDao.lprodPagingList(pageVo));
		
		int lprodCnt = lprodDao.lprodCnt();
		
		int paginationSize = (int) Math.ceil((double)lprodCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

}
