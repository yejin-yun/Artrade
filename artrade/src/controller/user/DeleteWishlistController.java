package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.Manager;

public class DeleteWishlistController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Manager manager = Manager.getInstance();
		
		if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";		// login form 요청으로 redirect
        }
		System.out.println("In deleteWishlistController");
		
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
        
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
		
		String[] checkedWish = request.getParameterValues("checked");
		
		for(int i = 0; i < checkedWish.length; i++) {
			int artworkNo = Integer.parseInt(checkedWish[i]);
			
			manager.removeWishArtwork(userNo, artworkNo);
		}
		
		return "redirect:/user/wishlist";
	}

}
