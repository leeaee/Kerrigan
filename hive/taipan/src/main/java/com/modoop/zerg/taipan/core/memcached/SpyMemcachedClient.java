package com.modoop.zerg.taipan.core.memcached;

import com.modoop.zerg.taipan.core.mapper.JsonMapper;
import com.modoop.zerg.taipan.core.util.Strings;
import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 对SpyMemcached Client的二次封装,提供常用的Get/GetBulk/Set/Replace/Delete/Incr/Decr函数的同步与异步操作封装.
 * <p/>
 * 未提供封装的函数可直接调用getClient()取出Spy的原版MemcachedClient来使用.
 *
 * @author Genkyo Lee
 */
public class SpyMemcachedClient implements DisposableBean
{
    private static Logger logger = LoggerFactory.getLogger(SpyMemcachedClient.class);

    private MemcachedClient memcachedClient;

    private long shutdownTimeout = 2500;

    private long updateTimeout = 2500;

    private JsonMapper jsonMapper = JsonMapper.buildNormalMapper();

    /**
     * Get方法, 转换结果类型并屏蔽异常, 仅返回Null.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key)
    {
        try
        {
            if (!Strings.isEmpty(key))
            {
                logger.debug("get object for key {}", key);
                return (T) memcachedClient.get(key);
            }
            else
            {
                logger.warn("ignore getting for null key");
                return null;
            }

        }
        catch (RuntimeException e)
        {
            handleException(e, key);
            return null;
        }
    }

    /**
     * GetBulk方法, 转换结果类型并屏蔽异常.
     */
    @SuppressWarnings("unchecked")
    public <T> Map<String, T> getBulk(Collection<String> keys)
    {
        try
        {
            return (Map<String, T>) memcachedClient.getBulk(keys);
        }
        catch (RuntimeException e)
        {
            handleException(e, Strings.join(keys, ","));
            return null;
        }
    }

    /**
     * 异步Set方法, 不考虑执行结果.
     */
    public void set(String key, Object value, int expiration)
    {
        if (!Strings.isEmpty(key) && value != null)
        {
            logger.debug("set {} for {} seconds", jsonMapper.toJson(value), expiration);
            memcachedClient.set(key, expiration, value);
            logger.debug("set {} value for {} seconds", key, expiration);
        }
        else
        {
            logger.warn("ignore setting value for empty key or null value");
        }
    }

    /**
     * 安全的Set方法, 保证在updateTimeout秒内返回执行结果, 否则返回false并取消操作.
     */
    public boolean safeSet(String key, Object value, int expiration)
    {
        if (!Strings.isEmpty(key) && value != null)
        {
            Future<Boolean> future = memcachedClient.set(key, expiration, value);
            try
            {
                logger.debug("safe set {} value for {} seconds", key, expiration);
                return future.get(updateTimeout, TimeUnit.MILLISECONDS);
            }
            catch (Exception e)
            {
                future.cancel(false);
            }
        }
        else
        {
            logger.warn("ignore setting value for empty key or null value");
        }
        return false;
    }

    /**
     * 异步Set方法, 不考虑执行结果.
     */
    public void replace(String key, Object value, int expiration)
    {
        try
        {
            if (!Strings.isEmpty(key) && value != null)
            {
                if (memcachedClient.get(key) != null)
                {
                    memcachedClient.replace(key, expiration, value);
                    logger.debug("replace {} value for {} seconds", key, expiration);
                }
                else
                {
                    logger.debug("ignore replacing no cached value for key {}", key);
                }
            }
            else
            {
                logger.warn("ignore replacing value for empty key or null value");
            }
        }
        catch (Exception e)
        {
            handleException(e, key);
        }
    }

    /**
     * 异步 Delete方法, 不考虑执行结果.
     */
    public void delete(String key)
    {
        if (!Strings.isEmpty(key))
        {
            memcachedClient.delete(key);
            logger.debug("delete {} value", key);
        }
    }

    /**
     * 安全的Delete方法, 保证在updateTimeout秒内返回执行结果, 否则返回false并取消操作.
     */
    public boolean safeDelete(String key)
    {
        if (!Strings.isEmpty(key))
        {
            Future<Boolean> future = memcachedClient.delete(key);
            try
            {
                logger.debug("safe delete {} value", key);
                return future.get(updateTimeout, TimeUnit.MILLISECONDS);
            }
            catch (Exception e)
            {
                future.cancel(false);
            }
        }
        return false;
    }

    /**
     * Incr方法.
     */
    public long incr(String key, int by, long defaultValue)
    {
        return memcachedClient.incr(key, by, defaultValue);
    }

    /**
     * Decr方法.
     */
    public long decr(String key, int by, long defaultValue)
    {
        return memcachedClient.decr(key, by, defaultValue);
    }

    /**
     * 异步Incr方法, 不支持默认值, 若key不存在返回-1.
     */
    public Future<Long> asyncIncr(String key, int by)
    {
        return memcachedClient.asyncIncr(key, by);
    }

    /**
     * 异步Decr方法, 不支持默认值, 若key不存在返回-1.
     */
    public Future<Long> asyncDecr(String key, int by)
    {
        return memcachedClient.asyncDecr(key, by);
    }

    private void handleException(Exception e, String key)
    {
        logger.warn("spymemcached client receive an exception with key:" + key, e);
    }

    @Override
    public void destroy() throws Exception
    {
        if (memcachedClient != null)
        {
            memcachedClient.shutdown(shutdownTimeout, TimeUnit.MILLISECONDS);
        }
    }

    public MemcachedClient getMemcachedClient()
    {
        return memcachedClient;
    }

    public void setMemcachedClient(MemcachedClient memcachedClient)
    {
        this.memcachedClient = memcachedClient;
    }

    public void setUpdateTimeout(long updateTimeout)
    {
        this.updateTimeout = updateTimeout;
    }

    public void setShutdownTimeout(long shutdownTimeout)
    {
        this.shutdownTimeout = shutdownTimeout;
    }
}