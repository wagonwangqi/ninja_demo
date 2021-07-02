package mag.gaia.admin.controllers;


import com.alibaba.fastjson.JSON;
import com.google.inject.Inject;
import mag.gaia.admin.dto.CategoryDto;
import mag.gaia.admin.filters.AdminFilter;
import mag.gaia.common.dao.BigCategoryDao;
import mag.gaia.common.dao.SmallCategoryDao;
import mag.gaia.common.models.BigCategory;
import mag.gaia.common.models.SmallCategory;
import mag.gaia.common.services.GenericService;
import mag.gaia.common.services.SmallCategoryService;
import mag.gaia.common.utils.Page;

import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.params.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

@FilterWith(AdminFilter.class)
public class AdminController {
    final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Inject
    GenericService<BigCategory> bigCategoryGenericService;
    @Inject
    GenericService<SmallCategory> smallCategoryGenericService;
    @Inject
    SmallCategoryService smallCategoryService;
    @Inject
    SmallCategoryDao smallCategoryDao;
    @Inject
    BigCategoryDao bigCategoryDao;

    public Result index(Context context) {
        return Results.html();
    }

    public Result bigList(@Param("page") int p) {
        Page<BigCategory> page = bigCategoryGenericService.listPaged(p, 6);
        return Results.html().render("page", page);
    }


    public Result addBig() {
        return Results.html().template("/mag/gaia/admin/views/AdminController/bigAdd.ftl.html");
    }

    public Result addBigPost(@Param("bigCategoryName") String bigCategoryName,
                             Context context) {
        if (bigCategoryName == null) {
            context.getFlashScope().error("bagayalu");
            return Results.redirect("/admin/addBig");
        }
        BigCategory bigCategory = new BigCategory();
        bigCategory.setBigCategoryName(bigCategoryName);
        bigCategoryGenericService.create(bigCategory);
        return Results.redirect("/admin/bigList");
    }

    public Result smallList(@PathParam("bigId") Long bigId,
                            @Param("page") int p) {
        Page<SmallCategory> page = smallCategoryService.smallCategoryList(p, 6, bigId);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(bigId);
        return Results.html().render("page", page).render(categoryDto);
    }

    public Result addSmall(@PathParam("bigId") Long bigId) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(bigId);
        return Results.html().template("/mag/gaia/admin/views/AdminController/smallAdd.ftl.html").render(categoryDto);
    }
    public Result addSmallPost(@PathParam("bigId") Long bigId,
                               @Param("smallCategoryName") String smallCategoryName,
                               Context context) {
        if (bigId == null && smallCategoryName.isEmpty()) {
            context.getFlashScope().error("baga");
            return Results.html().redirect("/admin/addSmall/"+bigId);
        }
        BigCategory bigCategory = bigCategoryGenericService.find(bigId);
        SmallCategory smallCategory = new SmallCategory();
        smallCategory.setSmallCategoryName(smallCategoryName);
        smallCategory.setBigCategory(bigCategory);
        smallCategoryGenericService.create(smallCategory);
        return Results.redirect("/admin/viewSmall/"+bigId);
    }

}
