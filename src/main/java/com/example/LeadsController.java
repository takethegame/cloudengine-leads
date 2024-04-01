package com.example;


import com.example.vo.LeadsVO;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "leads 添加")
public class LeadsController {

    @PostMapping("/addleads")
    @ResponseBody
    public String addLeads(@RequestBody LeadsVO leadsVO) {

        return "success";
    }


}
