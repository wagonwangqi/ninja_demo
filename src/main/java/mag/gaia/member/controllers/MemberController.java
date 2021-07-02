package mag.gaia.member.controllers;

import com.google.inject.Inject;
import mag.gaia.common.dao.UpLoadVideoDao;
import mag.gaia.common.enums.Collects;
import mag.gaia.common.models.*;
import mag.gaia.common.services.*;
import mag.gaia.common.utils.ListUtil;
import mag.gaia.common.utils.Page;
import mag.gaia.web.dto.CourseDto;
import mag.gaia.web.dto.MenuDto;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.params.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberController {
    final Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Inject
    GenericService<Chapter> chapterGenericService;
    @Inject
    GenericService<Course> courseGenericService;
    @Inject
    CourseService courseService;
    @Inject
    VideoService videoService;
    @Inject
   ChapterService chapterService;
    @Inject
    GenericService<BigCategory> bigCategoryGenericService;
    @Inject
    GenericService<Section> sectionGenericService;
    @Inject
    GenericService<Note> noteGenericService;


    public Result index(@Param("page") int p) {
        MenuDto menuDto = new MenuDto();
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("java");
        courseDto.setCourseId(1l);
        CourseDto courseDto1 = new CourseDto();
        courseDto1.setCourseName("ninja");
        courseDto1.setCourseId(2l);
        List<CourseDto> courseDtoList = new ArrayList<>();
        courseDtoList.add(courseDto1);
        courseDtoList.add(courseDto);
        menuDto.setCategoryName("s");
        menuDto.setCourses(courseDtoList);
        MenuDto menuDto1 = new MenuDto();
        menuDto1.setCategoryName("s1");
        menuDto1.setCourses(courseDtoList);
        List<MenuDto> menuDtoList = new ArrayList<>();
        menuDtoList.add(menuDto1);
        menuDtoList.add(menuDto);
        ListUtil listUtils = new ListUtil();
        listUtils.setItems(menuDtoList);
        Page<Course> page = courseGenericService.listPaged(p, 6);
        return Results.html().render("listUtils", listUtils).render("page", page);
    }

    public Result courseIndex(@Param("page") int p,
                              @PathParam("courseId")Long courseId,
                              @PathParam("memberId") Long memberId,
                              Context context) {
        String s = context.getSession().get("id");
            Long ss = Long.valueOf(s);
        if (!String.valueOf(memberId).equals(s)){
            logger.info(String.valueOf(memberId));
            return Results.redirect("/courseIndex/"+courseId+"/"+ss);
        }
        SmallCategory smallCategory =new SmallCategory();
        smallCategory.setSmallCategoryName("java");
        smallCategory.setId(1l);
        SmallCategory smallCategory2 =new SmallCategory();
        smallCategory2.setSmallCategoryName("ninja");
        smallCategory2.setId(2l);
        List<SmallCategory> smallCategories =new ArrayList<>();
        smallCategories.add(smallCategory2);
        smallCategories.add(smallCategory);
        List<BigCategory> bigCategories=new ArrayList<>();
        ListUtil listUtils = new ListUtil();
        listUtils.setItems(bigCategories);
        Course course = courseGenericService.find(courseId);
        Collects collects =courseService.isCollect(courseId,memberId);
        int collectValue = collects.toValue();
        return Results.html().render("listUtils", listUtils).
                render("course",course). render("collectValue",collectValue);
    }

    public  Result chapterIndex(@PathParam("chapterId")Long chapterId,
                                @PathParam("memberId")Long memberId) {
        Chapter chapter = chapterGenericService.find(chapterId);
        Long courseId = chapter.getCourse().getCouId();
        List<Section> sections = chapter.getSections();
        Long id = sections.get(0).getId();
        Video video = videoService.getVideo(id);
        ListUtil listUtils = new ListUtil();
        List<BigCategory> all = bigCategoryGenericService.findAll();
        listUtils.setItems(all);
        Course course = courseGenericService.find(courseId);
        Collects collects = courseService.isCollect(courseId, memberId);
        int collectValue = collects.toValue();
        String staffName = course.getStaff().getStaffName();
        List<Chapter> chapters = chapterService.chapterList(course);
        List<Course> courseList = courseGenericService.findAll();
        List<Homework> homeworkList = chapterService.getHomeworkList(chapterId);
        return Results.html().render("listUtils",listUtils).
                render("course",course).render("collectValue",collectValue).
                render("video",video).render("staffName",staffName).
                render("chapters",chapters).render("courseList",courseList).render("homeworkList",homeworkList);
    }

    public  Result section(@PathParam("sectionId")Long sectionId,
                           @PathParam("memberId")Long memberId) {
        Section section = sectionGenericService.find(sectionId);
        Long courseId = section.getChapter().getCourse().getCouId();
        String staffName = section.getChapter().getCourse().getStaff().getStaffName();
        Video video = videoService.getVideo(sectionId);
        ListUtil listUtils = new ListUtil();
        List<BigCategory> all = bigCategoryGenericService.findAll();
        listUtils.setItems(all);
        Course course = courseGenericService.find(courseId);
        Collects collects = courseService.isCollect(courseId, memberId);
        int collectValue = collects.toValue();
        List<Course> courseList = courseGenericService.findAll();
        Long chapterId = section.getChapter().getId();
        List<Homework> homeworkList = chapterService.getHomeworkList(chapterId);
        List<Chapter> chapters = chapterService.chapterList(course);
        return Results.html().render("listUtils",listUtils).
                render("course",course).render("collectValue",collectValue).
                render("video",video).render("staffName",staffName).
               render("courseList",courseList).render("homeworkList",homeworkList).
                render("chapters",chapters);
    }

    public Result collection(@Param("courseId") Long courseId,
                             @Param("memberId") Long memberId) {
        Collects collects = courseService.isCollect(courseId, memberId);
        if (collects.equals(Collects.NEWCOLLECT)) {
            try {
                courseService.newCollect(courseId, memberId);
                return Results.json().render(200);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (collects.equals(Collects.OLDCOLLECT)) {
            Collect collect = courseService.selectCollect(courseId, memberId);
            courseService.oldCollect(collect);
            return Results.json().render(200);
        }
        return null;
    }


    public  Result note(@PathParam("courseId")Long courseId,
                        @PathParam("memberId")Long memberId,
                        @PathParam("chapterId")Long chapterId,
                        @Param("note")String noteName) {
        Note note =new Note();
        note.setNoteName(noteName);
        Course course = courseGenericService.find(courseId);
        note.setCourse(course);
        note.setCreated(new Date());
        noteGenericService.create(note);
        return Results.redirect("/member/chapterIndex/"+chapterId+"/"+memberId);
    }
}
