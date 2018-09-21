package com.heo.service;

import com.heo.entity.vo.ReturnData;

/**
 * @Auth justinniu
 * @Date 2018/9/21
 * @Desc
 */
public interface IExpressOrderService {
    ReturnData createExpressOrder();
    ReturnData modifyExpressOrder();
    ReturnData deleteExpressOrder();
}
