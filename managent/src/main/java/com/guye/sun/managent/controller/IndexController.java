package com.guye.sun.managent.controller;

import com.guye.sun.common.exception.DefinedException;
import com.guye.sun.managent.pojo.message.ApiResult;
import com.guye.sun.managent.pojo.po.Menu;
import com.guye.sun.managent.pojo.po.Operate;
import com.guye.sun.managent.service.MenuService;
import com.guye.sun.managent.service.OperateService;
import com.guye.sun.managent.utils.SessionUtil;
import com.mysql.jdbc.Connection;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;

/**
 * Created by suneee on 2018/5/28.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private OperateService operateService;

    @ApiIgnore
    @CrossOrigin
    @GetMapping(value = {"/index"})
    public String index(Model model) {
        Integer roleId = SessionUtil.getSession().getRoleId().intValue();
        model.addAttribute("trees", menuService.listTree(roleId));
        return "index";
    }

    @ApiIgnore
    @GetMapping(value = "denied")
    public String denied() {
        return DENIED;
    }


    @ApiIgnore
    @GetMapping(value = "main")
    public String main() {
        return PATH_MAIN;
    }

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @ApiIgnore
    @GetMapping("install")
    @ResponseBody
    public ApiResult<String> install() {
        try {
            Class.forName(driver).newInstance();
            Connection conn = (Connection) DriverManager.getConnection(url, username, password);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setErrorLogWriter(null);
            runner.setLogWriter(null);
            runner.runScript((new InputStreamReader(getClass().getResourceAsStream("/使用须知/install.sql"), "UTF-8")));
        } catch (Exception e) {
            return ApiResult.getFailure("初始化失败！请联系管理员");
        }
        return ApiResult.getSuccess("初始化成功");
    }

    @RequestMapping("op_{oper}_{menuId}")
    @ApiOperation(value = "根据oper与id转发到指定的地址", httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功"), @ApiResponse(code = 404, message = "地址错误"),
            @ApiResponse(code = 500, message = "系统错误,请联系管理人员")})
    public String forward(@PathVariable String oper, @PathVariable Integer menuId, Model model) {
        if (SessionUtil.getSession() == null) {
            return "redirect:/denied";
        }
        Menu menu = this.menuService.selectById(menuId).orElseThrow(() -> DefinedException.notFound("无访问权限"));
        Integer roleId = SessionUtil.getSession().getRoleId().intValue();
        List<Operate> op = Optional.ofNullable(this.operateService.listOperateByRoleIdAndMenuId(roleId, menuId)).orElseThrow(() -> DefinedException.notFound("无权限"));
        if (CollectionUtils.isEmpty(op)) {
            return "redirect:/denied";
        }
        model.addAttribute("operates", op);
        model.addAttribute("OP", op.get(0));
        // 获取对应的菜单对象
        model.addAttribute("MENU", menu);
        String forward = String.format("forward:%s/%s", menu.getChannel(), oper);
        logger.info("[转发地址] - [{}]", forward);
        return forward;
    }
}
