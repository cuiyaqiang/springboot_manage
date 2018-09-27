package com.guye.sun.managent.controller.sys;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.guye.sun.common.annotation.DefinedLog;
import com.guye.sun.common.exception.DefinedException;
import com.guye.sun.managent.config.rabbitmq.BookRabbitConfig;
import com.guye.sun.managent.config.rabbitmq.ManagerRabbitConfig;
import com.guye.sun.managent.controller.BaseController;
import com.guye.sun.managent.pojo.dto.ManagerDto;
import com.guye.sun.managent.pojo.message.ApiResult;
import com.guye.sun.managent.pojo.po.Manager;
import com.guye.sun.managent.service.ManagerService;
import com.guye.sun.managent.service.RoleService;
import com.guye.sun.mybatis.page.DataGrid;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by suneee on 2018/5/30.
 */
@Controller
@RequestMapping("/sys/manager")
public class ManagerController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiIgnore
    @GetMapping(value = "/list")
    public String list(){
        return "sys/manager/list";
    }

    @ApiIgnore
    @GetMapping(value = "/edit")
    public String edit(Integer id){
        if (id != null){
            request.setAttribute("dto",managerService.selectById(id).orElseThrow(() -> DefinedException.notFound("该数据已失效")));
        }
        request.setAttribute("roles",roleService.listRole() );
        return "sys/manager/edit";
    }

    @ApiOperation(value = "带条件分页查询")
    @GetMapping(value = "/query")
    @ResponseBody
    public PageInfo<ManagerDto> query(DataGrid dataGrid, String name, String account){
        return managerService.listManagerByName(dataGrid,name,account);
    }

    @ApiOperation(value = "添加/修改用户信息")
    @DefinedLog(module = "用户管理", methods = "保存用户", description = "添加/修改用户信息")
    @PostMapping(value = "/save")
    @ResponseBody
    public ApiResult<Manager> save(Manager manager){
        manager.setGmtModified(new Date());
        if (manager.getId() != null) {
            return ApiResult.getResponse(this.managerService.updateSelectiveById(manager));
        }
        boolean isIsert = this.managerService.insertSelective(manager);
        if (isIsert){
            System.out.println("=====================================================进入mq");
            // 添加延时队列
            rabbitTemplate.convertAndSend(ManagerRabbitConfig.REGISTER_DELAY_EXCHANGE, ManagerRabbitConfig.DELAY_ROUTING_KEY, manager, message -> {
                // TODO 第一句是可要可不要,根据自己需要自行处理
                message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, Manager.class.getName());
                // TODO 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
                message.getMessageProperties().setExpiration(5 * 1000 + "");
                return message;
            });
            log.info("[发送时间] - [{}]", LocalDateTime.now());
        }
        return ApiResult.getResponse(isIsert);
    }

    @ApiOperation(value = "删除用户信息")
    @DefinedLog(module = "用户管理", methods = "移除用户", description = "删除用户信息")
    @PostMapping(value = "/remove")
    @ResponseBody
    public ApiResult<String> del(Integer[] ids) {
        Lists.newArrayList(ids).forEach(this.managerService::deleteById);
        return ApiResult.SUCCESS;
    }
}
