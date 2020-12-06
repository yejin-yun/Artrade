package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class MyPageController  implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";		
        }
		
		if(request.getMethod().equals("GET")) {
			return "/user/mypage.jsp"; 
		}
		
		return null;
	}

}
