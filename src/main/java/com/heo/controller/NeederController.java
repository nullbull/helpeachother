package com.heo.controller;

import com.alibaba.fastjson.JSON;
import com.heo.common.constant.Constants;
import com.heo.entity.mapper.Express;
import com.heo.entity.vo.ReturnData;
import com.heo.service.IExpressService;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Auth justinniu
 * @Date 2018/9/28
 * @Desc
 */
@Controller
public class NeederController {

    @Autowired
    private IExpressService expressService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/express")
    public String getExpress() {
        return "needer/publish";
    }
    @PostMapping("/express")
    @ResponseBody
    public ReturnData createExpress(@NotNull @NotBlank String params) {
        String methodDesc = "创建Express接口";
        ReturnData rd = getReturnData();
        try {
            Express express = JSON.parseObject(params, Express.class);
            rd = expressService.createExpress(express);

        } catch (Exception e) {

        }
        return rd;
    }

    private ReturnData getReturnData() {
        ReturnData rd = new ReturnData();
        rd.setCode(Constants.FAIL_CODE);
        return rd;
    }
}
