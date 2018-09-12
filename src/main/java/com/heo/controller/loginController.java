package com.heo.controller;

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
        return "test";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() { return "hello";}

//    @PostMapping("/login")
//    @ResponseBody
//    public String ajaxLogin(String username, String password, Boolean rememberMe)
//    {
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
//        Subject subject = SecurityUtils.getSubject();
//        subject.login(token);
//        return username;
//
//
//    }

}
