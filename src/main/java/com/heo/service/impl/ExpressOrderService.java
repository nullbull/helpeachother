package com.heo.service.impl;

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

    @Override
    public ReturnData createExpressOrder(ExpressOrder expressOrder) {
        return null;
    }

    @Override
    public ReturnData modifyExpressOrder(ExpressOrder expressOrder) {
        return null;
    }

    @Override
    public ReturnData deleteExpressOrder(Long id) {
        return null;
    }
}
