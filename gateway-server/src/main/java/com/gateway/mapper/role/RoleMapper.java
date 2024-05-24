package com.gateway.mapper.role;

import com.gateway.vo.role.RolesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    List<RolesVO> queryRoelsByName(
            @Param("roleName") String roleName,
            @Param("page") int page,
            @Param("size") int size
    );
}
