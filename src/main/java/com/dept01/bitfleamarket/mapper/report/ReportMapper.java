package com.dept01.bitfleamarket.mapper.report;

import com.dept01.bitfleamarket.pojo.report.Report;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReportMapper {
    // 插入举报
    @Insert("INSERT INTO report(reporter_id, product_id, violation_type, description) VALUES(#{reporterId}, #{productId}, #{violationType}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "reportId")
    int insert(Report report);

    // 删除举报
    @Delete("DELETE FROM report WHERE report_id = #{reportId}")
    int delete(int reportId);

    // 根据reportId查询举报
    @Select("SELECT * FROM report WHERE report_id = #{reportId}")
    Report selectById(int reportId);

    // 查询所有举报
    @Select("SELECT * FROM report ORDER BY create_time DESC")
    List<Report> selectAll();
}