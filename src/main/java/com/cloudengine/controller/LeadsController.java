package com.cloudengine.controller;


import com.alibaba.fastjson.JSON;
import com.cloudengine.dao.dataobject.LeadsDO;
import com.cloudengine.service.LeadsService;
import com.cloudengine.util.LeadsCheckUtil;
import com.cloudengine.vo.LeadsVO;
import com.cloudengine.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.ByteArrayResource;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "API接口测试", tags = "API接口测试")
public class LeadsController {

    @Autowired
    private LeadsService leadsService;


    @ApiOperation(value = "获取用户信息", notes = "根据用户ID获取用户信息")
    @PostMapping("/addleads")
    @ResponseBody
    public String addLeads(@RequestParam("name") String name,
                           @RequestParam("phoneNumber") String phoneNumber,
                           @RequestParam("email") String email) {
        Result<Boolean> r = new Result<>();

        LeadsVO leadsVO = new LeadsVO();
        leadsVO.setName(name);
        leadsVO.setPhoneNumber(phoneNumber);
        leadsVO.setEmail(email);

        if(StringUtils.isEmpty(leadsVO.getPhoneNumber())
                || StringUtils.isEmpty(leadsVO.getEmail())
                || StringUtils.isEmpty(leadsVO.getName())
                || ! LeadsCheckUtil.validPhoneNumber(leadsVO.getPhoneNumber())
                || ! LeadsCheckUtil.validEmail(leadsVO.getEmail())
        ) {
            r.failed(false);

            return JSON.toJSONString(r);
        }
        boolean b = leadsService.addLeads(leadsVO);


        r.success(b);

        return JSON.toJSONString(r);
    }

    @ApiOperation(value = "获取用户信息", notes = "根据用户ID获取用户信息")
    @PostMapping("/addleads2")
    @ResponseBody
    public String addLeads2(@ApiParam  @RequestBody LeadsVO leadsVO) {
        Result<Boolean> r = new Result<>();

        if(StringUtils.isEmpty(leadsVO.getPhoneNumber())
                || StringUtils.isEmpty(leadsVO.getEmail())
                || StringUtils.isEmpty(leadsVO.getName())
                || ! LeadsCheckUtil.validPhoneNumber(leadsVO.getPhoneNumber())
                || ! LeadsCheckUtil.validEmail(leadsVO.getEmail())
        ) {
            r.failed(false);

            return JSON.toJSONString(r);
        }
        boolean b = leadsService.addLeads(leadsVO);


        r.success(b);

        return JSON.toJSONString(r);
    }

    @GetMapping("/download-csv")
    public ResponseEntity<ByteArrayResource> downloadCsv(@Param("startDate") String startDate) throws IOException {

        // 设置HTTP响应头以触发文件下载
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "data.csv");

        // 创建输出流并写入CSV数据
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d ;
        try {
            d = sdf.parse(startDate);
        } catch (Exception e) {
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("eror message ")
                    .withRecordSeparator("\n"));
            String[] error = new String[]{"param error: " + startDate};
            csvPrinter.printRecord(error);
            ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
            return ResponseEntity.ok().headers(headers).body(resource);
        }

        List<LeadsDO> leads = leadsService.queryForCsv(d);


        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("Name", "Email", "PhoneNumber", "Description")
                .withRecordSeparator("\n"))) {
            for (LeadsDO leadsDO : leads) {
                String[] record = new String[] {leadsDO.getName(), leadsDO.getEmail(), leadsDO.getPhoneNumber(), leadsDO.getDescription()};
                csvPrinter.printRecord(record);
            }
        }

        // 将输出流转换为字节数组资源
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        // 返回响应实体
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

}
