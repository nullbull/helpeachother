package com.heo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.heo.common.constant.Constants;
import com.heo.common.redis.ERedisKey;
import com.heo.common.utils.RedisUtil;
import com.heo.common.utils.security.ShiroUtils;
import com.heo.dao.UserMapper;
import com.heo.entity.dto.ExpressMessageDTO;
import com.heo.entity.dto.ExpressOrderEmailDTO;
import com.heo.entity.dto.ExpressOrderNameDTO;
import com.heo.entity.dto.IncomeStatisEmailDTO;
import com.heo.entity.mapper.*;
import com.heo.entity.vo.ExpressOrderQueryVO;
import com.heo.entity.vo.ExpressOrderVO;
import com.heo.entity.vo.ReturnData;
import com.heo.service.IExpressOrderService;
import org.apache.shiro.crypto.hash.Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Auth justinniu
 * @Date 2018/9/21
 * @Desc
 */
@Service
public class ExpressOrderServiceImpl extends BaseService implements IExpressOrderService {
    private String REDIS_METHOD_LOCK =  "lock:createOrder";
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 创建快递代送单
     * @param   id
     * @return
     */
    @Override
    public ReturnData createExpressOrder(@NotNull Long id) {
        ReturnData rd = getReturnData();
        String methodDesc = "创建快递代送单";
        try {
            logger.info(methodDesc + "开始>>>>>>>>>>>>>>>>>>>>>>>>id:{}", id);
            Express express = expressMapper.selectByPrimaryKey(id);
            if (null == express) {
                rd.setMsg("代送信息不存在");
                logger.info(methodDesc + "失败 >>>>>>>>>>>>>>>>>>>>>>>> id:{} 不存在", id);
                return rd;
            }
            if (null == ShiroUtils.getUser()) {
                rd.setMsg("未登录");
                logger.info(methodDesc + "失败 >>>>>>>>>>>>>>>>>>>>>>>>>> 用户未登录", id);
                return rd;
            }
            String key = ERedisKey.EXPRESS_ORDER + id.toString();
            if (redisUtil.lock(getJedis(), REDIS_METHOD_LOCK, id.toString())) {
                if (null != redisUtil.get(key)) {
                    redisUtil.del(ERedisKey.EXPRESS_ORDER, id.toString());
                    /**
                     * 数据封装
                     */
                    ExpressMessageDTO expressMessageDTO = new ExpressMessageDTO();
                    expressMessageDTO.setCreatedAt(new Date());
                    expressMessageDTO.setId(id);
                    expressMessageDTO.setProviderId(ShiroUtils.getUserId());

                    /**
                     * 发送到RabbitMq
                     */
                    amqpTemplate.convertAndSend(EXCHANGE_NAME, QUEUE_A, JSON.toJSONString(expressMessageDTO));

                    /**
                     * 释放锁
                     */
                    redisUtil.releaseLock(jedisPool.getResource(), REDIS_METHOD_LOCK, id.toString());
                    rd.setMsg("预订中,请等候");
                    rd.setCode(Constants.SUCCESS_CODE);
                    logger.info(methodDesc + "交给RabbitMQ处理>>>>>>>>>>>id", id);
                }
                rd.setMsg("该订单已被下单,请尝试其他");
                logger.info(methodDesc + "失败 >>>>>>>>>>> {}已被下单", id);
                return rd;
            } else {
                /**
                 * 重新尝试获取锁
                 */
                if (redisUtil.tryLock(getJedis(), REDIS_METHOD_LOCK, id.toString())) {
                    if (null != redisUtil.get(key)) {
                        redisUtil.del(ERedisKey.EXPRESS_ORDER, id.toString());
                        amqpTemplate.convertAndSend(EXCHANGE_NAME, QUEUE_A, id.toString());
                    }
                    rd.setMsg("该订单已被下单,请尝试其他");
                    logger.info(methodDesc + "失败 >>>>>>>>>>> {}已被下单", id);
                    return rd;
                }
                rd.setMsg("服务器繁忙,请重试");
                logger.info(methodDesc + "多次尝试获取锁失败>>>>>>>>>>>>>>>>id{}", id);
            }
        } catch (Exception e) {
            rd.setMsg("未知错误");
            logger.error(methodDesc + "失败， 未知错误 e ：{}", e);
        }
        return rd;
    }

