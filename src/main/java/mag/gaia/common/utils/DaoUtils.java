package mag.gaia.common.utils;

import com.google.inject.Singleton;

import javax.persistence.TypedQuery;
import java.util.List;

@Singleton
public class DaoUtils {

    public <T> T getSingleResult(TypedQuery<T> query) {
        query.setFirstResult(0);
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }
}
