package com.heo.service.impl;

import com.heo.common.constant.Constants;
import com.heo.entity.mapper.Express;
import com.heo.entity.mapper.ExpressOrder;
import com.heo.entity.mapper.LocationInfo;
import com.heo.entity.vo.ExpressOrderVO;
import com.heo.entity.vo.ReturnData;
import com.heo.service.BaseService;
import com.heo.service.IExpressOrderService;
import com.heo.service.IExpressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auth justinniu
 * @Date 2018/9/21
 * @Desc
 */
public class ExpressOrderService extends BaseService implements IExpressOrderService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 创建快递代送单
     * @param expressOrder
     * @return
     */
    @Override
    public ReturnData createExpressOrder(ExpressOrder expressOrder) {
        ReturnData rd = getReturnData();
        String methodDesc = "创建快递代送单";
        try {
            if (null == expressOrder.getNeederId() || null == expressOrder.getProviderId() || null == expressOrder.getPrice()) {
                rd.setMsg("关键信息不能为空");
                logger.info(methodDesc + "失败 + expressOrder : {}", expressOrder);
                return rd;
            }
            //todo 不想让某人接单，用户可以设置黑名单
            expressOrderMapper.insertSelective(expressOrder);
            rd.setMsg("完成");
            rd.setCode(Constants.SUCCESS_CODE);
            logger.info(methodDesc + "完成");
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
            expressOrderMapper.updateByPrimaryKeySelective(expressOrder);
            rd.setMsg("完成");
            rd.setCode(Constants.SUCCESS_CODE);
            logger.info(methodDesc + "完成， expressOrder：{}", expressOrder);
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
