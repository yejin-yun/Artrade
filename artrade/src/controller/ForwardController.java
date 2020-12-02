package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import model.service.Manager;

public class ForwardController implements Controller {
    private String forwardUrl;

    public ForwardController(String forwardUrl) {
        if (forwardUrl == null) {
            throw new NullPointerException("forwardUrl is null. �̵��� URL�� �Է��ϼ���.");
        }
        this.forwardUrl = forwardUrl;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	System.out.println("forwardUrl = " + forwardUrl);
    	if(forwardUrl.equals("/main/index.jsp")) {
    		Manager manager = Manager.getInstance();
    		List<Artwork> artworks = manager.findArtworkList();
    		if(artworks == null ) {
    			System.out.println("nulll");
    		}else {
    			System.out.println("aaaa = " + artworks.size());
    		}
        	List<Exhibition> exhibitions = manager.findExhibitionListForNotUser();
        	//List<Onedayclass> onedayclasses = manager.findOnedayclassList();
    		req.setAttribute("artworks", artworks);
    		req.setAttribute("exhibitions", exhibitions);
    		//req.setAttribute("onedayclasses", onedayclasses);
    	}
        return forwardUrl;
    }
}
