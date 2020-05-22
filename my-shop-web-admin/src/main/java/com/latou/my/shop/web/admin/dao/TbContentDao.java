package com.latou.my.shop.web.admin.dao;

import com.latou.my.shop.domain.TbContent;
import com.latou.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbContentDao {

    public List<TbContent> selectAll();

    /**
     * 新增
     * @param tbContent
     */
    void insert(TbContent tbContent);

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
    TbContent getById(Long id);

    /**
     * 更新
     * @param tbContent
     */

    void update(TbContent tbContent);



    /**
     * 批量删除
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param params start开始 /length 每页纪律数
     * @return
     */
    List<TbContent> page(Map<String,Object> params);

    /**
     * 记录总数
     * @return
     */
    int count1(TbContent tbContent);
}
