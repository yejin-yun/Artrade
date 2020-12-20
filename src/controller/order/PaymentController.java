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
		
		
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";	
        }    
        
    	
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
    	List<Artwork> artworkList2 = null; 
    	String[] artworkNoList = null;
    	
    	if(request.getMethod().equals("GET")) {
    		String servletPath = request.getParameter("servletPath");
    		String[] array = servletPath.split("/");
 
    		request.setAttribute("frontServletPath", array[1]);
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
    	
    	ArtworkOrder artworkOrder = new ArtworkOrder();
    	artworkOrder.setUserNo(userNo);
    	artworkOrder.setDestination(request.getParameter("destination"));
    	artworkOrder.setReceiver(request.getParameter("receiver"));
    	artworkOrder.setPhone(request.getParameter("phone"));
    	
    	

    		artworkList = (List<Artwork>)request.getAttribute("artworkList");
    		System.out.println("pay = " + request.getParameter("artworkNo"));
    		Artwork artwork = manager.findArtworkForUser(userNo, Integer.parseInt((String)request.getParameter("artworkNo")));
    		artworkList.add(artwork);
    	} else {
    		artworkNoList = request.getParameterValues("payment_product");
    		
    		artworkList2 = new ArrayList<Artwork>();
        	for(String artworkNo : artworkNoList) {
        		System.out.println("userNo... = " + userNo);
        		int an = Integer.parseInt(artworkNo);
        		System.out.println("an = " + an);
        		artworkList2.add(manager.findArtworkForUser(userNo, an));
        		
        	}
        	
    	}
    	
    	
    	
    	manager.createArtworkOrder(artworkOrder, artworkList2);
		
    	for(Artwork a : artworkList2) {
    		
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
		
		// 濡쒓렇�씤 �븞�뻽�뒗�뜲 寃곗젣�븯�젮怨� �븯�땲源� login �럹�씠吏�濡� �씠�룞�떆耳쒖＜湲� 
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";		// login form �슂泥��쑝濡� redirect
        }    
        
    	// 寃곗젣濡� �꽆�뼱媛��뒗 �럹�씠吏��뿉�꽌�뒗 userNo怨� isInCart媛�, artworkNo or artworList(setAtt濡� 蹂대궡二쇨린..)瑜� PaymentController履쎌쑝濡� �꽆寃⑥쨾�빞 �븳�떎...
    	
    	int userNo = Integer.parseInt(request.getParameter("userNo"));
    	
    	request.setAttribute("userNo", userNo);
    	request.setAttribute("isInCart", isInCart);
    	
    	if(request.getMethod().equals("GET")) {
    		// 移댄듃�뿉�꽌 �삩嫄댁�, artwork/detail �럹�씠吏��뿉�꽌 �삩嫄댁�. 
    		String servletPath = request.getParameter("servletPath");
    		String[] array = servletPath.split("/");
    		//System.out.println("request.getServletPath() = " + request.getServletPath());
        	//System.out.println("array[0] = " + array[1]);
    		request.setAttribute("frontServletPath", array[1]);
    		if(isInCart == 0) { //  artwork/detail �럹�씠吏��뿉�꽌 �삩 寃껋씠�떎. 
    			Artwork artwork = manager.findArtworkForUser(userNo, Integer.parseInt(request.getParameter("artworkNo")));
    			request.setAttribute("artworkNo", Integer.parseInt(request.getParameter("artworkNo")));
    			request.setAttribute("artwork", artwork);
    		} else {
    			request.setAttribute("artworkList", (List<Artwork>) request.getAttribute("artworkList"));
    		}
    		
    		return "/user/order.jsp";
    		
    	}
    	// 寃곗젣 �셿猷뚯뿉�꽌 �꽆�뼱�삩 寃쎌슦 寃곗젣 �젙蹂대�� ���옣�븿. 
    	
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