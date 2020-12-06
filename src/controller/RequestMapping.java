package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.artwork.DetailArtworkController;
import controller.artwork.ListArtworkController;
import controller.artwork.RegisterArtworkController;
import controller.exhibition.EnterExhibitionController;
import controller.exhibition.ListExhibitionController;
import controller.user.CartAddController;
import controller.user.CartRemoveController;
import controller.user.DeleteUserController;
import controller.user.DeleteWishlistController;
import controller.user.ListCartController;
import controller.user.ListWishlistController;
import controller.user.LoginController;
import controller.user.LogoutController;
import controller.user.MoveFromWishToCartController;
import controller.user.RegisterUserController;
import controller.user.WishlistUpdateController;
import controller.order.ListOrderController;
import controller.order.OrderProductsController;
import controller.order.PaymentController;

//import controller.user.*;
//import controller.exhibition.EnterExhibitionController;
//import controller.onedayclass.RegisterOnedayclassController;
//import controller.order.ListOrderController;
//import controller.order.OrderProductsController;
//import controller.trade.RegisterParticipantTradeController;
//import controller.trade.RegisterTradeController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 揶쏉옙 占쎌뒄筌ｏ옙 uri占쎈퓠 占쏙옙占쎈립 controller 揶쏆빘猿쒐몴占� 占쏙옙占쎌삢占쎈막 HashMap 占쎄문占쎄쉐
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 揶쏉옙 uri占쎈퓠 占쏙옙占쎌벓占쎈┷占쎈뮉 controller 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐 獄쏉옙 占쏙옙占쎌삢
        mappings.put("/main", new ForwardController("/main/index.jsp"));
        
        mappings.put("/user/login", new LoginController()); //clear.
        mappings.put("/user/logout", new LogoutController()); //clear
        mappings.put("/user/register", new RegisterUserController()); //clear
      //  mappings.put("/user/update", new UpdateUserController()); //clear
        mappings.put("/user/quit", new DeleteUserController()); //clear
        mappings.put("/user/wishlist", new ListWishlistController()); //clear
        mappings.put("/user/cartlist", new ListCartController()); //clear
     //   mappings.put("/user/order", new OrderController()); //clear //雅뚯눖揆筌ｌ꼶�봺
          mappings.put("/user/wishlistLike", new WishlistUpdateController());//half-clear
        mappings.put("/user/deletewishlist", new DeleteWishlistController());
          mappings.put("/user/fromWishToCart", new MoveFromWishToCartController());
          mappings.put("/user/cartAdd", new CartAddController()); 
          mappings.put("/user/cartRemove", new CartRemoveController()); 
 
//
        mappings.put("/order/product", new OrderProductsController()); //clear //�뤃�됤꼻占쎄땀占쎈열占쎄맒占쎄쉭癰귣떯由� // /user/ or /order/
        mappings.put("/order/payment", new PaymentController());
        mappings.put("/order/list", new ListOrderController()); //clear //雅뚯눖揆鈺곌퀬�돳
        
        mappings.put("/artwork/list", new ListArtworkController()); //clear
        mappings.put("/artwork/register", new RegisterArtworkController()); //clear
        mappings.put("/artwork/detail", new DetailArtworkController()); //clear
       // mappings.put("/artwork/order/form", new OrderArtworkController());
//
//        mappings.put("/onedayclass/list", new ListOnedayclassController()); //clear
//       // mappings.put("onedayclass/apply", new ApplyOnedaycalssController());
//        mappings.put("/onedayclass/register", new RegisterOnedayclassController()); //clear
//        
        mappings.put("/exhibition/list", new ListExhibitionController()); //clear
        mappings.put("/exhibition/entrance", new EnterExhibitionController());//half-clear
        mappings.put("/exhibition/detail", new ForwardController("/exhibition/detail.jsp")); //clear
        
        mappings.put("/exhibition/search", new ExhibitionSearchController());
        
//        //mappings.put("exhibition/detail", new DetailArtworkInExhController());
//        
//        mappings.put("/trade/list", new ListTradeController()); //clear
//        mappings.put("/trade/register", new RegisterTradeController()); //clear
//        mappings.put("/trade/participantRegister", new RegisterParticipantTradeController());//clear
        
        mappings.put("/search", new SearchAllContentsController());

        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 雅뚯눘堉깍쭪占� uri占쎈퓠 占쏙옙占쎌벓占쎈┷占쎈뮉 controller 揶쏆빘猿쒐몴占� 筌≪뼚釉� 獄쏆꼹�넎
        return mappings.get(uri);
    }
}
