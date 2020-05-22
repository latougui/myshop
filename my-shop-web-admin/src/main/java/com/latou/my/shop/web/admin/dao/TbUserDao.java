package com.latou.my.shop.web.admin.dao;

import com.latou.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao{
    public List<TbUser> selectAll();

    /**
     * 新增
     * @param tbUser
     */
    void insert(TbUser tbUser);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    TbUser getById(Long id);

    void update(TbUser tbUser);

    /**
     * 根据用户名模糊查询
     * @param username
     * @return
     */
    List<TbUser> selectByUsername(String username);

    /**
     * 根据邮箱查询用户信息
     * @param eamil
     * @return
     */
    TbUser getByEmail(String eamil);



    /**
     * 批量删除
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param params start开始 /length 每页纪律数
     * @return
     */
    List<TbUser> page(Map<String,Object> params);

    /**
     * 记录总数
     * @return
     */
    int count1(TbUser tbUser);
}
