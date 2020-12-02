package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.Manager;

public class CartRemoveController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
    		
    		return "redirect:/user/login";
        } 
    	Manager manager = Manager.getInstance();
    	//로그인한 사용자 아이디 
    	String userId = UserSessionUtils.getLoginUserId(request.getSession());
        //아이디로 사용자번호 찾기
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
		
		try {
			String[] checkedWish = request.getParameterValues("checked");
			
			
			for(int i = 0; i < checkedWish.length; i++) {
				int artworkNo = Integer.parseInt(checkedWish[i]);
				
				//manager.removeWishArtwork(userNo, artworkNo); //장바구니로 보낸 위시리스트를 삭제하고 싶으면 주석 해제!
				manager.removeCartArtwork(userNo, artworkNo);
			}
			
			/*request.setAttribute("curUserId", 
					UserSessionUtils.getLoginUserId(request.getSession()));		*/
	
			//cartlist로 보내버리기
			return "/user/cartlist";
			
		}catch(Exception e){
			e.printStackTrace();
			return "/user/cartlist";
		}
	}

}
