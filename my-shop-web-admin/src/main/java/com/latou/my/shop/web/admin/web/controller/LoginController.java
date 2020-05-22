package com.latou.my.shop.web.admin.web.controller;

import com.latou.my.shop.commons.constant.ConstantUtils;
import com.latou.my.shop.domain.TbUser;
import com.latou.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = {"","login"}, method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required=true)String email, @RequestParam(required=true)String password, HttpServletRequest httpServletRequest, Model model){
        TbUser tbUser = tbUserService.login(email, password);
        System.out.println("lalala:"+tbUser);
        //登录失败
        if(tbUser ==null){
            model.addAttribute("message","用户名或密码错误,请重新输入");
            return login();
        }
        //登录成功
        else{
            //将登录信息放入会话
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER,tbUser);
            return "redirect:/main";
        }
        //        User user= userService.login(email, password);
//        //登录失败
//        if(user == null){
//            return login();
//        }
//        //登录成功 重定向
//        else{
////            //将登录信息放入会话
//            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER,user);
//            return "redirect:/main";
//        }
    }

    /**
     * 注销
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "loginout",method = RequestMethod.GET)
    public String loginout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return login();
    }
}