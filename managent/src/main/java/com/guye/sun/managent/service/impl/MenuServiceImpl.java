package com.guye.sun.managent.service.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.guye.sun.common.model.TreeNode;
import com.guye.sun.managent.dao.AuthMapper;
import com.guye.sun.managent.dao.MenuMapper;
import com.guye.sun.managent.dao.OperateMapper;
import com.guye.sun.managent.pojo.dto.PermissionDto;
import com.guye.sun.managent.pojo.po.Menu;
import com.guye.sun.managent.service.MenuService;
import com.guye.sun.mybatis.page.DataGrid;
import com.guye.sun.mybatis.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by suneee on 2018/5/28.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private OperateMapper operateMapper;

    private List<TreeNode> createTree(List<Menu> menus, Integer pid) {
        return menus.stream().filter(m -> m.getPaterId() != null && Objects.equals(pid, m.getPaterId()))
                .map(m -> new TreeNode(m.getId(), m.getName(), m.getIcon())).collect(Collectors.toList());
    }

    @Override
    public List<TreeNode> listTree(Integer roleId) {
        List<TreeNode> trees = Lists.newArrayList();
        List<Menu> menus = this.authMapper.listMenuByRoleId(roleId);
        menus.stream().filter(m -> m.getPaterId() == null).forEach(m -> {
            List<TreeNode> twoTrees = createTree(menus, m.getId());
            /*menus.stream().filter(n -> n.getPaterId() != null && n.getnLevel() == 1).forEach(n -> {
                List<TreeNode> threeTrees = createTree(menus, n.getId());
                if (CollectionUtils.isNotEmpty(threeTrees)) {
                    twoTrees.add(new TreeNode(n.getId(), n.getName(), n.getIcon(), threeTrees));
                }
            });*/
            if (CollectionUtils.isNotEmpty(twoTrees)) {
                trees.add(new TreeNode(m.getId(), m.getName(), m.getIcon(), twoTrees));
            }
        });

        return trees;
    }

    @Override
    public List<PermissionDto> listPermissions() {
        List<PermissionDto> list = operateMapper.listPermissions();
        // 根目录
        List<PermissionDto> trees = list.stream().filter(m1 -> m1.getPaterId() == null).collect(Collectors.toList());
        trees.forEach(m2 -> {
            List<PermissionDto> permissions = list.stream()
                    .filter(p1 -> StringUtils.equals(p1.getOp(), "list") && Objects.equals(p1.getPaterId(), m2.getMenuId()))
                    .collect(Collectors.toList());
            permissions.forEach(m3 -> {
                m3.setChildren(list.stream()
                        .filter(b1 -> !StringUtils.equals(b1.getOp(), "list") && Objects.equals(b1.getMenuId(), m3.getMenuId()))
                        .collect(Collectors.toList()));
            });
            m2.setChildren(permissions);
        });
        return trees;
    }

    @Override
    public PageInfo<Menu> listMenuForDataGrid(DataGrid grid) {

        return PageHelper.startPage(grid.getPageNum(), grid.getPageSize()).doSelectPageInfo(menuMapper :: listMenu);
    }

    @Override
    public List<Menu> listMenu() {
        List<Menu> menus = this.menuMapper.listMenu();
        Consumer<Menu> giverRaise = m -> m.setName(m.getPaterId() == null ? "--" + m.getName() : "----" + m.getName());
        menus.forEach(giverRaise);
        return menus;
    }

    @Override
    public void saveOrUpdate(Menu menu) {
        menu.setGmtModified(new Date());
        if (menu.getId() != null) {
            super.updateSelectiveById(menu);
        } else {
            super.insertSelective(menu);
        }
        this.menuMapper.refreshTreeNodes();
    }
}
