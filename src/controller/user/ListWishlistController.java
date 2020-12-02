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
	// private static final int countPerPage = 100;	// 한 화면에 출력할 사용자 수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	Manager manager = Manager.getInstance();
		
		// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";		// login form 요청으로 redirect
        }

    	
        String userId = UserSessionUtils.getLoginUserId(request.getSession());
        User user = manager.findUserById(userId);
        List<Artwork> wishlist = manager.findArtworksInWish(user.getUserNo());
        
		request.setAttribute("userNo", user.getUserNo());		 
		request.setAttribute("wishlist", wishlist);  

		request.setAttribute("sIndex", request.getParameter("sIndex"));
		System.out.println("lac_sindex = " + request.getParameter("sIndex"));
		
		// 유저 아이디에 해당하는 userNo도 넘겨줘야 함. 

		return "/user/wishlist.jsp"; 
 
    }
}