package com.latou.my.shop.web.admin.service.impl;

import com.latou.my.shop.commons.dto.BaseResult;
import com.latou.my.shop.domain.TbContent;
import com.latou.my.shop.domain.TbContentCategory;
import com.latou.my.shop.web.admin.dao.TbContentCategoryDao;
import com.latou.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Autowired
    private TbContentCategoryDao tbContentCategoryDao;


    @Override
    public List<TbContentCategory> selectAll() {
        return tbContentCategoryDao.selectAll();
    }

    @Override
    public List<TbContentCategory> selectByPid(Long pid) {
        return tbContentCategoryDao.selectByPid(pid);
    }

    @Override
    public BaseResult save(TbContentCategory tbContentCategory) {
        BaseResult baseResult = checkTbContentCategory(tbContentCategory);

        //通过验证
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            tbContentCategory.setUpdated(new Date());
            //新增
            if(tbContentCategory.getParentId() == null){
                tbContentCategory.setCreated(new Date());
                tbContentCategoryDao.insert(tbContentCategory);
            }
            //编辑用户
            else{
                tbContentCategoryDao.update(tbContentCategory);
            }
            baseResult.setMessage("保存内容信息成功");
        }

        return baseResult;
    }

    @Override
    public void delete(Long id) {
        tbContentCategoryDao.delete(id);
    }

    @Override
    public TbContentCategory getById(Long id) {
        return tbContentCategoryDao.getById(id);
    }

    @Override
    public void update(TbContentCategory tbContentCategory) {
        tbContentCategoryDao.update(tbContentCategory);
    }

    private BaseResult checkTbContentCategory(TbContentCategory tbContentCategory){
        BaseResult baseResult = BaseResult.success();
        //非空验证

        if(tbContentCategory.getParentId()==null){
            baseResult = BaseResult.fail("内容的所属分类不能为空,请重新输入");
        }

        return baseResult;
    }
}
