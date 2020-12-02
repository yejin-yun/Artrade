package controller.exhibition;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.*;
import model.service.Manager;

public class ListExhibitionController implements Controller {
	// private static final int countPerPage = 100;	// 한 화면에 출력할 사용자 수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
    	Manager manager = Manager.getInstance();
    	// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
    		
    		List<Exhibition> exhibitionList = manager.findExhibitionListForNotUser();
    		
    		request.setAttribute("exhibitionList", exhibitionList);	
    		
            return "/exhibition/view.jsp";		
        } 
    
    	/*
    	String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
    	*/
    	
    	/** 로그인 되었을 경우 */
    	String userId = UserSessionUtils.getLoginUserId(request.getSession());
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
		
		List<Exhibition> exhibitionList = manager.findExhibitionListForUser(userNo);
		// List<User> userList = manager.findUserList(currentPage, countPerPage);

		request.setAttribute("exhibitionList", exhibitionList);				
		/*request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		*/
		
		return "/exhibition/view.jsp";        
    }
}
