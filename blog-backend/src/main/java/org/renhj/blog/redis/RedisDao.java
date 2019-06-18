package org.renhj.blog.redis;

public interface RedisDao {
        String set(String key, Object value);
        Object get(String key);

        /**
         * 设置key过期时间
         * @param key 需要设置过期时间的key
         * @param expire 过期时间 毫秒
         * @return 成功 true 失败 false
         */
        boolean expire(String key, long expire);
        boolean expireAt(String key, long unixTime);
        Long ttl(String key);
        Boolean exist(String key);
        Long del(String... key);
}
