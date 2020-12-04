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
		
		// 위시리스트에 들어왔다는 게 로그인이 되어있다는 거니까 필요 없는 듯 
		//if (!UserSessionUtils.hasLogined(request.getSession())) {
          //  return "redirect:/user/login";		// login form 요청으로 redirect
        //}
		System.out.println("In deleteWishlistController");
		
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
        
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
		
		String[] checkedWish = request.getParameterValues("checkArtwork");
		if(checkedWish == null) {
			System.out.println("checkedWish null");
		}
		
		for(int i = 0; i < checkedWish.length; i++) {
			int artworkNo = Integer.parseInt(checkedWish[i]);
			System.out.println("delete wish = " + artworkNo);
			manager.removeWishArtwork(userNo, artworkNo);
		}
		
		return "redirect:/user/wishlist";
	}

}
