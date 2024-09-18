package com.mobileBank.service.impl;

import com.mobileBank.mapper.TransferInfoMapper;
import com.mobileBank.pojo.TransferInfo;
import com.mobileBank.service.TransferInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TransferInfoServiceImpl implements TransferInfoService {
    @Autowired
    private TransferInfoMapper transferInfoMapper;

    @Override
    public List<TransferInfo> selectTransferInfoByPayAccountId(Integer payAccountId) {
        return transferInfoMapper.selectTransferInfoByPayAccountId(payAccountId);
    }

    @Override
    public List<TransferInfo> selectTransferInfoByTargetPayAccountId(Integer targetPayAccountId) {
        return transferInfoMapper.selectTransferInfoByTargetPayAccountId(targetPayAccountId);
    }

    @Override
    public List<TransferInfo> selectTransferInfoByPayAccountIdAndDateRange(Integer payAccountId, Date startDate, Date endDate) {
        return transferInfoMapper.selectTransferInfoByPayAccountIdAndDateRange(payAccountId, startDate, endDate);
    }

    @Override
    public List<TransferInfo> selectTransferInfoByTargetPayAccountIdAndDateRange(Integer targetPayAccountId, Date startDate, Date endDate) {
        return transferInfoMapper.selectTransferInfoByTargetPayAccountIdAndDateRange(targetPayAccountId, startDate, endDate);
    }

    @Override
    public Integer addTransferInfo(TransferInfo transferInfo) {
        return transferInfoMapper.addTransferInfo(transferInfo);
    }

    @Override
    public List<Map<String, Object>> selectRecipients(Integer id) {
        return transferInfoMapper.selectRecipients(id);
    }

}
