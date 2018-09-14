package com.guye.sun.managent.dao;

import com.guye.sun.managent.pojo.po.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by suneee on 2018/5/28.
 */
@Mapper
public interface AuthMapper {

    /**
     * 根据角色ID查询可操作的菜单
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    @Select("SELECT t1.* FROM t_sys_menu t1 JOIN t_sys_operate t2 ON t1.id = t2.menu_id AND t1.locked = 1\n" +
            "\t\tJOIN t_sys_role_operate t3 ON t2.id = t3.operate_id AND t2.op = 'list' AND role_id = #{roleId}")
    List<Menu> listMenuByRoleId(@Param("roleId") Integer roleId);
}
