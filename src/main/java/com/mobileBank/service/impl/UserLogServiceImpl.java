package com.mobileBank.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mobileBank.mapper.UserLogMapper;
import com.mobileBank.pojo.UserLog;
import com.mobileBank.pojo.UserLogSearch;
import com.mobileBank.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class UserLogServiceImpl implements UserLogService {
    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public Integer addUserLog(UserLog userLog) {
        return userLogMapper.addUserLog(userLog);
    }

    @Override
    public PageInfo<UserLog> selectAllUserLog(Integer pageNum, Integer limit) {
        PageHelper.startPage(pageNum, limit);
        List<UserLog> readerInfoList = userLogMapper.selectAllUserLog();
        return new PageInfo<>(readerInfoList);
    }

    @Override
    public PageInfo<UserLog> selectUserLogBySearch(String searchParams, Integer pageNum, Integer limit) {
        UserLogSearch search = JSON.parseObject(searchParams, UserLogSearch.class);
        List<UserLog> allList = userLogMapper.selectUserLogBySearch(search);
        allList.sort(new Comparator<UserLog>() {
            @Override
            public int compare(UserLog o1, UserLog o2) {
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
    public List<UserLog> selectUserLogByUserId(Integer userId) {
        return userLogMapper.selectUserLogByUserId(userId);
    }

    @Override
    public List<UserLog> selectUserLogByOperationTimeRange(Date startDate, Date endDate) {
        return userLogMapper.selectUserLogByOperationTimeRange(startDate, endDate);
    }
}
