package com.guye.sun.managent.service;


import com.github.pagehelper.PageInfo;
import com.guye.sun.managent.pojo.dto.OperateDto;
import com.guye.sun.managent.pojo.dto.ShiroPermission;
import com.guye.sun.managent.pojo.po.Operate;
import com.guye.sun.mybatis.page.DataGrid;
import com.guye.sun.mybatis.service.BaseService;

import java.util.List;

public interface OperateService extends BaseService<Operate> {

    /**
     * 根据角色ID和菜单ID查询操作列表
     *
     * @param roleId 角色ID
     * @param menuId 菜单ID
     * @return 查询结果
     */
    List<Operate> listOperateByRoleIdAndMenuId(Integer roleId, Integer menuId);

    /**
     * 分页查询 操作列表
     *
     * @param grid 分页信息
     * @return 查询结果
     */
    PageInfo<OperateDto> listOperateByPage(DataGrid grid);

    /**
     * 根据 角色ID 查询 权限
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<ShiroPermission> listShiroPermissions(Integer roleId);

}
