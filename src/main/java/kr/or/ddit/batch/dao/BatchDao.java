package kr.or.ddit.batch.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.batch.model.BatchVO;

@Repository
public class BatchDao implements IBatchDao {

	@Resource(name = "sqlSession")
	SqlSessionTemplate sqlSession;
	
	
	/**
	* Method : deleteDaily
	* 작성자 : PC23
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일 실적 일괄 삭제
	*/
	@Override
	public int deleteDaily(String ym) {
		return sqlSession.delete("batch.deleteDaily", ym);
	}

	
	/**
	* Method : createDaily
	* 작성자 : PC23
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일 실적 일괄 생성
	*/
	@Override
	public int createDaily(String ym) {
		return sqlSession.insert("batch.createDaily", ym);
	}


	/**
	* Method : insertBatch
	* 작성자 : PC23
	* 변경이력 :
	* @param batchVo
	* @return
	* Method 설명 : 배치 실행 데이터 생성
	*/
	@Override
	public int insertBatch(BatchVO batchVo) {
		return sqlSession.insert("batch.insertBatch", batchVo);
	}


	/**
	* Method : updateBatch
	* 작성자 : PC23
	* 변경이력 :
	* @param batchVo
	* @return
	* Method 설명 : 배치 정보 수정
	*/
	@Override
	public int updateBatch(BatchVO batchVo) {
		return sqlSession.update("batch.updateBatch", batchVo);
	}

}
