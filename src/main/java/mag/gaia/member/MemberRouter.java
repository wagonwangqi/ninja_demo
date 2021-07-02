package mag.gaia.member;

import mag.gaia.member.controllers.MemberController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class MemberRouter implements ApplicationRoutes {
    @Override
    public void init(Router router) {
        router.GET().route(url("")).with(MemberController::index);
        router.GET().route(url("/")).with(MemberController::index);
        router.POST().route("/member/collection").with(MemberController::collection);
        router.GET().route("/courseIndex/{courseId}/{memberId}").with(MemberController::courseIndex);
        router.GET().route("/member/chapterIndex/{chapterId}/{memberId}").with(MemberController::chapterIndex);
        router.GET().route("/member/section/{sectionId}/{memberId}").with(MemberController::section);
        router.POST().route("/member/note/{courseId}/{memberId}").with(MemberController::note);
    }

    public String url(String url){
        return "/member".concat(url);
    }
}
