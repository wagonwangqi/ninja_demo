package mag.gaia.common.services;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import mag.gaia.common.dao.MemberDao;
import mag.gaia.common.models.Member;
import mag.gaia.common.utils.AccountUtil;

import java.util.Objects;

public class MemberService {

    @Inject
    MemberDao memberDao;

    @Inject
    AccountUtil accountUtil;

    public Member getMemberByLoginId(String loginId){
        return memberDao.getMemberByLoginId(loginId);
    }

    public Boolean validMemberPlainpassword(Member member,String plainPassword){
        if(Objects.isNull(member)|| Strings.isNullOrEmpty(plainPassword)){
            throw new IllegalArgumentException("Member and plainPassword is required");
        }
        return accountUtil.validatePassword(member,plainPassword);
    }
}
