package com.guye.sun.dubbo.api;

import com.github.pagehelper.PageInfo;
import com.guye.sun.dubbo.pojo.dto.ManagerDto;
import com.guye.sun.mybatis.page.DataGrid;

/**
 * Created by suneee on 2018/10/8.
 */
public interface TestProvider {

    /**
     * 带分页查询账户信息
     *
     * @param grid    分页信息
     * @param name    名称
     * @param account 账户
     * @return 查询结果
     */
    PageInfo<ManagerDto> listManagerByName(DataGrid grid, String name, String account);

    /**
     * 根据账号查询信息
     *
     * @param account 账号
     * @return 查询结果
     */
    ManagerDto selectManagerByAccount(String account);
}
