package com.mobileBank.mapper;

import com.mobileBank.pojo.TransferInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface TransferInfoMapper {
    List<TransferInfo> selectTransferInfoByPayAccountId(@Param("payAccountId") Integer payAccountId);

    List<TransferInfo> selectTransferInfoByTargetPayAccountId(@Param("targetPayAccountId") Integer targetPayAccountId);

    List<TransferInfo> selectTransferInfoByPayAccountIdAndDateRange(@Param("payAccountId") Integer payAccountId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<TransferInfo> selectTransferInfoByTargetPayAccountIdAndDateRange(@Param("targetPayAccountId") Integer targetPayAccountId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    Integer addTransferInfo(TransferInfo transferInfo);

    List<Map<String, Object>> selectRecipients(@Param("id") Integer id);
}
