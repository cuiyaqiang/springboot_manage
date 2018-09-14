package com.guye.sun.managent.service;

import com.guye.sun.managent.pojo.message.ApiResult;
import com.guye.sun.managent.pojo.po.RoleOperate;
import com.guye.sun.mybatis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by suneee on 2018/5/29.
 */
public interface RoleOperateService extends BaseService<RoleOperate> {

    /**
     * 根据角色ID查询 角色操作信息
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<RoleOperate> listRoleOperateByRoleId(Integer roleId);

    /**
     * 批量添加 角色操作信息
     *
     * @param operateId 操作ID
     * @param roleId    角色ID
     * @return 操作结果
     */
    ApiResult<String> batchInsertRoleOperate(Integer[] operateId, Integer roleId);

}
