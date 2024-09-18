package com.mobileBank.service;

import com.github.pagehelper.PageInfo;
import com.mobileBank.pojo.UserLog;

import java.util.Date;
import java.util.List;

public interface UserLogService {
    /**
     * 功能：系统添加用户日志服务
     *
     * @param userLog
     * @return
     */
    Integer addUserLog(UserLog userLog);

    /**
     * 功能：管理员查询所有用户日志服务
     *
     * @param
     * @return
     */
    PageInfo<UserLog> selectAllUserLog(Integer pageNum, Integer limit);

    /**
     * 功能：管理员条件查询用户日志服务
     *
     * @param
     * @return
     */
    PageInfo<UserLog> selectUserLogBySearch(String searchParams, Integer pageNum, Integer limit);

    /**
     * 功能：管理员查询用户日志服务
     *
     * @param userId
     * @return
     */
    List<UserLog> selectUserLogByUserId(Integer userId);

    /**
     * 功能：管理员通过operationTime查询用户日志服务
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<UserLog> selectUserLogByOperationTimeRange(Date startDate, Date endDate);

}
