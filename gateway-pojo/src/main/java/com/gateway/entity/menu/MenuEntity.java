package com.gateway.entity.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity {

    private String id;

    private String path;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String component;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String redirect;

    private String name;

    private MetaEntity meta;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuEntity> children;

    private int status;
}
