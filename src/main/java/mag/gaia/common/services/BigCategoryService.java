package mag.gaia.common.services;

import com.google.inject.Inject;
import mag.gaia.common.dao.BigCategoryDao;
import mag.gaia.common.models.Course;
import mag.gaia.common.utils.Page;

public class BigCategoryService {
    @Inject
    BigCategoryDao bigCategoryDao;
    public Page<Course> coursesByBig(String bigName, int p, int limit){
        return bigCategoryDao.coursesByBig(bigName, p, limit);
    }
}
