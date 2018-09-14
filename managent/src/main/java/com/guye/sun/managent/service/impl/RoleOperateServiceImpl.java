package com.guye.sun.managent.service.impl;

import com.google.common.collect.Lists;
import com.guye.sun.managent.dao.RoleOperateMapper;
import com.guye.sun.managent.pojo.message.ApiResult;
import com.guye.sun.managent.pojo.po.RoleOperate;
import com.guye.sun.managent.service.RoleOperateService;
import com.guye.sun.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by suneee on 2018/5/29.
 */
@Service
public class RoleOperateServiceImpl extends BaseServiceImpl<RoleOperate> implements RoleOperateService {

    @Autowired
    private RoleOperateMapper roleOperateMapper;

    @Override
    public List<RoleOperate> listRoleOperateByRoleId(Integer roleId) {
        return roleOperateMapper.listRoleOperateByRoleId(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResult<String> batchInsertRoleOperate(Integer[] operateIds, Integer roleId) {
        roleOperateMapper.delete(new RoleOperate(roleId));
        List<RoleOperate> ops = Lists.newArrayList(operateIds).stream().map(id -> new RoleOperate(roleId, id)).collect(toList());
        int result = roleOperateMapper.batchInsertRoleOperate(ops);
        return ApiResult.getResponse(result > 0);
    }
}
