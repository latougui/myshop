package com.latou.my.shop.web.admin.web.controller;

import com.latou.my.shop.commons.dto.BaseResult;
import com.latou.my.shop.commons.dto.PageInfo;
import com.latou.my.shop.domain.TbUser;
import com.latou.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private TbUserService tbUserService;

    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser = null;

        //id不为空,则从数据库获取
        if(id != null){
            tbUser = tbUserService.getById(id);
        }
        else{
            tbUser = new TbUser();
        }
        return tbUser;
    }
    /**
     * 跳转到用户页面
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){

        return "user_list";
    }

    /**
     * 跳转用户表单页
     * @return
     */
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
//        TbUser tbUser = new TbUser();
//        tbUser.setEmail("asd@qq.com");
//        model.addAttribute("tbUser",tbUser);
        return "user_form";
    }

    /**
     * 保存用户信息
     * @param tbUser
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model,RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbUserService.save(tbUser);
        if (baseResult.getStatus()==200){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }
        //保存失败
        else {
          //  System.out.println("保存失败"+baseResult.getMessage());
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }
    }


    /**
     * 删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if(StringUtils.isNotBlank(ids)){
            baseResult = BaseResult.success("删除数据成功");
            String[] idArray = ids.split(",");
            tbUserService.deleteMulti(idArray);
        }
        else{
             baseResult = BaseResult.fail("删除失败失败");
        }

        System.out.println(ids);
        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest request,TbUser tbUser){
        Map<String,Object> result = new HashMap<>();
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart== null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 0 : Integer.parseInt(strLength);

        PageInfo<TbUser> pageInfo = tbUserService.page(start,length,draw,tbUser);



        return pageInfo;
    }

    /**
     *
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbUser tbUser){
        return "user_detail";
    }
}
