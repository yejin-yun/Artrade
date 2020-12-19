package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.User;
import model.service.ExistingUserException;
import model.service.Manager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

       
      User user = null;
      

      try {
            if(request.getMethod().equals("GET")){
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
               request.setAttribute("noDuplication", 0);
=======
            	request.setAttribute("noDuplication", 0);
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
=======
            	request.setAttribute("noDuplication", 0);
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
=======
            	request.setAttribute("noDuplication", 0);
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
                return "/user/registerForm.jsp";
            }

            // 로그인 페이지의 자바스크립트로 빈창이 없는지 있는지 체크할 것. 
            user = new User(
                request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("nickname"),
                request.getParameter("email"),
                request.getParameter("phone")
            );
            

            Manager manager = Manager.getInstance();

            String userId = request.getParameter("userId");
            if( manager.existingUser(userId) ) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
               throw new ExistingUserException(userId + "는 존재하는 아이디입니다.");
=======
            	throw new ExistingUserException(userId + "는 존재하는 아이디입니다.");
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
=======
            	throw new ExistingUserException(userId + "는 존재하는 아이디입니다.");
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
=======
            	throw new ExistingUserException(userId + "는 존재하는 아이디입니다.");
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
            }
            
            if(!request.getParameter("submitBtn").equals("1")) {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
               request.setAttribute("user", user);
               if(!userId.equals("") && userId != null) {
                  request.setAttribute("noDuplication", 1);
               }
             return "/user/registerForm.jsp";
=======
=======
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
=======
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
            	request.setAttribute("user", user);
            	if(!userId.equals("") && userId != null) {
            		request.setAttribute("noDuplication", 1);
            	}
    			return "/user/registerForm.jsp";
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
=======
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
=======
>>>>>>> abf894c21ac248b1cedc130f6238b9c2bb9df229
            }
            

            log.debug("Create User : {}", user);

            manager.createUser(user);
    
           return "redirect:/user/login";   // 성공 시 로그인 페이지로 넘김
           
      } catch (ExistingUserException e) {   // 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
         request.setAttribute("exception", e);
         request.setAttribute("user", user);
         return "/user/registerForm.jsp";
      }
    }
}
