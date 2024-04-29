package com.gateway.dto.menu;

import lombok.Data;

@Data
public class MenuDTO {

    private String id;
    private String parentId;
    private String path;
    private String title;
    private String component;
    private String name;
    private int sort;
    private int status;
    private int type;
    private boolean noTagsView;
    private boolean noCache;
    private boolean hidden;
    private boolean canTo;
    private boolean breadcrumb;
    private boolean alwaysShow;
    private boolean affix;
}
