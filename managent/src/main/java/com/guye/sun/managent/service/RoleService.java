package com.guye.sun.managent.service;

import com.guye.sun.managent.pojo.po.Role;
import com.guye.sun.mybatis.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by suneee on 2018/5/30.
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 查询所有角色,根据ID排序
     *
     * @return 查询结果
     */
    List<Role> listRole();

    /**
     * 批量删除
     *
     * @param ids ID集
     */
    void deleteRoleAndOperate(Integer[] ids);
}