    @Override
    public ReturnData doCreateExpressOrder(ExpressMessageDTO dto) {
        ReturnData rd = getReturnData();
        String methodDesc = "真实创建ExpressOrder接口";
        try {
            logger.info(methodDesc + "开始>>>>>>>>>>>>ExpressMessageDTO:{}", dto);
            Express express = expressMapper.selectByPrimaryKey(dto.getId());
            //数据封装
            express.setStartAt(dto.getCreatedAt());
            express.setProviderId(dto.getProviderId());
            express.setOrderStatus(Constants.ORDER_NEW);
            //todo 不想让某人接单，用户可以设置黑名单
            expressMapper.insertSelective(express);
            rd.setMsg("完成");
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setData(express);
            logger.info(methodDesc + "完成 >>>>>>>>>>>>> express:{}", express);
        } catch (Exception e) {
            rd.setMsg("未知错误");
            logger.error(methodDesc + "失败， 未知错误 e ：{}", e);
            throw new RuntimeException(e.getMessage());
        }
        return rd;
    }

    /**
     * 获取代送单详情
     * @param id
     * @return
     */

//    @Override
//    public ReturnData getExpressOrderDetail(Long id) {
//        ReturnData rd = getReturnData();
//        String methodDesc = "获取代送单详情";
//        ExpressOrderVO orderVO = new ExpressOrderVO();
//        try {
//            ExpressOrder expressOrder =  expressOrderMapper.selectByExpressId(id);
//            if (expressOrder == null) {
//                rd.setMsg("该订单不存在");
//                logger.info(methodDesc + "订单不存在");
//            }
//            orderVO = expressOrderMapper.selectExpressOrderAndUserName(id);
//            Express express = expressMapper.selectByPrimaryKey(orderVO.getExpressId());
//            /*
//                orderVO数据封装
//             */
//            orderVO.setExpressType(express.getExpressType());
//            orderVO.setExpressName(Constants.EXPRESS_INFO.get(orderVO.getExpressType()));
//            orderVO.setLocationId(express.getLocationId());
//            LocationInfo locationInfo = locationInfoMapper.selectByPrimaryKey(express.getLocationId());
//            orderVO.setLocationName( locationInfo == null ? "" : locationInfo.getName());
//            orderVO.setGetCode(express.getGetCode());
//            rd.setData(orderVO);
//            rd.setMsg("完成");
//            rd.setCode(Constants.SUCCESS_CODE);
//            logger.info(methodDesc + "完成， rd：{}", rd);
//        } catch (Exception e) {
//            rd.setMsg("未知错误");
//            logger.error(methodDesc + "失败， 未知错误 e ：{}", e);
//        }
//        return rd;
//    }



