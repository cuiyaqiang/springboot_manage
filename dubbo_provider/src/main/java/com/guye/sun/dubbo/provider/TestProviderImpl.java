package com.guye.sun.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.guye.sun.dubbo.api.TestProvider;
import com.guye.sun.dubbo.pojo.dto.ManagerDto;
import com.guye.sun.dubbo.service.ManagerService;
import com.guye.sun.mybatis.page.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by suneee on 2018/10/8.
 */
@Service
public class TestProviderImpl implements TestProvider {

    @Autowired
    private ManagerService managerService;

    @Override
    public PageInfo<ManagerDto> listManagerByName(DataGrid grid, String name, String account) {
        return managerService.listManagerByName(grid,name,account);
    }

    @Override
    public ManagerDto selectManagerByAccount(String account) {
        System.out.println("=================================进入provider");
        return managerService.selectManagerByAccount(account);
    }


}
