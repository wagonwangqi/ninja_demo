package mag.gaia.member.filters;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import mag.gaia.common.utils.CookieUtils;
import ninja.*;
import ninja.application.ApplicationFilters;

import java.util.List;

@Singleton
public class MemberFilter implements Filter {

    @Inject
    CookieUtils cookieUtils;
    @Override
    public Result filter(FilterChain filterChain, Context context) {
        String path = context.getRequestPath();
        if(path.startsWith("/member")){
            String memberUid = cookieUtils.getCookie(context,"member_uid");
            if(Strings.isNullOrEmpty(memberUid)){
                cookieUtils.clearCookie(context);
                return Results.redirect("/login");
            }
        }
        return filterChain.next(context);
    }
}
