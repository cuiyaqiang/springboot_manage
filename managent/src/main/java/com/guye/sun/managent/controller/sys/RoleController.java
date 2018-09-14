package com.guye.sun.managent.controller.sys;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.guye.sun.common.annotation.DefinedLog;
import com.guye.sun.common.exception.DefinedException;
import com.guye.sun.managent.controller.BaseController;
import com.guye.sun.managent.pojo.dto.ManagerDto;
import com.guye.sun.managent.pojo.message.ApiResult;
import com.guye.sun.managent.pojo.po.Manager;
import com.guye.sun.managent.pojo.po.Role;
import com.guye.sun.managent.service.MenuService;
import com.guye.sun.managent.service.RoleService;
import com.guye.sun.mybatis.page.DataGrid;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * Created by suneee on 2018/5/30.
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @ApiIgnore
    @GetMapping(value = "/list")
    public String list(){
        return "sys/role/list";
    }

    @GetMapping("/permissions")
    public String permissions(Integer roleId) {
        request.setAttribute("permissions", menuService.listPermissions());
        request.setAttribute("roleId", roleId);
        return "sys/role/permissions";
    }

    @ApiIgnore
    @GetMapping(value = "/edit")
    public String edit(Integer id){
        if (id != null){
            request.setAttribute("dto",roleService.selectById(id).orElseThrow(() -> DefinedException.notFound("该数据已失效")));
        }
        return "sys/role/edit";
    }

    @ApiOperation(value = "带条件分页查询")
    @GetMapping(value = "/query")
    @ResponseBody
    public PageInfo<Role> query(DataGrid dataGrid, String name, String account) {
        return roleService.listForDataGrid(dataGrid);
    }

    @ApiOperation(value = "添加/修改角色信息")
    @DefinedLog(module = "角色管理", methods = "保存角色", description = "添加/修改角色信息")
    @PostMapping(value = "/save")
    @ResponseBody
    public ApiResult<Role> save(Role role){
        role.setGmtModified(new Date());
        if (role.getId() != null) {
            return ApiResult.getResponse(this.roleService.updateSelectiveById(role));
        }
        return ApiResult.getResponse(this.roleService.insertSelective(role));
    }

    @ApiOperation(value = "删除角色信息")
    @DefinedLog(module = "角色管理", methods = "移除角色", description = "删除角色信息")
    @PostMapping(value = "/remove")
    @ResponseBody
    public ApiResult<String> del(Integer[] ids) {
        Lists.newArrayList(ids).forEach(this.roleService::deleteById);
        return ApiResult.SUCCESS;
    }


}
