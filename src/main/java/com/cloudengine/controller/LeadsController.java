package com.cloudengine.controller;


import com.alibaba.fastjson.JSON;
import com.cloudengine.service.LeadsService;
import com.cloudengine.util.LeadsCheckUtil;
import com.cloudengine.vo.LeadsVO;
import com.cloudengine.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import java.util.Arrays;
import java.util.List;

@RestController
@Api(value = "API接口测试", tags = "API接口测试")
public class LeadsController {

    @Autowired
    private LeadsService leadsService;

    @ApiOperation(value = "获取用户信息", notes = "根据用户ID获取用户信息")
    @PostMapping("/addleads")
    @ResponseBody
    public String addLeads(@ApiParam  @RequestBody LeadsVO leadsVO) {
        Result<Boolean> r = new Result<>();

        if(StringUtils.isEmpty(leadsVO.getPhoneNumber())
                || StringUtils.isEmpty(leadsVO.getEmail())
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
    public ResponseEntity<ByteArrayResource> downloadCsv() throws IOException {
        // 准备CSV数据
        List<String[]> csvData = Arrays.asList(
                new String[]{"Name", "Age", "Email"},
                new String[]{"Alice", "25", "alice@example.com"},
                new String[]{"Bob", "30", "bob@example.com"}
        );

        // 创建输出流并写入CSV数据
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("Name", "Age", "Email")
                .withRecordSeparator("\n"))) {
            for (String[] record : csvData) {
                csvPrinter.printRecord(record);
            }
        }

        // 将输出流转换为字节数组资源
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        // 设置HTTP响应头以触发文件下载
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "data.csv");

        // 返回响应实体
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

}
