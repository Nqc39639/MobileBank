package com.mobileBank.service;

import com.mobileBank.pojo.TransferInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TransferInfoService {
    /**
     * 功能：用户查询银行卡所有转账信息服务
     *
     * @param payAccountId
     * @return
     */
    List<TransferInfo> selectTransferInfoByPayAccountId(Integer payAccountId);

    /**
     * 功能：用户查询银行卡所有收账信息服务
     *
     * @param targetPayAccountId
     * @return
     */
    List<TransferInfo> selectTransferInfoByTargetPayAccountId(Integer targetPayAccountId);

    /**
     * 功能：用户通过日期筛选银行卡转账信息服务
     *
     * @param payAccountId
     * @param startDate
     * @param endDate
     * @return
     */
    List<TransferInfo> selectTransferInfoByPayAccountIdAndDateRange(Integer payAccountId, Date startDate, Date endDate);

    /**
     * 功能：用户通过日期筛选银行卡收账信息服务
     *
     * @param targetPayAccountId
     * @param startDate
     * @param endDate
     * @return
     */
    List<TransferInfo> selectTransferInfoByTargetPayAccountIdAndDateRange(Integer targetPayAccountId, Date startDate, Date endDate);

    /**
     * 功能：系统添加银行卡转账信息服务
     *
     * @param transferInfo
     * @return
     */
    Integer addTransferInfo(TransferInfo transferInfo);

    /**
     * 功能：用户查询所有账户的收款方信息服务
     *
     * @param id
     * @return
     */
    List<Map<String, Object>> selectRecipients(Integer id);

}
