package com.heo.service;

import com.heo.entity.mapper.ExpressOrder;
import com.heo.entity.vo.ReturnData;
import com.heo.service.impl.ExpressOrderService;

/**
 * @Auth justinniu
 * @Date 2018/9/21
 * @Desc
 */
public interface IExpressOrderService {
    ReturnData createExpressOrder(ExpressOrder expressOrder);
    ReturnData getExpressOrderDetail(Long id);
    ReturnData deleteExpressOrder(Long id);
}
