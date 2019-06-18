package org.renhj.blog.redis;

import io.lettuce.core.RedisException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class RedisDaoImpl implements RedisDao {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public String set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisException(e.getMessage());
        }
    }

    @Override
    public Object get(String key) {
        if (key == null) return null;
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisException(e.getMessage());
        }
    }

    @Override
    public boolean expire(String key, long expire) {
        try {
            if (expire > 0) {
                redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisException(e.getMessage());
        }
    }

    @Override
    public boolean expireAt(String key, long unixTime) {
        try {
            return redisTemplate.expireAt(key, new Date(unixTime));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisException(e.getMessage());
        }
    }

    @Override
    public Long ttl(String key) {
        try {
            if (key == null) {
                return null;
            }
            return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisException(e.getMessage());
        }
    }

    @Override
    public Boolean exist(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisException(e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Long del(String... key) {
        try {
            if (key != null && key.length > 0) {
                if (key.length == 1) {
                    Boolean b = redisTemplate.delete(key[0]);
                    return 1L;
                } else {
                    return redisTemplate != null ? redisTemplate.delete(CollectionUtils.arrayToList(key)) : null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisException(e.getMessage());
        }
    }
}
