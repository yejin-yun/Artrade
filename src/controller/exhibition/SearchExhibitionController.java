package controller.exhibition;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Exhibition;
import model.User;
import model.service.Manager;

public class SearchExhibitionController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//사용자가 입력한 검색어
		//String searchKey = (String)request.getAttribute("searchKey"); //attribute로 넘어올 시
		String searchKey = request.getParameter("exhSearch"); //parameter로 넘어올 시
		
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			//로그인 안되어 있는 경우
			Manager manager = Manager.getInstance();
			
			List<Exhibition> exhList = null;
			exhList = manager.findExhibitionListByTitleForNotUser(searchKey);
	        
			request.setAttribute("exhibitionList", exhList);
			
			return "/exhibition/searchResult.jsp";
	    }
		
		//로그인 되어 있는 경우
		Manager manager = Manager.getInstance();
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
        
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
		
		List<Exhibition> exhList = null;
		exhList = manager.findExhibitionListByTitleForUser(userNo, searchKey);
		
		request.setAttribute("exhibitionList", exhList);
		
		return "/exhibition/searchResult.jsp";
	}

}
