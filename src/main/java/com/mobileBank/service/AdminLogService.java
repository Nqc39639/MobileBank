package com.mobileBank.service;

import com.github.pagehelper.PageInfo;
import com.mobileBank.pojo.AdminLog;

import java.util.Date;
import java.util.List;

public interface AdminLogService {
    /**
     * 功能：系统添加用户日志服务
     *
     * @param adminLog
     * @return
     */
    Integer addAdminLog(AdminLog adminLog);

    /**
     * 功能：管理员查询所有管理员日志服务
     *
     * @return
     */
    PageInfo<AdminLog> selectAllAdminLog(Integer pageNum, Integer limit);

    /**
     * 功能：管理员条件查询管理员日志服务
     *
     * @param
     * @return
     */
    PageInfo<AdminLog> selectAdminLogBySearch(String searchParams, Integer pageNum, Integer limit);

    /**
     * 功能：管理员查询用户日志服务
     *
     * @param adminId
     * @return
     */
    List<AdminLog> selectAdminLogByAdminId(Integer adminId);

    /**
     * 功能：管理员通过operationTime查询用户日志服务
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<AdminLog> selectAdminLogByOperationTimeRange(Date startDate, Date endDate);

}
