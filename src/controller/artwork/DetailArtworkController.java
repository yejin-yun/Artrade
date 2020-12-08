package controller.artwork;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Artwork;
import model.SimpleArtworkInfo;
import model.User;
import model.service.Manager;

public class DetailArtworkController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Manager manager = Manager.getInstance();
		int artworkNo = Integer.parseInt(request.getParameter("artworkNo"));
		
    	// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
    		
    		//List<SimpleArtworkInfo> artworkList = manager.findSimpleArtworkInfoListForNotUser();
    		Artwork artwork = manager.findArtworkForNotUser(artworkNo);
            request.setAttribute("isLogined", 0);
    		request.setAttribute("artwork", artwork);	
    		
    		return "/artwork/detail.jsp";
        } 
    	
    	String userId = UserSessionUtils.getLoginUserId(request.getSession());
        
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
		
		//List<SimpleArtworkInfo> artworkList = manager.findSimpleArtworkInfoListForUser(userNo);
		Artwork artwork = manager.findArtworkForUser(userNo, artworkNo);
		
		if(artwork == null) {
			System.out.println("DetailArtworkController send null");
		}
		
		request.setAttribute("isLogined", 1);
		request.setAttribute("artwork", artwork);	
		request.setAttribute("userNo", request.getParameter("userNo"));
		
		System.out.println("Detail...");
		
		return "/artwork/detail.jsp";
		
	}

}
