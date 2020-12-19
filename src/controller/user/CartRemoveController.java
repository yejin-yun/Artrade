package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.Manager;

public class CartRemoveController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 濡쒓렇�씤 �뿬遺� �솗�씤
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
    		
    		return "redirect:/user/login";
        } 
    	Manager manager = Manager.getInstance();
    	//濡쒓렇�씤�븳 �궗�슜�옄 �븘�씠�뵒 
    	String userId = UserSessionUtils.getLoginUserId(request.getSession());
        //�븘�씠�뵒濡� �궗�슜�옄踰덊샇 李얘린
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
		
		try {
			String[] checkedWish = request.getParameterValues("cartArtwork");
			
			
			for(int i = 0; i < checkedWish.length; i++) {
				int artworkNo = Integer.parseInt(checkedWish[i]);
				
				//manager.removeWishArtwork(userNo, artworkNo); //�옣諛붽뎄�땲濡� 蹂대궦 �쐞�떆由ъ뒪�듃瑜� �궘�젣�븯怨� �떢�쑝硫� 二쇱꽍 �빐�젣!
				manager.removeCartArtwork(userNo, artworkNo);
			}
			
			/*request.setAttribute("curUserId", 
					UserSessionUtils.getLoginUserId(request.getSession()));		*/
	
			//cartlist濡� 蹂대궡踰꾨━湲�
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
