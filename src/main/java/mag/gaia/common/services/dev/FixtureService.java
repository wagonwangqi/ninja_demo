package mag.gaia.common.services.dev;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.sun.org.apache.xpath.internal.operations.Bool;
import mag.gaia.common.dao.GenericDao;
import mag.gaia.common.dao.MemberDao;
import mag.gaia.common.models.Member;
import mag.gaia.common.utils.AccountUtil;

import java.util.Objects;

public class FixtureService {

    @Inject
    GenericDao<Member> memberGenericDao;

    @Inject
    AccountUtil accountUtil;

    @Inject
    MemberDao memberDao;

    public void fixture(){
        this.fixtureDevAdmin();
    }

    protected Member fixtureDevAdmin(){
        Member member = memberDao.getMemberByLoginId("gaia@mag.52cebu.com");
        Boolean exists = true;
        if(Objects.isNull(member)) {
            member = new Member();
            exists = false;
        }

        member.setEmail("gaia@mag.52cebu.com");
        member.setLevel("admin");
        member = accountUtil.setMemberPlainPassword(member,"admin123");

        if(exists){
            member = memberGenericDao.update(member);
        }else{
            member = memberGenericDao.create(member);
        }
        return member;
    }

}
