package com.guye.sun.managent.controller.sys;

import com.alibaba.fastjson.JSON;
import com.guye.sun.managent.controller.BaseController;
import com.guye.sun.managent.pojo.message.ApiResult;
import com.guye.sun.managent.pojo.po.RoleOperate;
import com.guye.sun.managent.service.RoleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by suneee on 2018/5/29.
 */
@Controller
@RequestMapping("/sys/role/operate")
public class RoleOperateController extends BaseController {

    @Autowired
    private RoleOperateService roleOperateService;

    @GetMapping("{roleId}")
    @ResponseBody
    public ApiResult<List<RoleOperate>> listOperate(@PathVariable Integer roleId) {
        return ApiResult.getSuccess(roleOperateService.listRoleOperateByRoleId(roleId));
    }

    @PostMapping("/permissions")
    @ResponseBody
    public ApiResult<String> savePermissions(Integer[] operateId, Integer roleId) {
        logger.debug("[数据] - [{}] - [{}]", JSON.toJSONString(operateId), roleId);
        return this.roleOperateService.batchInsertRoleOperate(operateId, roleId);
    }
}
