package mag.gaia.common.services;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.persist.Transactional;

import mag.gaia.common.dao.GenericDao;
import mag.gaia.common.utils.Page;
import ninja.jpa.UnitOfWork;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GenericService <T>
{
    @Inject
    GenericDao<T> dao;


    public T create(T t){
        return dao.create(t);
    }

    public void delete(T t){
        dao.delete(t);
    }

    public T find(final Long id){
        return dao.find(id);
    }

    public T update(T t){
        return dao.update(t);
    }

    public long count() {
        return dao.count();
    }

    public Page<T> listPaged(int p, int limit){
        return dao.listPaged(p,limit);
    }

    public Page<T> listPaged(int p, int limit,String sortname,String sortorder){
        return dao.listPaged(p,limit,sortname,sortorder);
    }

    public List<T> findAll(){
        return dao.findAll();
    }

    public Integer deleteAll() {
        return dao.deleteAll();
    }
}
