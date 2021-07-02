package mag.gaia.common.dao;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.persist.Transactional;

import mag.gaia.common.utils.Page;
import ninja.jpa.UnitOfWork;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GenericDao <T>
{

    protected final Class< T > type;

    @Inject
    Provider<EntityManager> provider;

    @Inject
    public GenericDao(TypeLiteral<T> type)
    {
        this.type = (Class<T>)type.getRawType();
    }

    @Transactional
    public T create(T t)
    {
        EntityManager em = provider.get();
        em.persist(t);
        em.flush();
        return t;
    }

    @Transactional
    public void delete(T t)
    {
        t = this.provider.get().merge(t);
        this.provider.get().remove(t);
    }

    @UnitOfWork
    public T find(final Long id)
    {
        return this.provider.get().find(type, id);
    }

    @Transactional
    public T update(T t)
    {
        EntityManager em = provider.get();
        em.merge(t);
        em.flush();
        return t;
    }

    @UnitOfWork
    public long count() {
        final StringBuffer queryString = new StringBuffer(
                "SELECT count(o) from ");
        queryString.append(type.getSimpleName()).append(" o ");
        final Query query = this.provider.get().createQuery(queryString.toString());

        Long count =  (Long) query.getSingleResult();
        return count;
    }

    @UnitOfWork
    public Page<T> listPaged(int p, int limit){
        Page<T> page = new Page<T>(p,limit);
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(type.getSimpleName()).append(" o order by o.id desc");
        final Query q = this.provider.get().createQuery(queryString.toString());
        q.setFirstResult(page.getFirstResult());
        q.setMaxResults(page.getMaxResults());
        List<T> l = q.getResultList();
        page.setAllrecords(this.count());
        page.setItems(l);
        return page;
    }
    @UnitOfWork
    public Page<T> listPaged(int p, int limit,String sortname,String sortorder){
        Page<T> page = new Page<T>(p,limit);
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(type.getSimpleName()).append(" o order by o."+sortname+" "+sortorder);
        final Query q = this.provider.get().createQuery(queryString.toString());
        page.setAllrecords(this.count());
        q.setFirstResult(page.getFirstResult());
        q.setMaxResults(page.getMaxResults());
        List<T> l = q.getResultList();
        page.setItems(l);
        return page;
    }

    @UnitOfWork
    public List<T> findAll(){
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(type.getSimpleName()).append(" o order by o.id desc");
        final Query query = this.provider.get().createQuery(queryString.toString());
        return query.getResultList();
    }

    @Transactional
    public Integer deleteAll() {
        final StringBuffer queryString = new StringBuffer(
                "DELETE  from ");
        queryString.append(type.getSimpleName()).append(" o ");
        final Query query = this.provider.get().createQuery(queryString.toString());

        Integer count =  (Integer) query.executeUpdate();
        return count;
    }
}
