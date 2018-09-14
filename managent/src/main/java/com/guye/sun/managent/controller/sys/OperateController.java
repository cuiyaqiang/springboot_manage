package com.guye.sun.managent.controller.sys;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.guye.sun.common.annotation.DefinedLog;
import com.guye.sun.common.exception.DefinedException;
import com.guye.sun.managent.controller.BaseController;
import com.guye.sun.managent.pojo.dto.OperateDto;
import com.guye.sun.managent.pojo.message.ApiResult;
import com.guye.sun.managent.pojo.po.Menu;
import com.guye.sun.managent.pojo.po.Operate;
import com.guye.sun.managent.service.MenuService;
import com.guye.sun.managent.service.OperateService;
import com.guye.sun.mybatis.page.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityNotFoundException;

/**
 * Created by suneee on 2018/5/29.
 */
@Controller
@RequestMapping("/sys/operate")
public class OperateController extends BaseController {

    @Autowired
    private OperateService operateService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public String list(){
//        PageInfo<Operate> operates = operateService.listForDataGrid(dataGrid);
//        request.setAttribute("page",operates);
        return "sys/operate/list";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id) {
        if (id != null) {
            request.setAttribute("dto", operateService.selectById(id).orElseThrow(() -> DefinedException.notFound("该数据已失效")));
        }
        request.setAttribute("menus", menuService.listMenu());
        return "sys/operate/edit";
    }

    @GetMapping(value = "/query")
    @ResponseBody
    public PageInfo<OperateDto> query(DataGrid grid) {
        return this.operateService.listOperateByPage(grid);
    }

    @DefinedLog(module = "菜单管理", methods = "保存菜单", description = "添加/修改菜单信息")
    @PostMapping(value = "/save")
    @ResponseBody
    public ApiResult<String> save(Operate operate) {
        if (operate != null && operate.getId() != null) {
            return ApiResult.getResponse(operateService.updateSelectiveById(operate));
        }
        return ApiResult.getResponse(operateService.insertSelective(operate));
    }

    @DefinedLog(module = "菜单管理", methods = "移除菜单", description = "删除菜单信息")
    @PostMapping(value = "/remove")
    @ResponseBody
    public ApiResult<String> del(Integer[] ids) {
        Lists.newArrayList(ids).forEach(this.operateService::deleteById);
        return ApiResult.SUCCESS;
    }
}
