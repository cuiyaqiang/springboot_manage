package com.guye.sun.managent.service;


import com.github.pagehelper.PageInfo;
import com.guye.sun.managent.pojo.po.Log;
import com.guye.sun.mybatis.page.DataGrid;
import com.guye.sun.mybatis.service.BaseService;

public interface LogService extends BaseService<Log> {

    PageInfo<Log> listForDataGrid(DataGrid grid, String datetime);

}