    @Override
    public ReturnData finishExpressOrder(Long id) {
        ReturnData rd = getReturnData();
        String methodDesc = "完成快递代送单";
        Express express = null;
        try {
            logger.info(methodDesc + "开始， id：{}", id);
            express = expressMapper.selectByPrimaryKey(id);
            if (null == express) {
                rd.setMsg("该快递代送单不存在");
                logger.info(methodDesc + "失败， 该快递代送单不存在 id：{}", id);
                return rd;
            }
            express.setOrderStatus(Constants.ORDER_FINISH);
            express.setExpressStatus(Constants.ORDER_FINISH);
            expressMapper.updateByPrimaryKeySelective(express);
            ExpressOrderEmailDTO orderDTO = new ExpressOrderEmailDTO();
            orderDTO.setExpressOrderId(id);
            orderDTO.setProviderName(userMapper.selectByPrimaryKey(express.getProviderId()).getUserName());
            orderDTO.setFinishTime(new Date());
            orderDTO.setPrice(express.getPrice());
            kafkaService.sendMessage(id, JSON.toJSONString(orderDTO));
            rd.setMsg("完成");
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setData(express);
            logger.info(methodDesc + "完成， expressOrder：{}", express);
        } catch (Exception e) {
            rd.setMsg("未知系统错误");
            logger.error(methodDesc + "失败, 未知系统错误, e:{}", e);
        }

        /**
         * 更新缓存中的income
         */
        String key = express.getProviderId()+"DAILY_INCOME";
        try
        {
            if(redisUtil.get(key)!=null)
            {
                BigDecimal bigDecimal = (BigDecimal) redisUtil.get(key);
                bigDecimal.add(express.getPrice());
                redisUtil.set(key, bigDecimal);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rd;
    }

    @Override
    public ReturnData getExpressOrderListById(@NotNull ExpressOrderQueryVO vo, int limit, int offset) {
        ReturnData rd = getReturnData();
        String methodDesc = "根据用户Id获取快递代送单列表";
        try {
            logger.info(methodDesc + "开始 + vo：{} limit:{} offset:{} type:{}", vo, limit, offset);
            PageHelper.startPage(limit, offset);
            Long id = vo.getId();
            Byte type = vo.getType();
            Byte status = vo.getStatus();
            Date beginTime = vo.getBeginTime();
            Date endTime = vo.getEndTime();
            if (type == null || !type.equals(Constants.PROVIDER) || !type.equals(Constants.NEEDER)) {
                logger.info("不存在该用户类型， type:{}", type);
                rd.setMsg("不存在该用户类型");
                return rd;
            }
            ExpressExample example = new ExpressExample();
            if (type.equals(Constants.PROVIDER)) {
                example.createCriteria().andProviderIdEqualTo(id);
            } else if (type.equals(Constants.NEEDER)) {
                example.createCriteria().andUserIdEqualTo(id);
            }
            if (null != status) {
                example.createCriteria().andOrderStatusEqualTo(status);
            }
            if (beginTime != null) {
                example.createCriteria().andCreatedAtGreaterThanOrEqualTo(beginTime);
            }
            if (endTime != null) {
                example.createCriteria().andUpdatedAtLessThanOrEqualTo(endTime);
            }
            List<Express> expressList = expressMapper.selectByExample(example);
            rd.setMsg("完成");
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setData(expressList);
            logger.info(methodDesc + "完成 >>>>>>>>>>>>>>>> expressOrderList:{}", expressList);
            return rd;
        }catch (Exception e) {
            rd.setMsg("未知系统错误");
            logger.error(methodDesc + "失败, 未知系统错误, e:{}", e);
        }

        return rd;
    }

    @Override
    public ReturnData getByStatus(Long id, Byte status) {
        return null;
    }

//    @Override
//    public ReturnData getByStatus(Long id, Byte status) {
//        ReturnData rd = getReturnData();
//        String methodDesc = "根据状态获取"
//    }

    @Override
    public ReturnData getByProviderAndNeederId(@NotNull @Min(1) Long providerId, @NotNull @Min(1) Long neederId) {
        ReturnData rd = getReturnData();
        String methodDesc = "根据跑腿者和需求者id获取快递代送单";
        try {
            logger.info(methodDesc + " 开始 providerId:{}, neederId:{}", providerId, neederId);
            List<ExpressOrderNameDTO> express = expressMapper.selectExpressOrderByTwoIds(providerId, neederId);
            if (null == express) {
                rd.setMsg("该快递代送单不存在");
                logger.info(methodDesc + "失败，不再存在该单 providerId:{}, neederId:{} ", providerId, neederId);
            }
            rd.setMsg("成功");
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setData(express);
            logger.info(methodDesc + "完成>>>>>>>>>>>>express", express);
        } catch (Exception e) {
            rd.setMsg("未知系统错误");
            logger.error(methodDesc + "失败, 未知系统错误, e:{}", e);
        }
        return rd;
    }

    @Override
    public ReturnData deleteExpressOrder(Long id) {
        ReturnData rd = getReturnData();
        String methodDesc = "删除订单接口";
        try {
            logger.info(methodDesc + "开始>>>>>>>>>>>>>>>>>>>>>>>>>>id:{}", id);
            Express express;
            if (null == (express = expressMapper.selectByPrimaryKey(id))) {
                rd.setMsg("该订单不存在");
                logger.info(methodDesc + "失败>>>>>>>>>>>>>>>>>>>>>>id{}不存在", id);
                return rd;
            }
            express.setOrderStatus(Constants.ORDER_DELETE);
            expressMapper.updateByPrimaryKeySelective(express);
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setMsg("成功");
            logger.info(methodDesc + "完成");
        } catch (Exception e) {
            rd.setMsg("未知系统错误");
            logger.error(methodDesc + "失败, 未知系统错误, e:{}", e);
        }
        return rd;
    }

    /**
     * 根据用户ID获取时间段内收入v
     * @param providerId
     * @param hour
     * @return
     */
    public void statisIncome(Long providerId, int hour) {
        String methodDesc = "根据用户ID获取时间段内收入";
        logger.info(methodDesc + "开始>>>>>>>>>>>>>>>>>>>id:{}, hour:{}", providerId, hour);
        try {
            IncomeStatisEmailDTO income =  expressMapper.selectIncomeByProviderIdAndDate(providerId, getPastDateByHour(hour));
            amqpTemplate.convertAndSend(rabbitMqProperties.getEmailExchange(), rabbitMqProperties.getEmailQueue(), JSON.toJSON(income));
            logger.info(methodDesc + "发送MQ完成>>>>>>>>>>>>>>>>>>>>>>income:{}", income);
        } catch (Exception e) {
            logger.error(methodDesc + "失败, 未知系统异常", e);
        }
    }

}
