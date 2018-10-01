package com.heo.controller;

import com.alibaba.fastjson.JSON;
import com.heo.common.constant.Constants;
import com.heo.common.utils.RedisUtil;
import com.heo.entity.dto.ExpressQueryDTO;
import com.heo.entity.vo.ReturnData;
import com.heo.service.IExpressOrderService;
import com.heo.service.IExpressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;

/**
 * @Auth justinniu
 * @Date 2018/9/28
 * @Desc
 */
@Controller
public class ProviderController {
    @Autowired
    private IExpressService expressService;

    @Autowired
    private IExpressOrderService expressOrderService;

    @Autowired
    private RedisUtil redisUtil;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/expressList")
    public String showExpress() {
        return "/provider/list";
    }
    @PostMapping("/expressList")
    @ResponseBody
    public ReturnData getExpressList(@NotNull String params) {
        String methodDesc = "查询Express列表接口";
        ReturnData rd = getReturnData();
        logger.info(methodDesc + "开始 >>>>>>>>>>>>>>>>>>  params:{}", params);
        try {
            ExpressQueryDTO expressQureyDTO = JSON.parseObject(params, ExpressQueryDTO.class);
            rd = expressService.getExpressList(expressQureyDTO);
        } catch (Exception e) {
            logger.error("");
        }
        return rd;
    }
    @GetMapping("/expressOrder/{id}")
    @ResponseBody
    public ReturnData createExpressOrder(@PathVariable("id") Long id) {
        String methodDesc = "创建ExpressOrder接口";
        ReturnData rd = new ReturnData();
        try {
            logger.info(methodDesc + "开始 >>>>>>>>>>>>>>>>>>>> id:{}", id);
            rd = expressOrderService.createExpressOrder(id);
        } catch (Exception e) {
            logger.error(methodDesc + "失败，未知系统异常 e:{}", e.getMessage());
        }
        return rd;
    }
    @GetMapping("/test")
    @ResponseBody
    public String redisTest() {
        redisUtil.set("zwt", "zwt");
        return redisUtil.get("zwt").toString();
    }
    private ReturnData getReturnData() {
        ReturnData rd = new ReturnData();
        rd.setCode(Constants.FAIL_CODE);
        return rd;
    }


}
