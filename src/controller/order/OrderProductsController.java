package controller.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Artwork;
import model.User;
import model.service.Manager;

public class OrderProductsController implements Controller {

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      // 로그인 여부 확인
       if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";      // login form 요청으로 redirect
        }
        
       /*
       String currentPageStr = request.getParameter("currentPage");   
      int currentPage = 1;
      if (currentPageStr != null && !currentPageStr.equals("")) {
         currentPage = Integer.parseInt(currentPageStr);
      }      
       */
       
       Manager manager = Manager.getInstance();
       
       
      
       System.out.println("artworkOrderNo = " + request.getParameter("ArtworkOrderNo"));
      int artworkOrderNo = 	Integer.parseInt(request.getParameter("ArtworkOrderNo"));
      
      String userId = UserSessionUtils.getLoginUserId(request.getSession());
      
      User user = manager.findUserById(userId);
      int userNo = user.getUserNo();
      
      List<Artwork> artworkList = manager.findOrderArtworkListByOrdNo(artworkOrderNo);
      
      // List<User> userList = manager.findUserList(currentPage, countPerPage);

      // cartArtworkList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
      request.setAttribute("artworkList", artworkList);          
      request.setAttribute("isLogined", request.getParameter("isLogined")); // artwork detail 페이지에서 사용하기 위해서 
      request.setAttribute("userNo", request.getParameter("userNo")); // artwork detail 페이지에서 사용하기 위해서      
      request.setAttribute("ArtworkOrderNo", artworkOrderNo);

      return "/user/orderDetail.jsp"; 
      
   }

}