package com.latou.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.latou.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbUser extends BaseEntity {
    @Length(min=6,max = 20 ,message = "姓名长度必须介于6-20位之间")
    private String username;

    @JsonIgnore
    private String password;
    private String phone;
    private String email;




}

