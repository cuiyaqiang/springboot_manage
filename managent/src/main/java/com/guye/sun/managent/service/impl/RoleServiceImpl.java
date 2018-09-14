package com.guye.sun.managent.service.impl;

import com.google.common.collect.Lists;
import com.guye.sun.managent.dao.RoleMapper;
import com.guye.sun.managent.dao.RoleOperateMapper;
import com.guye.sun.managent.pojo.po.Role;
import com.guye.sun.managent.service.RoleService;
import com.guye.sun.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.orderbyhelper.OrderByHelper;

import java.util.List;

/**
 * Created by suneee on 2018/5/30.
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleOperateMapper roleOperateMapper;

    @Override
    public List<Role> listRole() {
        OrderByHelper.orderBy("ID ASC");
        return roleMapper.selectAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRoleAndOperate(Integer[] ids) {
        Lists.newArrayList(ids).forEach(id -> {
            super.deleteById(id);
            this.roleOperateMapper.deleteRoleOperateByRoleId(id);
        });
    }
}
