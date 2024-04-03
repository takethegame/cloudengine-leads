package com.cloudengine.controller;


import com.alibaba.fastjson.JSON;
import com.cloudengine.service.LeadsService;
import com.cloudengine.vo.LeadsVO;
import com.cloudengine.vo.Result;
import com.fasterxml.jackson.core.JsonGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@RestController
@Api(value = "API接口测试", tags = "API接口测试")
public class LeadsController {

    @Autowired
    private LeadsService leadsService;

    @ApiOperation(value = "获取用户信息", notes = "根据用户ID获取用户信息")
    @PostMapping("/addleads")
    @ResponseBody
    public String addLeads(@ApiParam  @RequestBody LeadsVO leadsVO) {

        boolean b = leadsService.addLeads(leadsVO);

        Result<Boolean> r = new Result<>();
        r.success(b);

        return JSON.toJSONString(r);
    }


}
