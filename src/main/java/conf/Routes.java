package conf;

import mag.gaia.admin.AdminRouter;
import mag.gaia.staff.StaffRouter;
import mag.gaia.member.MemberRouter;
import mag.gaia.web.WebRouter;
import mag.gaia.web.controllers.MoocController;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;

import com.google.inject.Inject;

import mag.gaia.web.controllers.ApplicationController;


public class Routes implements ApplicationRoutes {
    
    @Inject
    NinjaProperties ninjaProperties;

    @Inject
    AdminRouter adminRouter;

    @Inject
    StaffRouter staffRouter;

    @Inject
    WebRouter webRouter;

    @Inject
    MemberRouter memberRouter;

    @Override
    public void init(Router router) {  
        
        // puts test data into db:
        if (!ninjaProperties.isProd()) {

        }

        webRouter.init(router);
        staffRouter.init(router);
        adminRouter.init(router);
        memberRouter.init(router);

 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
        router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").with(MoocController::moocIndex);
    }

}
