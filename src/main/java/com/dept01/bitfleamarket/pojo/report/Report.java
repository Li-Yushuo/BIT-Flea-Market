package com.dept01.bitfleamarket.pojo.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private Integer reportId;
    private Integer reporterId;
    private Integer productId;
    private String violationType; // 在数据库中是enum类型
    private String description;
    private LocalDateTime createTime;
}