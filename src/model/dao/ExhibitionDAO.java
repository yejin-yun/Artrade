package model.dao;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Artwork;
import model.Exhibition;
import model.ExhibitionBuyTicket;

public class ExhibitionDAO {
	private SqlSessionFactory sqlSessionFactory;
	
	public ExhibitionDAO() {
		String resource = "mybatis-config2.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	public Exhibition insertExhibition(Exhibition exh) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int result = sqlSession.getMapper(ExhibitionMapper.class).insertExhibition(exh);
			if(result > 0) {
				sqlSession.commit();
			}
			return exh;
		}
		finally {
			sqlSession.close();
		}
		
	}
	public int deleteExhibition(int exhibitionNo) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(ExhibitionMapper.class).deleteExhibition(exhibitionNo);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;	
		} finally {
			sqlSession.close();
		}
		
	}
	public int updateExhibition(Exhibition exh) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(ExhibitionMapper.class).updateExhibition(exh);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	public int increaseOneVisitorInExhibition(int exhibitionNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(ExhibitionMapper.class).increaseOneVisitorInExhibition(exhibitionNo);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	
	public List<Artwork> selectArtworkInExhibition(int exhibitionNo){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ExhibitionMapper.class).selectArtworkInExhibition(exhibitionNo);	
		} finally {
			sqlSession.close();
		}
	}
	
	public List<Exhibition> getExhibitionListForNotUser(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ExhibitionMapper.class).selectAllExhibitionForNotUser();	
		} finally {
			sqlSession.close();
		}
		
	}
	
	public List<Exhibition> getExhibitionListForUser(int userNo){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ExhibitionMapper.class).selectAllExhibitionForUser(userNo);	
		} finally {
			sqlSession.close();
		}
		
	}
	public List<Exhibition> getExhibitionByTitleForUser(int userNo, String title){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ExhibitionMapper.class).selectExhibitionByTitleForUser(userNo, title);
		} finally {
			sqlSession.close();
		}
	}
	
	public List<Exhibition> getExhibitionByTitleForNotUser(String title){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ExhibitionMapper.class).selectExhibitionByTitleForNotUser(title);
		} finally {
			sqlSession.close();
		}
	}
	
	public ExhibitionBuyTicket insertExhBuyTicket(ExhibitionBuyTicket ebt) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(ExhibitionMapper.class).insertExhBuyTicket(ebt);
			if (result > 0) {
				sqlSession.commit();
			} 
			return ebt;
		} finally {
			sqlSession.close();
		}
	}

	public List<ExhibitionBuyTicket> findExhBuyTicketList(int userNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ExhibitionMapper.class).selectAllExhBuyTicketForUser(userNo);			
		} finally {
			sqlSession.close();
		}
	}
	
	public int countHavingTicket(int userNo, int exhibitionNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ExhibitionMapper.class).countHavingTicket(userNo, exhibitionNo);			
		} finally {
			sqlSession.close();
		}
	}
	
	public Exhibition getExhibitionByNo(int exhibitionNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ExhibitionMapper.class).selectExhibitionByNo(exhibitionNo);			
		} finally {
			sqlSession.close();
		}
	}
	
}
