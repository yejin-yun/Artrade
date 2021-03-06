package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.Manager;

public class CartAddController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Manager manager = Manager.getInstance();
		
		// 濡쒓렇�씤 �뿬遺� �솗�씤
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
    		
    		return "redirect:/user/login";
        } 
    	
    	//濡쒓렇�씤�븳 �궗�슜�옄 �븘�씠�뵒 
    	String userId = UserSessionUtils.getLoginUserId(request.getSession());
        //�븘�씠�뵒濡� �궗�슜�옄踰덊샇 李얘린
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
		
		int artworkNo = Integer.parseInt(request.getParameter("artworkNo"));
		
		int result = manager.addCartArtwork(userNo, artworkNo);
		
		if(result == 0) {
			System.out.println("cartAddController's insert failed");
		}
			
		/*request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		*/

		//cartlist濡� 蹂대궡踰꾨━湲�
		return "/user/cartlist";
	}

}
