package com.mobileBank.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mobileBank.mapper.AdminLogMapper;
import com.mobileBank.pojo.AdminLog;
import com.mobileBank.pojo.UserLogSearch;
import com.mobileBank.service.AdminLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class AdminLogServiceImpl implements AdminLogService {
    @Autowired
    private AdminLogMapper adminLogMapper;

    @Override
    public Integer addAdminLog(AdminLog adminLog) {
        return adminLogMapper.addAdminLog(adminLog);
    }

    @Override
    public PageInfo<AdminLog> selectAllAdminLog(Integer pageNum, Integer limit) {
        PageHelper.startPage(pageNum, limit);
        List<AdminLog> readerInfoList = adminLogMapper.selectAllAdminLog();
        return new PageInfo<>(readerInfoList);
    }

    @Override
    public PageInfo<AdminLog> selectAdminLogBySearch(String searchParams, Integer pageNum, Integer limit) {
        UserLogSearch search = JSON.parseObject(searchParams, UserLogSearch.class);
        List<AdminLog> allList = adminLogMapper.selectAdminLogBySearch(search);
        allList.sort(new Comparator<AdminLog>() {
            @Override
            public int compare(AdminLog o1, AdminLog o2) {
                if (o1.getId().compareTo(o2.getId()) > 0) {
                    return -1;
                } else if (o1.getId().compareTo(o2.getId()) < 0) {
                    return 1;
                }
                return 0;
            }
        });
        return new PageInfo<>(allList);
    }

    @Override
    public List<AdminLog> selectAdminLogByAdminId(Integer adminId) {
        return adminLogMapper.selectAdminLogByAdminId(adminId);
    }

    @Override
    public List<AdminLog> selectAdminLogByOperationTimeRange(Date startDate, Date endDate) {
        return adminLogMapper.selectAdminLogByOperationTimeRange(startDate, endDate);
    }
}
