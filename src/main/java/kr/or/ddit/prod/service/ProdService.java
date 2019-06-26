package kr.or.ddit.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.model.ProdVO;

@Service
public class ProdService implements IProdService {

	@Resource(name="prodDao")
	private IProdDao prodDao;

	
	/**
	* Method : prodList
	* 작성자 : PC23
	* 변경이력 :
	* @param prod_lgu
	* @return
	* Method 설명 : Prod 리스트 조회
	*/
	@Override
	public List<ProdVO> prodList(String prod_lgu) {
		return prodDao.prodList(prod_lgu);
	}


	/**
	* Method : prodCnt
	* 작성자 : PC23
	* 변경이력 :
	* @return
	* Method 설명 : Prod 전체 갯수 
	*/
	@Override
	public int prodCnt() {
		return prodDao.prodCnt();
	}


	/**
	* Method : prodPagingList
	* 작성자 : PC23
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : Prod 페이징 리스트 조회
	*/
	@Override
	public Map<String, Object> prodPagingList(PageVO pageVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("prodList", prodDao.prodPagingList(pageVo));
		
		int prodCnt = prodDao.prodCnt();
		
		int paginationSize = (int) Math.ceil((double)prodCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

}
