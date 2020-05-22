package com.latou.my.shop.web.admin.service;

import com.latou.my.shop.commons.dto.BaseResult;
import com.latou.my.shop.commons.dto.PageInfo;
import com.latou.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService {
    /**
     * 查询全部
     * @return
     */
    public List<TbUser> selectAll();

    /**
     * 保存用户信息
     * @param tbUser
     * @return
     */
     BaseResult save(TbUser tbUser);

    /**
     * 删除用户信息
     * @param id
     */
     void delete(Long id);

    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    TbUser getById(Long id);

    /**
     * 更新用户
     * @param tbUser
     */
     void update(TbUser tbUser);



    /**
     * 用户登录
     * @param email
     * @param passsword
     * @return
     */
     TbUser login(String email,String passsword);



    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页
     */
    PageInfo<TbUser> page(int start, int length,int draw,TbUser tbUser);

    /**
     * 查询总数
     * @return
     */
    int count(TbUser tbUser);
}
