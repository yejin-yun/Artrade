package controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Artwork;
import model.ArtworkOrder;
import model.User;
import model.service.Manager;

public class PaymentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Manager manager = Manager.getInstance();
		
		
		// 로그인 안했는데 결제하려고 하니까 login 페이지로 이동시켜주기 
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";	
        }    
        
    	// 결제로 넘어가는 페이지에서는 userNo과 isInCart값, artworkNo or artworList(setAtt로 보내주기..)를 PaymentController쪽으로 넘겨줘야 한다...
    	
    	System.out.println("isInCart = " + request.getParameter("isInCart"));
    	System.out.println("userNo = " + request.getParameter("userNo"));
    	int isInCart = Integer.parseInt((String)request.getParameter("isInCart"));
    	//int userNo = Integer.parseInt(request.getParameter("userNo"));
    	
    	String userId = UserSessionUtils.getLoginUserId(request.getSession());
        
		User user = manager.findUserById(userId);
		int userNo = user.getUserNo();
    	
    	request.setAttribute("userNo", userNo);
    	request.setAttribute("isInCart", isInCart);
    	
    	List<Artwork> artworkList = null; 
    	String[] artworkNoList = null;
    	
    	if(request.getMethod().equals("GET")) {
    		// 카트에서 온건지, artwork/detail 페이지에서 온건지. 
    		String servletPath = request.getParameter("servletPath");
    		String[] array = servletPath.split("/");
 
    		request.setAttribute("frontServletPath", array[1]);
    		if(isInCart == 0) { //  artwork/detail 페이지에서 온 것이다. 
    			Artwork artwork = manager.findArtworkForUser(userNo, Integer.parseInt(request.getParameter("artworkNo")));
    			request.setAttribute("artworkNo", Integer.parseInt(request.getParameter("artworkNo")));
    			request.setAttribute("artwork", artwork);
    		} else {
    			//request.setAttribute("artworkList", (List<Artwork>) request.getAttribute("artworkList"));
    			artworkNoList = request.getParameterValues("cartArtwork");
    			
        		artworkList = new ArrayList<Artwork>();
            	for(String artworkNo : artworkNoList) {
            		System.out.println("userNo... = " + userNo);
            		int an = Integer.parseInt(artworkNo);
            		System.out.println("an = " + an);
            		artworkList.add(manager.findArtworkForUser(userNo, an));
            		request.setAttribute("artworkList", artworkList);
            	}
    		}

    		
    		return "/user/order.jsp";
    		
    	}
    	// 결제 완료에서 넘어온 경우 결제 정보를 저장함. 
    	
    	ArtworkOrder artworkOrder = new ArtworkOrder();
    	artworkOrder.setUserNo(userNo);
    	artworkOrder.setDestination(request.getParameter("destination"));
    	artworkOrder.setReceiver(request.getParameter("receiver"));
    	artworkOrder.setPhone(request.getParameter("phone"));
    	
    	

    	if(isInCart == 0) {
    		artworkList = new ArrayList<Artwork>();
    		System.out.println("pay = " + request.getParameter("artworkNo"));
    		Artwork artwork = manager.findArtworkForUser(userNo, Integer.parseInt((String)request.getParameter("artworkNo")));
    		artworkList.add(artwork);
    	} else {
    		artworkNoList = request.getParameterValues("cartArtwork");
    	}
    	
    	
    	
    	manager.createArtworkOrder(artworkOrder, artworkList);
		
    	for(Artwork a : artworkList) {
    		
    		manager.updateSoldOut(a.getArtworkNo());
    	}
    	
    	
    	return "redirect:/order/list";
	}

}


/*package controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Artwork;
import model.ArtworkOrder;
import model.service.Manager;

public class PaymentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Manager manager = Manager.getInstance();
		int isInCart = Integer.parseInt((String)request.getParameter("isInCart"));
		
		// 로그인 안했는데 결제하려고 하니까 login 페이지로 이동시켜주기 
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";		// login form 요청으로 redirect
        }    
        
    	// 결제로 넘어가는 페이지에서는 userNo과 isInCart값, artworkNo or artworList(setAtt로 보내주기..)를 PaymentController쪽으로 넘겨줘야 한다...
    	
    	int userNo = Integer.parseInt(request.getParameter("userNo"));
    	
    	request.setAttribute("userNo", userNo);
    	request.setAttribute("isInCart", isInCart);
    	
    	if(request.getMethod().equals("GET")) {
    		// 카트에서 온건지, artwork/detail 페이지에서 온건지. 
    		String servletPath = request.getParameter("servletPath");
    		String[] array = servletPath.split("/");
    		//System.out.println("request.getServletPath() = " + request.getServletPath());
        	//System.out.println("array[0] = " + array[1]);
    		request.setAttribute("frontServletPath", array[1]);
    		if(isInCart == 0) { //  artwork/detail 페이지에서 온 것이다. 
    			Artwork artwork = manager.findArtworkForUser(userNo, Integer.parseInt(request.getParameter("artworkNo")));
    			request.setAttribute("artworkNo", Integer.parseInt(request.getParameter("artworkNo")));
    			request.setAttribute("artwork", artwork);
    		} else {
    			request.setAttribute("artworkList", (List<Artwork>) request.getAttribute("artworkList"));
    		}
    		
    		return "/user/order.jsp";
    		
    	}
    	// 결제 완료에서 넘어온 경우 결제 정보를 저장함. 
    	
    	ArtworkOrder artworkOrder = new ArtworkOrder();
    	artworkOrder.setUserNo(Integer.parseInt(request.getParameter("userNo")));
    	artworkOrder.setDestination(request.getParameter("destination"));
    	artworkOrder.setReceiver(request.getParameter("receiver"));
    	artworkOrder.setPhone(request.getParameter("phone"));
    	
    	List<Artwork> artworkList; 
    	if(isInCart == 0) {
    		artworkList = new ArrayList<Artwork>();
    		System.out.println("pay = " + request.getParameter("artworkNo"));
    		Artwork artwork = manager.findArtworkForUser(userNo, Integer.parseInt((String)request.getParameter("artworkNo")));
    		artworkList.add(artwork);
    	} else {
    		//System.out.println("paymentController = " + request.getParameter("artworkList");)
    		artworkList = (List<Artwork>) request.getAttribute("artworkList");
    	}
    	
    	int artworkOrderNo = manager.createArtworkOrder(artworkOrder, artworkList);
    	request.setAttribute("artworkOrderNo", artworkOrderNo);
		
    	return "redirect:/order/product";
	}

}
*/