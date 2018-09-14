package com.guye.sun.managent.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guye.sun.managent.dao.OperateMapper;
import com.guye.sun.managent.pojo.dto.OperateDto;
import com.guye.sun.managent.pojo.dto.ShiroPermission;
import com.guye.sun.managent.pojo.po.Operate;
import com.guye.sun.managent.service.OperateService;
import com.guye.sun.mybatis.page.DataGrid;
import com.guye.sun.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OperateServiceImpl extends BaseServiceImpl<Operate> implements OperateService {

    private final OperateMapper operateMapper;

    @Autowired
    public OperateServiceImpl(OperateMapper operateMapper) {
        this.operateMapper = operateMapper;
    }

    @Override
    public List<Operate> listOperateByRoleIdAndMenuId(Integer roleId, Integer menuId) {
        return this.operateMapper.listOperateByRoleIdAndMenuId(roleId, menuId);

    }

    @Override
    public PageInfo<OperateDto> listOperateByPage(DataGrid grid) {
        grid.getOrderBy();
        return PageHelper.startPage(grid.getPageNum(), grid.getPageSize())
                .doSelectPageInfo(operateMapper::listOperateByPage);
    }

    @Override
    public List<ShiroPermission> listShiroPermissions(Integer roleId) {
        return this.operateMapper.listShiroPermissionByRoleId(roleId);
    }

}
