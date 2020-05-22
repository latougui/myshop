package com.latou.my.shop.web.admin.service.impl;

import com.latou.my.shop.commons.dto.BaseResult;
import com.latou.my.shop.commons.dto.PageInfo;
import com.latou.my.shop.commons.utils.RegexpUtils;
import com.latou.my.shop.commons.validator.BeanValidator;
import com.latou.my.shop.domain.TbUser;
import com.latou.my.shop.web.admin.dao.TbUserDao;
import com.latou.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;


    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);

            //不通过
        if (validator != null){
            return BaseResult.fail(validator);
        }
        //通过
        else {
            tbUser.setUpdated(new Date());
            //新增用户
            if(tbUser.getId() == null){
                //密码加密
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUserDao.insert(tbUser);
            }
            //编辑用户
            else{
                tbUserDao.update(tbUser);
            }
            return BaseResult.success("保存用户信息成功");
        }


    }

    @Override
    public void delete(Long id) {
        tbUserDao.delete(id);
    }

    @Override
    public TbUser getById(Long id) {
       return  tbUserDao.getById(id);
    }

    @Override
    public void update(TbUser tbUser) {
        tbUserDao.update(tbUser);
    }



    @Override
    public TbUser login(String email, String passsword) {
        TbUser tbUser = tbUserDao.getByEmail(email);
//        if(tbUser !=null) {
//            String md5Password = DigestUtils.md5DigestAsHex(passsword.getBytes());
//            if(md5Password.equals(tbUser.getPassword())){
//                return tbUser;
//            }
//        }
        return tbUser;
    }



    @Override
    public void deleteMulti(String[] ids) {
         tbUserDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbUser> page(int start, int length,int draw,TbUser tbUser) {
        int count = tbUserDao.count1(tbUser);

        Map<String,Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("tbUser",tbUser);
        PageInfo<TbUser> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbUserDao.page(params));


        return pageInfo;
    }

    @Override
    public int count(TbUser tbUser) {
        return tbUserDao.count1(tbUser);
    }


//    private BaseResult checkTbUser(TbUser tbUser){
//        BaseResult baseResult = BaseResult.success();
//        //非空验证
//        System.out.println(tbUser.getEmail());
//        if(StringUtils.isBlank(tbUser.getEmail())){
//            baseResult = BaseResult.fail("邮箱不能为空,请重新输入");
//        }
//        else if (!RegexpUtils.checkEmail(tbUser.getEmail())){
//            baseResult = BaseResult.fail("邮箱格式不正确,请重新输入");
//        }
//        else if(StringUtils.isBlank(tbUser.getPassword())){
//            baseResult = BaseResult.fail("密码不能为空,请重新输入");
//        }
//        else if(StringUtils.isBlank(tbUser.getUsername())){
//            baseResult = BaseResult.fail("姓名不能为空,请重新输入");
//        }
//        else if(StringUtils.isBlank(tbUser.getPhone())){
//            baseResult = BaseResult.fail("手机不能为空,请重新输入");
//        }
//        else if (!RegexpUtils.checkPhone(tbUser.getPhone())){
//            baseResult = BaseResult.fail("手机格式不正确,请重新输入");
//        }
//        return baseResult;
//    }
}
