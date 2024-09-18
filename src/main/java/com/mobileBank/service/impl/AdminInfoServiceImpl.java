package com.mobileBank.service.impl;

import com.mobileBank.mapper.AdminInfoMapper;
import com.mobileBank.pojo.AdminInfo;
import com.mobileBank.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {
    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Override
    public Integer checkAdminInfo(AdminInfo adminInfo) {
        Integer flag;
        if (adminInfoMapper.selectAdminInfoByAccount(adminInfo.getAccount()) == null) {   // 账号不存在
            flag = 0;
        } else if (adminInfoMapper.checkAdminInfo(adminInfo) != null) {   // 密码正确
            flag = 1;
        } else {   // 密码不正确
            flag = 2;
        }
        return flag;
    }

    @Override
    public Integer updateAdminInfo(AdminInfo adminInfo) {
        return adminInfoMapper.updateAdminInfo(adminInfo);
    }

    @Override
    public Integer deleteAdminInfo(AdminInfo adminInfo) {
        return adminInfoMapper.deleteAdminInfo(adminInfo);
    }

    @Override
    public AdminInfo selectAdminInfoById(Integer id) {
        return adminInfoMapper.selectAdminInfoById(id);
    }

    @Override
    public AdminInfo selectAdminInfoByAccount(String account) {
        return adminInfoMapper.selectAdminInfoByAccount(account);
    }
}
