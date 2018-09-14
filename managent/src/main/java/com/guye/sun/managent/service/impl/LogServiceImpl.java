package com.guye.sun.managent.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guye.sun.managent.dao.LogMapper;
import com.guye.sun.managent.pojo.po.Log;
import com.guye.sun.managent.service.LogService;
import com.guye.sun.mybatis.page.DataGrid;
import com.guye.sun.mybatis.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public PageInfo<Log> listForDataGrid(DataGrid grid, String datetime) {
        Example example = new Example(Log.class);
        String startTime, endTime;
        if (StringUtils.isNotEmpty(datetime)) {
            String[] time = datetime.split(" - ");
            startTime = time[0];
            endTime = time[1];
            example.createCriteria().andBetween("gmtCreate", startTime, endTime);
        }
        return PageHelper.startPage(grid.getPageNum(), grid.getPageSize()).doSelectPageInfo(() -> this.logMapper.selectByExample(example));
    }
}
