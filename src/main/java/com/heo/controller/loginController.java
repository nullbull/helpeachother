package com.heo.controller;

import com.heo.common.constant.Constants;
import com.heo.common.utils.security.ShiroUtils;
import com.heo.entity.vo.ReturnData;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auth justinniu
 * @Date 2018/9/12
 * @Desc
 */
@Controller
public class loginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/dologin")
    public String login(Model model) {

        model.addAttribute("hhh" , "123");
        return "login";
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

}
