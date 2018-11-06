package com.heo.app.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

/**
 * justinniu首创 js调用 thymeleaf 实现按钮权限可见性
 * 
 * @author justinniu
 */
@Component
public class PermissionService
{
    public String hasPermi(String permission)
    {
        return isPermittedOperator(permission) ? "" : "hidden";
    }

    private boolean isPermittedOperator(String permission)
    {
        if (SecurityUtils.getSubject().isPermitted(permission))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
