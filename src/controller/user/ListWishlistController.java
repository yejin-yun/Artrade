package controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Artwork;
import model.Exhibition;
import model.Onedayclass;
import model.SimpleArtworkInfo;
import model.User;
import model.service.Manager;

public class ListWishlistController implements Controller {
	// private static final int countPerPage = 100;	// �븳 �솕硫댁뿉 異쒕젰�븷 �궗�슜�옄 �닔

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	Manager manager = Manager.getInstance();
		
		// 濡쒓렇�씤 �뿬遺� �솗�씤
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";		// login form �슂泥��쑝濡� redirect
        }

    	
        String userId = UserSessionUtils.getLoginUserId(request.getSession());
        User user = manager.findUserById(userId);
        List<Artwork> wishlist = manager.findArtworksInWish(user.getUserNo());
        
		request.setAttribute("userNo", user.getUserNo());		 
		request.setAttribute("wishlist", wishlist);  

		request.setAttribute("sIndex", request.getParameter("sIndex"));
		System.out.println("lac_sindex = " + request.getParameter("sIndex"));
		
		// �쑀�� �븘�씠�뵒�뿉 �빐�떦�븯�뒗 userNo�룄 �꽆寃⑥쨾�빞 �븿. 

		return "/user/wishlist.jsp"; 
 
    }
}