package com.latou.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.latou.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

@Data
public class TbContentCategory extends BaseEntity {


    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Boolean isParent;

}
