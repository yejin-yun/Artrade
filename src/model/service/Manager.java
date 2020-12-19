package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Artwork;
import model.ArtworkOrder;
import model.Exhibition;
import model.ExhibitionBuyTicket;
import model.OcApply;
import model.Onedayclass;
//import model.OcApply;
//import model.Onedayclass;
import model.SimpleArtworkInfo;
//import model.SimpleTradeInfo;
//import model.TradeWork;
import model.User;
import model.dao.*;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * DAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class Manager {
   private static Manager mananer = new Manager();
   private UserDAO userDao;
   //private UserAnalysis userAanlysis;
   
   private ArtworkDAO artworkDao;
   private ExhibitionDAO exhDao;
   private OnedayclassDAO odcDao;
   //private TradeDAO tradeDao;

   private Manager() {
      try {
         userDao = new UserDAO();
         //userAanlysis = new UserAnalysis(userDAO);
         artworkDao = new ArtworkDAO();
         exhDao = new ExhibitionDAO();
         odcDao = new OnedayclassDAO();
         //tradeDao = new TradeDAO();
         
      } catch (Exception e) {
         e.printStackTrace();
      }         
   }
   
   public static Manager getInstance() {
      return mananer;
   }
   
   /** UserDAO */
   public boolean existingUser(String userId) throws SQLException, ExistingUserException {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      return userDao.existingUser(userId);
=======
	   return userDao.existingUser(userId);
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
=======
	   return userDao.existingUser(userId);
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
=======
	   return userDao.existingUser(userId);
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
   }
   
   public int createUser(User user) throws SQLException, ExistingUserException {
      if (userDao.existingUser(user.getUserId()) == true) {
         throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
      }
      return userDao.insertUser(user);
   }

   public int updateUser(User user) throws SQLException, UserNotFoundException {
      return userDao.updateUserInfo(user);
   }   

   public int removeUser(int userNo) throws SQLException, UserNotFoundException {
      return userDao.deleteUser(userNo);
   }

   public User findUserById(String userId)
      throws SQLException, UserNotFoundException {
      User user = userDao.getUserInfoById(userId);
      
      if (user == null) {
         throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
      }      
      return user;
   }
   
   public User findUserbyNo(int userNo)
         throws SQLException, UserNotFoundException {
         User user = userDao.getUserInfoByNo(userNo);
               
         return user;
      }

   /*public List<User> findUserList() throws SQLException {
         return userDao.findUserList();
   }*/
   
   /*public List<User> findUserList(int currentPage, int countPerPage)
      throws SQLException {
      return userDao.findUserList(currentPage, countPerPage);
   }*/
   
   public int addWishArtwork(int userNo, int artworkNo) throws SQLException {
      return userDao.insertWishArtwork(userNo, artworkNo);
   }
   public int removeWishArtwork(int userNo, int artworkNo) throws SQLException {
      return userDao.deleteWishArtwork(userNo, artworkNo);
   }
   public List<Artwork> findArtworksInWish(int userNo) throws SQLException {
      return userDao.getWishArtwork(userNo);
   }
   public List<Artwork> findArtworksInCart(int userNo) throws SQLException {
      return userDao.getCartArtwork(userNo);
   }
   public int addCartArtwork(int userNo, int artworkNo) throws SQLException {
      return userDao.insertCartArtwork(userNo, artworkNo);
   }
   
   public int removeCartArtwork(int userNo, int artworkNo) throws SQLException{
      return userDao.deleteCartArtwork(userNo, artworkNo);
   }
   
   public List<Exhibition> findBuyTicketExhByUserNo(int userNo) throws SQLException {
      return userDao.getBuyTicketExhByUserNo(userNo);
   }
   public List<ArtworkOrder> findArtworkOrderByUserNo(int userNo) throws SQLException {
      return userDao.getArtworkOrderByUserNo(userNo);
   }
   public int createArtworkOrder(ArtworkOrder artworkOrder, List<Artwork> artworkList) throws SQLException{
      //artwork를 list로 전달받음
      return userDao.insertArtworkOrder(artworkOrder, artworkList);
   }
   
   public List<Artwork> findOrderArtworkListByOrdNo(int artworkOrderNo) throws SQLException {
      return userDao.getOdrArtworkListByOdrNo(artworkOrderNo);
   }

   public boolean login(String userId, String password)
      throws SQLException, UserNotFoundException, PasswordMismatchException {
      User user = findUserById(userId);

      if (!user.matchPassword(password)) {
         throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
      }
      return true;
   }
      
   public UserDAO getUserDAO() {
      return this.userDao;
   }
   
   
   
   /**  ArtworkDAO */
   
   public int createArtwork(Artwork artwork) throws SQLException{
      return artworkDao.insertArtwork(artwork);
   }
   public int removeArtwork(int artworkNo) throws SQLException{
      return artworkDao.deleteArtwork(artworkNo);
   }
   /*public int updateArtwork(Onedayclass odc) throws SQLException{
      return odcDao.updateOnedayclass(odc);
   }*/
   
   public Artwork findArtworkForNotUser(int artworkNo) throws SQLException{
      return artworkDao.getArtworkByNoForNotUser(artworkNo);
   }
   
   public Artwork findArtworkForUser(int userNo, int artworkNo) throws SQLException{
      Artwork artwork = artworkDao.getArtworkByNoForUser(userNo, artworkNo);
      if (artwork == null) {
         //throw new UserNotFoundException(onedayclassNo + "는 존재하지 않는 원데이클래스입니다.");
      }
      return artwork;
   }
   public List<Artwork> findArtworkList() throws SQLException{
      return artworkDao.getArtworkList();
   }
   
   
   public List<SimpleArtworkInfo> findSimpleArtworkInfoListForNotUser()throws SQLException{
      return artworkDao.getSimpleArtworkInfoForNotUser();
   }
   
   
   public List<SimpleArtworkInfo> findSimpleArtworkInfoListForUser(int userNo)throws SQLException{
      List<SimpleArtworkInfo> list = artworkDao.getSimpleArtworkInfoForUser(userNo);
      
      if( list == null) System.out.println("manager~method is null");
      
      return list;
   }
   
   public List<SimpleArtworkInfo> searchArtworkByKeyword(String keyword) throws SQLException{
      return artworkDao.searchArtworkByKeywordForNotUser(keyword); //수정요망
   }
   
   /*public List<SimpleArtworkInfo> searchArtworkByKeywordForUser(String keyword, int userNo) throws SQLException{
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
         return artworkDao.searchArtworkByKeywordForUser(keyword, userNo); //수정요망
=======
	      return artworkDao.searchArtworkByKeywordForUser(keyword, userNo); //수정요망
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
=======
	      return artworkDao.searchArtworkByKeywordForUser(keyword, userNo); //수정요망
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
=======
	      return artworkDao.searchArtworkByKeywordForUser(keyword, userNo); //수정요망
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
   }*/
   
   public List<SimpleArtworkInfo> searchArtworkByKey(String key) throws SQLException{
      return artworkDao.searchArtworkByKey(key);
   }
   public int createKeyword(int artworkNo, String keyword) throws SQLException {
      return artworkDao.insertKeyword(artworkNo, keyword);
   }
   
   public int increaseOneLikeCnt(int artworkNo) throws SQLException {
      return artworkDao.increaseOneLikeCnt(artworkNo);
   }
   
   public int decreaseOneLikeCnt(int artworkNo) throws SQLException {
      return artworkDao.decreaseOneLikeCnt(artworkNo);
   }
   
   public int updateSoldOut(int artworkNo) throws SQLException {
      return artworkDao.updateSoldOut(artworkNo);
   }
   
   
   /** 
    *  ExhibitionDAO 
    *  */
   public Exhibition createExhibition(Exhibition exh) throws SQLException{
      return exhDao.insertExhibition(exh);
   }
   public int updateExhibition(Exhibition exh) throws SQLException{
      return exhDao.updateExhibition(exh);
   }
   public int removeExhibition(int exhibitionNo) throws SQLException{
      return exhDao.deleteExhibition(exhibitionNo);
   }
   
   public List<Artwork> findArtworkInExhibition(int exhibitionNo){
      return exhDao.selectArtworkInExhibition(exhibitionNo);
   }
   
   public List<Exhibition> findExhibitionListForUser(int userNo) throws SQLException{
      return exhDao.getExhibitionListForUser(userNo);
   }
   
   public List<Exhibition> findExhibitionListForNotUser() throws SQLException{
      return exhDao.getExhibitionListForNotUser();
   }
   
   public List<Exhibition> findExhibitionListByTitleForUser(int userNo, String title) throws SQLException{
      return exhDao.getExhibitionByTitleForUser(userNo, title);
   }
   
   public List<Exhibition> findExhibitionListByTitleForNotUser(String title) throws SQLException{
      return exhDao.getExhibitionByTitleForNotUser(title);
   }
   
   
   public ExhibitionBuyTicket createExhBuyTicket(ExhibitionBuyTicket ebt) throws SQLException{
      return exhDao.insertExhBuyTicket(ebt);
   }
   
   public List<ExhibitionBuyTicket> findExhBuyTicketList(int userNo) throws SQLException{
      return exhDao.findExhBuyTicketList(userNo);
   }
   
   public int increaseOneVisitorInExhibition(int exhibitionNo) throws SQLException{
      return exhDao.increaseOneVisitorInExhibition(exhibitionNo);
   }
   
   
   /** OnedayclassDAO  */
   public Onedayclass createOnedayclass(Onedayclass odc) throws SQLException{
      return odcDao.insertOnedayclass(odc);
   }
   public int removeOnedayclass(int onedayclassNo) throws SQLException{
      return odcDao.deleteOnedayclass(onedayclassNo);
   }
   public int updateOnedayclass(Onedayclass odc) throws SQLException{
      return odcDao.updateOnedayclass(odc);
   }
   public Onedayclass findOnedayclass(int onedayclassNo) throws SQLException{
      Onedayclass odc = odcDao.getOnedayclass(onedayclassNo);
      if (odc == null) {
         //throw new UserNotFoundException(onedayclassNo + "는 존재하지 않는 원데이클래스입니다.");
      }
      return odc;
   }
   public List<Onedayclass> findOnedayclassList() throws SQLException{
      return odcDao.getOnedayclassList();
   }
   public OcApply createOcApply (OcApply oca) throws SQLException{
      return odcDao.insertOcApply(oca);
   }
   
   public int increaseOneApplicant(int onedayclassNo) {
      return odcDao.increaseOneApplicant(onedayclassNo);
   }
   
   /** TradeWorkDAO */
//   public int createTradework (TradeWork tradework) throws SQLException{
//      return tradeDao.insertTradework(tradework);
//   }
//   public int removeTradework (int tradeworkNo) throws SQLException{
//      return tradeDao.deleteTradework(tradeworkNo);
//   }
//   
//   public int updateTradework (TradeWork tw) throws SQLException{
//      return tradeDao.updateTradework(tw);
//   }
//   public TradeWork getTradework (int tradeworkNo) throws SQLException{
//      TradeWork tw = tradeDao.getTradework(tradeworkNo);
//      if (tw == null) {
//         //throw new UserNotFoundException(tradeworkNo + "는 존재하지 않는 교환작품입니다.");
//      }
//      return tw;
//   }
//   
//   public List<TradeWork> getParticipantWork (int tradeNo) throws SQLException{
//      return tradeDao.getParticipantWork(tradeNo);
//   }
//   public int createTradeParticipant(int tradeNo, int participantTradeWorkNo) throws SQLException{
//      return tradeDao.insertTradeParticipant(tradeNo, participantTradeWorkNo);
//   }
//   public int removeTradeParticipant (int tradeNo, int tradeWorkNo) throws SQLException{
//      return tradeDao.deleteTradeParticipant(tradeNo, tradeWorkNo);
//   }
//   public List<SimpleTradeInfo> getSimpleTradeInfo() throws SQLException{
//      return tradeDao.getSimpleTradeInfo();
//      
//   }

   
}