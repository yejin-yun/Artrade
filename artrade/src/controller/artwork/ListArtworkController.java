package controller.artwork;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.*;
import model.service.Manager;
import controller.user.UserSessionUtils;

public class ListArtworkController implements Controller {
	// private static final int countPerPage = 100;	// �븳 �솕硫댁뿉 異쒕젰�븷 �궗�슜�옄 �닔

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {

		Manager manager = Manager.getInstance(); //ArtworkDao瑜� �씠�슜�븿
		List<SimpleArtworkInfo> artworkList = null;
    	
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
    		request.setAttribute("isLogined", "0");
    		artworkList = manager.findSimpleArtworkInfoListForNotUser();
    		
        }  else {
        	request.setAttribute("isLogined", "1");
        	request.setAttribute("curUserId", 
    				UserSessionUtils.getLoginUserId(request.getSession()));
    		if(UserSessionUtils.getLoginUserId(request.getSession()) != null || !(UserSessionUtils.getLoginUserId(request.getSession()).equals(""))) {
    			System.out.println("list_art_gessession");
    			User user = manager.findUserById(UserSessionUtils.getLoginUserId(request.getSession()));
    			request.setAttribute("userNo", user.getUserNo());
    			artworkList = manager.findSimpleArtworkInfoListForUser(user.getUserNo());
    		}
        	
        }
        
    	
    	if(artworkList == null) {
			System.out.println("Controller is null");
		}

		request.setAttribute("sIndex", request.getParameter("sIndex"));
		System.out.println("lac_sindex = " + request.getParameter("sIndex"));
		//System.out.println("sIndex_con = " + request.getAttribute("sIndex"));
		request.setAttribute("artworkList", artworkList);		

		
		// 유저 아이디에 해당하는 userNo도 넘겨줘야 함. 

		return "/artwork/view.jsp";        
    }
}
