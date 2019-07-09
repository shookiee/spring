package kr.or.ddit.batch.dao;

import kr.or.ddit.batch.model.BatchVO;

public interface IBatchDao {

	/**
	* Method : deleteDaily
	* 작성자 : PC23
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일 실적 일괄 삭제
	*/
	int deleteDaily(String ym);
	
	
	/**
	* Method : createDaily
	* 작성자 : PC23
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일 실적 일괄 생성
	*/
	int createDaily(String ym);


	/**
	* Method : insertBatch
	* 작성자 : PC23
	* 변경이력 :
	* @param batchVo
	* @return
	* Method 설명 : 배치 실행 데이터 생성
	*/
	int insertBatch(BatchVO batchVo);


	/**
	* Method : updateBatch
	* 작성자 : PC23
	* 변경이력 :
	* @param batchVo
	* @return
	* Method 설명 : 배치 정보 수정
	*/
	int updateBatch(BatchVO batchVo);
	
}
