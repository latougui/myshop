package com.latou.my.shop.web.admin.service;

import com.latou.my.shop.commons.dto.BaseResult;
import com.latou.my.shop.domain.TbContent;
import com.latou.my.shop.domain.TbContentCategory;
import org.springframework.ui.Model;

import java.util.List;

public interface TbContentCategoryService {
    List<TbContentCategory> selectAll();
    /**
     * 根据父级节点ID查询所有子节点
     * @param pid
     * @return
     */
    List<TbContentCategory> selectByPid(Long pid);

    /**
     * 新增
     * @param tbContentCategory
     */
    BaseResult save(TbContentCategory tbContentCategory);

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
    TbContentCategory getById(Long id);

    /**
     * 更新
     * @param tbContentCategory
     */

    void update(TbContentCategory tbContentCategory);
}
