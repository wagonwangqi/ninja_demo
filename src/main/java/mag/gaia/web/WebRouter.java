package mag.gaia.web;

import mag.gaia.staff.controllers.StaffController;
import mag.gaia.web.controllers.LoginLogoutController;
import mag.gaia.web.controllers.MoocController;
import mag.gaia.web.controllers.RegisterController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class WebRouter implements ApplicationRoutes {
    @Override
    public void init(Router router) {
        router.GET().route("/login").with(LoginLogoutController::login);
        router.POST().route("/login").with(LoginLogoutController::loginPost);
        router.GET().route("/logout").with(LoginLogoutController::logout);
        router.GET().route("/register").with(RegisterController::register);
        router.POST().route("/register").with(RegisterController::registerPost);
        router.POST().route("/searchByCourseName").with(MoocController::searchByCourseName);
        router.GET().route("/moocIndex").with(MoocController::moocIndex);
        router.GET().route("/courseIndex/{courseId}").with(MoocController::courseIndex);
        router.GET().route("/bigMenu/{bigName}").with(MoocController::bigMenuList);
        router.GET().route("/smallMenu/{smallName}").with(MoocController::smallMenuList);
        router.POST().route("/search").with(MoocController::searchByCourseName);
    }
}
