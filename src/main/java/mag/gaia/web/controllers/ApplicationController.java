
package mag.gaia.web.controllers;

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import mag.gaia.common.utils.CookieUtils;
import ninja.Context;
import ninja.Result;
import ninja.Results;

import com.google.common.collect.Maps;
import com.google.inject.Inject;


public class ApplicationController {

    @Inject
    CookieUtils cookieUtils;

    public Result index(Context context) {
        String adminUid = cookieUtils.getCookie(context,"admin_uid");
        String staffUid = cookieUtils.getCookie(context,"staff_uid");
        String memberUid = cookieUtils.getCookie(context,"member_uid");
        if(!Strings.isNullOrEmpty(adminUid)){
            return Results.redirect("/admin");
        }
        if(!Strings.isNullOrEmpty(staffUid)){
            return Results.redirect("/staff");
        }
        if(!Strings.isNullOrEmpty(memberUid)){
            return Results.redirect("/member");
        }
            return Results.redirect("/moocIndex");
    }
}
