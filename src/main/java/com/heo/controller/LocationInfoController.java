package com.heo.controller;

import com.heo.common.constant.Constants;
import com.heo.entity.mapper.LocationInfo;
import com.heo.entity.vo.ReturnData;
import com.heo.service.ILocationInfoService;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.annotation.TypeAnnotation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auth justinniu
 * @Date 2018/9/27
 * @Desc
 */
@RestController
public class LocationInfoController {
    @Autowired
    ILocationInfoService locationInfoService;

    private final Logger logger = LoggerFactory.getLogger(LocationInfoController.class);

    @GetMapping("/location/{id}")
    public ReturnData getLocaiton(@NotNull @Min(1) @PathVariable("id") Byte id) {
        String methodDesc = "根据宿舍区获取该区域宿舍接口";
        ReturnData rd = getReturnData();
        logger.info(methodDesc + "开始   id:{}", id);
        try {
            List<LocationInfo> locationInfoList = new ArrayList<>(25);
            locationInfoList = locationInfoService.getLocationsByPart(id);
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setMsg("完成");
            rd.setData(locationInfoList);
            logger.info(methodDesc = "完成, locaitonInfolist :{}", locationInfoList);
        } catch (Exception e) {
            rd.setMsg("未知系统异常");
            logger.error(methodDesc + "未知系统异常， e:{}", e);
        }
        return rd;
    }
    private ReturnData getReturnData() {
        ReturnData rd = new ReturnData();
        rd.setCode(Constants.FAIL_CODE);
        return rd;
    }
}
