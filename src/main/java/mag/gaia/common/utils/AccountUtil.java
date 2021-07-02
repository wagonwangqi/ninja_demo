package mag.gaia.common.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.google.inject.Singleton;
import mag.gaia.common.models.Member;

@Singleton
public class AccountUtil {

    public Member setMemberPlainPassword(Member member,String plainpassword){
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, plainpassword.toCharArray());
        member.setPassword(bcryptHashString);
        return member;
    }

    public Boolean validatePassword(Member member,String plainPassword){
        BCrypt.Result result =  BCrypt.verifyer().verify(plainPassword.toCharArray(),member.getPassword());
        return result.verified;
    }
}
