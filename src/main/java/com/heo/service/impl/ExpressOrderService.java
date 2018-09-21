package com.heo.service.impl;

import com.heo.common.constant.Constants;
import com.heo.entity.mapper.Express;
import com.heo.entity.mapper.ExpressOrder;
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

    @Override
    public ReturnData getExpressOrderDetail(Long id) {
        ReturnData rd = getReturnData();
        String methodDesc = "获取代送单详情";
        try {
            ExpressOrder expressOrder =  expressOrderMapper.selectByExpressId(id);

        } catch (Exception e) {
            rd.setMsg("未知错误");
            logger.error(methodDesc + "失败， 未知错误 e ：{}", e.getMessage());
        }
        return rd;
    }


    @Override
    public ReturnData deleteExpressOrder(Long id) {
        return null;
    }
}
