package com.heo.controller;

import com.alibaba.fastjson.JSON;
import com.heo.common.constant.Constants;
import com.heo.entity.mapper.Express;
import com.heo.entity.vo.ReturnData;
import com.heo.service.IExpressOrderService;
import com.heo.service.IExpressService;
import com.heo.service.IKafkaService;
import com.heo.service.impl.EmailService;
import org.hibernate.validator.constraints.NotBlank;
import org.omg.CORBA.SystemException;
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
    @Autowired
    private IKafkaService kafkaService;
    @Autowired
    private IExpressOrderService expressOrderService;
    @Autowired
    EmailService emailService;

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
            logger.info(methodDesc  + "开始>>>>>>>>>>>>>>>>>>>>>> params:{}", params);
            Express express = JSON.parseObject(params, Express.class);
            rd = expressService.createExpress(express);

        } catch (Exception e) {
            rd.setMsg("未知系统异常");
            logger.error(methodDesc + "失败>>>>>>>>>>>>>>>>>>未知系统异常 e:{}", e);
        }
        return rd;
    }

    @GetMapping("/expressOrder/fin/{id}")
    @ResponseBody
    public ReturnData finshExpressOrder(@PathVariable("id") Long id) {
        String methodDesc = "完成ExpressOrder 接口";
        ReturnData rd = getReturnData();
//       kafkaService.sendMessage(id, "zwt");
// emailService.sendHtmlEmail("1129114837@qq.com", "justinniu@yeah.net", "test", "test", "test",false);
        logger.info(methodDesc + "开始>>>>>>>>>>>>>>>>>>>>>id:{}", id);
        rd = expressOrderService.finishExpressOrder(id);
       return rd;
    }


    private ReturnData getReturnData() {
        ReturnData rd = new ReturnData();
        rd.setCode(Constants.FAIL_CODE);
        return rd;
    }
}
