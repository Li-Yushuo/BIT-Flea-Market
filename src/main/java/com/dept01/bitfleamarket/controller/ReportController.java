package com.dept01.bitfleamarket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    //获取举报列表
    @GetMapping("/reports")
    public String getReports() {
        return "reports";
    }

    //举报商品
    @PostMapping("/reports")
    public String reportProduct() {
        return "report";
    }

    //查看举报详情
    @GetMapping("/reports/{report_id}")
    public String getReportDetail() {
        return "report";
    }

    //处理举报
    @PutMapping("/reports/{report_id}")
    public String handleReport() {
        return "handle";
    }
}
