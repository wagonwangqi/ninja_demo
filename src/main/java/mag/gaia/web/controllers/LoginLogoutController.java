
package mag.gaia.web.controllers;

import com.google.common.base.Strings;
import mag.gaia.common.models.BigCategory;
import mag.gaia.common.models.Member;
import mag.gaia.common.services.GenericService;
import mag.gaia.common.services.MemberService;
import mag.gaia.common.utils.CookieUtils;
import mag.gaia.common.utils.ListUtil;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.session.Session;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import mag.gaia.common.dao.MemberDao;
import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;

@Singleton
public class LoginLogoutController {
    
    @Inject
    MemberService memberService;

    @Inject
    CookieUtils cookieUtils;
    @Inject
    GenericService<BigCategory> bigCategoryGenericService;

    public Result login(Context context) {
        ListUtil listUtils = new ListUtil();
        List<BigCategory> all = bigCategoryGenericService.findAll();
        listUtils.setItems(all);
        return Results.html().render("listUtils",listUtils);
    }

    public Result loginPost(@Param("loginId") String loginId,
                            @Param("password") String password,
                            Context context) {
        if(Strings.isNullOrEmpty(loginId)||Strings.isNullOrEmpty(password)){
            context.getFlashScope().error("LoginId and Password is Required");
            return Results.redirect("/login");
        }
        Boolean valid = false;
        Member member = memberService.getMemberByLoginId(loginId);
        if(!Objects.isNull(member)){
            valid = memberService.validMemberPlainpassword(member,password);
        }
        if (valid){
            Session session = context.getSession();
            session.put("loginId", loginId);
            session.setExpiryTime(24 * 60 * 60 * 1000L);
            context.getFlashScope().success("login.loginSuccessful");
            if("member".equals(member.getLevel())){
                cookieUtils.setCookie(context,"member_uid",member.getUid());
                session.put("id", String.valueOf(member.getMemId()));
                session.put("level",member.getLevel());
                return Results.redirect("/");
            }
            if("staff".equals(member.getLevel())){
                cookieUtils.setCookie(context,"staff_uid",member.getUid());
                session.put("id", String.valueOf(member.getMemId()));
                session.put("level",member.getLevel());
                return Results.redirect("/staff");
            }
            if("admin".equals(member.getLevel())){
                session.put("level",member.getLevel());
                cookieUtils.setCookie(context,"admin_uid",member.getUid());
                return Results.redirect("/admin");
            }
            return Results.redirect("/login"); //? refine later
        } else {
            context.getFlashScope().error("login.errorLogin");
            return Results.redirect("/login");
        }
    }

    public Result logout(Context context) {
//         remove any user dependent information
        context.getSession().clear();
        context.getFlashScope().success("login.logoutSuccessful");

        cookieUtils.clearCookie(context);

        return Results.redirect("/");
    }

}
