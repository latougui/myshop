package com.latou.my.shop.web.admin.service;

import com.latou.my.shop.commons.dto.BaseResult;
import com.latou.my.shop.commons.dto.PageInfo;
import com.latou.my.shop.domain.TbContent;

import java.util.List;
import java.util.Map;

public interface TbContentService {
    public List<TbContent> selectAll();

    /**
     * 新增
     * @param tbContent
     */
    BaseResult save(TbContent tbContent);

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
     *
     *
     */
    PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent);

    /**
     * 记录总数
     * @return
     */
    int count1(TbContent tbContent);
}
