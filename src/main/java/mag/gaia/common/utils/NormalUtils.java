package mag.gaia.common.utils;

import javax.persistence.TypedQuery;
import java.util.List;

public class NormalUtils {
    public static <T> T getSingleResult(TypedQuery<T> query) {
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
