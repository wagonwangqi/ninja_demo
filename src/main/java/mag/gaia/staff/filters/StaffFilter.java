package mag.gaia.staff.filters;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import mag.gaia.common.utils.CookieUtils;
import ninja.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class StaffFilter implements Filter {

    static final Logger logger = LoggerFactory.getLogger(StaffFilter.class);

    @Inject
    CookieUtils cookieUtils;


    @Override
    public Result filter(FilterChain filterChain, Context context) {
        String path = context.getRequestPath();
        if (path.startsWith("/staff")) {
            String staffUid = cookieUtils.getCookie(context, "staff_uid");
            if (Strings.isNullOrEmpty(staffUid)) {
                cookieUtils.clearCookie(context);
                return Results.redirect("/login");
            }
        }
        return filterChain.next(context);
    }
}

