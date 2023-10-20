package by.vstu.dean.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CacheManager {


    private final org.springframework.cache.CacheManager cacheManager;

    public void evictSingleCacheValue(String cacheName, String cacheKey) {
        cacheManager.getCache(cacheName).evict(cacheKey);
    }

    public void evictAllCacheValues(String cacheName) {
        cacheManager.getCache(cacheName).clear();
    }


    public void clearAll() {
        cacheManager.getCacheNames().forEach(name -> {
            log.debug("Cleared: " + name);
            this.evictAllCacheValues(name);
        });
    }
}
