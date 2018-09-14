package com.guye.sun.managent.dao;

import com.guye.sun.managent.pojo.dto.RoleOperateDto;
import com.guye.sun.managent.pojo.po.Role;
import com.guye.sun.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据角色ID查询角色操作项
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<RoleOperateDto> listRoleOperateById(@Param("roleId") Integer roleId);
}