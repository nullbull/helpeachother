package com.heo.controller;

import com.heo.common.constant.Constants;
import com.heo.entity.vo.ReturnData;
import com.heo.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auth justinniu
 * @Date 2018/9/28
 * @Desc
 */
@RestController
public class ExpressInfoController {
    @Resource(name = "baseService")
    IBaseService iBaseService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/expressInfo")
    public ReturnData getExrpessInfo() {
        ReturnData rd = iBaseService.getReturnData();
        String methodDesc = "获取快递信息接口";
        logger.info(methodDesc + "开始");
        try {
            rd.setData(iBaseService.getExpressInfo());
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setMsg("成功");
            logger.info(methodDesc + "完成");
        } catch (Exception e) {
            rd.setMsg("位置系统异常");
            logger.error(methodDesc + "未知系统异常, e:{}", e);
        }
        return rd;
    }

}
