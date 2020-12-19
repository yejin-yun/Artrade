package controller.exhibition;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Artwork;
import model.Exhibition;
import model.ExhibitionBuyTicket;
import model.User;
import model.service.Manager;

public class BuyExhTicketController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 로그인 안했는데 결제하려고 하니까 login 페이지로 이동시켜주기 
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";	
        } 
    	
    	Manager manager = Manager.getInstance();
    	
    	//userNo 알아내기
    	String userId = UserSessionUtils.getLoginUserId(request.getSession());
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
		
		//exhibitionNo 알아내기
		//Exhibition exh = (Exhibition) request.getAttribute("exhibition");
		//int exhibitionNo = exh.getExhibitionNo();
		//int exhibitionNo = (int) request.getAttribute("exhibitionNo");
		int exhibitionNo = Integer.valueOf(request.getParameter("exhibitionNo"));
    	
    	if(request.getMethod().equals("GET")) {
    		Exhibition exh = manager.findExhibitionByNo(exhibitionNo);
    		
    		request.setAttribute("exhibition", exh);
    		return "/exhibition/exhOrder.jsp";
    	}
    	
		//ExhibitionBuyTicket 객체 생성
		ExhibitionBuyTicket ebt = new ExhibitionBuyTicket();
		ebt.setExhibitionNo(exhibitionNo);
		ebt.setUserNo(userNo);
		
		//입장권 구매내역 생성
		manager.createExhBuyTicket(ebt);
		
		//전시회에 전시되는 작품 목록
		List<Artwork> artworkList = manager.findArtworkInExhibition(exhibitionNo);
		
		int result = manager.increaseOneVisitorInExhibition(exhibitionNo);
        if(result <= 0) System.out.println("ERROR : update failed : increase visitor + 1");
        
		request.setAttribute("artworkList", artworkList);
		
		return "/exhibition/entrance.jsp";
	}

}
