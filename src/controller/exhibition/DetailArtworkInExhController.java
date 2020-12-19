package controller.exhibition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Artwork;
import model.service.Manager;

public class DetailArtworkInExhController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Manager manager = Manager.getInstance();
		int artworkNo = Integer.parseInt(request.getParameter("artworkNo"));
		
		Artwork artwork = manager.findArtworkForNotUser(artworkNo);
		
		request.setAttribute("artwork", artwork);
		
		return "/exhibition/detail.jsp";
	}

}
