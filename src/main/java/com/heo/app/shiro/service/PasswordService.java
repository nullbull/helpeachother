package com.heo.app.shiro.service;


import com.heo.common.constant.Constants;
import com.heo.common.exception.user.UserPasswordNotMatchException;
import com.heo.common.exception.user.UserPasswordRetryLimitExceedException;
import com.heo.common.utils.MessageUtils;
import com.heo.common.utils.SystemLogUtils;
import com.heo.entity.mapper.User;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 登录密码方法
 * 
 * @author justinniu
 */
@Component
public class PasswordService
{

    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @PostConstruct
    public void init()
    {
        loginRecordCache = cacheManager.getCache("loginRecordCache");
    }

    public void validate(User user, String password)
    {
        String loginName = user.getUserName();

        AtomicInteger retryCount = loginRecordCache.get(loginName);

        if (retryCount == null)
        {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue())
        {
            SystemLogUtils.log(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount));
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }

        if (!matches(user, password))
        {
            SystemLogUtils.log(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", retryCount, password));
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        }
        else
        {
            clearLoginRecordCache(loginName);
        }
    }

    public boolean matches(User user, String newPassword)
    {
        return user.getPassWord().equals(encryptPassword(user.getUserName(), newPassword, user.getSalt().toString()));
    }

    public void clearLoginRecordCache(String username)
    {
        loginRecordCache.remove(username);
    }

    public String encryptPassword(String username, String password, String salt)
    {
        return new Md5Hash(username + password + salt).toHex().toString();
    }

}
