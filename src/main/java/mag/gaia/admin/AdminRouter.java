package mag.gaia.admin;

import mag.gaia.admin.controllers.AdminController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class AdminRouter implements ApplicationRoutes {
    @Override
    public void init(Router router) {
        router.GET().route(url("")).with(AdminController::index);
        router.GET().route(url("/")).with(AdminController::index);
        router.GET().route("/admin/bigList").with(AdminController::bigList);
        router.POST().route("/admin/addBig").with(AdminController::addBigPost);
        router.GET().route("/admin/addBig").with(AdminController::addBig);
        router.GET().route("/admin/viewSmall/{bigId}").with(AdminController::smallList);
        router.GET().route("/admin/addSmall/{bigId}").with(AdminController::addSmall);
        router.POST().route("/admin/addSmall/{bigId}").with(AdminController::addSmallPost);
    }

    public String url(String url){
        return "/admin".concat(url);
    }
}
