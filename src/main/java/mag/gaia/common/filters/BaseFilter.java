package mag.gaia.common.filters;

import com.google.inject.Inject;
import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;

public class BaseFilter implements Filter{

    @Override
    public Result filter(FilterChain filterChain, Context context) {
         return filterChain.next(context);
    }
}

