package com.mobileBank.service;

import com.mobileBank.pojo.PayInfo;

import java.util.Date;
import java.util.List;

public interface PayInfoService {
    /**
     * 功能：用户查询银行卡所有支付信息服务
     * @param payAccountId
     * @return
     */
    public List<PayInfo> selectPayInfoByPayAccountId(Integer payAccountId);

    /**
     * 功能：用户通过日期筛选银行卡支付信息服务
     * @param payAccountId
     * @param startDate
     * @param endDate
     * @return
     */
    public List<PayInfo> selectPayInfoByPayAccountIdAndDateRange(Integer payAccountId, Date startDate, Date endDate);

    /**
     * 功能：系统添加银行卡转账信息服务
     * @param payInfo
     * @return
     */
    public Integer addPayInfo(PayInfo payInfo);

}
