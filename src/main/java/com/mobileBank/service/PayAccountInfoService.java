package com.mobileBank.service;

import com.mobileBank.pojo.PayAccountInfo;

import java.util.List;
import java.util.Map;

public interface PayAccountInfoService {
    /**
     * 功能：用户查询所有账户信息服务
     * @param userId
     * @return
     */
    public List<PayAccountInfo> selectPayAccountInfoByUserId(Integer userId);

    /**
     * 功能：根据id查询账户信息服务
     * @param id
     * @return
     */
    public PayAccountInfo selectPayAccountInfoById(Integer id);

    /**
     * 功能：根据id查询用户和账户信息服务【转账时收款人的信息】
     * @param id
     * @return
     */
    public Map<String, Object> selectPayeeAccountInfoById(Integer id);

    /**
     * 功能：用户添加银行卡信息服务
     * @param payAccountInfo
     * @return
     */
    public Integer addPayAccountInfo(PayAccountInfo payAccountInfo);

    /**
     * 功能：用户查询银行卡余额服务【账单由PayInfoService和TransferInfoService服务负责】
     * @param bankCardID
     * @return
     */
    public PayAccountInfo selectPayAccountInfoByBankCardID(String bankCardID);

    /**
     * 功能：用户解绑银行卡服务【伪删除】
     * update ... status where bankCardID
     * @param id
     * @return
     */
    public Integer deletePayAccountInfo(Integer id);

    /**
     * 功能：用户银行卡转账服务【关联TransferInfoService】
     * @param payAccountInfo
     * @return
     */
    public Integer updatePayAccountBalance(PayAccountInfo payAccountInfo);

    /**
     * 功能：用户修改银行卡支付密码服务
     * @param bankCardID
     * @param password
     * @return
     */
    public Integer updatePayAccountPassword(String bankCardID, String password);

}
