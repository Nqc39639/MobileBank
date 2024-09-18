package com.mobileBank.service.impl;

import com.mobileBank.mapper.PayInfoMapper;
import com.mobileBank.pojo.PayInfo;
import com.mobileBank.service.PayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PayInfoServiceImpl implements PayInfoService {
    @Autowired
    private PayInfoMapper payInfoMapper;

    @Override
    public List<PayInfo> selectPayInfoByPayAccountId(Integer payAccountId) {
        return payInfoMapper.selectPayInfoByPayAccountId(payAccountId);
    }

    @Override
    public List<PayInfo> selectPayInfoByPayAccountIdAndDateRange(Integer payAccountId, Date startDate, Date endDate) {
        return payInfoMapper.selectPayInfoByPayAccountIdAndDateRange(payAccountId, startDate, endDate);
    }

    @Override
    public Integer addPayInfo(PayInfo payInfo) {
        return payInfoMapper.addPayInfo(payInfo);
    }
}
