package com.dept01.bitfleamarket.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class LabelController {

    //获取标签列表
    @GetMapping("/labels")
    public String getLabels() {
        return "labels";
    }

    //获取标签详情
    @GetMapping("/labels/{label_id}")
    public String getLabelDetail() {
        return "label";
    }

    //添加标签
    @PostMapping("/labels")
    public String addLabel() {
        return "add";
    }

    //删除标签
    @DeleteMapping("/labels/{label_id}")
    public String deleteLabel() {
        return "delete";
    }

    //修改标签
    @PutMapping("/labels/{label_id}")
    public String modifyLabel() {
        return "modify";
    }

    //搜索标签
    @GetMapping("/labels/search")
    public String searchLabel() {
        return "search";
    }
}
