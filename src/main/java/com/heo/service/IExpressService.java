package com.heo.service;

import com.heo.entity.mapper.Express;
import com.heo.entity.mapper.ExpressOrder;
import com.heo.entity.vo.ReturnData;

/**
 * @Auth justinniu
 * @Date 2018/9/16
 * @Desc
 */
public interface IExpressService {
    ReturnData createExpress(Express exress);
    ReturnData modifyExpress(Express express);
    ReturnData deleteExpress(Long id);
}
