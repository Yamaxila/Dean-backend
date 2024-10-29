package by.vstu.dean.core.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Objects;

@Configuration
@EnableCaching
@Slf4j
public class CachingConfiguration {

    final static String CACHE_RESOLVER_NAME = "simpleCacheResolver";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

    @Bean(CACHE_RESOLVER_NAME)
    public CacheResolver cacheResolver(CacheManager cacheManager) {
        return new RuntimeCacheResolver(cacheManager);
    }

    @Scheduled(fixedRateString = "${caching.spring.ttl}")
    public void clearCache() {
        log.info("clearing cache...");

        long startTime = System.currentTimeMillis();

        this.cacheManager()
                .getCacheNames()
                .stream()
                .map(cacheManager()::getCache)
                .filter(Objects::nonNull)
                .forEach(Cache::clear);

        log.info("done in {} s.!", (System.currentTimeMillis() - startTime) / 1000L);
    }

}
