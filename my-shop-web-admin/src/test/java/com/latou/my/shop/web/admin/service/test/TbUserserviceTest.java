package com.latou.my.shop.web.admin.service.test;

import com.latou.my.shop.domain.TbUser;
import com.latou.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbUserserviceTest {
    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser : tbUsers){
            System.out.println(tbUser.getUsername());
        }
    }
    @Test
    public void testInsert(){
        TbUser tbUser = new TbUser();
        tbUser.setEmail("admin@admin2.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setPhone("15888885588");
        tbUser.setUsername("Lusifer2");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserService.save(tbUser);
    }
    @Test
    public void testDelete(){
        tbUserService.delete(40L);
    }

    @Test
    public void testGetById(){
       TbUser tbUser = tbUserService.getById(37L);
    }
    @Test
    public void testUpdate(){
        TbUser tbUser = tbUserService.getById(37L);
        tbUser.setUsername("Lusifer3");
        tbUserService.update(tbUser);
    }


    @Test
    public void testMD5(){
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }
}
