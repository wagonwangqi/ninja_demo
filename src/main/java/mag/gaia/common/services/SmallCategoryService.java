package mag.gaia.common.services;

import com.google.inject.Inject;
import mag.gaia.common.dao.BigCategoryDao;
import mag.gaia.common.dao.SmallCategoryDao;
import mag.gaia.common.models.Course;
import mag.gaia.common.models.SmallCategory;
import mag.gaia.common.utils.Page;


public class SmallCategoryService {
    @Inject
    SmallCategoryDao smallCategoryDao;
    @Inject
    BigCategoryDao bigCategoryDao;
    public Page<SmallCategory> smallCategoryList(int p,int limit,Long bigId){
        return smallCategoryDao.smallCategoryList(p,limit,bigId);
    }

    public Page<Course> coursesBySmall(String smallName, int p, int limit){
        return smallCategoryDao.coursesBySmall(smallName,p,limit);
    }


}
