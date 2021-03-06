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
            return "redirect:/user/login";		// login form �슂泥��쑝濡� redirect
        }
		
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
        
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
		
		String[] checkedWish = request.getParameterValues("checkArtwork");
		
		for(int i = 0; i < checkedWish.length; i++) {
			int artworkNo = Integer.parseInt(checkedWish[i]);
			
			//manager.removeWishArtwork(userNo, artworkNo); //�옣諛붽뎄�땲濡� 蹂대궦 �쐞�떆由ъ뒪�듃瑜� �궘�젣�븯怨� �떢�쑝硫� 二쇱꽍 �빐�젣!
			manager.addCartArtwork(userNo, artworkNo);
		}
		
		return "redirect:/user/cartlist";
	}

}
