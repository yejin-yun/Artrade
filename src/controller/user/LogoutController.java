package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

public class LogoutController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

      HttpSession session = request.getSession();
        
      //로그인 된 상태가 아니었을 때
      if(session.getAttribute(UserSessionUtils.USER_SESSION_KEY) == null) { 
         return "redirect:/user/login";
      } else {
         session.removeAttribute(UserSessionUtils.USER_SESSION_KEY);
         session.invalidate();   
      }
      
        return "redirect:/main";
    }
}