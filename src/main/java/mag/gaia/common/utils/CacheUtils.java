package mag.gaia.common.utils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ninja.cache.NinjaCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class CacheUtils {

    final static Logger logger = LoggerFactory.getLogger(CacheUtils.class);

    @Inject
    NinjaCache cache;

    public void add(String key, Object value, String expiration) {
        cache.add(key,value,expiration);
    }

    public void add(String key, Object value) {
        cache.add(key,value,"100mn");
    }

    public void flush(String key,Object value){
        this.remove(key);
        this.add(key,value);
    }

    public void set(String key, Object value,String expiration) {
        cache.set(key, value, expiration);
    }

    public Object get(String key) {
        Object value = cache.get(key);
        return value;
    }

    public void remove(String key) {
        cache.delete(key);
    }

}
