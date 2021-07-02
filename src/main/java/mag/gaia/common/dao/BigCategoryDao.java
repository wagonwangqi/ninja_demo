package mag.gaia.common.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import mag.gaia.common.models.BigCategory;
import mag.gaia.common.models.Course;
import mag.gaia.common.utils.Page;
import ninja.jpa.UnitOfWork;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

public class BigCategoryDao {
    @Inject
    Provider<EntityManager> provider;
    @Inject
    GenericDao<BigCategory> bigCategoryGenericDao;


    @UnitOfWork
    public List<BigCategory> menuList(){
        List<BigCategory> bigCategoryGenericDaoAll = bigCategoryGenericDao.findAll();
         return bigCategoryGenericDaoAll;
    }
    @UnitOfWork
    public Page<Course> coursesByBig(String bigName,int p,int limit){
        Page<Course> page=new Page<>(p,limit);
        if(Objects.isNull(bigName)){
            throw new IllegalArgumentException("bigName required");
        }
        EntityManager em = provider.get();
        TypedQuery<Course> query = em.createQuery("select x from Course x where couBig = :bigName order by x.couId desc",Course.class);
        query.setParameter("bigName",bigName).setMaxResults(page.getMaxResults()).setFirstResult(page.getFirstResult());
        List<Course> resultList = query.getResultList();
        page.setAllrecords(resultList.size());
        page.setItems(resultList);
        return  page;
    }

}
