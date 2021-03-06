package com.orange.tbk.adminweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.orange.tbk.adminweb.annotation.Open;
import com.orange.tbk.adminweb.annotation.RspHandle;
import com.orange.tbk.adminweb.model.Response;
import com.orange.tbk.adminweb.model.ResponseCode;
import com.orange.tbk.api.bean.SystemConfig;
import com.orange.tbk.api.service.SystemConfigService;
import com.orange.tbk.api.vo.open.SystemConfigVo;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "systemConfig")
public class SystemConfigController {

    @Reference
    private SystemConfigService systemConfigService;

    @Open(explain = "获取系统配置信息...网站底部...")
    @RspHandle
    @RequestMapping(value = "getSystemConfig",method = RequestMethod.GET)
    @ResponseBody
    public Response getSystemConfig() {

        SystemConfigVo systemConfigVo = systemConfigService.getSystemConfigVo();

        return Response.build(ResponseCode.QUERY_SUCCESS,systemConfigVo);
    }

    @RspHandle
    @RequiresUser
    @RequestMapping(value = "single",method = RequestMethod.GET)
    @ResponseBody
    public Response single() {

        List<SystemConfig> list = systemConfigService.list();
        if (list == null || list.size() == 0) {
            return Response.build(ResponseCode.EMPTY);
        }

        return Response.build(ResponseCode.QUERY_SUCCESS,list.get(0));
    }

    @RspHandle
    @RequiresUser
    @RequestMapping(value = "create",method = RequestMethod.POST)
    @ResponseBody
    public Response create(SystemConfig systemConfig) {

        systemConfigService.create(systemConfig);

        return Response.build(ResponseCode.SUCCESS);
    }

}
