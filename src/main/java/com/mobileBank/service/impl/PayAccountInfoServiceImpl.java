package com.mobileBank.service.impl;

import com.mobileBank.mapper.PayAccountInfoMapper;
import com.mobileBank.pojo.PayAccountInfo;
import com.mobileBank.service.PayAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PayAccountInfoServiceImpl implements PayAccountInfoService {
    @Autowired
    private PayAccountInfoMapper payAccountInfoMapper;

    @Override
    public List<PayAccountInfo> selectPayAccountInfoByUserId(Integer userId) {
        return payAccountInfoMapper.selectPayAccountInfoByUserId(userId);
    }

    @Override
    public PayAccountInfo selectPayAccountInfoById(Integer id) {
        return payAccountInfoMapper.selectPayAccountInfoById(id);
    }

    @Override
    public Map<String, Object> selectPayeeAccountInfoById(Integer id) {
        return payAccountInfoMapper.selectPayeeAccountInfoById(id);
    }

    @Override
    public Integer addPayAccountInfo(PayAccountInfo payAccountInfo) {
        return payAccountInfoMapper.addPayAccountInfo(payAccountInfo);
    }

    @Override
    public PayAccountInfo selectPayAccountInfoByBankCardID(String bankCardID) {
        return payAccountInfoMapper.selectPayAccountInfoByBankCardID(bankCardID);
    }

    @Override
    public Integer deletePayAccountInfo(Integer id) {
        return payAccountInfoMapper.deletePayAccountInfo(id);
    }

    @Override
    public Integer updatePayAccountBalance(PayAccountInfo payAccountInfo) {
        return payAccountInfoMapper.updatePayAccountBalance(payAccountInfo);
    }

    @Override
    public Integer updatePayAccountPassword(String bankCardID, String password) {
        return payAccountInfoMapper.updatePayAccountPassword(bankCardID, password);
    }

}
