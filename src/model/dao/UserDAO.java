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
		jdbcUtil = new JDBCUtil();	// JDBCUtil 占쏙옙체 占쏙옙占쏙옙
	}
	
	private ConnectionManager cm = new ConnectionManager();
	
	/** 
	 *  USERINFO �뀒�씠釉붿뿉 �깉濡쒖슫 �뻾 �궫�엯
	 *  */
	public int insertUser(User user) throws SQLException {
		String sql = "INSERT INTO USERINFO (userNo, userId, password, name, nickname, email, phone) " +
				"VALUES (seq_pk.nextval, ?, ?, ?, ?, ?, ?)";	
		Object[] param = new Object[] {user.getUserId(), user.getPassword(), user.getName(), 
										user.getNickname(), user.getEmail(), user.getPhone() };				
		jdbcUtil.setSqlAndParameters(sql, param);	
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 占쏙옙환
		}		
		return 0;
		
	}
	
	/** 
	 *  USERINFO �뀒�씠釉붿쓽  �엯�젰媛믪씤 userNo�뿉 �빐�떦�븯�뒗 �뻾 �궘�젣
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
	
	/** userNo 媛믪쓣 諛쏆븘�꽌 �빐�떦 USERINFO �뻾�쓣 USER 媛앹껜濡�  諛섑솚�븳�떎.
	 */
	public User getUserInfoByNo(int userNo) throws SQLException {
        String sql = "SELECT userNo, userId, password, name, nickname, email, phone "
					+ "FROM USERINFO "
					+ "WHERE userNo = ?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});	// JDBCUtil占쏙옙 query占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쏙옙占쏙옙
			if (rs.next()) {						// 占싻삼옙 占쏙옙占쏙옙 占쌩곤옙
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
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
}
	

	
	
	public List<User> getUserInfoByName (String name) {
		//: user name占쎌뱽 占쎌뵠占쎌뒠占쎈퉸 user 占쎌젟癰귣�占쏙옙 筌≪뼚釉� 獄쏆꼹�넎
		Connection conn = null;
		PreparedStatement pStmt = null;			
		ResultSet rs = null;
		
		String query = "SELECT userNo, userId, password, name, nickname, email, phone "
						+ "FROM USERINFO  "
						+ "WHERE name = ?";
		List<User> userList = null;

		try {
			conn = cm.getConnection();	// DBMS占쏙옙占쎌벥 占쎈염野껓옙 占쎌돴占쎈굣 
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
		} finally {		// 占쎌쁽占쎌뜚 獄쏆꼶沅�
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
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil占쏙옙 query占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쏙옙占쏙옙
			if (rs.next()) {						// 占싻삼옙 占쏙옙占쏙옙 占쌩곤옙
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
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
		
	}
	
	/** USERINFO �뀒�씠釉붿쓽 �뻾�쓣 �닔�젙. �쉶�썝�젙蹂� �닔�젙 
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
	
	
	
	/** userNo, artworkNo �쓣 諛쏆븘�꽌 WISHARTWORK �뀒�씠釉붿뿉 �깉濡쒖슫 �뻾�쓣 �깮�꽦 / �궗�슜�옄 愿��젏 : �쐞�떆由ъ뒪�듃�뿉 �듅�젙 artwork瑜� 異붽��븯���쓬
	 */
	public int insertWishArtwork(int userNo, int artworkNo) throws SQLException {
		String sql = "INSERT INTO WISHARTWORK (wishArtworkNo, userNo, artworkNo) " +
					"VALUES (seq_pk.nextval, ?, ?)"	;
		Object[] param = new Object[] { userNo, artworkNo };				
		jdbcUtil.setSqlAndParameters(sql, param);	
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 占쏙옙환
		}		
		return 0;
	}
	
	/** userNo, artworkNo �뿉 �빐�떦�븯�뒗  WISHARTWORK �뀒�씠釉붿쓽 �뻾�쓣 �궘�젣 / �궗�슜�옄 愿��젏 : �쐞�떆由ъ뒪�듃�뿉�꽌 �듅�젙 artwork瑜� �궘�젣�븯���쓬
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
	
	/** �궗�슜�옄媛� �쐞�떆由ъ뒪�듃�뿉 異붽��븳 artwork 由ъ뒪�듃濡� 諛섑솚 
	 * */
	public List<Artwork> getWishArtwork(int userNo){
		String sql = "SELECT a.artworkNo AS artworkNo, a.image AS image, a.workSize AS workSize, a.title AS title, a.artistName AS artistName, "
				+ "a.price AS price, a.likeCnt AS likeCnt, a.description AS description, a.isSoldOut AS isSoldOut "
				+ "FROM wishArtwork w, userinfo u, artwork a "
				+ "WHERE w.userNo = u.userNo "
				+ "AND w.artworkNo = a.artworkNo "
				+ "AND u.userNo = ?" ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});		// JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
					
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
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
		
	}
	
	
	/*
	public List<Onedayclass> getWishOnedayclass(int userNo) {
		//: 占쎈퉸占쎈뼣 占쎌�占쏙옙占쎌벥 onedayclass占쎈퓠 占쏙옙占쎈립 wish�몴占� 揶쏉옙占쎌죬占쎌궔占쎈뼄.
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
			conn = cm.getConnection();	// DBMS占쏙옙占쎌벥 占쎈염野껓옙 占쎌돴占쎈굣 
			pStmt = conn.prepareStatement(query);	
			
			pStmt.setInt(1, userNo);
			
			pStmt.executeQuery();

			List<Onedayclass> odcList = new ArrayList<Onedayclass>();
			
			while(rs.next()){
				Onedayclass odc = new Onedayclass();

				odc.setOnedayclassNo(rs.getInt("onedayclassNo"));
				
				odc.setHost( getUserInfoByNo(rs.getInt("hostNo")) ); //揶쏉옙占쎈뮟占쎈릭占쎈뼄筌롳옙
				
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
		} finally {		// 占쎌쁽占쎌뜚 獄쏆꼶沅�
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
	 * cart�뿉 artwork異붽�
	 * */
	public int insertCartArtwork(int userNo, int artworkNo) {
		String sql = "INSERT INTO CARTARTWORK "
				+" VALUES (SEQ_PK.nextval, ?, ?)";	
		Object[] param = new Object[] {userNo, artworkNo};				
		jdbcUtil.setSqlAndParameters(sql, param);	
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 占쏙옙환
		}		
		return 0;
		
	}
	
	/** 
	 * cart�뿉�꽌 artwork �궘�젣
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
	
	
	
	/** �옣諛붽뎄�땲�뿉 �떞寃⑥졇�엳�뒗 artwork 紐⑸줉�쓣 由ъ뒪�듃濡� 諛섑솚 
	 */
	public List<Artwork> getCartArtwork(int userNo){
		String sql = "SELECT a.artworkNo AS artworkNo, a.image AS image, a.workSize AS workSize, a.title AS title, a.artistName AS artistName, "
				+ "a.price AS price, a.likeCnt AS likeCnt, a.description AS description, a.isSoldOut AS isSoldOut "
				+ "FROM cartArtwork c, userinfo u, artwork a "
				+ "WHERE c.userNo = u.userNo "
				+ "AND c.artworkNo = a.artworkNo "
				+ "AND u.userNo = ?" ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});		// JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
					
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
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
		
		
	}
	 
	
	/*
	public List<Onedayclass> getOpenOnedayclassByUser(int userNo) {
		
	}*/
	
	/** �떊泥��븳 �썝�뜲�씠 �겢�옒�뒪 紐⑸줉*/
	/*public List<Onedayclass> getApplyOnedayclass(int userNo) { 
		
		String sql = "SELECT o.onedayclassNo AS onedayclassNo, o.hostNo AS hostNo, u.name AS name, o.title AS title, o.image AS image, "
				+ "o.price AS price, o.classDate AS classDate, o.maximum AS maximum, o.runningTime AS runningTime, "
				+ "o.deadline AS deadline, o.applicant AS applicant, o.place AS place " 
				+ "FROM USERINFO u, ONEDAYCLASS o, ocapply oca "
				+ "WHERE oca.userNo = u.userNo "
				+ "AND o.onedayclassNo = oca.onedayclassNo " 
				+ "AND u.userNo = ? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});		// JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
					
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
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
		
		
	} */
	
	/** �엯�옣沅뚯쓣 援щℓ�븳 �쟾�떆�쉶 紐⑸줉*/
	public List<Exhibition> getBuyTicketExhByUserNo(int userNo)
	{
		String sql = "SELECT e.exhibitionNo AS exhibitionNo, title, description, period, price, visitor, image "
				+"FROM ExhibitionBuyTicket ebt, userinfo u, Exhibition e "
				+"WHERE ebt.userNo = u.userNo "
				+"AND ebt.exhibitionNo = e.exhibitionNo AND u.userNo = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});		// JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
					
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
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
		
	}
	/*
	public List<TradeWork> getTradeWorkByUser(int userNo) {//: 占쎈퉸占쎈뼣 占쎌�占쏙옙揶쏉옙 占쎈쾻嚥≪빜釉� tradeWork占쎈굶占쎌뱽 獄쏆꼹�넎占쎈립占쎈뼄. 
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
				tw.setTrader(trader); // trader媛� User���엯�씠�땲源� .. userNo濡� User媛앹껜 留뚮뱾�뼱�꽌 ,, 洹몃젃寃� �꽔湲� ?!
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
	
	
	//二쇰Ц�궡�뿭 異붽��븯湲�
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
			int result = jdbcUtil.executeUpdate(key);     // insert 臾� �떎�뻾
			ResultSet rs = jdbcUtil.getGeneratedKeys();    // �깮�꽦�맂 PK 媛믪쓣 �룷�븿�븳 result set 媛앹껜 諛섑솚
			
			int generatedKey = 0;
			if(rs.next()) {
				generatedKey = rs.getInt(1);     //  PK 媛믪쓣 �씫�쓬
			    // ... �쐞�뿉�꽌 援ы븳 PK 媛믪쓣 �씠�슜�빐�꽌 �븘�슂�븳 �옉�뾽 援ы쁽
			}
			
			int result2 = 0;
			for(Artwork artwork : artworkList) {
				Object[] param2 = new Object[] { generatedKey, artwork.getArtworkNo() };
				jdbcUtil.setSqlAndParameters(sql2, param2);
				result2 = jdbcUtil.executeUpdate();
			}
			
			return generatedKey;   // �삉�뒗 Test �뀒�씠釉붿뿉 ���쓳�릺�뒗 VO/DTO 媛앹껜瑜� �깮�꽦�븯�뿬 PK 媛믪쓣 ���옣�븳 �썑 由ы꽩
			   
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 占쏙옙환
		}		
		return 0;
		
		
		
	}
	
	
	/** �궗�슜�옄�쓽 artwork 二쇰Ц�궡�뿭�쓣 ArtworkOrder 媛앹껜濡� 諛섑솚 
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
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNo});		// JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
		
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
			
			/** 二쇰Ц踰덊샇�뿉 �빐�떦�븯�뒗 紐⑤뱺 Artwork�뱾�쓣 由ъ뒪�듃濡� 諛섑솚�븯�뿬 ArtworkOrder媛앹껜�뿉 ���옣*/
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
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
		
	}
	
	
	/**  artworkOrderNo(�옉�뭹 二쇰Ц踰덊샇)�뿉 �빐�떦�븯�뒗 Artwork瑜� List濡� 諛섑솚 (�븯�굹�쓽 二쇰Ц踰덊샇�뿉 �뿬�윭媛쒖쓽 �옉�뭹�씠 �엳�쓣 �닔 �엳�쓬)
	 * */

	public List<Artwork> getOdrArtworkListByOdrNo(int artworkOrderNo) { 
	      //�븯�굹�쓽 二쇰Ц�젙蹂댁뿉 ���븳 �븘�듃�썙�겕 由ъ뒪�듃瑜� 諛섑솚. 

		String sql = "SELECT a1.artworkNo AS artworkNo, image, workSize, title, price, likeCnt, artistName, description, isSoldOut "
					+"FROM ARTWORK a1, ARTWORKORDER a2, ODRARTWORKLIST o "
					+"WHERE a2.artworkOrderNo = ? "
					+ "AND o.ArtworkOrderNo = a2.ArtworkOrderNo "
					+ "AND o.artworkNo = a1.artworkNo ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {artworkOrderNo});		// JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
					
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
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
		
	}
	
	
	public boolean existingUser(String userId) throws SQLException {
		String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil占쏙옙 query占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쏙옙占쏙옙
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return false;
	}
	
	
	
	
	

}
