package com.ant.admin.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * @author zhaopinchao
 * @date 2018-7-29 10:07
 */
@Component
public class JedisUtil {

    private Logger logger = LoggerFactory.getLogger(JedisUtil.class);
    private static JedisPool jedisPool;

    @Value("${spring.redis.host}")
    private String REDIS_HOST;

    @Value("${spring.redis.port}")
    private int REDIS_PORT;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.password}")
    private String password;


    private Jedis getResource() {
        synchronized (JedisUtil.class) {
            if (jedisPool == null) {
                jedisPool = new JedisPool(new JedisPoolConfig(), REDIS_HOST, REDIS_PORT,timeout,password);
            }
            return jedisPool.getResource();
        }
    }


    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = getResource();
        try {
            jedis.set(key, value);
            if (expire > 0) {
                jedis.expire(key, expire);
            }
        } finally {
            jedis.close();
        }
        return value;
    }

    public byte[] get(byte[] key) {
        Jedis jedis = getResource();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    public long del(byte[] key) {
        Jedis jedis = getResource();
        try {
            return jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public Set<byte[]> keys(String prefix) {
        Jedis jedis = getResource();
        try {
            return jedis.keys((prefix + "*").getBytes());
        } finally {
            jedis.close();
        }
    }
}
