package com.heo.service.impl;

import com.github.pagehelper.PageHelper;
import com.heo.common.constant.Constants;
import com.heo.common.utils.security.ShiroUtils;
import com.heo.entity.mapper.Express;
import com.heo.entity.mapper.ExpressOrder;
import com.heo.entity.mapper.ExpressOrderExample;
import com.heo.entity.mapper.LocationInfo;
import com.heo.entity.vo.ExpressOrderQueryVO;
import com.heo.entity.vo.ExpressOrderVO;
import com.heo.entity.vo.ReturnData;
import com.heo.service.IExpressOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Auth justinniu
 * @Date 2018/9/21
 * @Desc
 */
@Service
public class ExpressOrderServiceImpl extends BaseService implements IExpressOrderService {
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
            ExpressOrder expressOrder = new ExpressOrder();
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
            //数据封装
            expressOrder.setExpressId(express.getId());
            expressOrder.setStatus(Constants.ORDER_NEW);
            expressOrder.setNeederId(express.getUserId());
            expressOrder.setProviderId(ShiroUtils.getUserId());
            expressOrder.setPrice(express.getPrice());
            expressOrder.setCreatedAt(new Date());

            //todo 不想让某人接单，用户可以设置黑名单
            expressOrderMapper.insertSelective(expressOrder);
            rd.setMsg("完成");
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setData(expressOrder);
            logger.info(methodDesc + "完成 expressOrder:{}", expressOrder);
        } catch (Exception e) {
            rd.setMsg("未知错误");
            logger.error(methodDesc + "失败， 未知错误 e ：{}", e.getMessage());
        }
        return rd;
    }

    /**
     * 获取代送单详情
     * @param id
     * @return
     */

    @Override
    public ReturnData getExpressOrderDetail(Long id) {
        ReturnData rd = getReturnData();
        String methodDesc = "获取代送单详情";
        ExpressOrderVO orderVO = new ExpressOrderVO();
        try {
            ExpressOrder expressOrder =  expressOrderMapper.selectByExpressId(id);
            if (expressOrder == null) {
                rd.setMsg("该订单不存在");
                logger.info(methodDesc + "订单不存在");
            }
            orderVO = expressOrderMapper.selectExpressOrderAndUserName(id);
            Express express = expressMapper.selectByPrimaryKey(orderVO.getExpressId());
            /*
                orderVO数据封装
             */
            orderVO.setExpressType(express.getExpressType());
            orderVO.setExpressName(Constants.EXPRESS_INFO.get(orderVO.getExpressType()));
            orderVO.setLocationId(express.getLocationId());
            LocationInfo locationInfo = locationInfoMapper.selectByPrimaryKey(express.getLocationId());
            orderVO.setLocationName( locationInfo == null ? "" : locationInfo.getName());
            orderVO.setGetCode(express.getGetCode());
            rd.setData(orderVO);
            rd.setMsg("完成");
            rd.setCode(Constants.SUCCESS_CODE);
            logger.info(methodDesc + "完成， rd：{}", rd);
        } catch (Exception e) {
            rd.setMsg("未知错误");
            logger.error(methodDesc + "失败， 未知错误 e ：{}", e.getMessage());
        }
        return rd;
    }



    @Override
    public ReturnData finishExpressOrder(Long id) {
        ReturnData rd = getReturnData();
        String methodDesc = "完成快递代送单";
        try {
            logger.info(methodDesc + "开始， id：{}", id);
            ExpressOrder expressOrder = expressOrderMapper.selectByPrimaryKey(id);
            if (null == expressOrder) {
                rd.setMsg("该快递代送单不存在");
                logger.info(methodDesc + "失败， 该快递代送单不存在 id：{}", id);
                return rd;
            }
            expressOrder.setStatus(Constants.ORDER_FINISH);
            Express express = expressMapper.selectByPrimaryKey(expressOrder.getExpressId());
            express.setStatus(Constants.ORDER_FINISH);
            expressOrderMapper.updateByPrimaryKeySelective(expressOrder);
            expressMapper.updateByPrimaryKeySelective(express);
            rd.setMsg("完成");
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setData(expressOrder);
            logger.info(methodDesc + "完成， expressOrder：{}", expressOrder);
        } catch (Exception e) {
            rd.setMsg("未知系统错误");
            logger.error(methodDesc + "失败, 未知系统错误, e:{}", e);
        }
        return rd;
    }

    @Override
    public ReturnData getExpressOrderListById(@NotNull ExpressOrderQueryVO vo, int limit, int offset) {
        ReturnData rd = getReturnData();
        String methodDesc = "根据Id获取快递代送单列表";
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
            ExpressOrderExample example = new ExpressOrderExample();
            if (type.equals(Constants.PROVIDER)) {
                example.createCriteria().andProviderIdEqualTo(id);
            } else if (type.equals(Constants.NEEDER)) {
                example.createCriteria().andNeederIdEqualTo(id);
            }
            if (null != status) {
                example.createCriteria().andStatusEqualTo(status);
            }
            if (beginTime != null) {
                example.createCriteria().andCreatedAtGreaterThanOrEqualTo(beginTime);
            }
            if (endTime != null) {
                example.createCriteria().andUpdatedAtLessThanOrEqualTo(endTime);
            }
            List<ExpressOrder> expressOrderList = expressOrderMapper.selectByExample(example);
            rd.setMsg("完成");
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setData(expressOrderList);
            logger.info(methodDesc + "完成 >>>>>>>>>>>>>>>> expressOrderList:{}", expressOrderList);
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
            ExpressOrder expressOrder = expressOrderMapper.selectExpressOrderByTwoIds(providerId, neederId);
            if (null == expressOrder) {
                rd.setMsg("该快递代送单不存在");
                logger.info(methodDesc + "失败，不再存在该单 providerId:{}, neederId:{} ", providerId, neederId);
            }
            rd.setMsg("成功");
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setData(expressOrder);
        } catch (Exception e) {
            rd.setMsg("未知系统错误");
            logger.error(methodDesc + "失败, 未知系统错误, e:{}", e);
        }
        return rd;
    }

    @Override
    public ReturnData deleteExpressOrder(Long id) {
        return null;
    }

}
