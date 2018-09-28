package com.heo.controller;

import com.heo.entity.dto.ExpressQureyDTO;
import com.heo.entity.mapper.Express;
import com.heo.entity.vo.ReturnData;
import com.heo.service.IExpressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auth justinniu
 * @Date 2018/9/28
 * @Desc
 */
@Controller
public class ProviderController {
    @Autowired
    private IExpressService expressService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/ExpressList/")
    public ReturnData getExpressList(ExpressQureyDTO expressQureyDTO) {

    }

}
