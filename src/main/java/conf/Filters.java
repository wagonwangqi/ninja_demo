
package conf;

import java.util.List;


import mag.gaia.admin.filters.AdminFilter;
import mag.gaia.member.filters.MemberFilter;
import mag.gaia.staff.filters.StaffFilter;
import ninja.Filter;

public class Filters implements ninja.application.ApplicationFilters {

    @Override
    public void addFilters(List<Class<? extends Filter>> filters) {
        // Add your application - wide mag.gaia.common.filters here
        // mag.gaia.common.filters.add(MyFilter.class);
        filters.add(MemberFilter.class);
        filters.add(StaffFilter.class);
        filters.add(AdminFilter.class);
    }
}
