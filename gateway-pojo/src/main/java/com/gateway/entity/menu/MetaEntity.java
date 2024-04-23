package com.gateway.entity.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaEntity {

    private String id;

    private String title;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String icon;

    private Boolean alwaysShow;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Boolean noCache;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Boolean affix;
}
