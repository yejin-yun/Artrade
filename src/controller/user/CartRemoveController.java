package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.Manager;

public class CartRemoveController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!UserSessionUtils.hasLogined(request.getSession())) {
    		return "redirect:/user/login";
        } 
    	Manager manager = Manager.getInstance();
   
    	String userId = UserSessionUtils.getLoginUserId(request.getSession());
        
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
				
		String[] checkedCart = request.getParameterValues("cartArtwork");
		
			
		for(int i = 0; i < checkedCart.length; i++) {
			int artworkNo = Integer.parseInt(checkedCart[i]);
			
			manager.removeCartArtwork(userNo, artworkNo);
		}
		
		return "redirect:/user/cartlist";
			
		
	}

}
