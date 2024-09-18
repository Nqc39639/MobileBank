package com.mobileBank.mapper;

import com.mobileBank.pojo.AdminLog;
import com.mobileBank.pojo.UserLogSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface AdminLogMapper {
    Integer addAdminLog(AdminLog adminLog);

    List<AdminLog> selectAllAdminLog();

    List<AdminLog> selectAdminLogBySearch(UserLogSearch search);

    List<AdminLog> selectAdminLogByAdminId(@Param("adminId") Integer adminId);

    List<AdminLog> selectAdminLogByOperationTimeRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
