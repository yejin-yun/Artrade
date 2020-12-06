package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Artwork;
import model.ArtworkOrder;
import model.Exhibition;
import model.SimpleArtworkInfo;
//import model.Onedayclass;
//import model.TradeWork;
import model.User;

public class UserDAO {
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	private ConnectionManager cm = new ConnectionManager();
	
	/** 
	 *  USERINFO 테이블에 새로운 행 삽입
	 *  */
	public int insertUser(User user) throws SQLException {
		String sql = "INSERT INTO USERINFO (userNo, userId, password, name, nickname, email, phone) " +
				"VALUES (seq_pk.nextval, ?, ?, ?, ?, ?, ?)";	
		Object[] param = new Object[] {user.getUserId(), user.getPassword(), user.getName(), 
										user.getNickname(), user.getEmail(), user.getPhone() };				
		jdbcUtil.setSqlAndParameters(sql, param);	
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
		
	}
	
	/** 
	 *  USERINFO 테이블의  입력값인 userNo에 해당하는 행 삭제
	 *  */
	public int deleteUser(int userNo) throws SQLException {
		String sql = "DELETE FROM USERINFO WHERE userNo=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});	

		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}
	
	/** userNo 값을 받아서 해당 USERINFO 행을 USER 객체로  반환한다.
	 */
	public User getUserInfoByNo(int userNo) throws SQLException {
        String sql = "SELECT userNo, userId, password, name, nickname, email, phone "
					+ "FROM USERINFO "
					+ "WHERE userNo = ?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});	// JDBCUtil�� query���� �Ű� ���� ����
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				User user = new User();
				
				user.setUserNo(rs.getInt("userNo"));
				user.setUserId(rs.getString("userId"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
}
	

	
	
	public List<User> getUserInfoByName (String name) {
		//: user name�쓣 �씠�슜�빐 user �젙蹂대�� 李얠븘 諛섑솚
		Connection conn = null;
		PreparedStatement pStmt = null;			
		ResultSet rs = null;
		
		String query = "SELECT userNo, userId, password, name, nickname, email, phone "
						+ "FROM USERINFO  "
						+ "WHERE name = ?";
		List<User> userList = null;

		try {
			conn = cm.getConnection();	// DBMS���쓽 �뿰寃� �쉷�뱷 
			pStmt = conn.prepareStatement(query);	
			
			pStmt.setString(1, name);

			int result = pStmt.executeUpdate();
			
			while(rs.next()){
				User user = new User();

				pStmt.setInt(1, user.getUserNo());
				pStmt.setString(2,  user.getUserId());
				pStmt.setString(3, user.getPassword());
				pStmt.setString(4, user.getName());
				pStmt.setString(5, user.getNickname());
				pStmt.setString(6, user.getEmail());
				pStmt.setString(7, user.getPhone());
				
				userList.add(user);
			}
			return userList;	

			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {		// �옄�썝 諛섎궔
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
	}
	
	public User getUserInfoById(String userId) throws SQLException {
		
		String sql = "SELECT userNo, userId, password, name, nickname, email, phone "
					+ "FROM USERINFO "
					+ "WHERE userId = ?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�� query���� �Ű� ���� ����
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				User user = new User();
				
				user.setUserNo(rs.getInt("userNo"));
				user.setUserId(rs.getString("userId"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
		
	}
	
	/** USERINFO 테이블의 행을 수정. 회원정보 수정 
	 * */
	public int updateUserInfo(User user) throws SQLException {
		String sql = " UPDATE USERINFO  "
				+ " SET password = ?, name = ?, nickname = ?, email = ?, phone = ? "
				+ " WHERE userNo = ? ";
		Object[] param = new Object[] {user.getPassword(), user.getName(), user.getNickname(), user.getEmail(), user.getPhone(), user.getUserNo()};				
		jdbcUtil.setSqlAndParameters(sql, param);	
			
		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}
	
	
	
	/** userNo, artworkNo 을 받아서 WISHARTWORK 테이블에 새로운 행을 생성 / 사용자 관점 : 위시리스트에 특정 artwork를 추가하였음
	 */
	public int insertWishArtwork(int userNo, int artworkNo) throws SQLException {
		String sql = "INSERT INTO WISHARTWORK (wishArtworkNo, userNo, artworkNo) " +
					"VALUES (seq_pk.nextval, ?, ?)"	;
		Object[] param = new Object[] { userNo, artworkNo };				
		jdbcUtil.setSqlAndParameters(sql, param);	
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	/** userNo, artworkNo 에 해당하는  WISHARTWORK 테이블의 행을 삭제 / 사용자 관점 : 위시리스트에서 특정 artwork를 삭제하였음
	 */
	public int deleteWishArtwork(int userNo, int artworkNo) throws SQLException {
		String sql = "DELETE FROM WISHARTWORK WHERE userNo = ? AND artworkNo =? ";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo, artworkNo});	

		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
		
	}
	
	/** 사용자가 위시리스트에 추가한 artwork 리스트로 반환 
	 * */
	public List<Artwork> getWishArtwork(int userNo){
		String sql = "SELECT a.artworkNo AS artworkNo, a.image AS image, a.workSize AS workSize, a.title AS title, a.artistName AS artistName, "
				+ "a.price AS price, a.likeCnt AS likeCnt, a.description AS description, a.isSoldOut AS isSoldOut "
				+ "FROM wishArtwork w, userinfo u, artwork a "
				+ "WHERE w.userNo = u.userNo "
				+ "AND w.artworkNo = a.artworkNo "
				+ "AND u.userNo = ?" ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			List<Artwork> artworkList = new ArrayList<Artwork>();
			
			while (rs.next()) {
				Artwork artwork = new Artwork();
				
				artwork.setArtworkNo(rs.getInt("artworkNo"));
				artwork.setImage(rs.getString("image"));
				artwork.setWorkSize(rs.getString("workSize"));
				artwork.setTitle(rs.getString("title"));
				artwork.setArtistName(rs.getString("artistName"));
				artwork.setPrice(rs.getInt("price"));
				artwork.setLikeCnt(rs.getInt("likeCnt"));
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
	
	
	/*
	public List<Onedayclass> getWishOnedayclass(int userNo) {
		//: �빐�떦 �쑀���쓽 onedayclass�뿉 ���븳 wish瑜� 媛��졇�삩�떎.
		Connection conn = null;
		PreparedStatement pStmt = null;			
		ResultSet rs = null;
		
		String query = "SELECT onedayclassNo, hostNo, title, image, price, classDate, maximum, "
						+ "runningTime, deadline, applicant, place "
						+ "from wishOnedayclass, userinfo, Onedayclass "
						+ "where wishOnedayclass.userNo = userinfo.userNo "
						+ "AND wishOnedayclass.onedayclassNo = Onedayclass.onedayclassNo "
						+ "User.userNo = ?";
		

		try {
			conn = cm.getConnection();	// DBMS���쓽 �뿰寃� �쉷�뱷 
			pStmt = conn.prepareStatement(query);	
			
			pStmt.setInt(1, userNo);
			
			pStmt.executeQuery();

			List<Onedayclass> odcList = new ArrayList<Onedayclass>();
			
			while(rs.next()){
				Onedayclass odc = new Onedayclass();

				odc.setOnedayclassNo(rs.getInt("onedayclassNo"));
				
				odc.setHost( getUserInfoByNo(rs.getInt("hostNo")) ); //媛��뒫�븯�떎硫�
				
				odc.setTitle(rs.getString("title"));
				odc.setImage(rs.getString("image"));
				odc.setPrice(rs.getInt("price"));
				odc.setDate(rs.getString("classDate"));
				odc.setMaximum(rs.getInt("maximum"));
				odc.setRunningTime(rs.getInt("runningTime"));
				odc.setDeadline(rs.getString("deadline"));
				odc.setApplicant(rs.getInt("applicant"));
				odc.setPlace(rs.getString("place"));
				
				odcList.add(odc);
			}

			return odcList;
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		} finally {		// �옄�썝 諛섎궔
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
	} */
	/*
	public List<Exhibition> getWishExhibition(int userNo) {
		  
		Connection conn = null;
		PreparedStatement pStmt = null;			
		ResultSet rs = null;
		
		String query = "SELECT exhibitionNo, title, explanation, period, isFree, price, visitor, image "
						+ "from wishExhibition, userinfo, Exhibition "
						+ "where wishExhibition.userNo = userinfo.userNo "
						+ "AND wishExhibition.exhibitionNo = Exhibition.exhibitionNo "
						+ "User.userNo = ?";
		

		try {
			conn = cm.getConnection();	 
			pStmt = conn.prepareStatement(query);	
			
			pStmt.setInt(1, userNo);
			
			pStmt.executeQuery();

			List<Exhibition> exhList = new ArrayList<Exhibition>();
			
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
		} finally {	
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
	
	/** 
	 * cart에 artwork추가
	 * */
	public int insertCartArtwork(int userNo, int artworkNo) {
		String sql = "INSERT INTO CARTARTWORK "
				+" VALUES (SEQ_PK.nextval, ?, ?)";	
		Object[] param = new Object[] {userNo, artworkNo};				
		jdbcUtil.setSqlAndParameters(sql, param);	
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
		
	}
	
	/** 
	 * cart에서 artwork 삭제
	 * */
	public int deleteCartArtwork(int userNo, int artworkNo) throws SQLException {
		
		String sql = "DELETE FROM CARTARTWORK WHERE userNo = ? AND artworkNo =? ";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo, artworkNo});	

		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
		
	}
	
	
	
	/** 장바구니에 담겨져있는 artwork 목록을 리스트로 반환 
	 */
	public List<Artwork> getCartArtwork(int userNo){
		String sql = "SELECT a.artworkNo AS artworkNo, a.image AS image, a.workSize AS workSize, a.title AS title, a.artistName AS artistName, "
				+ "a.price AS price, a.likeCnt AS likeCnt, a.description AS description, a.isSoldOut AS isSoldOut "
				+ "FROM cartArtwork c, userinfo u, artwork a "
				+ "WHERE c.userNo = u.userNo "
				+ "AND c.artworkNo = a.artworkNo "
				+ "AND u.userNo = ?" ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			List<Artwork> artworkList = new ArrayList<Artwork>();
			
			while (rs.next()) {
				Artwork artwork = new Artwork();
				
				artwork.setArtworkNo(rs.getInt("artworkNo"));
				artwork.setImage(rs.getString("image"));
				artwork.setWorkSize(rs.getString("workSize"));
				artwork.setTitle(rs.getString("title"));
				artwork.setArtistName(rs.getString("artistName"));
				artwork.setPrice(rs.getInt("price"));
				artwork.setLikeCnt(rs.getInt("likeCnt"));
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
	 
	
	/*
	public List<Onedayclass> getOpenOnedayclassByUser(int userNo) {
		
	}*/
	
	/** 신청한 원데이 클래스 목록*/
	/*public List<Onedayclass> getApplyOnedayclass(int userNo) { 
		
		String sql = "SELECT o.onedayclassNo AS onedayclassNo, o.hostNo AS hostNo, u.name AS name, o.title AS title, o.image AS image, "
				+ "o.price AS price, o.classDate AS classDate, o.maximum AS maximum, o.runningTime AS runningTime, "
				+ "o.deadline AS deadline, o.applicant AS applicant, o.place AS place " 
				+ "FROM USERINFO u, ONEDAYCLASS o, ocapply oca "
				+ "WHERE oca.userNo = u.userNo "
				+ "AND o.onedayclassNo = oca.onedayclassNo " 
				+ "AND u.userNo = ? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			List<Onedayclass> applyList = new ArrayList<Onedayclass>();
			
			while (rs.next()) {
				Onedayclass odc = new Onedayclass();
				
				odc.setOnedayclassNo(rs.getInt("onedayclassNo"));
				
				//User host
				User host = new User();
				host.setUserNo(rs.getInt("hostNo"));
				host.setName(rs.getString("name"));
				odc.setHost(host);
				
				odc.setTitle(rs.getString("title"));
				odc.setImage(rs.getString("image"));
				odc.setPrice(rs.getInt("price"));
				odc.setDate(rs.getString("classDate"));
				odc.setMaximum(rs.getInt("maximum"));
				odc.setRunningTime(rs.getInt("runningTime"));
				odc.setDeadline(rs.getString("deadline"));
				odc.setApplicant(rs.getInt("applicant"));
				odc.setPlace(rs.getString("place"));
				
				applyList.add(odc);
			}		
			return applyList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
		
		
	} */
	
	/** 입장권을 구매한 전시회 목록*/
	public List<Exhibition> getBuyTicketExhByUserNo(int userNo)
	{
		String sql = "SELECT e.exhibitionNo AS exhibitionNo, title, description, period, price, visitor, image "
				+"FROM ExhibitionBuyTicket ebt, userinfo u, Exhibition e "
				+"WHERE ebt.userNo = u.userNo "
				+"AND ebt.exhibitionNo = e.exhibitionNo AND u.userNo = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			List<Exhibition> ticketList = new ArrayList<Exhibition>();
			
			while (rs.next()) {
				Exhibition exh = new Exhibition();
				
				exh.setExhibitionNo(rs.getInt("exhibitionNo"));
				exh.setTitle(rs.getString("title"));
				exh.setDescription(rs.getString("description"));
				exh.setPeriod(rs.getString("period"));
				exh.setVisitor(rs.getInt("visitor"));
				exh.setImage(rs.getString("image"));
				
				ticketList.add(exh);
			}		
			return ticketList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
		
	}
	/*
	public List<TradeWork> getTradeWorkByUser(int userNo) {//: �빐�떦 �쑀��媛� �벑濡앺븳 tradeWork�뱾�쓣 諛섑솚�븳�떎. 
		Connection conn = null;
		PreparedStatement pStmt = null;			
		ResultSet rs = null;
		
		String query = "SELECT tradeWorkNo, trader, image, title, location, size, description, artist "
						+ "from userinfo, Tradework "
						+ "where Tradework.userNo = userinfo.userNo "
						+ "userNo = ?";
		
		
		String query2 = "SELECT userNo, userId, password, name, nickname, email, phone "
				+ "FROM user "
				+ "WHERE userNo = ?";

		try {
			conn = cm.getConnection();	 
			pStmt = conn.prepareStatement(query);	
			
			pStmt.setInt(1, userNo);
			
			pStmt.executeQuery();

			List<TradeWork> twList = new ArrayList<TradeWork>();
			
			while(rs.next()){
				
				TradeWork tw = new TradeWork();
				
				tw.setTradeWorkNo(rs.getInt("tradeWorkNo"));
				tw.setTrader(trader); // trader가 User타입이니까 .. userNo로 User객체 만들어서 ,, 그렇게 넣기 ?!
				tw.setImage(rs.getString("image"));
				tw.setTitle(rs.getString("title"));
				tw.setLocation(rs.getString("location"));
				tw.setSize(rs.getString("size"));
				tw.setDescription(rs.getString("description"));
				tw.setArtist(rs.getString("artist"));
				
				twList.add(tw);
			}
			
			return twList;
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
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
	
	
	//주문내역 추가하기
	public int insertArtworkOrder(ArtworkOrder artworkOrder, List<Artwork> artworkList){
		
		String sql = "INSERT INTO ARTWORKORDER(artworkorderno, userno, destination, receiver, phone) "
					+ "VALUES (SEQ_PK.nextval, ?, ?, ?, ?)";
	
		String sql2 = "INSERT INTO ODRARTWORKLIST (odrartworklistno, artworkorderno, artworkno) "
						+" VALUES (SEQ_PK.nextval, ?, ?) ";
		
		Object[] param = new Object[] { artworkOrder.getUserNo(), artworkOrder.getDestination(), 
										artworkOrder.getReceiver(), artworkOrder.getPhone()};
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[]={"artworkorderno"};
		
		try {
			int result = jdbcUtil.executeUpdate(key);     // insert 문 실행
			ResultSet rs = jdbcUtil.getGeneratedKeys();    // 생성된 PK 값을 포함한 result set 객체 반환
			
			int generatedKey = 0;
			if(rs.next()) {
				generatedKey = rs.getInt(1);     //  PK 값을 읽음
			    // ... 위에서 구한 PK 값을 이용해서 필요한 작업 구현
			}
			
			int result2 = 0;
			for(Artwork artwork : artworkList) {
				Object[] param2 = new Object[] { generatedKey, artwork.getArtworkNo() };
				jdbcUtil.setSqlAndParameters(sql2, param2);
				result2 = jdbcUtil.executeUpdate();
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
	
	
	/** 사용자의 artwork 주문내역을 ArtworkOrder 객체로 반환 
	 * */
	public List<ArtworkOrder> getArtworkOrderByUserNo(int userNo) {
		String sql = "SELECT a.artworkOrderNo AS artworkOrderNo, u.userNo AS userNo, a.destination AS destination, "
					+ "a.receiver AS receiver, a.phone AS phone "
					+ "FROM USERINFO u , ARTWORKORDER a "
					+ "WHERE u.userNo = a.userNo "
					+ "AND u.userNo = ?";
		
		String sql2 = "SELECT a.artworkNo AS artworkNo, image, title , artistName, price "
				+ "FROM odrartworklist o, artwork a "
				+ "WHERE o.artworkorderno = ? "
				+ "AND o.artworkno = a.artworkno";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});		// JDBCUtil�� query�� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			List<ArtworkOrder> artworkOrderList = new ArrayList<ArtworkOrder>();
			
			while (rs.next()) {
				ArtworkOrder artworkOrder = new ArtworkOrder();
				
				artworkOrder.setArtworkOrderNo(rs.getInt("artworkOrderNo"));
				artworkOrder.setUserNo(rs.getInt("userNo")   );
				artworkOrder.setDestination(rs.getString("destination"));
				artworkOrder.setReceiver(rs.getString("receiver"));
				artworkOrder.setPhone(rs.getString("phone"));
				
				artworkOrderList.add(artworkOrder);
			}	
			
			/** 주문번호에 해당하는 모든 Artwork들을 리스트로 반환하여 ArtworkOrder객체에 저장*/
			for(int i = 0; i < artworkOrderList.size(); i++) {
				ArtworkOrder a = artworkOrderList.get(i);
				jdbcUtil.setSqlAndParameters(sql2, new Object[] {a.getArtworkOrderNo()});
				ResultSet rs2 = jdbcUtil.executeQuery();
				
				List<SimpleArtworkInfo> artworks = new ArrayList<SimpleArtworkInfo>();
				while(rs2.next()) {
					SimpleArtworkInfo simpleArtwork = new SimpleArtworkInfo();
					
					simpleArtwork.setArtworkNo(rs2.getInt("artworkNo"));
					simpleArtwork.setArtistName(rs2.getString("artistName"));
					simpleArtwork.setTitle(rs2.getString("title"));
					simpleArtwork.setImage(rs2.getString("image"));
					simpleArtwork.setPrice(rs2.getInt("price"));
					
					artworks.add(simpleArtwork);
				}
				a.setArtworks(artworks);
				
				artworkOrderList.set(i, a);
			}
			/*
			for(ArtworkOrder a : artworkOrderList) {
				jdbcUtil.setSqlAndParameters(sql2, new Object[] {a.getArtworkOrderNo()});
				ResultSet rs2 = jdbcUtil.executeQuery();
				
				List<SimpleArtworkInfo> artworks = new ArrayList<SimpleArtworkInfo>();
				while(rs2.next()) {
					SimpleArtworkInfo simpleArtwork = new SimpleArtworkInfo();
					
					simpleArtwork.setArtworkNo(rs2.getInt("artworkNo"));
					simpleArtwork.setArtistName(rs2.getString("artistName"));
					simpleArtwork.setTitle(rs2.getString("title"));
					simpleArtwork.setImage(rs2.getString("image"));
					simpleArtwork.setPrice(rs2.getInt("price"));
					
					artworks.add(simpleArtwork);
				}
				a.setArtworks(artworks);
			}*/
			
			return artworkOrderList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
		
	}
	
	
	/**  artworkOrderNo(작품 주문번호)에 해당하는 Artwork를 List로 반환 (하나의 주문번호에 여러개의 작품이 있을 수 있음)
	 * */

	public List<Artwork> getOdrArtworkListByOdrNo(int artworkOrderNo) { 
	      //하나의 주문정보에 대한 아트워크 리스트를 반환. 

		String sql = "SELECT a1.artworkNo AS artworkNo, image, workSize, title, price, likeCnt, artistName, description, isSoldOut "
					+"FROM ARTWORK a1, ARTWORKORDER a2, ODRARTWORKLIST o "
					+"WHERE a2.artworkOrderNo = ? "
					+ "AND o.ArtworkOrderNo = a2.ArtworkOrderNo "
					+ "AND o.artworkNo = a1.artworkNo ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artworkOrderNo});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			List<Artwork> artworkList = new ArrayList<Artwork>();
			
			while (rs.next()) {
				Artwork artwork = new Artwork();
				
				artwork.setArtworkNo(rs.getInt("artworkNo"));
				artwork.setImage(rs.getString("image"));
				artwork.setWorkSize(rs.getString("workSize"));
				artwork.setTitle(rs.getString("title"));
				artwork.setArtistName(rs.getString("artistName"));
				artwork.setPrice(rs.getInt("price"));
				artwork.setLikeCnt(rs.getInt("likeCnt"));
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
	
	
	public boolean existingUser(String userId) throws SQLException {
		String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}
	

}
