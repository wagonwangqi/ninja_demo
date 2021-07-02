
package mag.gaia.common.dao;

import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import mag.gaia.common.utils.DaoUtils;
import ninja.jpa.UnitOfWork;

import com.google.inject.Inject;
import com.google.inject.Provider;
import mag.gaia.common.models.Member;

public class MemberDao {
    
    @Inject
    Provider<EntityManager> provider;

    @Inject
    DaoUtils daoUtils;

    @UnitOfWork
    public Member getMemberByLoginId(String loginId){
        if(Objects.isNull(loginId)){
            throw new IllegalArgumentException("loginId required");
        }
        EntityManager em = provider.get();
        TypedQuery<Member> query = em.createQuery("select x from Member x where loginId = :loginId order by x.memId desc",Member.class);
        query.setParameter("loginId",loginId);

        Member member = daoUtils.getSingleResult(query);

        return member;
    }

}
