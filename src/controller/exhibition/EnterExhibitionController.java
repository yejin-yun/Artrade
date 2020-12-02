package controller.exhibition;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Artwork;
import model.User;
import model.service.Manager;

public class EnterExhibitionController implements Controller{
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login";		// login form ��û���� redirect
        }

        Manager manager = Manager.getInstance();
        String userId = UserSessionUtils.getLoginUserId(request.getSession());
        User user = manager.findUserById(userId);
		request.setAttribute("userNo", user.getUserNo());
        
        //user가 입장권을 구입했는지 exhibitionNo로 확인??...
        /*
        Boolean isBuy = manager.isBuyForUser(userNo, exhibitionNo);
        if(isBuy == false) {
            return "/exhibition/view.jsp";
        }
        
        manager.updateExhApplicant(request.getParameter("exhibitionNo"));
        int exhibitionNo = request.getAttribute("exhibitionNo");

        Exhibition exhibition = manager.findExhibition(exhibitionNo);
        request.setAttribute("exhibition", exhibition);
*/
        /** request를 통해 exhibitionNo가 들어온다고 가정
         * (jsp 쪽에서 입장권 구입여부를 판별해서 구입한 경우에만 이쪽으로 들어오도록 해야함 ) */
        // 해당 컨트롤러에서 userNo를 받을 필요없음. 현재 로그인한  사용자의 id 값으로 유저 객체를 얻어와 유저 번호를 이용해 해당 사용자가 입장권을 구매했는지 판단하고, 구매하지 않았을 경우 구매 페이지로 넘겨준다. 
        int exhibitionNo = (int)request.getAttribute("exhibitionNo");
        
        int result = manager.increaseOneVisitorInExhibition(exhibitionNo);
        if(result <= 0) System.out.println("ERROR : update failed : increase visitor + 1");
        
        List<Artwork> artworkInExh = manager.findArtworkInExhibition(exhibitionNo);
        
        //전시작품 리스트를 request로 전달
        request.setAttribute("artworkInExh", artworkInExh);
        
        return "/exhibition/entrance.jsp";


    }
}
