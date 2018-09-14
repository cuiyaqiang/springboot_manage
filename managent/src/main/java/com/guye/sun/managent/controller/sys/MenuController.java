package com.guye.sun.managent.controller.sys;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.guye.sun.common.annotation.DefinedLog;
import com.guye.sun.managent.controller.BaseController;
import com.guye.sun.managent.pojo.message.ApiResult;
import com.guye.sun.managent.pojo.po.Menu;
import com.guye.sun.managent.service.MenuService;
import com.guye.sun.mybatis.page.DataGrid;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by suneee on 2018/5/29.
 */
@Controller
@RequestMapping("/sys/menu")
@Api(value = "菜单管理")
@ApiIgnore
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public String list(DataGrid dataGrid){
        PageInfo<Menu> menus = menuService.listMenuForDataGrid(dataGrid);
        request.setAttribute("page",menus);
        return "sys/menu/list";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id) {
        if (id != null) {
            request.setAttribute("dto", menuService.selectById(id).orElseThrow(() -> new EntityNotFoundException("该数据已失效")));
        }
        request.setAttribute("menus", menuService.listMenu());
        return "sys/menu/edit";
    }

    @DefinedLog(module = "菜单管理", methods = "保存菜单", description = "添加/修改菜单信息")
    @PostMapping(value = "/save")
    @ResponseBody
    public ApiResult<String> save(Menu menu) {
        if (menu != null) {
            this.menuService.saveOrUpdate(menu);
        }
        return ApiResult.SUCCESS;
    }

    @DefinedLog(module = "菜单管理", methods = "移除菜单", description = "删除菜单信息")
    @PostMapping(value = "/remove")
    @ResponseBody
    public ApiResult<String> del(Integer[] ids) {
        Lists.newArrayList(ids).forEach(this.menuService::deleteById);
        return ApiResult.SUCCESS;
    }


}
