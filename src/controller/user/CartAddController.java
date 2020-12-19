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
		
		// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
    		
    		return "redirect:/user/login";
        } 
    	
    	//로그인한 사용자 아이디 
    	String userId = UserSessionUtils.getLoginUserId(request.getSession());
        //아이디로 사용자번호 찾기
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
		
		int artworkNo = (int)request.getAttribute("artworkNo");
		
		int result = manager.addCartArtwork(userNo, artworkNo);
		
		if(result == 0) {
			System.out.println("cartAddController's insert failed");
		}
			
		/*request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		*/

		//cartlist로 보내버리기
		return "/user/cartlist.jsp";
	}

}
