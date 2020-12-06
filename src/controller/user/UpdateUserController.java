package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.Manager;
import model.User;

public class UpdateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
    	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 수정 form 요청	
    		// 원래는 UpdateUserFormController가 처리하던 작업을 여기서 수행
    		
    		String userId = UserSessionUtils.getLoginUserId(request.getSession());
    		
    		Manager manager = Manager.getInstance();
			User user = manager.findUserById(userId);	// 수정하려는 사용자 정보 검색
			request.setAttribute("user", user);			

			
			return "/user/updateUserForm.jsp";   // 검색한 사용자 정보를 update form으로 전송     
			// 빈창 걸러주는 거 자바스크립트로 하기. 
	    }	
    	
    	// POST request (회원정보가 parameter로 전송됨)
		User updateUser = new User(
                request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("nickname"),
                request.getParameter("email"),
                request.getParameter("phone")
			);
		
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
		
		Manager manager = Manager.getInstance();
		User user = manager.findUserById(userId);
		updateUser.setUserNo(user.getUserNo());
		
    	log.debug("Update User : {}", updateUser);
		
		manager.updateUser(updateUser);		
		
        return "redirect:/user/update";			
    }
    
    
    
}
