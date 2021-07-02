package mag.gaia.common.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import mag.gaia.common.enums.Collects;
import mag.gaia.common.models.Collect;
import ninja.jpa.UnitOfWork;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static mag.gaia.common.utils.NormalUtils.getSingleResult;


public class CollectDao {
    @Inject
    Provider<EntityManager> entityManagerPovider;
    @UnitOfWork
    public  Collect selectCollect(Long courseId,Long memberId){
        EntityManager entityManager = entityManagerPovider.get();
        TypedQuery<Collect> query = entityManager.createQuery
                ("select x from Collect x " +
                        "where x.couId = :courseId and x.memId = :memberId", Collect.class);
        Collect collect =getSingleResult( query.setParameter("courseId", courseId).setParameter("memberId", memberId));
        return  collect;
    }
    @UnitOfWork
    public Collects isCollect(Long courseId, Long memberId) {
       Collect collect = selectCollect(courseId,memberId);
        if (!ObjectUtils.isEmpty(collect) && collect.getCollect()) {
            // 如果当前用户收藏过，且未在个人管理页面取消收藏
            return Collects.HASCOLLECT;
        }
        else if(ObjectUtils.isEmpty(collect)){
            //如果从未收藏
            return Collects.NEWCOLLECT; }
        else {
            //收藏过但在个人页面取消（数据库里已有）
            return Collects.OLDCOLLECT;
        }
    }

    @Transactional
    public void newCollect(Long courseId,Long memberId){
        Collect collect = new Collect();
        EntityManager entityManager = entityManagerPovider.get();
        collect.setCollect(true);
        collect.setCouId(courseId);
        collect.setMemId(memberId);
        entityManager.persist(collect);
    }
    @Transactional
    public void oldCollect(Collect collect){
        EntityManager entityManager = entityManagerPovider.get();
        collect.setCollect(true);
        entityManager.persist(collect);
    }
}

