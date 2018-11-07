package com.heo.service.impl;


import com.heo.app.shiro.service.PasswordService;
import com.heo.common.constant.Constants;
import com.heo.common.constant.UserConstants;
import com.heo.common.utils.DateUtils;
import com.heo.common.utils.RedisUtil;
import com.heo.dao.UserMapper;
import com.heo.entity.mapper.*;
import com.heo.entity.vo.ReturnData;
import com.heo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 用户 业务层处理
 * 
 * @author justinniu
 */
@Service("userService")
public class UserServiceImpl extends BaseService implements IUserService
{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private RedisUtil redisUtil;


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Random random = new Random(47);
    /**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * 
     * @return 用户信息集合信息
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByUserName(String userName)
    {
        UserExample example = new UserExample();
        UserExample.Criteria  criteria = example.createCriteria().andUserNameEqualTo(userName);
        List<User> temp = userMapper.selectByExample(example);
        if (temp.isEmpty())
            return null;
        return temp.get(0);
    }

    /**
     * 通过手机号码查询用户
     * 
     * @param phone 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByPhoneNumber(String phone)
    {
        UserExample example = new UserExample();
        UserExample.Criteria  criteria = example.createCriteria().andPhoneEqualTo(phone);
        List<User> temp = userMapper.selectByExample(example);
        if (temp.isEmpty())
            return null;
        return temp.get(0);
    }

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public User selectUserByEmail(String email)
    {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria().andEmailEqualTo(email);
        List<User> temp = userMapper.selectByExample(example);
        if (temp.isEmpty())
            return null;
        return temp.get(0);
    }

    @Override
    public User selectUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId)
    {

        return userMapper.deleteByPrimaryKey(userId);
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteUser(Long[] ids)
    {
        for (Long id : ids)
            userMapper.deleteByPrimaryKey(id);
        return 1;
    }

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 修改用户密码
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetUserPwd(User user)
    {
        user.setSalt(randomSalt());
        user.setPassWord(passwordService.encryptPassword(user.getUserName(), user.getPassWord(), user.getSalt().toString()));
        return updateUser(user);
    }


    /**
     * 校验用户名称是否唯一
     * 
     * @param userName 用户名
     * @return
     */
    public String checkLoginNameUnique(String userName)
    {

        if (selectUserByUserName(userName) != null)
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param phone 用户名
     * @return
     */
    public String checkPhoneUnique(String phone)
    {
        if (selectUserByPhoneNumber(phone) != null)
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param email 用户名
     * @return
     */
    public String checkEmailUnique(String email)
    {
        if (null != selectUserByEmail(email))
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    @Override
    public ReturnData registerUser(User user) {
        Long id = System.currentTimeMillis();
        String methodDesc = "用户注册";
        ReturnData rd = getReturnData();
        logger.info(methodDesc + "开始" + "user: {}", user);
        try {
            if (checkEmailUnique(user.getEmail()) == "1" ) {
                rd.setMsg("邮箱已注册");
                logger.info(methodDesc + "邮箱已注册");
                return rd;
            }
            if (checkLoginNameUnique(user.getUserName()) == "1") {
                rd.setMsg("用户名已注册");
                logger.info(methodDesc + "用户名已注册");
                return rd;
            }
            if (checkPhoneUnique(user.getPhone()) == "1") {
                rd.setMsg("手机号已注册");
                logger.info(methodDesc + "手机号已注册");
                return rd;
            }
            user.setId(id);
            user.setSalt(randomSalt());
            user.setPassWord(passwordService.encryptPassword(user.getUserName(), user.getPassWord(), user.getSalt().toString()));
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());
            /*
            ------------
             */
            user.setMajorId("1");
            //user.setLocationId((byte)17);


            userMapper.insertSelective(user);
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setMsg("完成");
            logger.info(methodDesc + "完成");
        } catch (Exception e) {
           logger.info(methodDesc + "失败, 未知系统异常 e:{}", e);
        }
        return rd;

    }

    /**
    根据ID得到日收入
     */
    @Override
    public BigDecimal getDailyIncome(Long id) {
        String key = id+"DAILY_INCOME";
        try
        {

            if(redisUtil.get(key)!=null)
            {
                BigDecimal bigDecimal = (BigDecimal) redisUtil.get(key);
                return bigDecimal;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andProviderIdEqualTo(id);
        criteria.andOrderStatusEqualTo((byte) 3);
        List<Express> expressOrders = expressMapper.selectByExample(example);
        BigDecimal income = new BigDecimal(0);
        for(int i = 0; i < expressOrders.size(); i++)
        {
            Express expressOrder = expressOrders.get(i);
            if(DateUtils.isToday(expressOrder.getUpdatedAt()))
            {
                income = income.add(expressOrder.getPrice());
            }
        }
        try
        {
            redisUtil.set(key, income);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return income;
    }

    private int randomSalt() {
          return random.nextInt(10000);
    }

}
