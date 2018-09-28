package com.heo.service;

import com.heo.entity.mapper.ExpressInfo;
import com.heo.entity.mapper.LocationInfo;
import com.heo.entity.vo.ReturnData;

import java.util.List;

/**
 * @Auth justinniu
 * @Date 2018/9/28
 * @Desc
 */
public interface IBaseService {
     ReturnData getReturnData();
     List<ExpressInfo> getExpressInfo() ;
     List<LocationInfo> getLocationByPart(Byte part);
 }
