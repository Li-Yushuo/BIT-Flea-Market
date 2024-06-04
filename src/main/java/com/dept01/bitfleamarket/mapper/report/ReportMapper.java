package com.dept01.bitfleamarket.mapper.report;

import com.dept01.bitfleamarket.pojo.report.Report;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReportMapper {
    // 插入举报
    @Insert("INSERT INTO report(reporter_id, product_id, violation_type, description, create_time) VALUES(#{reporterId}, #{productId}, #{violationType}, #{description}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "reportId")
    int insertReport(Report report);

    // 更新举报
    // @Update("UPDATE report SET reporter_id = #{reporterId}, product_id = #{productId}, violation_type = #{violationType}, description = #{description}, create_time = #{createTime} WHERE report_id = #{reportId}")
    // int updateReport(Report report);

    // 删除举报
    @Delete("DELETE FROM report WHERE report_id = #{reportId}")
    int deleteReport(int reportId);

    // 根据reportId查询举报
    @Select("SELECT * FROM report WHERE report_id = #{reportId}")
    Report getReportById(int reportId);

    // 查询所有举报
    @Select("SELECT * FROM report")
    List<Report> SelectAll();
}