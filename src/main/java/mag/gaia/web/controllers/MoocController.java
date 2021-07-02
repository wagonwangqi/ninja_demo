package mag.gaia.web.controllers;

import com.google.inject.Inject;
import mag.gaia.common.dao.BigCategoryDao;
import mag.gaia.common.dao.ChapterDao;
import mag.gaia.common.dao.SmallCategoryDao;
import mag.gaia.common.enums.Collects;
import mag.gaia.common.models.*;
import mag.gaia.common.services.*;
import mag.gaia.common.utils.ListUtil;
import mag.gaia.common.utils.Page;
import mag.gaia.web.dto.CourseDto;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.cache.NinjaCache;
import ninja.params.Param;
import ninja.params.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Objects;


public class MoocController {
    final Logger logger = LoggerFactory.getLogger(MoocController.class);
    @Inject
    GenericService<Course> courseGenericService;
    @Inject
    CourseService courseService;
    @Inject
    GenericService<BigCategory> bigCategoryGenericService;
    @Inject
    SmallCategoryService smallCategoryService;
    @Inject
    BigCategoryService bigCategoryService;
    @Inject
    GenericService<Chapter> chapterGenericService;
    @Inject
    ChapterService chapterService;

    public Result moocIndex(@Param("page") int p) {
        ListUtil listUtils = new ListUtil();
        List<BigCategory> all = bigCategoryGenericService.findAll();
        listUtils.setItems(all);
        Page<Course> page = courseGenericService.listPaged(p, 6);
        return Results.html().render("listUtils", listUtils).render("page", page);
    }
    public Result courseIndex(@Param("page") int p,
                              @PathParam("courseId") Long courseId,
                              Context context) {

        String id = context.getSession().get("id");
        if(Objects.isNull(id)){
            ListUtil listUtils = new ListUtil();
            List<BigCategory> all = bigCategoryGenericService.findAll();
            listUtils.setItems(all);
            Course course = courseGenericService.find(courseId);
            List<Chapter> chapters = chapterService.chapterList(course);
            ListUtil listUtil=new ListUtil();
            listUtil.setItems(chapters);
            String staffName = course.getStaff().getStaffName();
            CourseDto courseDto=new CourseDto();
            courseDto.setTeacherName(staffName);
            return Results.html().render("listUtils", listUtils).
                    render("course", course).render("collectValue",0).
                    render(listUtil).render("courseDto",courseDto);
        }
        Long memberId = Long.valueOf(id);
        ListUtil listUtils = new ListUtil();
        List<BigCategory> all = bigCategoryGenericService.findAll();
        listUtils.setItems(all);
        Course course = courseGenericService.find(courseId);
         String staffName = course.getStaff().getStaffName();
        CourseDto courseDto=new CourseDto();
        courseDto.setTeacherName(staffName);
        Collects collects = courseService.isCollect(courseId, memberId);
        int collectValue = collects.toValue();
        List<Chapter> chapters = chapterService.chapterList(course);
        ListUtil listUtil=new ListUtil();
        listUtil.setItems(chapters);
        return Results.html().render("listUtils", listUtils).
                render("course", course).render("collectValue", collectValue).
                render(listUtil).render("courseDto",courseDto);
    }
    public Result bigMenuList(@PathParam("bigName") String bigName,
                              @Param("page") int p) {
        ListUtil listUtils = new ListUtil();
        List<BigCategory> all = bigCategoryGenericService.findAll();
        listUtils.setItems(all);
        Page<Course> page = bigCategoryService.coursesByBig(bigName, p, 6);
        return Results.html().render("page", page).render("listUtils", listUtils);
    }
    public Result smallMenuList(@PathParam("smallName") String smallName,
                                @Param("page") int p) {
        ListUtil listUtils = new ListUtil();
        List<BigCategory> all = bigCategoryGenericService.findAll();
        listUtils.setItems(all);
        Page<Course> page = smallCategoryService.coursesBySmall(smallName, p, 6);
        return Results.html().render("page", page).render("listUtils", listUtils);
    }

    public Result searchByCourseName(@Param("name")String name,@Param("page")int p)
    {

        ListUtil listUtils = new ListUtil();
        List<BigCategory> all = bigCategoryGenericService.findAll();
        listUtils.setItems(all);
        Page<Course> page = courseService.searchByCourseName(name,p,6);
        return Results.html().render("page",page).render("listUtils",listUtils);
    }

}
