package mag.gaia.common.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import mag.gaia.common.models.BigCategory;
import mag.gaia.common.models.Course;
import mag.gaia.common.models.SmallCategory;
import mag.gaia.common.utils.Page;
import ninja.jpa.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SmallCategoryDao {
    final Logger logger = LoggerFactory.getLogger(SmallCategoryDao.class);

    @Inject
    Provider<EntityManager> provider;
    @Inject
    GenericDao<SmallCategory> smallCategoryGenericDao;
    @Inject
    GenericDao<BigCategory> bigCategoryGenericDao;
    @UnitOfWork
    public Page<SmallCategory> smallCategoryList(int p,int limit, Long bigId) {
        Page<SmallCategory> page = new Page<>(p,limit);
        BigCategory bigCategory = bigCategoryGenericDao.find(bigId);
        List<SmallCategory> smallCategories = bigCategory.getSmallCategories();
        page.setItems(smallCategories);
        page.setAllrecords(smallCategories.size());
        return page;
    }
    @UnitOfWork
    public Page<Course> coursesBySmall(String smallName, int p, int limit){
        Page<Course> page=new Page<>(p,limit);
        if(Objects.isNull(smallName)){
            throw new IllegalArgumentException("smallName required");
        }
        EntityManager em = provider.get();
        TypedQuery<Course> query =
                em.createQuery("select x from Course x where couSmall = :smallName order by x.couId desc",Course.class);
        query.setParameter("smallName",smallName).setMaxResults(page.getMaxResults()).setFirstResult(page.getFirstResult());
        List<Course> resultList = query.getResultList();
        page.setAllrecords(resultList.size());
        page.setItems(resultList);
        return  page;
    }





}
