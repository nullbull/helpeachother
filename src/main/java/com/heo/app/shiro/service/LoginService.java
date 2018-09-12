package com.heo.app.shiro.service;

import com.heo.common.constant.Constants;
import com.heo.common.constant.ShiroConstants;
import com.heo.common.constant.UserConstants;
import com.heo.common.exception.user.CaptchaException;
import com.heo.common.exception.user.UserNotExistsException;
import com.heo.common.exception.user.UserPasswordNotMatchException;
import com.heo.common.utils.DateUtils;
import com.heo.common.utils.MessageUtils;
import com.heo.common.utils.ServletUtils;
import com.heo.common.utils.SystemLogUtils;
import com.heo.common.utils.security.ShiroUtils;
import com.heo.entity.mapper.User;
import com.heo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 登录校验方法
 * 
 * @author ruoyi
 */
@Component
public class LoginService
{
    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IUserService userService;

    /**
     * 登录
     */
    public User login(String username, String password)
    {
        // 验证码校验
        if (!StringUtils.isEmpty(ServletUtils.getStrAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            SystemLogUtils.log(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error"));
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            SystemLogUtils.log(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null"));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            SystemLogUtils.log(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            SystemLogUtils.log(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        User user = userService.selectUserByLoginName(username);

        if (user == null && maybeMobilePhoneNumber(username))
        {
            user = userService.selectUserByPhoneNumber(username);
        }

        if (user == null && maybeEmail(username))
        {
            user = userService.selectUserByEmail(username);
        }

        if (user == null)
        {
            SystemLogUtils.log(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists"));
            throw new UserNotExistsException();
        }

        passwordService.validate(user, password);

        SystemLogUtils.log(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"));
        recordLoginInfo(user);
        return user;
    }

    private boolean maybeEmail(String username)
    {
        if (!username.matches(UserConstants.EMAIL_PATTERN))
        {
            return false;
        }
        return true;
    }

    private boolean maybeMobilePhoneNumber(String username)
    {
        if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN))
        {
            return false;
        }
        return true;
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(User user)
    {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLastLogin(DateUtils.getNowDate());
        userService.updateUser(user);
    }

}
