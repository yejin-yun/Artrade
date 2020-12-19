package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.User;
import model.service.Manager;

public class DeleteUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
    	//String deleteId = request.getParameter("userId");
    	//log.debug("Delete User : {}", deleteId);
    	
    	Manager manager = Manager.getInstance();	
    	
    	//int isSubmit = Integer.parseInt(request.getParameter("isSubmit"));
    	String userId = UserSessionUtils.getLoginUserId(request.getSession());
    	User user = manager.findUserById(userId);
    	
    	//탈퇴창에서 입력한 비밀번호 값
    	System.out.println("delete tst");
		String password = request.getParameter("password");
		System.out.println("password = " + password); //처음에 null 나옴
		//request.setAttribute("deleteClear", false);
		
		if(password == null || password.equals("")) { //처음에 null 나오는 데 password.equals("") --> 메소드 사용해서 NullPointerException 난 거. password.equals("") 할거면 || 뒤에 다가 해야 함. 할거면이 아니라 해야함. password가 null로 반환될때도 있지만 빈 문자열로 반환될 때도 있음. 아 맨처음에 getParameter때는 null로 들어오고, 새로고침이나 서브밋버튼 누르면 빈문자열 들어오는 듯.   
			request.setAttribute("deleteFailed", false);
			return "/user/withdraw.jsp";
		}

		if (!user.matchPassword(password)) {
			//비밀번호 틀림
			String message = "비밀번호가 일치하지 않습니다.";
			request.setAttribute("exception", new IllegalStateException(message));
			//if(isSubmit != 1) {
			//	request.setAttribute("deleteFailed", false);
			//}else {
				request.setAttribute("deleteFailed", true);
			//}
			
			return "/user/withdraw.jsp";
		}
		
		//request.setAttribute("deleteClear", true);
		int userNo = user.getUserNo();
		
		int result = manager.removeUser(userNo); 
		
		//logout처리
		return "redirect:/user/logout";
    }
}
