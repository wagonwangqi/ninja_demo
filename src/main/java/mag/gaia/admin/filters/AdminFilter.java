package mag.gaia.admin.filters;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import mag.gaia.common.utils.CookieUtils;
import ninja.*;
import ninja.application.ApplicationFilters;

import java.util.List;

@Singleton
public class AdminFilter implements Filter {

    @Inject
    CookieUtils cookieUtils;

    @Override
    public Result filter(FilterChain filterChain, Context context) {
        String path = context.getRequestPath();
        if(path.startsWith("/admin")){
            String adminUid = cookieUtils.getCookie(context,"admin_uid");
            if(Strings.isNullOrEmpty(adminUid)){
                cookieUtils.clearCookie(context);
                return Results.redirect("/login");
            }
        }
        return filterChain.next(context);
    }
}
