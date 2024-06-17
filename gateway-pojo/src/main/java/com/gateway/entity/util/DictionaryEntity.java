package com.gateway.entity.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryEntity {

    private String id;

    private String name;

    private String code;

    private String description;

    private String sortOrder;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
