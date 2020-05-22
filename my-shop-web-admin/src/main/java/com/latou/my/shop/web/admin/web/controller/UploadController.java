package com.latou.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {
    /**
     * 文件上传
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropFile, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        System.out.println(dropFile.getOriginalFilename());
        //文件存放路径
        String filePath = request.getSession().getServletContext().getRealPath("/static/upload");
        File file = new File(filePath);
            if(!file.exists()){
                file.mkdir();
            }

        return result;
    }
}
