package com.mobileBank.mapper;

import com.mobileBank.pojo.UserLog;
import com.mobileBank.pojo.UserLogSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserLogMapper {
    Integer addUserLog(UserLog userLog);

    List<UserLog> selectAllUserLog();

    List<UserLog> selectUserLogBySearch(UserLogSearch search);

    List<UserLog> selectUserLogByUserId(@Param("userId") Integer userId);

    List<UserLog> selectUserLogByOperationTimeRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
