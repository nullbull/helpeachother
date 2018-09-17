package com.heo.controller;

import com.alibaba.fastjson.JSON;
import com.heo.common.constant.Constants;
import com.heo.common.utils.security.ShiroUtils;
import com.heo.entity.mapper.User;
import com.heo.entity.vo.ReturnData;
import com.heo.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auth justinniu
 * @Date 2018/9/12
 * @Desc
 */
@Controller
public class loginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IUserService userService;
    @RequestMapping("/dologin")
    public String login(Model model) {
        model.addAttribute("hhh" , "123");
        return "login";
    }
    @RequestMapping(value = "/register")
    public String register(Model model) {
        return "register";
    }
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("hhh" , "123");
        return "test";
    }

    @ResponseBody
    @PostMapping("/login")
    public ReturnData ajaxLogin(String userName, String passWord, Boolean rememberMe)
    {
        ReturnData rd = new ReturnData();
        rd.setCode(Constants.FAIL_CODE);
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        if (ShiroUtils.getUser() != null) {
            rd.setCode(Constants.SUCCESS_CODE);
            rd.setMsg("登陆成功");
        }else {
            rd.setMsg("密码不正确");
        }
        return rd;
    }
    @ResponseBody
    @PostMapping("/doRegister")
    public ReturnData doRegister(String params) {
        ReturnData rd = getReturnData();
        String methodDisc = "用户注册";
        logger.info(methodDisc + "开始 >>>>>>>>>>>>>>>>>>> params:{}", params);
        if (StringUtils.isEmpty(params) || null == params) {
            logger.info(methodDisc + "失败，参数为空");
            rd.setMsg("注册失败");
        }
        User user = JSON.parseObject(params, User.class);
        rd = userService.registerUser(user);
        return rd;
    }

    private ReturnData getReturnData() {
        ReturnData rd = new ReturnData();
        rd.setCode(Constants.FAIL_CODE);
        return rd;
    }

}
