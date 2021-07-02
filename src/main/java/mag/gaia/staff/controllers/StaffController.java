package mag.gaia.staff.controllers;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import mag.gaia.common.dao.*;
import mag.gaia.common.models.*;
import mag.gaia.common.services.ChapterService;
import mag.gaia.common.services.CourseService;
import mag.gaia.common.services.GenericService;
import mag.gaia.common.services.VideoService;
import mag.gaia.common.utils.ListUtil;
import mag.gaia.common.utils.Page;

import mag.gaia.staff.dto.ChapterDto;
import mag.gaia.web.dto.CourseDto;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.params.PathParam;
import ninja.uploads.DiskFileItemProvider;
import ninja.uploads.FileItem;
import ninja.uploads.FileProvider;
import org.checkerframework.checker.units.qual.C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

@FileProvider(DiskFileItemProvider.class)
@Singleton
public class StaffController {
    @Inject
    GenericService<Video> videoGenericService;
    @Inject
    GenericService<Staff> staffGenericService;
    @Inject
    GenericService<Course> courseGenericService;
    @Inject
    VideoService videoService;
    @Inject
    CourseService courseService;
    @Inject
    BigCategoryDao bigCategoryDao;
    @Inject
    ChapterDao chapterDao;
    @Inject
    GenericService<Chapter> chapterGenericService;
    @Inject
    GenericService<Section> sectionGenericService;
    @Inject
    GenericService<Homework> homeworkGenericService;
    @Inject
    ChapterService chapterService;

    final Logger logger = LoggerFactory.getLogger(StaffController.class);

    public Result index(Context context) {
        return Results.html();
    }

    public Result addCourse() {
        return Results.html().template("/mag/gaia/staff/views/StaffController/courseAdd.ftl.html");
    }

    public Result addCoursePost(Context context,
                                @Param("upFilePic") FileItem upFilePic,
                                @PathParam("staffId") Long staffId,
                                @Param("couName") String couName,
                                @Param("staName") String staName,
                                @Param("upFileVideo") FileItem upFileVideo,
                                @Param("couDescription") String couDescription,
                                @Param("bigCategory") String couBig, @Param("smallCategory") String couSmall
    ) throws IOException {
        if (upFilePic == null || upFileVideo == null || staffId == null || couName.isEmpty() || staName.isEmpty() || couDescription.isEmpty()) {
            context.getFlashScope().error("bagayalu");
            logger.info(couName);
            return Results.redirect("/staff/addCourse/" + staffId + "");
        }
        String pathVid = "/assets/files/" + upFileVideo.getFileName();
        String pathPic = "/assets/files/" + upFilePic.getFileName();
        if (!(pathPic.endsWith(".jpg") || pathPic.endsWith(".png"))) {
            context.getFlashScope().error("wrong pic file");
            return Results.redirect("/staff/addCourse/" + staffId + "");
        }
        if (!(pathVid.endsWith(".mp4") || pathVid.endsWith(".mov"))) {
            context.getFlashScope().error("wrong video file");
            return Results.redirect("/staff/course/" + staffId + "");
        }
        courseService.savePic(upFilePic);
        courseService.saveVid(upFileVideo);
        Staff staff = staffGenericService.find(staffId);
        Course course = new Course();
        course.setCouName(couName);
        course.setCouPic(pathPic);
        course.setCouVideo(pathVid);
        course.setStaff(staff);
        course.setCouDescription(couDescription);
        course.setCouBig(couBig);
        course.setCouSmall(couSmall);
        courseGenericService.create(course);
        return Results.redirect("/staff/course/" + staffId + "");
    }

    public Result courseList(@Param("page") int p) {
        Page<Course> page = courseGenericService.listPaged(p, 6);
        return Results.html()
                .render("page", page);
    }


    public Result addChapter(@PathParam("courseId") Long courseId) {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(courseId);
        List<Chapter> chapterList = chapterService.getChapterList(courseId);
        ListUtil listUtil = new ListUtil();
        listUtil.setItems(chapterList);
        return Results.html().
                template("/mag/gaia/staff/views/StaffController/chapterAdd.ftl.html").
                render("courseDto", courseDto).render("listUtil", listUtil);
    }

    public Result addChapterPost(@PathParam("courseId") Long courseId,
                                 @PathParam("staffId") Long staffId,
                                 @Param("chaName") String chaName) {
        Course course = courseGenericService.find(courseId);
        Chapter chapter = new Chapter();
        chapter.setChapterName(chaName);
        chapter.setCourse(course);
        chapterGenericService.create(chapter);
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(courseId);
        List<Chapter> all = chapterService.getChapterList(courseId);
        ListUtil listUtil = new ListUtil();
        listUtil.setItems(all);
        return Results.html().
                template("/mag/gaia/staff/views/StaffController/chapterAdd.ftl.html").
                render("courseDto", courseDto).render("listUtil", listUtil);
    }

    public Result editCourse(@PathParam("courseId") Long courseId) {
        Course course = courseGenericService.find(courseId);
        return Results.html().template("/mag/gaia/staff/views/StaffController/courseEdit.ftl.html").
                render(course);
    }

