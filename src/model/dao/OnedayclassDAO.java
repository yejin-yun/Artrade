package model.dao;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Onedayclass;
import model.OcApply;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * Onedayclass 테이블에서 커뮤니티 정보를 추가, 수정, 삭제, 검색 수행 
 * OcApply 테이블에서 정보를 추가
 */


public class OnedayclassDAO {
	private SqlSessionFactory sqlSessionFactory;
	
	public OnedayclassDAO(){
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/**
	 * 원데이클래스 테이블에 새로운 행 생성 / 새로운 원데이클래스 강좌 생성
	 */
	
	public Onedayclass insertOnedayclass(Onedayclass odc)
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int result = sqlSession.getMapper(OnedayclassMapper.class).insertOnedayclass(odc);
			if(result > 0) {
				sqlSession.commit();
			}
			return odc;
		}
		finally {
			sqlSession.close();
		}
		
	}
	
	/**
	 * 주어진 원데이클래스 번호에 해당하는 원데이클래스 삭제
	 */
	
	public int deleteOnedayclass(int onedayclassNo) 
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(OnedayclassMapper.class).deleteOnedayclass(onedayclassNo);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;	
		} finally {
			sqlSession.close();
		}
		
		
	}
	
	/**
	 * 주어진 원데이클래스 번호에 해당하는 원데이클래스 반환
	 */
	public Onedayclass getOnedayclass(int onedayclassNo) 
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(OnedayclassMapper.class).selectOnedayclassByNo(onedayclassNo);			
		} finally {
			sqlSession.close();
		}
		
		
	}
	
	
	/**
	 * 원데이클래스의 신청자 수를 +1 더함
	 */
	public int increaseOneApplicant(int onedayclassNo) 
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(OnedayclassMapper.class).increaseOneApplicantInOnedayclass(onedayclassNo);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 기존 원데이클래스 정보를 수정
	 */
	public int updateOnedayclass(Onedayclass odc) 
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(OnedayclassMapper.class).updateOnedayclass(odc);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 원데이클래스 테이블의 모든 행을 리스트로 반환
	 */
	public List<Onedayclass> getOnedayclassList() 
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(OnedayclassMapper.class).selectAllOnedayclass();			
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * OcApply(원데이클래스 신청목록)테이블에 새로운 행을 생성
	 */
	public OcApply insertOcApply (OcApply oca) 
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(OnedayclassMapper.class).insertOcApply(oca);
			if (result > 0) {
				sqlSession.commit();
			} 
			return oca;
		} finally {
			sqlSession.close();
		}
	}

	
}
