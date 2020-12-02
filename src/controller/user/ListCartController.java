package controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Artwork;
import model.User;
import model.service.Manager;

public class ListCartController implements Controller {
	// private static final int countPerPage = 100;	// 한 화면에 출력할 사용자 수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";		// login form 요청으로 redirect
        }
        

    	/*
    	String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
    	*/
    	
    	Manager manager = Manager.getInstance();
    	
    	String userId = UserSessionUtils.getLoginUserId(request.getSession());
        
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
    	
		List<Artwork> cartArtworkList = manager.findArtworksInCart(userNo);
		// List<User> userList = manager.findUserList(currentPage, countPerPage);

		// cartArtworkList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("cartArtworkList", cartArtworkList);				
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		

		return "/user/cart.jsp";        
    }
}
