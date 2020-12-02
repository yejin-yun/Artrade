package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.Manager;

public class MoveFromWishToCartController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Manager manager = Manager.getInstance();
		
		if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";		// login form 요청으로 redirect
        }
		
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
        
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
		
		String[] checkedWish = request.getParameterValues("checked");
		
		for(int i = 0; i < checkedWish.length; i++) {
			int artworkNo = Integer.parseInt(checkedWish[i]);
			
			//manager.removeWishArtwork(userNo, artworkNo); //장바구니로 보낸 위시리스트를 삭제하고 싶으면 주석 해제!
			manager.addCartArtwork(userNo, artworkNo);
		}
		
		return "redirect:/user/wishlist";
	}

}
