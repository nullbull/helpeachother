package com.heo.controller;

import com.alibaba.fastjson.JSON;
import com.heo.common.constant.Constants;
import com.heo.common.utils.security.ShiroUtils;
import com.heo.entity.mapper.User;
import com.heo.entity.vo.ReturnData;
import com.heo.service.ILocationInfoService;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auth justinniu
 * @Date 2018/9/12
 * @Desc
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IUserService userService;

    @Autowired
    ILocationInfoService locationInfoService;

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

    @RequestMapping("/register_ok")
    public String register_ok()
    {
        return "register_ok";
    }

    @ResponseBody
    @PostMapping("/login")
    public ReturnData ajaxLogin(String userName, String passWord, Boolean rememberMe)
    {
        System.out.println("-----------------------------");
        System.out.println(passWord);
        ReturnData rd = new ReturnData();
        rd.setCode(Constants.FAIL_CODE);
        String methodDesc = "用户登陆";
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord, rememberMe);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            User user = ShiroUtils.getUser();
            if ( user != null ) {
                rd.setCode(Constants.SUCCESS_CODE);
                /*rd.setMsg("登陆成功");*/
                rd.setMsg(String.valueOf(user.getType()));
            }else {
                rd.setMsg("密码不正确");
            }
        } catch (Exception e) {
            logger.error(methodDesc + "未知错误, e {}", e);
            rd.setMsg("未知错误");
        }
        return rd;
    }
    /*@ResponseBody
    @PostMapping("/doRegister")
    public ReturnData doRegister(String params) {
        System.out.println("----------------------------");
        ReturnData rd = getReturnData();
        String methodDesc = "用户注册";
        try{
            logger.info(methodDesc + "开始 >>>>>>>>>>>>>>>>>>> params:{}", params);
            if (StringUtils.isEmpty(params) || null == params) {
                logger.info(methodDesc + "失败，参数为空");
                rd.setMsg("注册失败");
            }
            User user = JSON.parseObject(params, User.class);
            rd = userService.registerUser(user);
        } catch (Exception e) {
            logger.error(methodDesc + "未知错误, e {}", e);
            rd.setMsg("未知错误");
        }
        return rd;
    }*/

    @ResponseBody
    @PostMapping("/doRegister")
    public ReturnData doRegister(@Valid User user, BindingResult result, Model model) {
        ReturnData rd = getReturnData();
        if(result.hasErrors())
        {
            List<ObjectError> list = result.getAllErrors();
            StringBuffer sb = new StringBuffer();
            for(ObjectError error : list)
            {
                sb.append(error.getDefaultMessage());
                System.out.println(error.getCode()+" "+error.getArguments()+" "+error.getDefaultMessage());
            }
            rd.setMsg(sb.toString());
            rd.setCode((byte)1);
            return rd;
        }
        userService.registerUser(user);
        return rd;
    }

    @ResponseBody
    @GetMapping("/locaionInfo")
    public ReturnData getLocationInfos(Byte id) {
        ReturnData rd = getReturnData();
        String methodDesc = "获取公寓信息接口";
        try {
            logger.info(methodDesc + "开始 >>>>>>>>>>>>>>>>> id:{}", id);
            if (id == null) {
                logger.info(methodDesc + "失败， 参数为空");
                rd.setMsg("获取失败");
                return rd;
            }
            rd.setData(locationInfoService.getLocationsByPart(id));
            logger.info(methodDesc + "完成， rd:{}", rd);
        } catch (Exception e) {
            logger.error(methodDesc + "未知错误, e {}", e);
            rd.setMsg("未知错误");
        }

        return rd;
    }

    private ReturnData getReturnData() {
        ReturnData rd = new ReturnData();
        rd.setCode(Constants.FAIL_CODE);
        return rd;
    }

    @GetMapping("/user")
    public String user()
    {
        return "needer/user";
    }

    @GetMapping("/userB")
    public String userB()
    {
        return "provider/userB";
    }

}
