package com.guye.sun.managent.dao;

import com.guye.sun.managent.pojo.po.RoleOperate;
import com.guye.sun.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleOperateMapper extends BaseMapper<RoleOperate> {

    /**
     * 根据角色ID查询 角色操作信息
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    @Select("SELECT * FROM	t_sys_role_operate WHERE role_id = #{roleId} ")
    List<RoleOperate> listRoleOperateByRoleId(Integer roleId);

    /**
     * 根据角色ID删除 角色操作信息
     *
     * @param roleId 角色ID
     */
    @Delete("DELETE FROM t_sys_role_operate WHERE role_id = #{roleId}")
    void deleteRoleOperateByRoleId(Integer roleId);

    /**
     * 批量保存 角色操作信息
     *
     * @param ops 数据集
     * @return 操作结果
     */
    int batchInsertRoleOperate(List<RoleOperate> ops);
}