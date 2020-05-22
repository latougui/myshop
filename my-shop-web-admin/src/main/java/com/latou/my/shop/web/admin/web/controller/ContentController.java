package com.latou.my.shop.web.admin.web.controller;

import com.latou.my.shop.commons.dto.BaseResult;
import com.latou.my.shop.commons.dto.PageInfo;
import com.latou.my.shop.domain.TbContent;
import com.latou.my.shop.domain.TbUser;
import com.latou.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 内容管理
 */

@Controller
@RequestMapping(value = "content")
public class ContentController {
    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent = null;

        //id不为空,则从数据库获取
        if(id != null){
            tbContent = tbContentService.getById(id);
        }
        else{
            tbContent = new TbContent();
        }
        return tbContent;
    }
    /**
     * 跳转到内容页面
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){

        return "content_list";
    }

    /**
     * 跳转表单页
     * @return
     */
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
//        TbUser tbUser = new TbUser();
//        tbUser.setEmail("asd@qq.com");
//        model.addAttribute("tbUser",tbUser);
        return "content_form";
    }

    /**
     * 保存信息
     * @param tbContent
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbContentService.save(tbContent);
        if (baseResult.getStatus()==200){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }
        //保存失败
        else {
            //  System.out.println("保存失败"+baseResult.getMessage());
            model.addAttribute("baseResult",baseResult);
            return "content_form";
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
            String[] idArray = ids.split(",");
            tbContentService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除内容成功");
        }
        else{
            baseResult = BaseResult.fail("删除失败失败");
        }

        System.out.println(ids);
        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TbContent> page(HttpServletRequest request, TbContent tbContent){
        Map<String,Object> result = new HashMap<>();
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart== null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 0 : Integer.parseInt(strLength);

        PageInfo<TbContent> pageInfo = tbContentService.page(start,length,draw,tbContent);



        return pageInfo;
    }

    /**
     *显示详情
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbContent tbContent){
        return "content_detail";
    }

}
