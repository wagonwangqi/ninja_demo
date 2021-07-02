package mag.gaia.staff;

import mag.gaia.member.controllers.MemberController;
import mag.gaia.staff.controllers.StaffController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class StaffRouter implements ApplicationRoutes {
    @Override
    public void init(Router router) {
        router.GET().route(url("")).with(StaffController::index);
        router.GET().route(url("/")).with(StaffController::index);
        router.GET().route("/staff/course/{staffId}").with(StaffController::courseList);
        router.GET().route("/staff/homework/{staffId}").with(StaffController::homeworkList);
        router.GET().route("/staff/chapter/{staffId}").with(StaffController::chapterList);
        router.GET().route("/staff/section/{staffId}").with(StaffController::sectionList);
        router.GET().route("/staff/grade/{staffId}").with(StaffController::gradeList);
//        deleteCourse
        router.GET().route("/staff/deleteCourse/{courseId}/{staffId}").with(StaffController::deleteCourse);
        router.GET().route("/staff/editCourse/{courseId}/{staffId}").with(StaffController::editCourse);
        router.POST().route("/staff/editCourse/{courseId}/{staffId}").with(StaffController::editCoursePost);
    
        router.POST().route("/staff/getChapter/{staffId}").with(StaffController::getChapter);
        router.POST().route("/staff/getSection/{staffId}").with(StaffController::getSection);

        router.GET().route("/staff/editSection/{chapterId}/{staffId}").with(StaffController::editSection);
        router.POST().route("/staff/editSection/{chapterId}/{staffId}").with(StaffController::editSectionPost);
        router.GET().route("/staff/addSection/{chapterId}/{courseId}/{staffId}").with(StaffController::addSection);
        router.POST().route("/staff/addSection/{chapterId}/{courseId}/{staffId}").with(StaffController::addSectionPost);

        router.GET().route("/staff/addCourse/{staffId}").with(StaffController::addCourse);
        router.POST().route("/staff/addCourse/{staffId}").with(StaffController::addCoursePost);
        router.GET().route("/staff/course/{staffId}").with(StaffController::courseList);
        router.POST().route("/staff/collection").with(MemberController::collection);
        router.GET().route("/staff/editHomework/{chapterId}/{staffId}").with(StaffController::editHomework);
        router.POST().route("/staff/editHomework/{chapterId}/{staffId}").with(StaffController::editHomeworkPost);
        router.POST().route("/staff/getHomework/{staffId}").with(StaffController::getHomework);

        router.POST().route("/getCourseList").with(StaffController::getCourseList);
        router.POST().route("/getCategoryList").with(StaffController::getCategoryList);
        router.GET().route("/staff/addChapter/{courseId}/{staffId}").with(StaffController::addChapter);
        router.POST().route("/staff/addChapter/{courseId}/{staffId}").with(StaffController::addChapterPost);
        router.GET().route("/staff/editVideo/{sectionId}/{staffId}").with(StaffController::editVideo);
        router.POST().route("/staff/editVideo/{sectionId}/{staffId}").with(StaffController::editVideoPost);
        router.GET().route("/staff/viewStudent/{courseId}/{staffId}").with(StaffController::viewStudent);
        router.GET().route("/staff/deleteSection/{sectionId}/{staffId}").with(StaffController::deleteSection);
        router.GET().route("/staff/editChapterName/{chapterId}/{staffId}").with(StaffController::editChapterName);
        router.POST().route("/staff/editChapterName/{chapterId}/{staffId}").with(StaffController::editChapterNamePost);
    }

    public String url(String url){
        return "/staff".concat(url);
    }
}
