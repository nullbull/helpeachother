package com.heo.service;

import com.heo.entity.dto.ExpressQueryDTO;
import com.heo.entity.mapper.Express;
import com.heo.entity.vo.ReturnData;

/**
 * @Auth justinniu
 * @Date 2018/9/16
 * @Desc
 */
public interface IExpressService {
    ReturnData createExpress(Express exress);
    ReturnData getExpressList(ExpressQueryDTO expressQureyDTO);
    ReturnData modifyExpress(Express express);
    ReturnData deleteExpress(Long id);
}
