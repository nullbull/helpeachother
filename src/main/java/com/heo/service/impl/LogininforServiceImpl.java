package com.heo.service.impl;

import com.heo.dao.LoginInformationMapper;
import com.heo.entity.mapper.LoginInformation;
import com.heo.service.ILogininforService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 * @author ruoyi
 */
@Service("logininforService")
public class LogininforServiceImpl implements ILogininforService
{

    @Autowired
    private LoginInformationMapper logininforMapper;

    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(LoginInformation logininfor)
    {
        logininforMapper.insertSelective(logininfor);
    }

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<LoginInformation> selectLogininforList(LoginInformation logininfor)
    {
        return logininforMapper.selectLogininforList(logininfor);
    }

    /**
     * 批量删除系统登录日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int batchDeleteLogininfor(Long[] ids)
    {
        for (Long id : ids)
         logininforMapper.deleteByPrimaryKey(id.intValue());
        return 1;
    }
}
