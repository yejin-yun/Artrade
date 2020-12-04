package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Artwork;
import model.User;
import model.service.Manager;

public class WishlistUpdateController implements Controller {

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

      Manager manager = Manager.getInstance();
      
      // 로그인 여부 확인
       if (!UserSessionUtils.hasLogined(request.getSession())) {
          
          return "redirect:/user/login";
        } 
       
       System.out.println("in WishlistUpdateController");
       String userId = UserSessionUtils.getLoginUserId(request.getSession());
        
      User user = manager.findUserById(userId);
      int userNo = user.getUserNo();
      
      int artworkNo = Integer.parseInt(request.getParameter("artworkNo"));
      int like = Integer.parseInt(request.getParameter("like"));
      
      int result = 1;
      if(like == 0) {
         result = manager.removeWishArtwork(userNo, artworkNo);
      } else {
         result = manager.addWishArtwork(userNo, artworkNo);
      }
      
      if(result == 0) {
         System.out.println("wishlistUpdateController's insert failed");
      }
         
      /*request.setAttribute("curUserId", 
            UserSessionUtils.getLoginUserId(request.getSession()));      */

      return "redirect:/artwork/list";
      
   }

}