    public Result editCoursePost(Context context,
                                 @Param("upFilePic") FileItem upFilePic,
                                 @PathParam("staffId") Long staffId,
                                 @PathParam("courseId") Long courseId,
                                 @Param("couName") String couName,
                                 @Param("upFileVideo") FileItem upFileVideo,
                                 @Param("couDescription") String couDescription,
                                 @Param("bigCategory") String couBig, @Param("smallCategory") String couSmall
    ) throws IOException {
        if (upFilePic == null || upFileVideo == null || staffId == null || couName.isEmpty() || couDescription.isEmpty()) {
            context.getFlashScope().error("bagayalu");
            return Results.redirect("/staff/editCourse/" + courseId + "/" + staffId + "");
        }
        String pathVid = "/assets/files/" + upFileVideo.getFileName();
        String pathPic = "/assets/files/" + upFilePic.getFileName();
        if (!(pathPic.endsWith(".jpg") || pathPic.endsWith(".png"))) {
            context.getFlashScope().error("wrong pic file");
            return Results.redirect("/staff/editCourse/" + courseId + "/" + staffId + "");
        }
        if (!(pathVid.endsWith(".mp4") || pathVid.endsWith(".mov"))) {
            context.getFlashScope().error("wrong video file");
            return Results.redirect("/staff/editCourse/" + courseId + "/" + staffId + "");
        }
        courseService.savePic(upFilePic);
        courseService.saveVid(upFileVideo);
        Course course = courseGenericService.find(courseId);
        course.setCouName(couName);
        course.setCouPic(pathPic);
        course.setCouVideo(pathVid);
        course.setCouDescription(couDescription);
        course.setCouBig(couBig);
        course.setCouSmall(couSmall);
        course.setModified(new Date());
        courseGenericService.update(course);
        return Results.redirect("/staff/course/" + staffId + "");
    }

    public Result chapterList() {
        List<Course> courseList = chapterService.getCourseList();
        List<Chapter> chapterList = chapterGenericService.findAll();
        return Results.html().render("courseList", courseList).render("chapterList", chapterList);
    }

    public Result editSection(@PathParam("chapterId") Long chapterId) {
        Chapter chapter = chapterGenericService.find(chapterId);
        List<Section> sections = chapter.getSections();
        ChapterDto chapterDto = new ChapterDto();
        chapterDto.setId(chapterId);
        return Results.html().template("/mag/gaia/staff/views/StaffController/sectionEdit.ftl.html").
                render("sections", sections).render(chapterDto);
    }

    public Result addSection(@PathParam("chapterId") Long chapterId,
                             @Param("sectionName") String sectionName) {
        Section section = new Section();
        section.setSectionName(sectionName);
        Chapter chapter = chapterGenericService.find(chapterId);
        section.setChapter(chapter);
        return Results.html().
                template("/mag/gaia/staff/views/StaffController/sectionAdd.ftl.html");
    }

    public Result editSectionPost(@Param("sectionName") String sectionName,
                                  @PathParam("chapterId") Long chapterId,
                                  @PathParam("staffId")Long staffId ){
        Chapter chapter = chapterGenericService.find(chapterId);
        Section section = new Section();
        section.setChapter(chapter);
        section.setSectionName(sectionName);
        sectionGenericService.create(section);
        return Results.redirect("/staff/section/" + staffId);
    }

    public Result addSectionPost() {
        return null;
    }

    public Result getChapter(@Param("courseId") Long courseId) {
        List<Chapter> chapterList = chapterService.getChapterList(courseId);
        Object o = JSONArray.toJSON(chapterList);
        return Results.json().render("chapterList", o);
    }

    public Result getSection(@Param("courseId") String course,
                             @Param("chapterId") String chapter) {
        Long chapterId = Long.valueOf(chapter);
        Long courseId = Long.valueOf(course);
        List<Chapter> chapterList = chapterService.getChapterList(courseId);
        List<Section> sectionList = chapterList.get(Math.toIntExact(chapterId)).getSections();
        Object o = JSONArray.toJSON(sectionList);
        return Results.json().render("sectionList", o);
    }

    public Result sectionList() {
        List<Section> sectionList = sectionGenericService.findAll();

        return Results.html().render("sectionList", sectionList);
    }

    public Result editHomework(@PathParam("chapterId")Long chapterId) {


        return Results.html().template("/mag/gaia/staff/views/StaffController/homeworkEdit.ftl.html")
                .render("chapterId",chapterId);
    }

    public Result editHomeworkPost(@PathParam("chapterId")Long chapterId,
                                   @Param("content")String content,
                                   @Param("name")String name,
                                   @Param("created")Date created,
                                   @Param("modified")Date modified,
                                   @PathParam("staffId")Long staffId) {
        Homework homework = new Homework();
        homework.setChapterId(chapterId);
        homework.setContent(content);
        homework.setCreated(created);
        homework.setModified(modified);
        homework.setName(name);
        homeworkGenericService.create(homework);

        return Results.redirect("/staff/homework/" + staffId);
    }

