package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Artwork;
import model.ArtworkOrder;
//import model.Exhibition;
//import model.Onedayclass;
//import model.TradeWork;
import model.User;

import model.*;

public class ArtworkDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ArtworkDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	//likecnt 추가하는. isSoldOut바꾸는..
	public int updateSoldOut(int artworkNo) throws SQLException {
		String sql = "UPDATE ARTWORK "
					+ "SET isSoldOut = 1 "
					+ "WHERE artworkNo=?";
		Object[] param = new Object[] {artworkNo};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	
	//likeCnt +/-
	public int increaseOneLikeCnt(int artworkNo) throws SQLException {
		String sql = "UPDATE ARTWORK "
					+ "SET likeCnt = likeCnt + 1 "
					+ "WHERE artworkNo=?";
		Object[] param = new Object[] {artworkNo};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	public int decreaseOneLikeCnt(int artworkNo) throws SQLException {
		String sql = "DELETE FROM ARTWORK WHERE artworkNo=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artworkNo});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	
	/** Artwork 테이블에 새로운 행을 생성하고 PK를 반환해주는 메소드*/
	public int insertArtwork(Artwork artwork) throws SQLException {
		
		//수정본
//		String sql = "INSERT INTO ARTWORK (artworkNo, image, workSize, title, price, artistName, description, isSoldOut)"+
//					"VALUES (seq_pk.nextval, ?, ?, ?, ?, ?, ?, ?)";	
		String sql = "INSERT INTO ARTWORK (artworkNo, image, workSize, title, price, artistName, description, isSoldOut, sellerNo)"+
					"VALUES (seq_pk.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";	
		
		Object[] param = new Object[] { artwork.getImage(), artwork.getWorkSize(), artwork.getTitle(),
										artwork.getPrice(), artwork.getArtistName(), artwork.getDescription(), artwork.getIsSoldOut()
										, artwork.getSellerNo()};
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[]={"artworkNo"};
		
		try {
			int result = jdbcUtil.executeUpdate(key);     // insert 문 실행
			ResultSet rs = jdbcUtil.getGeneratedKeys();    // 생성된 PK 값을 포함한 result set 객체 반환
			
			int generatedKey = 0;
			if(rs.next()) {
				generatedKey = rs.getInt(1);     //  PK 값을 읽음
			    // ... 위에서 구한 PK 값을 이용해서 필요한 작업 구현
			}
			
			return generatedKey;   // 또는 Test 테이블에 대응되는 VO/DTO 객체를 생성하여 PK 값을 저장한 후 리턴
			   
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	
	/** Artwork의 간략한 정보 (비로그인용) --> /artwork/view.jsp*/
	public List<SimpleArtworkInfo> getSimpleArtworkInfoForNotUser() throws SQLException {
		
		String sql = "SELECT artworkNo, image, title, artistName, price " + 
					" FROM ARTWORK  " + 
					" WHERE isOrderAvailable != 0 ";	

		jdbcUtil.setSqlAndParameters(sql, null);
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
		
			List<SimpleArtworkInfo> simpleArtworkList = new ArrayList<SimpleArtworkInfo>();
			
			while (rs.next()) {
				SimpleArtworkInfo simpleArtwork = new SimpleArtworkInfo();
				simpleArtwork.setArtworkNo(rs.getInt("artworkNo"));
				simpleArtwork.setImage(rs.getString("image"));
				simpleArtwork.setTitle(rs.getString("title"));
				simpleArtwork.setArtistName(rs.getString("artistName"));
				simpleArtwork.setPrice(rs.getInt("price"));
	
				simpleArtworkList.add(simpleArtwork);	
			}		
			return simpleArtworkList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
		
	}
	
	
	/** Artwork의 간략한 정보 & (로그인용)wishlist에 추가한 artwork인지 확인 --> /artwork/view.jsp*/
	public List<SimpleArtworkInfo> getSimpleArtworkInfoForUser(int userNo) throws SQLException {
		
		String sql = "SELECT a.artworkNo AS artworkNo, a.image AS image, a.title AS title, a.artistName AS artistName, "
					+ "a.price AS price, NVL(w.wishArtworkNo, 0) AS wishArtworkNo" + 
					" FROM ARTWORK a, WISHARTWORK w " + 
					" WHERE a.artworkNo = w.artworkNo (+) "
					+ " AND w.userNo (+) = ?"
					+ " AND a.isOrderAvailable != 0 ";	

		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
		
			List<SimpleArtworkInfo> simpleArtworkList = new ArrayList<SimpleArtworkInfo>();
			
			while (rs.next()) {
				SimpleArtworkInfo simpleArtwork = new SimpleArtworkInfo();
				simpleArtwork.setArtworkNo(rs.getInt("artworkNo"));
				simpleArtwork.setImage(rs.getString("image"));
				simpleArtwork.setTitle(rs.getString("title"));
				simpleArtwork.setArtistName(rs.getString("artistName"));
				simpleArtwork.setPrice(rs.getInt("price"));
				if( rs.getInt("wishArtworkNo") == 0 ) {
					simpleArtwork.setIsInWishlist(0);
				} else {
					simpleArtwork.setIsInWishlist(1);
				}
				simpleArtworkList.add(simpleArtwork);
			}		
			return simpleArtworkList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
		
	}
	
	/** Artwork 테이블의 특정 행을 삭제*/
	public int deleteArtwork(int artworkNo) throws SQLException {
		
		String sql = "DELETE FROM ARTWORK WHERE artworkNo=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artworkNo});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	/** 비로그인/비사용자용  작품 detail 페이지*/
	public Artwork getArtworkByNoForNotUser(int artworkNo) throws SQLException{
//		String sql = "SELECT artworkNo, image, workSize, title, price, likeCnt, artistName, description, isSoldOut "
//				+ " FROM ARTWORK "
//				+ " WHERE artworkNo = ?";
		//수정본
		String sql = "SELECT artworkNo, image, workSize, title, price, likeCnt, artistName, description, isSoldOut, sellerNo "
				+ " FROM ARTWORK "
				+ " WHERE artworkNo = ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artworkNo});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			
			Artwork artwork = null;
			if (rs.next()) {
				artwork = new Artwork();
				
				artwork.setArtworkNo(rs.getInt("artworkNo"));
				artwork.setImage(rs.getString("image"));
				artwork.setWorkSize(rs.getString("workSize"));
				artwork.setTitle(rs.getString("title"));
				artwork.setPrice(rs.getInt("price"));
				artwork.setLikeCnt(rs.getInt("likeCnt"));
				artwork.setArtistName(rs.getString("artistName"));
				artwork.setDescription(rs.getString("description"));
				artwork.setIsSoldOut(rs.getInt("isSoldOut"));
				//수정
				artwork.setSellerNo(rs.getInt("sellerNo"));
				
				return artwork;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
		
	}
	
	
	/** artworkNo(입력값)에 해당하는 Artwork테이블의 행을 artwork 객체로 반환*/
	public Artwork getArtworkByNoForUser(int userNo, int artworkNo) throws SQLException{
		
		//수정본
		String sql = "SELECT artworkNo, image, workSize, title, price, likeCnt, artistName, description, isSoldOut, sellerNo "
					+ " FROM ARTWORK "
					+ " WHERE artworkNo = ?";
		
		String sql2 = "SELECT artworkNo, userNo, NVL(wishArtworkNo, 0) AS wishArtworkNo "
						+ "FROM wishArtwork "
						+ "WHERE artworkNo = ? AND userNo = ?";

		String sql3 = "SELECT artworkNo, userNo, NVL(cartartworkno, 0) AS cartArtworkNo "
						+ "FROM CARTARTWORK "
						+ "WHERE artworkNo = ? AND userNo = ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artworkNo});	// JDBCUtil�� query���� �Ű� ���� ����
		
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			
			Artwork artwork = null;
			if (rs.next()) {
				artwork = new Artwork();
				
				artwork.setArtworkNo(rs.getInt("artworkNo"));
				artwork.setImage(rs.getString("image"));
				artwork.setWorkSize(rs.getString("workSize"));
				artwork.setTitle(rs.getString("title"));
				artwork.setPrice(rs.getInt("price"));
				artwork.setLikeCnt(rs.getInt("likeCnt"));
				artwork.setArtistName(rs.getString("artistName"));
				artwork.setDescription(rs.getString("description"));
				artwork.setIsSoldOut(rs.getInt("isSoldOut"));
				//수정
				artwork.setSellerNo(rs.getInt("sellerNo"));
				//return artwork;
			}
			
			jdbcUtil.setSqlAndParameters(sql2, new Object[] {artworkNo, userNo});
			ResultSet rs2 = jdbcUtil.executeQuery();
			//boolean found = rs2.next();rs.isBeforeFirst() && 
			if(rs2.next()) {
				if( rs2.getInt("wishArtworkNo") != 0 ) artwork.setIsInWishList(1);
			} else {
				artwork.setIsInWishList(0);
			}
			
			jdbcUtil.setSqlAndParameters(sql3, new Object[] {artworkNo, userNo});
			ResultSet rs3 = jdbcUtil.executeQuery();
			if(rs3.next()) {
				if( rs3.getInt("cartArtworkNo") != 0 ) artwork.setIsInCart(1);
			} else {
				artwork.setIsInCart(0);
			}
			
			return artwork;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
		
	}
	
	
	/** Artwork테이블의 행을 List<Artwork>로 반환*/
	public List<Artwork> getArtworkList() throws SQLException{
		String sql = "SELECT artworkNo, image, workSize, title, price, likeCnt, artistName, description, isSoldOut "
					+ " FROM ARTWORK ";
		
		jdbcUtil.setSqlAndParameters(sql, null);
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
		
			List<Artwork> artworkList = new ArrayList<Artwork>();
			
			while (rs.next()) {
				Artwork artwork = new Artwork();
				
				artwork.setArtworkNo(rs.getInt("artworkNo"));
				artwork.setImage(rs.getString("image"));
				artwork.setWorkSize(rs.getString("workSize"));
				artwork.setTitle(rs.getString("title"));
				artwork.setPrice(rs.getInt("price"));
				artwork.setLikeCnt(rs.getInt("likeCnt"));
				artwork.setArtistName(rs.getString("artistName"));
				artwork.setDescription(rs.getString("description"));
				artwork.setIsSoldOut(rs.getInt("isSoldOut"));
				
				artworkList.add(artwork);
			}		
			return artworkList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
		
	}
	
	/** #키워드 검색*/
	public List<SimpleArtworkInfo> searchArtworkByKeyword(String keyword) throws SQLException{
		String sql = "SELECT artworkNo, image, title , artistName, price, NVL(w.wishArtworkNo, 0) AS wishArtworkNo "
					+ "FROM ARTWORK a, KEYWORD k, WISHARTWORK w "
					+ "WHERE a.artworkNo = w.artworkNo (+) "
					+ "AND a.artworkNo = k.artworkNo "
					+ "AND k.keyword LIKE ?";
		//title LIKE ‘%key%’ OR artist LIKE ‘%key%
		//+ "AND title LIKE ? OR artistName LIKE '%key%' "
		Object[] param = new Object[] { "%"+keyword+"%"};
		
		jdbcUtil.setSqlAndParameters(sql, param);
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
		
			List<SimpleArtworkInfo> simpleArtworkList = new ArrayList<SimpleArtworkInfo>();
			
			while (rs.next()) {
				SimpleArtworkInfo simpleArtwork = new SimpleArtworkInfo();
				simpleArtwork.setArtworkNo(rs.getInt("artworkNo"));
				simpleArtwork.setImage(rs.getString("image"));
				simpleArtwork.setTitle(rs.getString("title"));
				simpleArtwork.setArtistName(rs.getString("artistName"));
				simpleArtwork.setPrice(rs.getInt("price"));
				if( rs.getInt("wishArtworkNo") == 0 ) {
					simpleArtwork.setIsInWishlist(0);
				} else {
					simpleArtwork.setIsInWishlist(1);
				}
				
				simpleArtworkList.add(simpleArtwork);
			}		
			return simpleArtworkList;										
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
		
		
	}
	
	/** #키워드가 아닌 일반 작품제목/작가명 검색*/
	public List<SimpleArtworkInfo> searchArtworkByKey(String key) throws SQLException
	{
		String sql = "SELECT artworkNo, image, title , artistName, price, NVL(w.wishArtworkNo, 0) AS wishArtworkNo "
				+ "FROM ARTWORK a, KEYWORD k, WISHARTWORK w "
				+ "WHERE a.artworkNo = w.artworkNo (+) "
				+ "AND a.artworkNo = k.artworkNo "
				+ "AND a.title LIKE ? OR a.artistName LIKE ? ";

		Object[] param = new Object[] { "%"+key+"%", "%"+key+"%"};
	
		jdbcUtil.setSqlAndParameters(sql, param);
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
		
			List<SimpleArtworkInfo> simpleArtworkList = new ArrayList<SimpleArtworkInfo>();
			
			while (rs.next()) {
				SimpleArtworkInfo simpleArtwork = new SimpleArtworkInfo();
				simpleArtwork.setArtworkNo(rs.getInt("artworkNo"));
				simpleArtwork.setImage(rs.getString("image"));
				simpleArtwork.setTitle(rs.getString("title"));
				simpleArtwork.setArtistName(rs.getString("artistName"));
				simpleArtwork.setPrice(rs.getInt("price"));
				if( rs.getInt("wishArtworkNo") == 0 ) {
					simpleArtwork.setIsInWishlist(0);
				} else {
					simpleArtwork.setIsInWishlist(1);
				}
				
				simpleArtworkList.add(simpleArtwork);
			}		
			return simpleArtworkList;										
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	public int insertKeyword(int artworkNo, String keyword) throws SQLException //�궎�썙�뱶 �벑濡�
	{
		String sql = "INSERT INTO KEYWORD(artworkNo, keyword) "
					+ "VALUES (?, ?) ";
	
		Object[] param = new Object[] { artworkNo, keyword };
		
		jdbcUtil.setSqlAndParameters(sql, param);
		//String key[]={"artworkNo"};
		
		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
		
	}
	
	/*
	public List<Exhibition> getExhByArtwork(int artworkNo) throws SQLException //�빐�떦 �옉�뭹�쓣 �쟾�떆�븯怨� �엳�뒗 �쟾�떆�쉶瑜� 諛섑솚

	{
		Connection conn = null;
		PreparedStatement pStmt = null;			// PreparedStatment 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
		ResultSet rs = null;
		
		String query = "SELECT exhibitionNo, title, explanation, period, isFree, price, visitor, image "+
						"FROM Exhibition WHERE artworkNo = ?";
		
		try {
			conn = cm.getConnection();	// DBMS占쏙옙占쏙옙 占쏙옙占쏙옙 획占쏙옙 
			pStmt = conn.prepareStatement(query);	// Connection占쏙옙占쏙옙 PreparedStatement 占쏙옙체 占쏙옙占쏙옙
			
			pStmt.setInt(1, artworkNo);
			rs = pStmt.executeQuery();
			
			List<Exhibition> exhList = null;
			while(rs.next()){
				Exhibition exh = new Exhibition();
				
				exh.setExhibitionNo(rs.getInt("exhibitionNo"));
				exh.setTitle(rs.getString("title"));
				exh.setDescription(rs.getString("explanation"));
				exh.setPeriod(rs.getString("period"));
				if( rs.getInt("isFree") == 1) {
					exh.setIsFree(true);
				}
				else { 
					exh.setIsFree(false);
				}
				exh.setPrice(rs.getInt("price"));
				exh.setVisitor(rs.getInt("visitor"));
				exh.setImage(rs.getString("image"));
				
				exhList.add(exh);
			}
			
			return exhList;
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		} finally {		// 占쌘울옙 占쌥놂옙
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}	
		return null;
	}*/
}
