package com.mobileBank.mapper;

import com.mobileBank.pojo.PayInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface PayInfoMapper {
    List<PayInfo> selectPayInfoByPayAccountId(@Param("payAccountId") Integer payAccountId);

    List<PayInfo> selectPayInfoByPayAccountIdAndDateRange(@Param("payAccountId") Integer payAccountId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    Integer addPayInfo(PayInfo payInfo);
}
