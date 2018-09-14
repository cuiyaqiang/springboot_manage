package com.guye.sun.managent.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guye.sun.managent.dao.ManagerMapper;
import com.guye.sun.managent.pojo.dto.ManagerDto;
import com.guye.sun.managent.pojo.po.Manager;
import com.guye.sun.managent.service.ManagerService;
import com.guye.sun.mybatis.page.DataGrid;
import com.guye.sun.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl extends BaseServiceImpl<Manager> implements ManagerService {

	private final ManagerMapper managerMapper;

	@Autowired
	public ManagerServiceImpl(ManagerMapper managerMapper) {
		this.managerMapper = managerMapper;
	}

	@Override
	public PageInfo<ManagerDto> listManagerByName(DataGrid grid, String name, String account) {
		grid.getOrderBy();
		return PageHelper.startPage(grid.getPageNum(), grid.getPageSize())
				.doSelectPageInfo(() -> this.managerMapper.listManagerByName(name,account));
	}

	@Override
	public ManagerDto selectManagerByAccount(String account) {
		return this.managerMapper.selectManagerByAccount(account);
	}


}
