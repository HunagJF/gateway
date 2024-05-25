package com.gateway.mapper.role;

import com.gateway.vo.role.RolesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper {

    List<RolesVO> queryRoelsByName(Map<String, Object> parem);

    int queryRoelsByNameCount(Map<String, Object> parem);
}
