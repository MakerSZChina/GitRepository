package com.example.demo.common;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis操作类
 */
@Component
public class RedisUtils {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private ValueOperations<String,String> valueOperations;
    @Autowired
    private HashOperations<String,String,Object> hashOperations;
    @Autowired
    private ListOperations<String,Object> listOperations;
    @Autowired
    private SetOperations<String,Object> setOperations;
    @Autowired
    private ZSetOperations<String,Object> zSetOperations;

    /**  默认过期时长，单位：秒 */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**  不设置过期时长 */
    public final static long NOT_EXPIRE = -1;
    private final static Gson gson = new Gson();

    /**
     * 插入Map
     * @param key
     * @param hkey
     * @param object
     * @param expire
     */
    public void setMap(String key,String hkey,Object object,long expire){
        hashOperations.put(key,hkey,object);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key,expire,TimeUnit.SECONDS);
        }
    }

    public void setMap(String key,String hkey,Object object){
        setMap(key,hkey,object,NOT_EXPIRE);
    }

    /**
     * 查询Map
     * @param key
     * @param expire
     * @return
     */
    public Map<String,Object> getMap(String key,long expire){
        Map<String,Object> map = hashOperations.entries(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key,expire,TimeUnit.SECONDS);
        }
        return map;
    }

    public Map<String,Object> getMap(String key){
       return getMap(key,NOT_EXPIRE);
    }

    /**
     * 插入List
     * @param key
     * @param object
     * @param expire
     */
    public void setList(String key,Object object,long expire){
        listOperations.leftPush(key,object);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key,expire,TimeUnit.SECONDS);
        }
    }

    public void setList(String key,Object object){
        setString(key,object,NOT_EXPIRE);
    }

    /**
     * 查询返回List
     * @param key
     * @param expire
     * @return
     */
    public List<Object> getList(String key,long expire){
        List<Object> list = listOperations.range(key,0,-1);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key,expire,TimeUnit.SECONDS);
        }
        return list;
    }

    public List<Object> getList(String key){
        return getList(key,NOT_EXPIRE);
    }

    /**
     * 插入String
     * @param key
     * @param object
     * @param expire
     */
    public void setString(String key,Object object,long expire){
        valueOperations.set(key,toJson(object));
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key,expire,TimeUnit.SECONDS);
        }
    }

    public void setString(String key,Object object){
        setString(key,object,NOT_EXPIRE);
    }

    /**
     * 查询,带有效期
     * @param key
     * @param expire
     * @return
     */
    public String getString(String key,long expire){
        String value = valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String getString(String key){
        return getString(key,NOT_EXPIRE);
    }

    /**
     * 删除
     * @param key
     */
    public void delete(String key){
        redisTemplate.delete(key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object){
        if(object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String){
            return String.valueOf(object);
        }
        return gson.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz){
        return gson.fromJson(json, clazz);
    }
}
