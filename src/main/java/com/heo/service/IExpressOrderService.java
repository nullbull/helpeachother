package com.heo.service;

import com.heo.entity.mapper.ExpressOrder;
import com.heo.entity.vo.ExpressOrderQueryVO;
import com.heo.entity.vo.ReturnData;

/**
 * @Auth justinniu
 * @Date 2018/9/21
 * @Desc
 */
public interface IExpressOrderService {
    ReturnData createExpressOrder(Long id);
    ReturnData getExpressOrderDetail(Long id);
    ReturnData deleteExpressOrder(Long id);
    ReturnData finishExpressOrder(Long id);
    ReturnData getExpressOrderListById(ExpressOrderQueryVO vo, int limit, int offset);
    ReturnData getByStatus(Long id, Byte status);
    ReturnData getByProviderAndNeederId(Long providerId, Long neederId);
}
