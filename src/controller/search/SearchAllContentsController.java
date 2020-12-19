package controller.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Exhibition;
import model.SimpleArtworkInfo;
import model.User;
import model.service.Manager;

public class SearchAllContentsController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//사용자가 입력한 검색어
		//String searchKey = (String)request.getAttribute("searchKey"); //attribute로 넘어올 시
		String searchKey = request.getParameter("searchKey"); //parameter로 넘어올 시
		
		String keyword = "";
		boolean keywordFlag = false;
		if( searchKey.indexOf("#") != -1 ) {
			keywordFlag = true;
			keyword = searchKey.substring(searchKey.indexOf("#")+1, searchKey.length());
			System.out.println("keyword="+keyword);
		}
				
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			//로그인 안되어 있는 경우
			Manager manager = Manager.getInstance();
					
			if(keywordFlag == true) {
				List<SimpleArtworkInfo> artworkList = null;
				artworkList = manager.searchArtworkByKeyword(keyword);
		
				request.setAttribute("artworkList", artworkList);
			} else {
				List<SimpleArtworkInfo> artworkList = null;
				artworkList = manager.searchArtworkByKey(searchKey);
				
				request.setAttribute("artworkList", artworkList);
			}
			
			List<Exhibition> exhList = null;
			exhList = manager.findExhibitionListByTitleForNotUser(searchKey);
			
			request.setAttribute("exhibitionList", exhList);
			
			
			return "/main/searchResult.jsp";
		}
				
		//로그인 되어 있는 경우
		Manager manager = Manager.getInstance();
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
		        
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
				
		List<Exhibition> exhList = null;
		exhList = manager.findExhibitionListByTitleForUser(userNo, searchKey);
				
		
		if(keywordFlag == true) {
			List<SimpleArtworkInfo> artworkList = null;
			artworkList = manager.searchArtworkByKeyword(keyword);
	
			request.setAttribute("artworkList", artworkList);
		} else {
			List<SimpleArtworkInfo> artworkList = null;
			artworkList = manager.searchArtworkByKey(searchKey);
			
			request.setAttribute("artworkList", artworkList);
		}
		
		/*List<SimpleArtworkInfo> artworkList = null;
		artworkList = manager.searchArtworkByKey(searchKey);*/
		
		request.setAttribute("exhibitionList", exhList);
		//request.setAttribute("artworkList", artworkList);
				
		return "/main/searchResult.jsp";		
		
	}

}
