package com.miguel.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public final class RedisUtil {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    public boolean expire(String key, long timeout) {
        try {
            if (timeout > 0) {
                redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Long ttl(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public boolean del(String... keys) {
        if (keys.length != 0) {
            return redisTemplate.delete(Arrays.asList(keys)) > 0;
        }
        return false;
    }

    //---------------String相关操作-----------------

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean set(String key, Object value, Long seconds) {
        try {
            redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object get(String key) {
        return StringUtils.hasLength(key) ? redisTemplate.opsForValue().get(key) : null;
    }

    public Double incr(String key, double num) throws Exception {
        if (num < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, num);
    }

    public Long decr(String key, long num) {
        if (num > 0) {
            throw new RuntimeException("递减因子必须小于0");
        }
        return redisTemplate.opsForValue().decrement(key, num);
    }

    //---------------Hash相关操作-----------------

    public boolean hset(String key, String field, Object value) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hset(String key, String field, Object value, long timeout) {
        try {
            this.hset(key, field, value);
            if (timeout > 0) {
                this.expire(key, timeout);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hmset(String key, Map<String, Object> entries) {
        try {
            redisTemplate.opsForHash().putAll(key, entries);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hmset(String key, Map<String, Object> entries, long timeout) {
        try {
            this.hmset(key, entries);
            if (timeout > 0) {
                this.expire(key, timeout);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object hget(String key, String field) {
        return StringUtils.hasLength(key) ? redisTemplate.opsForHash().get(key, field) : null;
    }

    public List hmget(String key, List<String> fields) {
        return redisTemplate.opsForHash().multiGet(key, fields);
    }

    public Map<String, Object> hgetall(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public Long hdel(String key, Object... fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }

    public Boolean hexists(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    public double hincr(String key, String field, double incr) {
        return redisTemplate.opsForHash().increment(key, field, incr);
    }

    public double hdecr(String key, String field, double decr) {
        return redisTemplate.opsForHash().increment(key, field, -decr);
    }

    //---------------List相关操作-----------------
    public boolean lpush(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean lpush(String key, List value) {
        try {
            redisTemplate.opsForList().leftPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean rpush(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean rpush(String key, List value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object lpop(String key) {
        return StringUtils.hasLength(key) ? redisTemplate.opsForList().leftPop(key) : null;
    }

    public Object rpop(String key) {
        return StringUtils.hasLength(key) ? redisTemplate.opsForList().rightPop(key) : null;
    }

    public Object lindex(String key, long index) {
        return StringUtils.hasLength(key) ? redisTemplate.opsForList().index(key, index) : null;
    }

    public Long llen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    public boolean lrem(String key, Object value) {
        try {
            redisTemplate.opsForList().remove(key, 1, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean lset(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //---------------Set相关操作-----------------
    public boolean sadd(String key, Object... value) {
        try {
            redisTemplate.opsForSet().add(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Set smembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public Long srem(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    public boolean sismember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public Long scard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    //---------------Zset相关操作-----------------
    public boolean zadd(String key, Object value, double score) {
        try {
            redisTemplate.opsForZSet().add(key, value, score);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean zrem(String key, Object... values) {
        try {
            redisTemplate.opsForZSet().remove(key, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Long zcard(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    public Long zcount(String key, double min, double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }
}
