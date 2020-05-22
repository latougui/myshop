package com.latou.my.shop.web.admin.dao;

import com.latou.my.shop.domain.TbContent;
import com.latou.my.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentCategoryDao {

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
    void insert(TbContentCategory tbContentCategory);

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