    public Result homeworkList() {
        List<Course> courseList = courseGenericService.findAll();
        List<Chapter> chapterList = chapterGenericService.findAll();
        List<Homework> homeworkList = homeworkGenericService.findAll();
        return Results.html().render("courseList", courseList).
                render("chapterList", chapterList).render("homeworkList", homeworkList);
    }
    public  Result gradeList() {
        return null;
    }

    public Result getHomework(@Param("courseId") Long courseId,
                              @Param("chapterId") Long chapterId) {
        List<Chapter> chapterList = chapterService.getChapterList(courseId);


        return Results.json().render("sectionList");
    }

    public Result viewStudent(@PathParam("courseId") Long courseId) {
        List<Member> studentList = courseService.getStudentList(courseId);
        return Results.html().
                template("/mag/gaia/staff/views/StaffController/studentList.ftl.html").
                render("studentList", studentList);
    }


    public Result getCourseList() {
        List<Course> courseList = courseGenericService.findAll();
        Object o = JSONArray.toJSON(courseList);
        return Results.json().render("courseList", o);
    }

    public Result getCategoryList() {
        List<BigCategory> bigCategories = bigCategoryDao.menuList();
        Object s = JSONArray.toJSON(bigCategories);
        return Results.json().render("bigCategories", s);
    }
    public Result editVideoPost(@PathParam("sectionId") Long sectionId,
                                @Param("upFileVideo") FileItem upFile,
                                @PathParam("staffId") Long staffId) throws IOException {
        int videoNum = videoService.getVideoNum(sectionId);
        logger.info(String.valueOf(sectionId));
        logger.info(String.valueOf(videoNum));
        assert upFile!=null;
        String pathVid = "/assets/files/" + upFile.getFileName();
        if (videoNum ==0) {
            logger.info("没有");
            Video video = new Video();
            video.setSection_id(sectionId);
            video.setVideo_path(pathVid);
            videoService.createFile(upFile.getFileName(), upFile);
            videoGenericService.create(video);
        }
        else {
            Video video = videoService.getVideo(sectionId);
            logger.info("已有");
            video.setVideo_path(pathVid);
            video.setSection_id(sectionId);
            videoService.createFile(upFile.getFileName(), upFile);
            videoGenericService.update(video);
        }

        return Results.redirect("/staff/section/" + staffId);
    }

    public Result editVideo(@PathParam("sectionId") Long sectionId) {
        return Results.html().template("/mag/gaia/staff/views/StaffController/videoAdd.ftl.html").
                render("sectionId", sectionId);
    }
    public  Result deleteCourse(@PathParam("courseId") Long courseId,
                                @PathParam("staffId") Long staffId) {
        try {
            Course course = courseGenericService.find(courseId);
            List<Chapter> chapters = course.getChapters();
            Chapter chapter = chapters.get(0);
            Section section = chapter.getSections().get(0);
            sectionGenericService.delete(section);
            chapterGenericService.delete(chapter);
            courseGenericService.delete(course);
            logger.info("end");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return Results.redirect("/staff/course/" + staffId);
    }

    public  Result editChapterName(@PathParam("chapterId")Long chapterId) {
        Chapter chapter = chapterGenericService.find(chapterId);
        Long couId = chapter.getCourse().getCouId();
        List<Chapter> chapterList = chapterService.getChapterList(couId);;
        return Results.html().template("/mag/gaia/staff/views/StaffController/chapterEdit.ftl.html").
                render("chapter", chapter).render("couId",couId).render("chapterList",chapterList);
    }
    public  Result editChapterNamePost(@PathParam("chapterId")Long chapterId,
                                       @Param("chaName")String chapterName,
                                       @PathParam("staffId")String staffId) {
        Chapter chapter = chapterGenericService.find(chapterId);
        Long couId = chapter.getCourse().getCouId();
        Course course = courseGenericService.find(couId);
        chapter.setChapterName(chapterName);
        chapter.setCourse(course);
        chapterGenericService.update(chapter);
    return Results.redirect("/staff/chapter/"+staffId);}

    public  Result deleteSection(@PathParam("sectionId")Long sectionId) {
//        Section section = sectionGenericService.find(sectionId);
//        sectionGenericService.delete(section);
        logger.info(String.valueOf(sectionId));
        chapterDao.deleteSection(sectionId);
        return  null;
    }


}



//    public Result addVideo(@PathParam("sectionId") Long sectionId) {
//        return Results.html().template("/mag/gaia/staff/views/StaffController/videoAdd.ftl.html").
//                render("sectionId",sectionId);
//    }
//
//    public Result addVideoPost( @PathParam("sectionId")Long sectionId,
//                                 @Param("upFileVideo") FileItem upFile,
//                                 @PathParam("staffId")Long staffId) throws IOException {
//        if (upFile != null) {
//            videoService.createFile(upFile.getFileName(), upFile,sectionId);
//        }
//        String pathVid ="/assets/files/" + upFile.getFileName();
//        Video video = videoService.getVideo(sectionId);
//        video.setSection_id(sectionId);
//        video.setVideo_path(pathVid);
//        videoGenericService.update(video);
//        return Results.redirect("/staff/section/"+staffId);
//    }


